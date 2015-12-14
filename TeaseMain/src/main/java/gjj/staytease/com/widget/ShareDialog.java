package gjj.staytease.com.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import gjj.staytease.com.R;

/**
 * Created by gaojiangjian on 15/12/4.
 */
public class ShareDialog extends Dialog {
    public interface ReturnShare{
        void qqz();
        void weixin();
    }

    public ReturnShare returnShare;

    public void setReturnShare(ReturnShare returnShare) {
        this.returnShare = returnShare;
    }

    public ShareDialog(Context context) {
        super(context, R.style.MyDialogStyleBottom);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_);
        ImageView qqz=(ImageView)findViewById(R.id.qqz);
        ImageView wexinq=(ImageView)findViewById(R.id.weixinq);
        wexinq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnShare.weixin();
            }
        });
        qqz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnShare.qqz();
            }
        });
    }
}
