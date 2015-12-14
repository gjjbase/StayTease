package gjj.staytease.com.adapter;

/**
 * Created by gaojiangjian on 15/11/26.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import gjj.staytease.com.bean.PhotoEntity;
import gjj.staytease.com.ui.view.photo.PhotoDetaListFragment;

import java.util.ArrayList;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    ArrayList<PhotoEntity.ShowapiResBodyEntity> showapiResBodyEntityList;

    public void setShowapiResBodyEntityList(    ArrayList<PhotoEntity.ShowapiResBodyEntity> showapiResBodyEntityList
    ) {
        this.showapiResBodyEntityList = showapiResBodyEntityList;
    }

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
            return PhotoDetaListFragment.newInstance(showapiResBodyEntityList.get(position).getThumb(),showapiResBodyEntityList.get(position).getTitle());
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return showapiResBodyEntityList.size();
    }
}