package gjj.staytease.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import gjj.staytease.com.R;
import gjj.staytease.com.base.BaseApplication;
import gjj.staytease.com.bean.MusicEntity.ShowapiResBodyEntity.PagebeanEntity.SonglistEntity;

import java.util.List;

/**
 * Created by gaojiangjian on 15/11/28.
 */
public class MusicInterListAdapter extends BaseAdapter {
    List<SonglistEntity> songlistEntityList;
    public MusicInterListAdapter(Context context) {
        super();
    }

    public void setSonglistEntityList(List<SonglistEntity> songlistEntityList) {
        this.songlistEntityList = songlistEntityList;
    }

    @Override
    public int getCount() {
        return songlistEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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
        holder.txt_title.setText(songlistEntityList.get(position).getSingername());

        holder.txt_content.setText(songlistEntityList.get(position).getSongname());
//        holder.txt_content.setTag(songlistEntityList.get(position).getDownUrl());
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView txt_title, txt_content;
    }
}
