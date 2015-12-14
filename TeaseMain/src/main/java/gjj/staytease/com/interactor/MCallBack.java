package gjj.staytease.com.interactor;

import gjj.staytease.com.config.HttpServiceType;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public interface MCallBack<T> {
    void getTCallBack(T t, HttpServiceType type);
}
