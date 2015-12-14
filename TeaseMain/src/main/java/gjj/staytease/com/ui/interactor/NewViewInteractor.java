package gjj.staytease.com.ui.interactor;

import gjj.staytease.com.bean.NewContentEntity.ShowapiResBodyEntity.ChannelListEntity;
import gjj.staytease.com.ui.view.news.NewFragment;

import java.util.List;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public interface NewViewInteractor {
    void setMsg(NewFragment mainActivity, List<ChannelListEntity> channelListEntityList);
}
