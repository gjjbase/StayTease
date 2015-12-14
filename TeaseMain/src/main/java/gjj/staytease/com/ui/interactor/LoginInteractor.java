package gjj.staytease.com.ui.interactor;

import org.json.JSONObject;

import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.ui.LoginActivity;

/**
 * Created by gaojiangjian on 15/12/1.
 */
public interface LoginInteractor {
    void setMsg(LoginActivity loginActivity);
    void setMsg(LoginActivity loginActivity,JSONObject jsonObject, HttpServiceType type);
}
