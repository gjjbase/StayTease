package gjj.staytease.com.ui.view.photo;

import android.os.Bundle;

import gjj.staytease.com.R;
import gjj.staytease.com.ui.interactor.implview.PhotoViewImpl;

import org.loader.presenter.FragmentPresenterImpl;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoFragment extends FragmentPresenterImpl<PhotoViewImpl> {
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.setMsg(this, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.photo_menu2))), new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.photo_menu))));
    }
}
