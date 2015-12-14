package gjj.staytease.com.ui.view.photo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import gjj.staytease.com.R;
import gjj.staytease.com.ui.interactor.implview.PhotoDetaViewImpl;

import org.loader.presenter.ActivityPresenterImpl;

import gjj.staytease.com.bean.PhotoEntity.ShowapiResBodyEntity;

import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoDetaActivity extends ActivityPresenterImpl<PhotoDetaViewImpl> implements OnClickListener {
    @OnClick(R.id.fab)
    public void onClick(View v) {
        mView.showToas("Replace with your own action");
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        ArrayList<ShowapiResBodyEntity> showapiResBodyEntityList = getIntent().getParcelableArrayListExtra("imglist");
        mView.setPosition(getIntent().getIntExtra("position", 0));
        mView.setMsg(this,showapiResBodyEntityList);
    }

}
