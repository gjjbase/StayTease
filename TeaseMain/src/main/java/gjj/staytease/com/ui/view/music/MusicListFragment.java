package gjj.staytease.com.ui.view.music;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import gjj.staytease.com.R;
import gjj.staytease.com.interactor.PullCallback;
import gjj.staytease.com.ui.interactor.implview.MusicListViewImpl;

import org.loader.presenter.FragmentPresenterImpl;

import butterknife.OnItemClick;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public class MusicListFragment extends FragmentPresenterImpl<MusicListViewImpl> implements AdapterView.OnItemClickListener {
    public static MusicListFragment newInstance() {
        MusicListFragment fragment = new MusicListFragment();
        return fragment;
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.setMsg(this);
    }

    @OnItemClick(R.id.list)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mView.setMsg(this, position);
    }
}
