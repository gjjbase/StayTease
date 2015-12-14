package gjj.staytease.com.ui.interactor;

import gjj.staytease.com.ui.view.music.MusicListFragment;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public interface MusicListInteractor {
    void setMsg(MusicListFragment musicListFragment, int position);

    void setMsg(MusicListFragment musicListFragment);
}
