package gjj.staytease.com.ui.interactor;

import gjj.staytease.com.bean.PhotoEntity;
import gjj.staytease.com.ui.view.photo.PhotoListFragment;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public interface PhotoListViewInteractor {

    void setMsg(PhotoListFragment context, PhotoEntity photoEntity);

    void setMsg(PhotoListFragment photoListFragment, int position);

    void setMsg(PhotoListFragment photoListFragment);
}
