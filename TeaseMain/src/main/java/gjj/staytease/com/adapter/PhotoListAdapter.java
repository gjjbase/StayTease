package gjj.staytease.com.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import gjj.staytease.com.R;
import gjj.staytease.com.base.BaseAdapter;
import gjj.staytease.com.base.BaseViewHolder;
import gjj.staytease.com.bean.PhotoEntity.ShowapiResBodyEntity;

import java.util.List;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/24.
 */
public class PhotoListAdapter extends BaseAdapter<PhotoListAdapter.PhotoViewHoder,ShowapiResBodyEntity> {

    public PhotoListAdapter(Context context, List<ShowapiResBodyEntity> showapiResBodyEntityList) {
        super(context, showapiResBodyEntityList);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.item_photo;
    }

    @Override
    protected PhotoViewHoder getViewholder(View view) {
        return new PhotoViewHoder(getContentView());
    }

    @Override
    protected void setJsonData(PhotoViewHoder holder, ShowapiResBodyEntity showapiResBodyEntity) {
            Glide.with(holder.imageView.getContext()).load(showapiResBodyEntity.getThumb())
                    .placeholder(android.R.drawable.stat_notify_error)
                    .error(android.R.drawable.stat_notify_error)
                    .crossFade()
                    .fitCenter()//中心fit, 以原本圖片的長寬為主
                    .into(holder.imageView);
    }

    class PhotoViewHoder extends BaseViewHolder {
        @Bind(R.id.img_itme)
        ImageView imageView;

        public PhotoViewHoder(View itemView) {
            super(itemView);
        }

    }
}
