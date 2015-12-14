package gjj.staytease.com.ui.interactor;

import gjj.staytease.com.ui.view.photo.PhotoDetaListFragment;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public interface PhotoDetaListInteractor {
    void setMsg(PhotoDetaListFragment fragment, String img, String number);

    void setMsg(PhotoDetaListFragment fragment, String img);
}
