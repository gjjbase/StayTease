package gjj.staytease.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gjj.staytease.com.ui.view.music.MusicInterListFragment;
import gjj.staytease.com.ui.view.music.MusicListFragment;

import java.util.ArrayList;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public class MusicPagerSelectAdapter extends FragmentStatePagerAdapter {
    ArrayList<String> stringArrayName;

    public MusicPagerSelectAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setStringArrayName(ArrayList<String> stringArrayName) {
        this.stringArrayName = stringArrayName;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayName.get(position);

    }

    @Override
    public Fragment getItem(int position) {

        return position == 0 ? MusicListFragment.newInstance() : MusicInterListFragment.newInstance();
    }

    @Override
    public int getCount() {
        return stringArrayName.size();
    }
}
