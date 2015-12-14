package gjj.staytease.com.ui.view.photo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import gjj.staytease.com.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by gaojiangjian on 15/11/26.
 */
public class PhotoBigActivity extends AppCompatActivity {
    PhotoViewAttacher attacher;
    ImageView bigimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigimg);
        bigimg = (ImageView) findViewById(R.id.big_img);
        Glide.with(PhotoBigActivity.this).load(getIntent().getStringExtra("imgurl"))
                .placeholder(android.R.drawable.stat_notify_error)
                .error(android.R.drawable.stat_notify_error)
                .crossFade()
                .into(bigimg);
        attacher = new PhotoViewAttacher(bigimg);
    }
}
