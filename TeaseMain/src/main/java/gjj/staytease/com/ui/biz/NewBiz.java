package gjj.staytease.com.ui.biz;

import gjj.staytease.com.bean.NewContentEntity;
import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.config.OkhttpUtil;
import gjj.staytease.com.interactor.MCallBack;
import gjj.staytease.com.interactor.TCallBack;
import gjj.staytease.com.ui.view.MainActivity;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class NewBiz implements TCallBack<NewContentEntity> {
    MCallBack<NewContentEntity> mCallBack;
    OkhttpUtil<NewContentEntity> okhttpUtil;

    public void setmCallBack(MCallBack<NewContentEntity> mCallBack) {
        this.mCallBack = mCallBack;
        okhttpUtil = new OkhttpUtil<>(NewContentEntity.class);
        okhttpUtil.newtitle(new MainActivity());
        okhttpUtil.setTtCallBack(this);
    }

    @Override
    public void getTCallBack(NewContentEntity newContentEntity, HttpServiceType type) {
        mCallBack.getTCallBack(newContentEntity, type);
    }
}
