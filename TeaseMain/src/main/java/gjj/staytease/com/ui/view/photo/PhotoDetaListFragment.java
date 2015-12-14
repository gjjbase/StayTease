package gjj.staytease.com.ui.view.photo;

import android.os.Bundle;
import android.view.View;

import gjj.staytease.com.R;
import gjj.staytease.com.ui.interactor.implview.PhotoDetaListViewImpl;

import org.loader.presenter.FragmentPresenterImpl;

import butterknife.OnClick;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoDetaListFragment extends FragmentPresenterImpl<PhotoDetaListViewImpl> implements View.OnClickListener {
    private static final String ID = "id";
    private static final String NUMBER = "number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PhotoDetaListFragment newInstance(String sectionNumber, String img) {
        PhotoDetaListFragment fragment = new PhotoDetaListFragment();
        Bundle args = new Bundle();
        args.putString(ID, sectionNumber);
        args.putString(NUMBER, img);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.setMsg(this, getArguments().getString(ID), getArguments().getString(NUMBER));
    }

    @OnClick(R.id.section_label)
    public void onClick(View v) {
        mView.setMsg(this, (String) v.getTag());
    }
}
