package gjj.staytease.com.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gjj.staytease.com.R;
import gjj.staytease.com.interactor.OnRecyclerViewItemClickListener;


import java.util.List;

/**
 * Created by admin on 2015/9/12.
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> implements View.OnClickListener {

    public Context mContext;
    public List<T> dataList;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;


    protected abstract int getContentViewLayoutID();

    protected abstract VH getViewholder(View view);

    protected abstract void setJsonData(VH holder, T t);


    public BaseAdapter(Context context, List<T> dataList) {
        /* TODO Auto-generated constructor stub */
        this.mContext = context;
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setTag(R.id.postiton, position);
        setJsonData(holder, dataList.get(position));
        holder.itemView.setOnClickListener(this);
    }

    public int getItemCount() {
        return dataList.size();
    }

    protected View getContentView() {
        return LayoutInflater.from(mContext).inflate(getContentViewLayoutID(), null);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return getViewholder(getContentView());
    }

    public T getItem(int position) {
        // TODO Auto-generated method stub
        return dataList.get(position);
    }


    public void setOnItemClickListener2(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (onRecyclerViewItemClickListener != null)
            onRecyclerViewItemClickListener.itemClick(v, (Integer) v.getTag(R.id.postiton));
    }
}
