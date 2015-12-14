package gjj.staytease.com.interactor.impl;

import com.squareup.okhttp.Response;
import gjj.staytease.com.interactor.Parser;

import java.io.IOException;

/**
 * Created by gaojiangjian on 15/11/21.
 */
public class Parserimpl<T> implements Parser<String> {
    //    onResponse回调的参数是response，一般情况下，比如我们希望获得返回的字符串，„
//            可以通过response.body().string()获取；如果希望获得返回的二进制字节数组，则调用response.body().bytes()；
//    如果你想拿到返回的inputStream，则调用response.body().byteStream()
    @Override
    public String parse(Response response) {
        String result = null;
        try {
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
