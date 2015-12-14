package gjj.staytease.com.interactor.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import gjj.staytease.com.R;
import gjj.staytease.com.base.BaseApplication;
import gjj.staytease.com.config.ApiConstants;
import gjj.staytease.com.ui.view.music.MusicPlayActivity;
import gjj.staytease.com.util.ImageTool;
import gjj.staytease.com.util.MusicIconLoader;
import gjj.staytease.com.util.MusicUtils;
import gjj.staytease.com.util.SPUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public class PlayService extends Service implements OnCompletionListener {
    private static final String TAG =
            PlayService.class.getSimpleName();
    private SensorManager mSensorManager;
    private MediaPlayer mPlayer;
    private OnMusicEventListener mListener;
    private int mPlayingPosition;
    private WakeLock mWakeLock;
    private boolean isShaking;
    private Notification notification;
    private RemoteViews remoteViews;
    private NotificationManager notificationManager;
    //单线程池
    private ExecutorService mProgressUpdatedListener = Executors
            .newSingleThreadExecutor();

    public class PlayBinder extends Binder {
        public PlayService getService() {
            return PlayService.this;
        }
    }

    public void onCompletion(MediaPlayer mp) {
        next();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mSensorManager.registerListener(mSensorEventListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
        return new PlayBinder();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate() {
        super.onCreate();
        acquireWakeLock();//获取设备电源锁
        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);

        MusicUtils.initMusicList();
        mPlayingPosition = (Integer)
                SPUtils.get(this, ApiConstants.PLAY_POS, 0);

        Uri uri = Uri.parse(MusicUtils.sMusicList.get(
                getPlayingPosition()).getUri());
        mPlayer = MediaPlayer.create(PlayService.this, uri);
        mPlayer.setOnCompletionListener(this);
        // 开始更新进度的线程
        mProgressUpdatedListener.execute(mPublishProgressRunnable);

        /**
         * 该方法虽然被抛弃过时，但是通用！
         */
        PendingIntent pendingIntent = PendingIntent
                .getActivity(PlayService.this,
                        0, new Intent(PlayService.this, MusicPlayActivity.class), 0);
        remoteViews = new RemoteViews(getPackageName(),
                R.layout.play_notification);
        notification = new Notification(android.R.drawable.stat_notify_sync,
                "歌曲正在播放", System.currentTimeMillis());
        notification.contentIntent = pendingIntent;
        notification.contentView = remoteViews;
        //标记位，设置通知栏一直存在
        notification.flags = Notification.FLAG_ONGOING_EVENT;

        Intent intent = new Intent(PlayService.class.getSimpleName());
        intent.putExtra("BUTTON_NOTI", 1);
        PendingIntent preIntent = PendingIntent.getBroadcast(
                PlayService.this,
                1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(
                R.id.music_play_pre, preIntent);

        intent.putExtra("BUTTON_NOTI", 2);
        PendingIntent pauseIntent = PendingIntent.getBroadcast(
                PlayService.this,
                2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(
                R.id.music_play_pause, pauseIntent);

        intent.putExtra("BUTTON_NOTI", 3);
        PendingIntent nextIntent = PendingIntent.getBroadcast
                (PlayService.this,
                        3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(
                R.id.music_play_next, nextIntent);

        intent.putExtra("BUTTON_NOTI", 4);
        PendingIntent exit = PendingIntent.getBroadcast(PlayService.this,
                4, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(
                R.id.music_play_notifi_exit, exit);

        notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        setRemoteViews();

        /**
         * 注册广播接收者
         * 功能：
         * 监听通知栏按钮点击事件
         */
        IntentFilter filter = new IntentFilter(
                PlayService.class.getSimpleName());
        MyBroadCastReceiver receiver = new MyBroadCastReceiver();
        registerReceiver(receiver, filter);
    }

    public void setRemoteViews() {
        Log.e(TAG, "进入——》setRemoteViews()");
        remoteViews.setTextViewText(R.id.music_name,
                MusicUtils.sMusicList.get(
                        getPlayingPosition()).getTitle());
        remoteViews.setTextViewText(R.id.music_author,
                MusicUtils.sMusicList.get(
                        getPlayingPosition()).getArtist());
        try {
            Bitmap icon = MusicIconLoader.getInstance().load(
                    MusicUtils.sMusicList.get(
                            getPlayingPosition()).getImage());
            remoteViews.setImageViewBitmap(R.id.music_icon, icon == null
                    ? ImageTool.scaleBitmap(android.R.drawable.stat_notify_error)
                    : ImageTool
                    .scaleBitmap(icon));
        } catch (Exception E) {

        }
        if (isPlaying()) {
            remoteViews.setImageViewResource(R.id.music_play_pause,
                    android.R.drawable.ic_media_pause);
        } else {
            remoteViews.setImageViewResource(R.id.music_play_pause,
                    android.R.drawable.ic_media_play);
        }
        //通知栏更新
        notificationManager.notify(5, notification);
    }

    /**
     * 获取当前正在播放音乐的总时长
     *
     * @return
     */
    public int getDuration() {
        if (!isPlaying())
            return 0;
        return mPlayer.getDuration();
    }

    /**
     * 拖放到指定位置进行播放
     *
     * @param msec
     */
    public void seek(int msec) {
        if (!isPlaying())
            return;
        mPlayer.seekTo(msec);
    }

    /**
     * 播放
     *
     * @param position 音乐列表播放的位置
     * @return 当前播放的位置
     */
    public int play(int position) {
        Log.e(TAG, "play(int position)方法");
        if (position < 0)
            position = 0;
        if (position >= MusicUtils.sMusicList.size())
            position = MusicUtils.sMusicList.size() - 1;

        try {
            mPlayer.reset();
            mPlayer.setDataSource(MusicUtils
                    .sMusicList.get(position).getUri());
            mPlayer.prepare();
            start();
            if (mListener != null)
                mListener.onChange(position);
        } catch (Exception e) {
            e.printStackTrace();
            next();
        }

        mPlayingPosition = position;
        SPUtils.put(BaseApplication.context, ApiConstants.PLAY_POS, mPlayingPosition);
        setRemoteViews();
        return mPlayingPosition;
    }

    /**
     * 开始播放
     */
    private void start() {
        mPlayer.start();
    }

    /**
     * 是否正在播放
     *
     * @return
     */
    public boolean isPlaying() {
        return null != mPlayer && mPlayer.isPlaying();
    }

    /**
     * 继续播放
     *
     * @return 当前播放的位置 默认为0
     */
    public int resume() {
        if (isPlaying())
            return -1;
        mPlayer.start();
        setRemoteViews();
        return mPlayingPosition;
    }

    /**
     * 暂停播放
     *
     * @return 当前播放的位置
     */
    public int pause() {
        if (!isPlaying())
            return -1;
        mPlayer.pause();
        setRemoteViews();
        return mPlayingPosition;
    }

    /**
     * 下一曲
     *
     * @return 当前播放的位置
     */
    public int next() {
        if (mPlayingPosition >= MusicUtils.sMusicList.size() - 1) {
            return play(0);
        }
        return play(mPlayingPosition + 1);
    }

    /**
     * 上一曲
     *
     * @return 当前播放的位置
     */
    public int pre() {
        if (mPlayingPosition <= 0) {
            return play(MusicUtils.sMusicList.size() - 1);
        }
        return play(mPlayingPosition - 1);
    }

    /**
     * 更新进度的线程
     */
    private Runnable mPublishProgressRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (mPlayer != null && mPlayer.isPlaying()
                        && mListener != null) {
                    mListener.onPublish(mPlayer.getCurrentPosition());
                }
            /*
             * SystemClock.sleep(millis) is a utility function very similar
			 * to Thread.sleep(millis), but it ignores InterruptedException.
			 * Use this function for delays if you do not use
			 * Thread.interrupt(), as it will preserve the interrupted state
			 * of the thread. 这种sleep方式不会被Thread.interrupt()所打断
			 */
                SystemClock.sleep(200);
            }
        }
    };

    /**
     * 设置回调
     *
     * @param l
     */
    public void setOnMusicEventListener(OnMusicEventListener l) {
        mListener = l;
    }

    // 申请设备电源锁
    private void acquireWakeLock() {
        Log.e(TAG, "正在申请电源锁");
        if (null == mWakeLock) {
            PowerManager pm = (PowerManager) this
                    .getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK
                    | PowerManager.ON_AFTER_RELEASE, "");
            if (null != mWakeLock) {
                mWakeLock.acquire();
                Log.e(TAG, "电源锁申请成功");
            }
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("play service", "unbind");
        mSensorManager.unregisterListener(mSensorEventListener);
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        if (mListener != null)
            mListener.onChange(mPlayingPosition);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "PlayService.java的onDestroy()方法调用");
        release();
        stopForeground(true);
        mSensorManager.unregisterListener(mSensorEventListener);
        super.onDestroy();
    }

    /**
     * 服务销毁时，释放各种控件
     */
    private void release() {
        if (!mProgressUpdatedListener.isShutdown())
            mProgressUpdatedListener.shutdownNow();
        mProgressUpdatedListener = null;
        //释放设备电源锁
        releaseWakeLock();
        if (mPlayer != null)
            mPlayer.release();
        mPlayer = null;
    }

    // 释放设备电源锁
    private void releaseWakeLock() {
        Log.e(TAG, "正在释放电源锁");
        if (null != mWakeLock) {
            mWakeLock.release();
            mWakeLock = null;
            Log.e(TAG, "电源锁释放成功");
        }
    }

    /**
     * 获取正在播放的歌曲在歌曲列表的位置
     *
     * @return
     */
    public int getPlayingPosition() {
        return mPlayingPosition;
    }

    public void setmPlayingPosition(int mPlayingPosition) {
        this.mPlayingPosition = mPlayingPosition;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(0, notification);//让服务前台运行
        return Service.START_STICKY;
    }

    /**
     * 感应器的时间监听器
     */
    private SensorEventListener mSensorEventListener =
            new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (isShaking)
                        return;

                    if (Sensor.TYPE_ACCELEROMETER == event.sensor.getType()) {
                        float[] values = event.values;
                        /**
                         * 监听三个方向上的变化，数据变化剧烈，next()方法播放下一首歌曲
                         */
                        if (Math.abs(values[0]) > 8 && Math.abs(values[1]) > 8
                                && Math.abs(values[2]) > 8) {
                            isShaking = true;
                            next();
                            // 延迟200毫秒 防止抖动
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    isShaking = false;
                                }
                            }, 200);
                        }
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };

    private class MyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(
                    PlayService.class.getSimpleName())) {
                Log.e(TAG, "MyBroadCastReceiver类——》onReceive（）");
                Log.e(TAG, "button_noti-->"
                        + intent.getIntExtra("BUTTON_NOTI", 0));
                switch (intent.getIntExtra("BUTTON_NOTI", 0)) {
                    case 1:
                        pre();
                        break;
                    case 2:
                        if (isPlaying()) {
                            pause(); // 暂停
                        } else {
                            resume(); // 播放
                        }
                        break;
                    case 3:
                        next();
                        break;
                    case 4:
                        if (isPlaying()) {
                            pause();
                        }
                        //取消通知栏
                        notificationManager.cancel(5);
                        break;
                    default:
                        break;
                }
            }
            if (mListener != null) {
                mListener.onChange(getPlayingPosition());
            }
        }
    }

    /**
     * 音乐播放回调接口
     */
    public interface OnMusicEventListener {
        void onPublish(int percent);

        void onChange(int position);
    }
}
