package gjj.staytease.com.ui.interactor.implview;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
import org.loader.view.ViewImpl;


import gjj.staytease.com.R;
import gjj.staytease.com.base.BaseApplication;
import gjj.staytease.com.bean.QQEntity;
import gjj.staytease.com.bean.WeixinMsgEntity;
import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.ui.LoginActivity;
import gjj.staytease.com.ui.biz.WeixinLoginBiz;
import gjj.staytease.com.ui.interactor.LoginInteractor;
import gjj.staytease.com.util.GlobalsUtil;

/**
 * Created by gaojiangjian on 15/12/1.
 */
public class LoginViewImpl extends ViewImpl implements LoginInteractor {
    private UserInfo mInfo;
    private static Handler mHandler, eHandler;
    /* 常量 */
    private String userName, password;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    public void setMsg(final LoginActivity loginActivity) {

        if (!BaseApplication.qqAuth.isSessionValid()) {
            IUiListener listener = new BaseUiListener() {
                @Override
                protected void doComplete(String values) {
                    super.doComplete(values);
                    mInfo = new UserInfo(loginActivity, BaseApplication.tencent.getQQToken());
                    mInfo.getUserInfo(new BaseUiListener() {
                        @Override
                        protected void doComplete(String values) {
                            super.doComplete(values);
                            try {
                                final QQEntity qqEntity = GlobalsUtil.fromJsonObject(values, QQEntity.class);
                                Intent intent=new Intent();
                                intent.putExtra("qq", qqEntity);
                                loginActivity.setResult(Activity.RESULT_FIRST_USER, intent);
                                loginActivity.finish();

                            } catch (Exception e) {

                            }
                        }
                    });
                }
            };
            BaseApplication.tencent.loginWithOEM(loginActivity, "all", listener, "10000144",
                    "10000144", "xxxx");
        }
    }

    @Override
    public void setMsg(final LoginActivity loginActivity, JSONObject jsonObject, HttpServiceType type) {
        switch (type) {
            case LOGINWEIXIN_TYPE:
                try {
                    new WeixinLoginBiz().setMsgCallBack(loginActivity, jsonObject.getString("access_token"), jsonObject.getString("openid"));
                    loginActivity.isWXLogin = false;
                } catch (JSONException e) {
                }
                break;
            case MSGWEIXIN_TYPE:
                try {
                    final Intent intent = new Intent();
                    final WeixinMsgEntity weixinMsgEntity = GlobalsUtil.fromJsonObject(jsonObject.toString(), WeixinMsgEntity.class);
                    intent.putExtra("weixin", weixinMsgEntity);
                    loginActivity.setResult(Activity.RESULT_OK, intent);
                    loginActivity.finish();
                } catch (Exception e) {

                }

                break;
        }
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            doComplete(response.toString());
        }

        protected void doComplete(String values) {

        }

        @Override
        public void onError(UiError e) {

        }

        @Override
        public void onCancel() {

        }
    }
}
