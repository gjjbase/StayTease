package gjj.staytease.com.interactor;

import gjj.staytease.com.config.HttpServiceType;

/**
 * Created by gaojiangjian on 15/11/21.
 */
public interface TCallBack<T> {
    void getTCallBack(T t, HttpServiceType type);

}
