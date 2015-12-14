package gjj.staytease.com.ui.interactor.implview;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import gjj.staytease.com.R;
import gjj.staytease.com.base.BaseApplication;
import gjj.staytease.com.bean.Music;
import gjj.staytease.com.interactor.service.PlayService;
import gjj.staytease.com.ui.interactor.MusicPlayInteractor;
import gjj.staytease.com.ui.view.music.MusicPlayActivity;
import gjj.staytease.com.util.ImageTool;
import gjj.staytease.com.util.MusicIconLoader;
import gjj.staytease.com.util.MusicUtils;
import gjj.staytease.com.util.ScreenUtils;
import gjj.staytease.com.widget.CDView;
import gjj.staytease.com.widget.LrcView;

import org.loader.view.ViewImpl;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public class MusicPlayViewImpl extends ViewImpl implements MusicPlayInteractor {
    @Bind(R.id.play_cdview)
    CDView mCdView;
    @Bind(R.id.start)
    ImageView start;
    @Bind(R.id.play_first_lrc)
    LrcView play_first_lrc;
    PlayService mPlayService;
    @Bind(R.id.seekbar)
    SeekBar seekBar;
    static Handler handler;
    int item;

    public void setItem(int item) {
        this.item = item;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_musicplay;
    }


    @Override
    public void startMusic() {
        if (mPlayService.isPlaying()) {
            mPlayService.pause();
            mCdView.pause();
            start.setImageResource(android.R.drawable.ic_media_play);
        } else {
            mPlayService.resume();
            mCdView.start();
            start.setImageResource(android.R.drawable.ic_media_pause);

        }
    }

    @Override
    public void setPre() {
        mPlayService.pre();
    }

    @Override
    public void setNext() {
        mPlayService.next();
    }

    @Override
    public void setSeek(int seek) {
        mPlayService.seek(seek);
    }

    /**
     * 播放时，更新控制面板
     *
     * @param position
     */
    public void onPlay(int position) {
        if (MusicUtils.sMusicList.isEmpty() || position < 0)
            return;
        //设置进度条的总长度
        seekBar.setMax(mPlayService.getDuration());
        start.setImageResource(android.R.drawable.ic_media_pause);
        Music music = MusicUtils.sMusicList.get(position);
        String lrcPath = MusicUtils.getLrcDir() + music.getTitle() + ".lrc";
        play_first_lrc.setLrcPath(lrcPath);
        try {
            Bitmap bmp = MusicIconLoader.getInstance().load(music.getImage());
            if (bmp == null)
                bmp = BitmapFactory.decodeResource(BaseApplication.context.getResources(),
                        android.R.drawable.stat_notify_error);
            mCdView.setImage(ImageTool.scaleBitmap(bmp,
                    (int) (ScreenUtils.getScreenWidth(BaseApplication.context) * 0.9)));
        } catch (Exception e) {

        }
        //新启动一个线程更新通知栏，防止更新时间过长，导致界面卡顿！
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                mPlayService.setRemoteViews();
            }
        });
    }

    /**
     * 开启音乐服务
     * 注册广播接收器
     * 在下载歌曲完成或删除歌曲时，更新歌曲列表
     *
     * @param musicPlayActivity
     */
    @Override
    public void sBind(MusicPlayActivity musicPlayActivity) {
        musicPlayActivity.bindService(new Intent(musicPlayActivity, PlayService.class),
                mPlayServiceConnection,
                Context.BIND_AUTO_CREATE);
        /**
         * 开启广播
         */
        IntentFilter filter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_STARTED);
        filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        filter.addDataScheme("file");
        musicPlayActivity.registerReceiver(mScanSDCardReceiver, filter);
        seekBar.setOnSeekBarChangeListener(musicPlayActivity);
    }

    /**
     * 取消服务和广播
     *
     * @param musicPlayActivity
     */
    @Override
    public void unBind(MusicPlayActivity musicPlayActivity) {
        musicPlayActivity.unregisterReceiver(mScanSDCardReceiver);
        musicPlayActivity.unbindService(mPlayServiceConnection);
    }

    /**
     * 服务的接收者
     */
    ServiceConnection mPlayServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mPlayService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayService = ((PlayService.PlayBinder) service).getService();
            mPlayService.setOnMusicEventListener(mMusicEventListener);
            if (mPlayService.getPlayingPosition() == item) {
                mPlayService.resume();
            } else {
                mPlayService.setmPlayingPosition(item);
                mPlayService.play(item);
            }
            mCdView.start();
            onPlay(mPlayService.getPlayingPosition());
        }

        /**
         * 音乐播放服务回调接口的实现类
         */
        PlayService.OnMusicEventListener mMusicEventListener =
                new PlayService.OnMusicEventListener() {
                    @Override
                    public void onPublish(int progress) {
                        seekBar.setProgress(progress);
                    }

                    @Override
                    public void onChange(int position) {
                        onPlay(position);
                    }
                };

    };
    /**
     * 发送广播通知更新歌曲列表
     */
    private BroadcastReceiver mScanSDCardReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.e("ds", "mScanSDCardReceiver---->onReceive()");
            if (intent.getAction().equals(Intent.ACTION_MEDIA_SCANNER_FINISHED)) {
                MusicUtils.initMusicList();
            }
        }
    };
}
