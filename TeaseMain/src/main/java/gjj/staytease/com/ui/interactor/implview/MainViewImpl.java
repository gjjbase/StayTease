package gjj.staytease.com.ui.interactor.implview;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.zbar.lib.CaptureActivity;

import gjj.staytease.com.R;

import gjj.staytease.com.base.BaseApplication;
import gjj.staytease.com.bean.QQEntity;
import gjj.staytease.com.bean.WeixinMsgEntity;
import gjj.staytease.com.chatuidemo.activity.SplashActivity;
import gjj.staytease.com.ui.interactor.MainViewInteractor;
import gjj.staytease.com.ui.view.MainActivity;
import gjj.staytease.com.ui.view.music.MusicFragment;
import gjj.staytease.com.ui.view.news.NewFragment;
import gjj.staytease.com.ui.view.photo.PhotoFragment;
import gjj.staytease.com.ui.view.video.VideoFragment;

import org.json.JSONObject;
import org.loader.view.ViewImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import gjj.staytease.com.util.DateTools;
import gjj.staytease.com.util.SPUtils;
import gjj.staytease.com.util.ScreenUtils;
import gjj.staytease.com.widget.CircularImage;
import gjj.staytease.com.widget.ShareDialog;

/**
 * Created by gaojiangjian on 15/11/25.
 */
public class MainViewImpl extends ViewImpl implements MainViewInteractor {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.mtoolbar)
    Toolbar toolbar;
    CircularImage login_ico;
    TextView logo_name;
    TextView login_sex;
    TextView login_ads;
    private List<Fragment> fragments; // 一个tab页面对应一个Fragment
    private NewFragment newsFragment;
    private PhotoFragment photoFragment;
    private MusicFragment musicFragment;
    private VideoFragment videoFragment;
    private int currentTab;

    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void setMsg(MainActivity mainActivity) {
        fragments = new ArrayList<>();
        newsFragment = new NewFragment();
        photoFragment = new PhotoFragment();
        musicFragment = new MusicFragment();
        videoFragment = new VideoFragment();
        fragments.add(newsFragment);
        fragments.add(photoFragment);
        fragments.add(musicFragment);
        fragments.add(videoFragment);
        login_ico = (CircularImage) navigationView.getChildAt(0).findViewById(R.id.login_ico);
        logo_name = (TextView) navigationView.getChildAt(0).findViewById(R.id.logo_name);
        login_sex = (TextView) navigationView.getChildAt(0).findViewById(R.id.login_sex);
        login_sex = (TextView) navigationView.getChildAt(0).findViewById(R.id.login_ads);
        login_ico.setImageResource(android.R.mipmap.sym_def_app_icon);
        login_ico.setOnClickListener(mainActivity);
        logo_name.setText("登录");
        login_sex.setText("登录");
        login_sex.setText((String) SPUtils.get(mainActivity, "adds", ""));
        navigationView.setNavigationItemSelectedListener(mainActivity);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mainActivity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        FragmentTransaction fragmentManager = mainActivity.getSupportFragmentManager().beginTransaction();
        fragmentManager.add(R.id.framelayout, fragments.get(0));
        fragmentManager.commit();
        toolbar.setTitle(R.string.news);
    }

    public void setMsg(final MainActivity mainActivity, int currentTab, int id) {
        this.currentTab = currentTab;
        toolbar.setTitle(id);
        switch (currentTab) {
            case 0:
            case 1:
            case 2:
            case 3:
                Fragment fragment = fragments.get(currentTab);
                FragmentTransaction ft = obtainFragmentTransaction(mainActivity, currentTab);
                getCurrentFragment().onPause(); // 暂停当前tab
                if (fragment.isAdded())
                    fragment.onResume(); // 启动目标tab的onResume()
                else
                    ft.add(R.id.framelayout, fragment);
                showTab(currentTab, mainActivity); // 显示目标tab
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case 4:
                ShareDialog shareDialog = new ShareDialog(mainActivity);
                shareDialog.getWindow().setGravity(Gravity.BOTTOM);
                shareDialog.show();
                shareDialog.getWindow().setLayout(
                        ScreenUtils.getScreenWidth(mainActivity), ActionBar.LayoutParams.WRAP_CONTENT);
                shareDialog.setCanceledOnTouchOutside(true);
                shareDialog.setReturnShare(new ShareDialog.ReturnShare() {
                    @Override
                    public void qqz() {
                        final Bundle params = new Bundle();
                        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
                        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "StayTease");
                        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "StayTease一款牛逼的超级大demo");
                        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://www.nichewoche.com");
                        ArrayList<String> imageUrls = new ArrayList<String>();
                        imageUrls.add("http://media-cdn.tripadvisor.com/media/photo-s/01/3e/05/40/the-sandbar-that-links.jpg");
                        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
                        params.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
                        BaseApplication.tencent.shareToQzone(mainActivity, params, new BaseUiListener());
                    }

                    @Override
                    public void weixin() {
                        WXWebpageObject wxWebpageObject = new WXWebpageObject();
                        wxWebpageObject.webpageUrl = "http://www.nichewoche.com";
                        WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
                        wxMediaMessage.title = "StayTease一款牛逼的超级大demo";
                        wxMediaMessage.description = "StayTease一款牛逼的超级大demo";
                        try {
                            Bitmap bitmap = BitmapFactory.decodeResource(mainActivity.getResources(), R.mipmap.logo);
                            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
                            bitmap.recycle();
                            wxMediaMessage.setThumbImage(bitmap1);
                        } catch (Exception e) {

                        }
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = DateTools.getCurrentTime();
                        req.message = wxMediaMessage;
                        req.scene = SendMessageToWX.Req.WXSceneTimeline;
                        BaseApplication.msgApi.sendReq(req);
                    }
                });

                break;
            case 5:// 列出所有已经安装的插件
                // 测试通过ClassName匹配
                mainActivity.startActivity(new Intent(mainActivity, SplashActivity.class));
