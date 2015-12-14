package gjj.staytease.com.config;


import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import gjj.staytease.com.bean.PhotoEntity;
import gjj.staytease.com.interactor.TCallBack;
import gjj.staytease.com.interactor.impl.Callback;
import gjj.staytease.com.interactor.impl.Parserimpl;
import gjj.staytease.com.util.DateTools;
import gjj.staytease.com.util.GlobalsUtil;
import gjj.staytease.com.widget.FlippingLoadDialog;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaojiangjian on 15/11/21.
 */
public class OkhttpUtil<T> {
    protected Class type;

    protected OkHttpClient okHttpClient;
    protected TCallBack<T> ttCallBack;
    protected FlippingLoadDialog flippingLoadDialog;

    protected enum HTTPTYPE {
        GET,
        POST
    }

    public void setTtCallBack(TCallBack<T> ttCallBack) {
        this.ttCallBack = ttCallBack;
    }

    public OkhttpUtil(Class type) {
        this.type = type;
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
    }

    protected void postFrom(Activity activity, String url, RequestBody formBody, HTTPTYPE Type, final HttpServiceType httpServiceType) {
//
//        if (flippingLoadDialog == null) {
//            flippingLoadDialog = new FlippingLoadDialog(activity);
//            flippingLoadDialog.show();
//        }
        Request request = null;
        switch (Type) {
            case GET:
                request = new Request.Builder().url(url).tag(httpServiceType).build();
                break;
            case POST:
                request = new Request.Builder().url(url).tag(httpServiceType).post(formBody).build();
                break;
        }
        okHttpClient.newCall(request).enqueue(new Callback<T>(new Parserimpl(), type) {
            @Override
            public void onResponse(T t) {
                super.onResponse(t);
//                if (flippingLoadDialog.isShowing()) flippingLoadDialog.dismiss();

                try {

                    if (type == InputStream.class) {
                        InputStream inputStream = (InputStream) t;
                        int len;
                        byte[] buf = new byte[2048];
                        //可以在这里自定义路径
                        File file1 = new File("d:\\file.jpg");
                        FileOutputStream fileOutputStream = new FileOutputStream(file1);

                        while ((len = inputStream.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, len);
                        }

                        fileOutputStream.flush();
                        fileOutputStream.close();
                        inputStream.close();
                    } else if (type.equals(Byte.class)) {

                    } else if (type.equals(String.class)) {
                        ttCallBack.getTCallBack((T) t.toString(), httpServiceType);
                    } else if (type.equals(JSONObject.class)) {
                        ttCallBack.getTCallBack((T) new JSONObject(t.toString()), httpServiceType);
                    } else {
                        if (type.equals(PhotoEntity.class)) {
                            JSONObject jsonObject = new JSONObject(t.toString());
                            JSONObject jsonObject1 = jsonObject.getJSONObject("showapi_res_body");
                            JSONArray jsonArray1 = new JSONArray();
                            Iterator iterator = jsonObject1.keys();
                            while (iterator.hasNext())
                                try {
                                    jsonArray1.put(jsonObject1.getJSONObject(iterator.next() + ""));
                                } catch (Exception e) {

                                }
                            JSONObject js = new JSONObject();
                            js.put("showapi_res_code", jsonObject.getString("showapi_res_code"));
                            js.put("showapi_res_error", jsonObject.getString("showapi_res_error"));
                            js.put("showapi_res_body", jsonArray1);
                            t = (T) js.toString();
                        }
                        ttCallBack.getTCallBack((T) GlobalsUtil.fromJsonObject(t.toString(), type), httpServiceType);
                    }

                } catch (Exception e) {


                }
            }
        });
    }


    public void callHttp(HttpServiceType httpServiceType) {
        okHttpClient.cancel(httpServiceType);
    }

    /**
     * 微信登录
     *
     * @param activity
     * @param wxcode
     */
    public void weixinLogin(Activity activity, String wxcode) {
        RequestBody formBody = new FormEncodingBuilder().
                add("appid", ApiConstants.WEIXINAPP_ID).
                add("secret", ApiConstants.WX_SECRET).
                add("code", wxcode).
                add("grant_type", "authorization_code").
                build();
        postFrom(activity, ApiConstants.LOGINWEIXIN_URL, formBody, HTTPTYPE.POST, HttpServiceType.LOGINWEIXIN_TYPE);
    }

    /**
     * 获取微信登录成功后返回的个人信息
     *
     * @param activity
     * @param access_token
     * @param openId
     */
    public void weixinMsg(Activity activity, String access_token, String openId) {
        RequestBody formBody = new FormEncodingBuilder().
                add("access_token", access_token).
                add("openid", openId).
                build();
        postFrom(activity, ApiConstants.URL_MSGWEIXIN, formBody, HTTPTYPE.POST, HttpServiceType.MSGWEIXIN_TYPE);

    }
    /**
     * 获取新闻标题
     *
     * @param activity
     */
    public void newtitle(Activity activity) {
        RequestBody formBody = new FormEncodingBuilder().
                add("showapi_appid", Constants.APP_ID).
                add("showapi_sign", Constants.API_SIGN).
                add("showapi_timestamp", DateTools.getCurrentTimeNum()).
                build();
        postFrom(activity, ApiConstants.NEWLIST_URL, formBody, HTTPTYPE.POST, HttpServiceType.NEWLISTTITLETYPE);
    }

    /**
     * 获取新闻信息
     *
     * @param activity
     * @param id
     * @param page
     * @param name
     */
    public void newlist(Activity activity, String id, int page, String name) {
        RequestBody formBody = new FormEncodingBuilder().
                add("showapi_appid", Constants.APP_ID).
                add("showapi_sign", Constants.API_SIGN).
                add("showapi_timestamp", DateTools.getCurrentTimeNum()).
                add("channelId", id).
                add("channelName", name).
                add("title", "").
                add("page", String.valueOf(page)).
                build();
        postFrom(activity, ApiConstants.NEWDETAIL_URL, formBody, HTTPTYPE.POST, HttpServiceType.NEWDETAILTYPE);
    }

    /**
     * 获取图片信息
     *
     * @param activity
     * @param type
     * @param page
     */
    public void photolist(Activity activity, String type, int page) {
        RequestBody formBody = new FormEncodingBuilder().
                add("showapi_appid", Constants.APP_ID).
                add("showapi_sign", Constants.API_SIGN).
                add("showapi_timestamp", DateTools.getCurrentTimeNum()).
                add("type", type).
                add("page", String.valueOf(page)).
                add("num", ApiConstants.NUM).
                build();
        postFrom(activity, ApiConstants.PHOTO_URL, formBody, HTTPTYPE.POST, HttpServiceType.PHOTOTYPE);
    }

    /**
     * 音乐请求接口
     *
     * @param activity
     * @param topid
     */
    public void musliclist(Activity activity, String topid) {
        RequestBody formBody = new FormEncodingBuilder().
                add("showapi_appid", Constants.APP_ID).
                add("showapi_sign", Constants.API_SIGN).
                add("showapi_timestamp", DateTools.getCurrentTimeNum()).
                add("topid", topid).
                build();
        postFrom(activity, ApiConstants.MUSIC_URL, formBody, HTTPTYPE.POST, HttpServiceType.MUSICTYPE);

    }
}
