package gjj.staytease.com.ui.interactor.implview;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import gjj.staytease.com.R;
import gjj.staytease.com.adapter.PhotoPagerSelectAdapter;
import gjj.staytease.com.ui.interactor.PhotoViewInteractor;
import gjj.staytease.com.ui.view.photo.PhotoFragment;

import org.loader.view.ViewImpl;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoViewImpl extends ViewImpl implements PhotoViewInteractor {

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tab_layout)
    TabLayout tab_layout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all;
    }

    public void setMsg(PhotoFragment mainActivity, ArrayList<String> stringArrayid, ArrayList<String> stringArrayname) {
        PhotoPagerSelectAdapter photoPagerSelectAdapter = new PhotoPagerSelectAdapter(mainActivity.getChildFragmentManager());
        photoPagerSelectAdapter.setStringArrayid(stringArrayid);
        photoPagerSelectAdapter.setStringArrayName(stringArrayname);
//        viewpager.setOffscreenPageLimit(2);
        viewpager.setSaveEnabled(true);
        viewpager.setAdapter(photoPagerSelectAdapter);
        tab_layout.setupWithViewPager(viewpager);
        tab_layout.setTabsFromPagerAdapter(photoPagerSelectAdapter);

    }
}
