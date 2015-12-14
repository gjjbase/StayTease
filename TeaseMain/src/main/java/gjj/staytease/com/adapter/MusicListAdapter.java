package gjj.staytease.com.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import gjj.staytease.com.R;
import gjj.staytease.com.base.BaseApplication;
import gjj.staytease.com.util.MusicUtils;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public class MusicListAdapter extends BaseAdapter {

    private int mPlayingPosition;

    public void setPlayingPosition(int position) {
        mPlayingPosition = position;
    }

    public int getPlayingPosition() {
        return mPlayingPosition;
    }

    @Override
    public int getCount() {
        return MusicUtils.sMusicList.size();
    }

    @Override
    public Object getItem(int position) {
        return MusicUtils.sMusicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(BaseApplication.context, R.layout.musicdeta_item, null);
            holder = new ViewHolder();
            holder.txt_title = (TextView) convertView.findViewById(R.id.txt_title);
            holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(holder.imageView.getContext()).load(MusicUtils.sMusicList.get(position).getImage())
                .placeholder(android.R.drawable.stat_notify_error)
                .error(android.R.drawable.stat_notify_error)
                .crossFade()
                .fitCenter()//中心fit, 以原本圖片的長寬為主
                .into(holder.imageView);
        holder.txt_title.setText(MusicUtils.sMusicList.get(position).getTitle());
        holder.txt_content.setText(MusicUtils.sMusicList.get(position).getArtist());

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView txt_title, txt_content;
    }
}

