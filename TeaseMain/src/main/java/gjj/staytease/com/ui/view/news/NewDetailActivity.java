package gjj.staytease.com.ui.view.news;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import gjj.staytease.com.R;

public class NewDetailActivity extends AppCompatActivity {
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView content = (TextView) findViewById(R.id.content);
        setSupportActionBar(toolbar);
        StringBuffer stringBuffer = new StringBuffer();
        String desc = getIntent().getStringExtra("desc");
        for (int i = 0; i < 9; i++)
            stringBuffer.append(desc);
        content.setText(stringBuffer.toString());
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getIntent().getStringExtra("title"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
