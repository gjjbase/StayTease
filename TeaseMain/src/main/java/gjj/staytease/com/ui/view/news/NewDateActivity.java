package gjj.staytease.com.ui.view.news;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import gjj.staytease.com.R;


public class NewDateActivity extends AppCompatActivity {
    TextView title;
    TextView content;
    Toolbar mtoolbar;
    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_date);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        mtoolbar = (Toolbar) findViewById(R.id.mtoolbar);
        title.setText(getIntent().getStringExtra("title"));
        content.setText(getIntent().getStringExtra("desc"));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
