package gjj.staytease.com.ui.interactor.implview;


import android.support.v4.view.ViewPager;


import gjj.staytease.com.R;
import gjj.staytease.com.adapter.SectionsPagerAdapter;
import gjj.staytease.com.bean.PhotoEntity.ShowapiResBodyEntity;
import gjj.staytease.com.ui.interactor.PhotoDetaViewInteractor;
import gjj.staytease.com.ui.view.photo.PhotoDetaActivity;

import org.loader.view.ViewImpl;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoDetaViewImpl extends ViewImpl implements PhotoDetaViewInteractor {

    @Bind(R.id.container)
    ViewPager mViewPager;
    int position;
    SectionsPagerAdapter mSectionsPagerAdapter;


    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photot_deta;
    }

    public void setMsg(PhotoDetaActivity photoDetaActivity,ArrayList<ShowapiResBodyEntity> showapiResBodyEntityList) {
        mSectionsPagerAdapter = new SectionsPagerAdapter(photoDetaActivity.getSupportFragmentManager());
        mSectionsPagerAdapter.setShowapiResBodyEntityList(showapiResBodyEntityList);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(position);

    }
}
