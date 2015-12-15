package gjj.staytease.com.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import gjj.staytease.com.R;
import gjj.staytease.com.bean.QQEntity;
import gjj.staytease.com.bean.WeixinMsgEntity;
import gjj.staytease.com.ui.LoginActivity;
import gjj.staytease.com.ui.interactor.implview.MainViewImpl;

import org.loader.presenter.ActivityPresenterImpl;

import butterknife.OnClick;

/**
 * Created by gaojiangjian on 15/11/25.
 */
public class MainActivity extends ActivityPresenterImpl<MainViewImpl> implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    @OnClick({R.id.fab,R.id.login_ico})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                mView.showToas("Replace with your own action");
                break;
            case R.id.login_ico:
                startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), 1000);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000){
            if (resultCode==Activity.RESULT_OK){
                WeixinMsgEntity weixinMsgEntity=data.getParcelableExtra("weixin");
                mView.setMsg(this,weixinMsgEntity);
            }else {
                QQEntity qqEntity=data.getParcelableExtra("qq");
                mView.setMsg(this,qqEntity);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_camara:
                mView.setMsg(this, 0, R.string.news); // 更新目标tab为当前tab
                break;
            case R.id.nav_gallery:
                mView.setMsg(this, 1,R.string.photot); // 更新目标tab为当前tab
                break;
            case R.id.nav_slideshow:
                mView.setMsg(this, 2,R.string.music); // 更新目标tab为当前tab
                break;

            case R.id.nav_qrcode:
                mView.setMsg(this, 6,R.string.qrcode); // 更新目标tab为当前tab
                break;
            case R.id.nav_share:
                mView.setMsg(this, 4,R.string.share);// 更新目标tab为当前tab
                break;
            case R.id.nav_send:
                mView.setMsg(this, 5,R.string.callme); // 更新目标tab为当前tab
            case R.id.plugin:
                mView.setMsg(this, 7,R.string.plugin); // 更新目标tab为当前tab
                break;
        }

        return true;
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.setMsg(this);
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


}
