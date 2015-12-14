package gjj.staytease.com.ui.biz;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.config.OkhttpUtil;
import gjj.staytease.com.interactor.MCallBack;
import gjj.staytease.com.interactor.TCallBack;
import gjj.staytease.com.ui.view.MainActivity;

/**
 * Created by gaojiangjian on 15/12/1.
 */
public class WeixinLoginBiz implements TCallBack<JSONObject> {
    MCallBack<JSONObject> mCallBack;
    OkhttpUtil<JSONObject> okhttpUtil;

    public void setmCallBack(MCallBack<JSONObject> mCallBack, String code) {
        this.mCallBack = mCallBack;
        okhttpUtil = new OkhttpUtil<>(JSONObject.class);
        okhttpUtil.weixinLogin(new MainActivity(), code);
        okhttpUtil.setTtCallBack(this);
    }

    public void setMsgCallBack(MCallBack<JSONObject> mCallBack,String access_token, String openId){
        this.mCallBack = mCallBack;
        okhttpUtil = new OkhttpUtil<>(JSONObject.class);

        okhttpUtil.weixinMsg(new MainActivity(), access_token,openId);

        okhttpUtil.setTtCallBack(this);
    }

    @Override
    public void getTCallBack(JSONObject jsonObject, HttpServiceType type) {
        mCallBack.getTCallBack(jsonObject, type);
    }
}
