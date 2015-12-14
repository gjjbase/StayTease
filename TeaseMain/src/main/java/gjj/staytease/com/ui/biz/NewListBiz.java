package gjj.staytease.com.ui.biz;

import gjj.staytease.com.bean.NewContentListEntity;
import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.config.OkhttpUtil;
import gjj.staytease.com.interactor.MCallBack;
import gjj.staytease.com.interactor.TCallBack;
import gjj.staytease.com.ui.view.MainActivity;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class NewListBiz implements TCallBack<NewContentListEntity> {
    MCallBack<NewContentListEntity> mCallBack;
    OkhttpUtil<NewContentListEntity> okhttpUtil;

    public void setmCallBack(MCallBack<NewContentListEntity> mCallBack, String id, int page, String name) {
        this.mCallBack = mCallBack;
        okhttpUtil = new OkhttpUtil<>(NewContentListEntity.class);
        okhttpUtil.newlist(new MainActivity(), id, page, name);
        okhttpUtil.setTtCallBack(this);
    }

    @Override
    public void getTCallBack(NewContentListEntity newContentListEntity, HttpServiceType type) {
        mCallBack.getTCallBack(newContentListEntity, type);
    }
}
