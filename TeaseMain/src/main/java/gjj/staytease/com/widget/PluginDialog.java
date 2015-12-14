package gjj.staytease.com.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gjj.staytease.com.R;

/**
 * Created by gaojiangjian on 15/12/4.
 */

public class PluginDialog extends Dialog {
    public interface ReturnPlugin{
        void ind();
        void noind();
    }

    public ReturnPlugin returnPlugin;

    public void setReturnShare(ReturnPlugin returnPlugin) {
        this.returnPlugin = returnPlugin;
    }

    public PluginDialog(Context context) {
        super(context, R.style.MyDialogStyleBottom);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_plugin);

        findViewById(R.id.weixinq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnPlugin.ind();
            }
        });
        findViewById(R.id.qqz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnPlugin.noind();
            }
        });
    }
}