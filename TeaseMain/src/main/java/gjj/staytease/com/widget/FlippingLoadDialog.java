package gjj.staytease.com.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import gjj.staytease.com.R;


/**
 * Created by admin on 2015/10/12.
 */
public class FlippingLoadDialog extends Dialog {
    public FlippingLoadDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_filppingload);
    }
}
