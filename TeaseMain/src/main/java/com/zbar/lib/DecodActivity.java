package com.zbar.lib;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.zbar.lib.bitmap.BitmapLuminanceSource;

import java.util.Hashtable;
import java.util.Vector;

import gjj.staytease.com.R;

/**
 * Created by gaojiangjian on 15/12/4.
 */

public class DecodActivity extends Activity {
    Bitmap bitmap;
    MultiFormatReader multiFormatReader;
    // 开始对图像资源解码
    Result rawResult = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decod);
        bitmap= BitmapFactory.decodeFile(getIntent().getStringExtra("path"));
        ImageView longimg=(ImageView)findViewById(R.id.longimg);
        multiFormatReader = new MultiFormatReader();
        longimg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {

                    rawResult = multiFormatReader
                            .decodeWithState(new BinaryBitmap(new HybridBinarizer(
                                    new BitmapLuminanceSource(BitmapFactory.decodeResource(getResources(),R.drawable.qrc)))));
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
                Intent it = new Intent( Intent.ACTION_VIEW );
                it.setData( Uri.parse(rawResult.getText().toString().trim()) ); //这里面是需要调转的rul
                it = Intent.createChooser(it, null);
                startActivity( it );
                return false;
            }
        });


        // 解码的参数
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>(
                2);
        // 可以解析的编码类型
        Vector<BarcodeFormat> decodeFormats = new Vector<BarcodeFormat>();
        if (decodeFormats == null || decodeFormats.isEmpty()) {
            decodeFormats = new Vector<BarcodeFormat>();

            // 这里设置可扫描的类型，我这里选择了都支持
            decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        }
        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);

        // 设置继续的字符编码格式为UTF8
        // hints.put(DecodeHintType.CHARACTER_SET, "UTF8");

        // 设置解析配置参数
        multiFormatReader.setHints(hints);

        try {

            rawResult = multiFormatReader
                    .decodeWithState(new BinaryBitmap(new HybridBinarizer(
                            new BitmapLuminanceSource(bitmap))));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        TextView textView = (TextView) findViewById(R.id.tv_text);
        try{
            textView.setText(new StringBuilder().append("包括内容：")
                    .append(rawResult.getText()).append("\n编码方式：")
                    .append(rawResult.getBarcodeFormat()).append("\n解析时间：")
                    .append(rawResult.getTimestamp()).toString());
        }catch (Exception e){
            textView.setText("编码错误");
        }

    }

}