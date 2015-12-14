package gjj.staytease.com.ui.interactor;

import gjj.staytease.com.bean.MusicEntity;
import gjj.staytease.com.ui.view.music.MusicInterListFragment;

/**
 * Created by gaojiangjian on 15/11/28.
 */
public interface MusicInterListInteractor {
    void setMsg(MusicInterListFragment context, MusicEntity musicEntities);
    void setMsg(MusicInterListFragment context,int position);
}
