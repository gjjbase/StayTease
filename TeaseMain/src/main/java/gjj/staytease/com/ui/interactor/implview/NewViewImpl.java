package gjj.staytease.com.ui.interactor.implview;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import gjj.staytease.com.R;
import gjj.staytease.com.adapter.NewPagerSelectAdapter;
import gjj.staytease.com.bean.NewContentEntity.ShowapiResBodyEntity.ChannelListEntity;
import gjj.staytease.com.ui.interactor.NewViewInteractor;
import gjj.staytease.com.ui.view.news.NewFragment;
import org.loader.view.ViewImpl;

import java.util.List;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class NewViewImpl extends ViewImpl implements NewViewInteractor {
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tab_layout)
    TabLayout tab_layout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all;
    }

    public void setMsg(NewFragment mainActivity, List<ChannelListEntity> channelListEntityList) {
        NewPagerSelectAdapter newPagerSelectAdapter = new NewPagerSelectAdapter(mainActivity.getChildFragmentManager());
        newPagerSelectAdapter.setContentlistEntities(channelListEntityList);
        viewpager.setAdapter(newPagerSelectAdapter);
        tab_layout.setupWithViewPager(viewpager);
        tab_layout.setTabsFromPagerAdapter(newPagerSelectAdapter);
    }
}
