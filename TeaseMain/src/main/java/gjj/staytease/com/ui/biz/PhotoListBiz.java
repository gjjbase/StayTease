package gjj.staytease.com.ui.biz;

import gjj.staytease.com.bean.PhotoEntity;
import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.config.OkhttpUtil;
import gjj.staytease.com.interactor.MCallBack;
import gjj.staytease.com.interactor.TCallBack;
import gjj.staytease.com.ui.view.MainActivity;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoListBiz implements TCallBack<PhotoEntity> {
    MCallBack<PhotoEntity> mCallBack;
    OkhttpUtil<PhotoEntity> okhttpUtil;

    public void setmCallBack(MCallBack<PhotoEntity> mCallBack, String type, int page) {
        this.mCallBack = mCallBack;
        okhttpUtil = new OkhttpUtil<>(PhotoEntity.class);
        okhttpUtil.photolist(new MainActivity(), type, page);
        okhttpUtil.setTtCallBack(this);
    }

    @Override
    public void getTCallBack(PhotoEntity photoEntity, HttpServiceType type) {
        mCallBack.getTCallBack(photoEntity, type);
    }
}
