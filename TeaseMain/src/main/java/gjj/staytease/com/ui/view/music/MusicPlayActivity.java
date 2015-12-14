package gjj.staytease.com.ui.view.music;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import gjj.staytease.com.R;
import gjj.staytease.com.ui.interactor.implview.MusicPlayViewImpl;

import org.loader.presenter.ActivityPresenterImpl;

import butterknife.OnClick;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public class MusicPlayActivity extends ActivityPresenterImpl<MusicPlayViewImpl> implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    @OnClick({R.id.pre, R.id.start, R.id.next})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pre:
                mView.setPre();
                break;
            case R.id.start:
                mView.startMusic();
                break;
            case R.id.next:
                mView.setNext();
                break;
        }
    }

    @Override
    public void create(Bundle savedInstance) {
        super.create(savedInstance);
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.setItem(getIntent().getIntExtra("positon", 0));
        mView.sBind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mView.unBind(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mView.setSeek(seekBar.getProgress());
    }
}
