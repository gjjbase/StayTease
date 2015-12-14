package gjj.staytease.com.ui.view.news;

import android.os.Bundle;


import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.interactor.MCallBack;
import gjj.staytease.com.ui.biz.NewBiz;
import gjj.staytease.com.ui.interactor.implview.NewViewImpl;
import gjj.staytease.com.bean.NewContentEntity;
import gjj.staytease.com.bean.NewContentEntity.ShowapiResBodyEntity.ChannelListEntity;
import org.loader.presenter.FragmentPresenterImpl;

import java.util.List;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class NewFragment extends FragmentPresenterImpl<NewViewImpl> implements MCallBack<NewContentEntity> {
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        new NewBiz().setmCallBack(this);
    }

    @Override
    public void getTCallBack(NewContentEntity newContentEntity, HttpServiceType type) {
            List<ChannelListEntity> contentlistEntities=
                    newContentEntity.getShowapi_res_body().getChannelList();
        mView.setMsg(this, contentlistEntities);
    }
}
