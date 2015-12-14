package gjj.staytease.com.ui.view.music;

import android.os.Bundle;

import gjj.staytease.com.R;
import gjj.staytease.com.ui.interactor.implview.MusicViewImpl;

import org.loader.presenter.FragmentPresenterImpl;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class MusicFragment extends FragmentPresenterImpl<MusicViewImpl> {
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.setMsg(this, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.music_menu))));
    }
}
