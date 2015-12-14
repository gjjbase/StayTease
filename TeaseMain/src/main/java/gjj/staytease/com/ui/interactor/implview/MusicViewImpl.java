package gjj.staytease.com.ui.interactor.implview;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import gjj.staytease.com.R;
import gjj.staytease.com.adapter.MusicPagerSelectAdapter;
import gjj.staytease.com.ui.interactor.MusicInteractor;
import gjj.staytease.com.ui.view.music.MusicFragment;

import org.loader.view.ViewImpl;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class MusicViewImpl extends ViewImpl implements MusicInteractor {
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tab_layout)
    TabLayout tab_layout;
    MusicPagerSelectAdapter newPagerSelectAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    public void setMsg(MusicFragment musicFragment, ArrayList<String> arrayList) {
        newPagerSelectAdapter = new MusicPagerSelectAdapter(musicFragment.getChildFragmentManager());
        newPagerSelectAdapter.setStringArrayName(arrayList);
        viewpager.setAdapter(newPagerSelectAdapter);
        tab_layout.setupWithViewPager(viewpager);
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);
        tab_layout.setTabsFromPagerAdapter(newPagerSelectAdapter);
    }
}
