package gjj.staytease.com.ui.biz;

import gjj.staytease.com.bean.MusicEntity;
import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.config.OkhttpUtil;
import gjj.staytease.com.interactor.MCallBack;
import gjj.staytease.com.interactor.TCallBack;
import gjj.staytease.com.ui.view.MainActivity;

/**
 * Created by gaojiangjian on 15/11/28.
 */
public class MusicInterListBiz implements TCallBack<MusicEntity> {
    MCallBack<MusicEntity> mCallBack;
    OkhttpUtil<MusicEntity> okhttpUtil;

    public void setmCallBack(MCallBack<MusicEntity> mCallBack, String type) {
        this.mCallBack = mCallBack;
        okhttpUtil = new OkhttpUtil<>(MusicEntity.class);
        okhttpUtil.musliclist(new MainActivity(), type);
        okhttpUtil.setTtCallBack(this);
    }


    @Override
    public void getTCallBack(MusicEntity musicEntities, HttpServiceType type) {
        mCallBack.getTCallBack(musicEntities, type);
    }
}
