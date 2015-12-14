package gjj.staytease.com.ui.interactor.implview;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gjj.staytease.com.R;
import gjj.staytease.com.adapter.NewListAdapter;
import gjj.staytease.com.bean.NewContentListEntity;
import gjj.staytease.com.ui.interactor.NewListInteractor;
import gjj.staytease.com.ui.view.news.NewDateActivity;
import gjj.staytease.com.ui.view.news.NewListFragment;
import gjj.staytease.com.widget.PullToLoadView;

import org.loader.view.ViewImpl;
import gjj.staytease.com.bean.NewContentListEntity.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class NewListViewImpl extends ViewImpl implements NewListInteractor {
    PullToLoadView pulltoload;
    RecyclerView recyclerView;
    private boolean isLoading;
    private boolean isHasLoadAll;
    NewListAdapter newListAdapter;
    List<ContentlistEntity> contentlistEntityList;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        pulltoload.isLoadMoreEnabled(true);
        recyclerView.setHasFixedSize(false);
        return view;
    }
    public void setMsg(NewListFragment context, NewContentListEntity newContentListEntity) {
            int allNum=newContentListEntity.getShowapi_res_body().getPagebean().getAllNum();
            List<ContentlistEntity> contentlistEntities=newContentListEntity.getShowapi_res_body().getPagebean().getContentlist();
            contentlistEntityList.addAll(contentlistEntities);
            newListAdapter = new NewListAdapter(context.getActivity(), contentlistEntityList);
            recyclerView.setAdapter(newListAdapter);
            newListAdapter.setOnItemClickListener2(context);
            if (contentlistEntityList.size() == allNum) isHasLoadAll = true;
        isLoading = false;
        pulltoload.setComplete();
    }

    public void setMsg(NewListFragment context, int position) {
            context.startActivity(new Intent(context.getActivity(), NewDateActivity.class).putExtra("desc", contentlistEntityList.get(position).getDesc()).putExtra("title", contentlistEntityList.get(position).getTitle()));
    }

    public void setMsg(NewListFragment newListFragment) {
        contentlistEntityList=new ArrayList<>();
        pulltoload.setPullCallback(newListFragment);
        pulltoload.initLoad();
    }

}
