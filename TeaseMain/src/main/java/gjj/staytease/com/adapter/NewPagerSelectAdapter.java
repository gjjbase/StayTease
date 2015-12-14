package gjj.staytease.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import gjj.staytease.com.bean.NewContentEntity.ShowapiResBodyEntity.ChannelListEntity;

import gjj.staytease.com.ui.view.news.NewListFragment;


import java.util.List;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class NewPagerSelectAdapter extends FragmentStatePagerAdapter {
    List<ChannelListEntity> contentlistEntities;

    public void setContentlistEntities(List<ChannelListEntity> contentlistEntities) {
        this.contentlistEntities = contentlistEntities;
    }

    public NewPagerSelectAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return contentlistEntities.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return NewListFragment.newInstance(contentlistEntities.get(position).getChannelId(), contentlistEntities.get(position).getName());
    }

    @Override
    public int getCount() {
        return contentlistEntities.size();
    }
}
