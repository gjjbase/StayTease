package gjj.staytease.com.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import gjj.staytease.com.R;
import gjj.staytease.com.base.BaseAdapter;
import gjj.staytease.com.base.BaseViewHolder;
import gjj.staytease.com.bean.NewContentListEntity.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity;

import java.util.List;

import butterknife.Bind;

/**
 * Created by gaojiangjian on 15/11/20.
 */
public class NewListAdapter extends BaseAdapter<NewListAdapter.NewListViewHoder,ContentlistEntity> {
    public NewListAdapter(Context context, List<ContentlistEntity> contentlistEntities) {
        super(context, contentlistEntities);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_newlist;
    }

    @Override
    protected NewListAdapter.NewListViewHoder getViewholder(View view) {
        return new NewListViewHoder(getContentView());
    }

    @Override
    protected void setJsonData(NewListAdapter.NewListViewHoder holder, ContentlistEntity contentlistEntity) {

            holder.title.setText(contentlistEntity.getTitle());
            holder.content.setText(contentlistEntity.getDesc());
    }

    class NewListViewHoder extends BaseViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.content)
        TextView content;

        public NewListViewHoder(View itemView) {
            super(itemView);
        }
    }
}
