package gjj.staytease.com.ui;

import android.view.View;
import android.view.View.OnClickListener;

import com.tencent.connect.UserInfo;
import com.tencent.mm.sdk.modelmsg.SendAuth;

import org.json.JSONObject;
import org.loader.presenter.ActivityPresenterImpl;

import butterknife.OnClick;
import gjj.staytease.com.R;
import gjj.staytease.com.base.BaseApplication;
import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.interactor.MCallBack;
import gjj.staytease.com.ui.biz.WeixinLoginBiz;
import gjj.staytease.com.ui.interactor.implview.LoginViewImpl;

/**
 * Created by gaojiangjian on 15/12/1.
 */
public class LoginActivity extends ActivityPresenterImpl<LoginViewImpl> implements OnClickListener, MCallBack<JSONObject> {
    public static String WX_CODE;
    public static boolean isWXLogin = false;
    private UserInfo mInfo;

    @OnClick({R.id.img_qq, R.id.img_weixin})
    public void onClick(View v) {
        isWXLogin = false;
        switch (v.getId()) {
            case R.id.img_weixin:
                isWXLogin = true;
                SendAuth.Req req = new SendAuth.Req();
                    /*用snsapi_base提示没权限*/
                req.scope = "snsapi_userinfo";
					/*用户保持请求和回调的状态，授权后请求原样返回给第三方。该参数可以防止csrf攻击*/
                req.state = "staytease";
                BaseApplication.msgApi.sendReq(req);
                break;
            case R.id.img_qq:
                mView.setMsg(this);
                break;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isWXLogin) {
            new WeixinLoginBiz().setmCallBack(this, WX_CODE);
        }

    }

    @Override
    public void getTCallBack(JSONObject jsonObject, HttpServiceType type) {
      mView.setMsg(this,jsonObject,type);
    }

}
