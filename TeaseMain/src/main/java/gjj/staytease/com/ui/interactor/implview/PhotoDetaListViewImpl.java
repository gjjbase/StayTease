package gjj.staytease.com.ui.interactor.implview;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import gjj.staytease.com.R;
import gjj.staytease.com.ui.interactor.PhotoDetaListInteractor;
import gjj.staytease.com.ui.view.photo.PhotoBigActivity;
import gjj.staytease.com.ui.view.photo.PhotoDetaListFragment;

import org.loader.view.ViewImpl;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoDetaListViewImpl extends ViewImpl implements PhotoDetaListInteractor {

    @Bind(R.id.section_label)
    ImageView section;
    @Bind(R.id.txt_title)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_photot_deta;
    }
    public void setMsg(PhotoDetaListFragment fragment, String img, String number) {
        Glide.with(fragment.getActivity()).load(img)
                .placeholder(android.R.drawable.stat_notify_error)
                .error(android.R.drawable.stat_notify_error)
                .crossFade()
                .into(section);
        section.setTag(img);
        textView.setText(number);
    }

    public void setMsg(PhotoDetaListFragment fragment, String img) {
        fragment.startActivity(new Intent(fragment.getActivity(), PhotoBigActivity.class).putExtra("imgurl", img));
    }
}
