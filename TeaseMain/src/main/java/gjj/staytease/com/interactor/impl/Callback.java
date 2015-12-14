package gjj.staytease.com.interactor.impl;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import gjj.staytease.com.interactor.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;


/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-10-08
 * Time: 11:02
 */
public class Callback<T> implements com.squareup.okhttp.Callback {
    private Parser<T> mParser;
    private static final int CALLBACK_SUCCESSFUL = 0x01;
    private static final int CALLBACK_FAILED = 0x02;
    private Type type;

    static class UIHandler<T> extends Handler {
        private WeakReference mWeakReference;

        public UIHandler(gjj.staytease.com.interactor.impl.Callback<T> callback) {
            super(Looper.getMainLooper());
            mWeakReference = new WeakReference(callback);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CALLBACK_SUCCESSFUL: {
                    T t = (T) msg.obj;
                    gjj.staytease.com.interactor.impl.Callback callback = (gjj.staytease.com.interactor.impl.Callback) mWeakReference.get();
                    if (callback != null) {
                        callback.onResponse(t);
                    }
                    break;
                }
                case CALLBACK_FAILED: {
                    IOException e = (IOException) msg.obj;
                    gjj.staytease.com.interactor.impl.Callback callback = (gjj.staytease.com.interactor.impl.Callback) mWeakReference.get();
                    if (callback != null) {
                        callback.onFailure(e);
                    }
                    break;
                }
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    private Handler mHandler = new UIHandler(this);

    public Callback(Parser<T> mParser, Type type) {
        if (mParser == null) {
            throw new IllegalArgumentException("Parser can't be null");
        }
        this.type = type;
        this.mParser = mParser;
    }

    @Override
    public void onFailure(Request request, IOException e) {
        Message message = Message.obtain();
        message.what = CALLBACK_FAILED;
        message.obj = e;
        mHandler.sendMessage(message);
    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            T parseResult = mParser.parse(response);
            if (type.equals(InputStream.class)) {
                try {
                    parseResult = (T) response.body().byteStream();
                } catch (IOException e) {
                }
            } else if (type.equals(Byte.class)) {
                try {
                    parseResult = (T) response.body().bytes();
                } catch (IOException e) {
                }
            }
            Message message = Message.obtain();
            message.what = CALLBACK_SUCCESSFUL;
            message.obj = parseResult;
            mHandler.sendMessage(message);
        } else {
            Message message = Message.obtain();
            message.what = CALLBACK_FAILED;
            mHandler.sendMessage(message);
        }
    }

    public void onResponse(T t) {

    }

    public void onFailure(IOException e) {

    }
}
