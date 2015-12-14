package gjj.staytease.com.ui.view.news;

import android.os.Bundle;
import android.view.View;

import gjj.staytease.com.bean.NewContentListEntity;
import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.interactor.MCallBack;
import gjj.staytease.com.interactor.OnRecyclerViewItemClickListener;
import gjj.staytease.com.interactor.PullCallback;
import gjj.staytease.com.ui.biz.NewListBiz;
import gjj.staytease.com.ui.interactor.implview.NewListViewImpl;

import org.loader.presenter.FragmentPresenterImpl;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class NewListFragment extends FragmentPresenterImpl<NewListViewImpl> implements PullCallback, OnRecyclerViewItemClickListener, MCallBack<NewContentListEntity> {
    private static final String ID = "id";
    private static final String NAME = "name";

    public static NewListFragment newInstance(String id, String name) {
        NewListFragment fragment = new NewListFragment();
        Bundle args = new Bundle();
        args.putString(ID, id);
        args.putString(NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.setMsg(this);
    }

    @Override
    public void onLoadMore() {
        mView.setPage(mView.getPage() + 1);
        mView.setIsLoading(true);
        new NewListBiz().setmCallBack(this, getArguments().getString(ID), mView.getPage(), getArguments().getString(NAME));
    }

    @Override
    public void onRefresh() {
        mView.setIsHasLoadAll(false);
        mView.setIsLoading(true);
        mView.setPage(1);
        new NewListBiz().setmCallBack(this, getArguments().getString(ID), mView.getPage(), getArguments().getString(NAME));
    }

    @Override
    public boolean isLoading() {
        return mView.getIsLoading();
    }

    @Override
    public boolean hasLoadedAllItems() {
        if (mView.getIsHasLoadAll()) {
            mView.setIsLoading(false);
        }
        return mView.getIsHasLoadAll();
    }

    @Override
    public void itemClick(View view,int position) {
        mView.setMsg(this, position);
    }

    @Override
    public void getTCallBack(NewContentListEntity newContentListEntity, HttpServiceType type) {
        mView.setMsg(NewListFragment.this, newContentListEntity);
    }
}
