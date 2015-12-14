package gjj.staytease.com.ui.interactor;

import gjj.staytease.com.bean.NewContentListEntity;
import gjj.staytease.com.ui.view.news.NewListFragment;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public interface NewListInteractor {

    void setMsg(NewListFragment newListFragment, NewContentListEntity newContentListEntity);

    void setMsg(NewListFragment newListFragment, int positon);

    void setMsg(NewListFragment newListFragment);
}