//                try{
//                   Log.e("ss", EMChatManager.getInstance().getCurrentUser()) ;
//                    EMChat.getInstance().setAppInited();
//                    List<String> user_id = EMContactManager.getInstance().getContactUserNames();
//                    mainActivity.startActivity(new Intent(mainActivity, CallMainActivity.class));
//                }catch (EaseMobException e){
//
//                }//putExtra("userid",
//                userList.get(arg2)));
                break;
            case 6:
                mainActivity.startActivity(new Intent(mainActivity, CaptureActivity.class));
                break;
            case 7:
//                final PluginDialog pluginDialog = new PluginDialog(mainActivity);
//                pluginDialog.getWindow().setGravity(Gravity.BOTTOM);
//                pluginDialog.show();
//                pluginDialog.getWindow().setLayout(
//                        ScreenUtils.getScreenWidth(mainActivity), ActionBar.LayoutParams.WRAP_CONTENT);
//                pluginDialog.setCanceledOnTouchOutside(true);
                break;
            default:
                break;
        }


    }


    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            //V2.0版本，参数类型由JSONObject 改成了Object,具体类型参考api文档
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {
            //分享成功
        }

        @Override
        public void onError(UiError e) {
            //在这里处理错误信息
            if (e.errorDetail == null) {
                // ToastUtil.showToast(ProductDetailActivity.this,getString(R.string.no_qq));
            } else {
//            ToastUtil.showToast(ProductDetailActivity.this,
//                    getString(R.string.share_fail)+":"+e.errorDetail);
            }

        }

        @Override
        public void onCancel() {
            //分享被取消
            //ToastUtil.showToast(ProductDetailActivity.this,getString(R.string.share_cancel));
        }
    }

    @Override
    public <T> void setMsg(MainActivity mainActivity, T t) {
        if (t instanceof WeixinMsgEntity) {
            WeixinMsgEntity weixinMsgEntity = (WeixinMsgEntity) t;
            logo_name.setText(weixinMsgEntity.getNickname());
            login_sex.setText(weixinMsgEntity.getSex() == 1 ? "男" : "女" + "--" + weixinMsgEntity.getUnionid());
            Glide.with(mainActivity).load(weixinMsgEntity.getHeadimgurl())
                    .placeholder(android.R.drawable.stat_notify_error)
                    .error(android.R.drawable.stat_notify_error)
                    .crossFade()
                    .into(login_ico);
        } else if (t instanceof QQEntity) {
            QQEntity qqEntity = (QQEntity) t;
            logo_name.setText(qqEntity.getNickname());
            login_sex.setText(qqEntity.getGender());
            Glide.with(mainActivity).load(qqEntity.getFigureurl_2())
                    .placeholder(android.R.drawable.stat_notify_error)
                    .error(android.R.drawable.stat_notify_error)
                    .crossFade()
                    .into(login_ico);
        }

    }

    public FragmentTransaction obtainFragmentTransaction(MainActivity activity, int index) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        // 设置切换动画
        if (index > currentTab) {
            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        } else {
            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }
        return ft;
    }

    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    /**
     * 切换tab
     *
     * @param idx
     */
    public void showTab(int idx, MainActivity mainActivity) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(mainActivity, idx);

            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
    }

}
