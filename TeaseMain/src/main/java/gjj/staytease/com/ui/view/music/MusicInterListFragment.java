package gjj.staytease.com.ui.view.music;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import gjj.staytease.com.R;
import gjj.staytease.com.bean.MusicEntity;
import gjj.staytease.com.config.HttpServiceType;
import gjj.staytease.com.interactor.MCallBack;
import gjj.staytease.com.ui.biz.MusicInterListBiz;
import gjj.staytease.com.ui.interactor.implview.MusicInterListViewImpl;

import org.loader.presenter.FragmentPresenterImpl;

import butterknife.OnItemClick;

/**
 * Created by gaojiangjian on 15/11/28.
 */
public class MusicInterListFragment extends FragmentPresenterImpl<MusicInterListViewImpl> implements MCallBack<MusicEntity>,AdapterView.OnItemClickListener {
    public static MusicInterListFragment newInstance() {
        MusicInterListFragment fragment = new MusicInterListFragment();
        return fragment;
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        new MusicInterListBiz().setmCallBack(this, "5");
    }

    @Override
    public void getTCallBack(MusicEntity musicEntities, HttpServiceType type) {
        mView.setMsg(this, musicEntities);
    }


    @OnItemClick(R.id.list)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mView.setMsg(this, position);
    }

}
