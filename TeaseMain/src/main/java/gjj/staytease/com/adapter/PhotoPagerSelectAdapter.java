package gjj.staytease.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gjj.staytease.com.ui.view.photo.PhotoListFragment;


import java.util.ArrayList;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoPagerSelectAdapter extends FragmentStatePagerAdapter {
    ArrayList<String> stringArrayName;
    ArrayList<String> stringArrayid;

    public PhotoPagerSelectAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setStringArrayid(ArrayList<String> stringArrayid) {
        this.stringArrayid = stringArrayid;
    }

    public void setStringArrayName(ArrayList<String> stringArrayName) {
        this.stringArrayName = stringArrayName;
    }


    @Override
    public Fragment getItem(int position) {
        return PhotoListFragment.newInstance(stringArrayid.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayName.get(position);
    }

    @Override
    public int getCount() {
        return stringArrayid.size();
    }
}
