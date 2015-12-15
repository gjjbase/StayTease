package gjj.staytease.com.ui.view.photo;

import android.os.Bundle;
import android.view.View;

import gjj.staytease.com.bean.PhotoEntity;
import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.interactor.MCallBack;
import gjj.staytease.com.interactor.OnRecyclerViewItemClickListener;
import gjj.staytease.com.interactor.PullCallback;
import gjj.staytease.com.ui.biz.PhotoListBiz;
import gjj.staytease.com.ui.interactor.implview.PhotoListViewImpl;

import org.loader.presenter.FragmentPresenterImpl;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoListFragment extends FragmentPresenterImpl<PhotoListViewImpl> implements PullCallback, MCallBack<PhotoEntity>, OnRecyclerViewItemClickListener {
    private static final String TYPE = "type";

    public static PhotoListFragment newInstance(String type) {
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
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
//        mView.setIsLoading(true);
//        mView.setPage(mView.getPage() + 1);
//        new PhotoListBiz().setmCallBack(this, getArguments().getString(TYPE), mView.getPage());
    }

    @Override
    public void onRefresh() {
        mView.setIsHasLoadAll(false);
        mView.setIsLoading(true);
        mView.setPage(1);
        new PhotoListBiz().setmCallBack(this, getArguments().getString(TYPE), mView.getPage());
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
    public void getTCallBack(PhotoEntity photoEntity, HttpServiceType type) {
        mView.setMsg(PhotoListFragment.this, photoEntity);
    }

    @Override
    public void itemClick(View view, int position) {
        mView.setMsg(this, position);
    }
}
