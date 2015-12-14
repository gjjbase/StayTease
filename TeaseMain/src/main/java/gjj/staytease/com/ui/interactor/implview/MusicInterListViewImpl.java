package gjj.staytease.com.ui.interactor.implview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import gjj.staytease.com.R;
import gjj.staytease.com.adapter.MusicInterListAdapter;
import gjj.staytease.com.bean.MusicEntity;
import gjj.staytease.com.bean.MusicEntity.ShowapiResBodyEntity.PagebeanEntity.SonglistEntity;
import gjj.staytease.com.ui.interactor.MusicInterListInteractor;
import gjj.staytease.com.ui.view.music.FullscreenVlcPlayer;
import gjj.staytease.com.ui.view.music.MusicInterListFragment;

import org.loader.view.ViewImpl;

import java.util.List;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/28.
 */
public class MusicInterListViewImpl extends ViewImpl implements MusicInterListInteractor {
    @Bind(R.id.list)
    ListView list;
    MusicInterListAdapter musicInterListAdapter;
    List<SonglistEntity> songlistEntities;
    public int getLayoutId() {
        return R.layout.fragment_musiclist;
    }

    @Override
    public void setMsg(MusicInterListFragment context, MusicEntity musicEntities) {
       songlistEntities=musicEntities.getShowapi_res_body().getPagebean().getSonglist();
        musicInterListAdapter=new MusicInterListAdapter(context.getActivity());
        musicInterListAdapter.setSonglistEntityList(songlistEntities);
        list.setAdapter(musicInterListAdapter);
    }

    @Override
    public void setMsg(MusicInterListFragment context, int position) {
        Bundle bundle=new Bundle();
        bundle.putString("url",songlistEntities.get(position).getUrl());
        context.startActivity(new Intent(context.getActivity(), FullscreenVlcPlayer.class).putExtras(bundle));
    }
}
