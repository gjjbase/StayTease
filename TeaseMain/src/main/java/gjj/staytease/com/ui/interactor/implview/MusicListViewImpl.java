package gjj.staytease.com.ui.interactor.implview;

import android.content.Intent;
import android.widget.ListView;

import gjj.staytease.com.R;
import gjj.staytease.com.adapter.MusicListAdapter;
import gjj.staytease.com.ui.interactor.MusicListInteractor;
import gjj.staytease.com.ui.view.music.MusicListFragment;
import gjj.staytease.com.ui.view.music.MusicPlayActivity;
import gjj.staytease.com.util.MusicUtils;
import gjj.staytease.com.widget.PullToLoadView;

import org.loader.view.ViewImpl;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public class MusicListViewImpl extends ViewImpl implements MusicListInteractor {
    @Bind(R.id.list)
    ListView list;
    MusicListAdapter musicListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_musiclist;
    }

    @Override
    public void setMsg(MusicListFragment musicListFragment, int position) {
        musicListFragment.startActivity(new Intent(musicListFragment.getActivity(), MusicPlayActivity.class).putExtra("positon", position));
    }

    @Override
    public void setMsg(MusicListFragment musicListFragment) {
        musicListAdapter = new MusicListAdapter();
        list.setAdapter(musicListAdapter);
    }
}
