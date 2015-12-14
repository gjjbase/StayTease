package gjj.staytease.com.ui.interactor.implview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gjj.staytease.com.R;
import gjj.staytease.com.adapter.PhotoListAdapter;
import gjj.staytease.com.bean.PhotoEntity.ShowapiResBodyEntity;
import gjj.staytease.com.bean.PhotoEntity;
import gjj.staytease.com.ui.interactor.PhotoListViewInteractor;
import gjj.staytease.com.ui.view.photo.PhotoDetaActivity;
import gjj.staytease.com.ui.view.photo.PhotoListFragment;
import gjj.staytease.com.widget.PullToLoadView;

import org.loader.view.ViewImpl;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoListViewImpl extends ViewImpl implements PhotoListViewInteractor {

    @Bind(R.id.pulltoload)
    PullToLoadView pulltoload;
    RecyclerView recyclerView;
    boolean isLoading;
    boolean isHasLoadAll;
    PhotoListAdapter photoListAdapter;
    ArrayList<ShowapiResBodyEntity> showapiResBodyEntityList;
    int page;


    public boolean getIsHasLoadAll() {
        return isHasLoadAll;
    }

    public void setIsHasLoadAll(boolean isHasLoadAll) {
        this.isHasLoadAll = isHasLoadAll;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public boolean getIsLoading() {
        return isLoading;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }


    @Override
    public View create(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        pulltoload = (PullToLoadView) view.findViewById(R.id.pulltoload);
        recyclerView = pulltoload.getRecyclerView();
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        pulltoload.isLoadMoreEnabled(true);
        recyclerView.setHasFixedSize(false);
        return view;
    }

    public void setMsg(PhotoListFragment photoListFragment) {
        pulltoload.setPullCallback(photoListFragment);
        pulltoload.initLoad();
    }

    public void setMsg(PhotoListFragment photoListFragment, int position) {
        photoListFragment.startActivity(new Intent(photoListFragment.getActivity(), PhotoDetaActivity.class).putExtra("position", position).putParcelableArrayListExtra("imglist", showapiResBodyEntityList));
    }
    public void setMsg(PhotoListFragment context, PhotoEntity photoEntity) {
        if (page == 1) showapiResBodyEntityList = new ArrayList<>();
        showapiResBodyEntityList.addAll(photoEntity.getShowapi_res_body());
        photoListAdapter = new PhotoListAdapter(context.getActivity(), showapiResBodyEntityList);
        recyclerView.setAdapter(photoListAdapter);
        photoListAdapter.setOnItemClickListener2(context);
        if (photoEntity.getShowapi_res_body().size() < 10)
            isHasLoadAll = true;
        isLoading = false;
        pulltoload.setComplete();
    }

}
