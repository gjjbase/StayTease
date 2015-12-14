package gjj.staytease.com.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gjj.staytease.com.config.HttpServiceType;

/**
 * Created by gaojiangjian on 15/12/3.
 */
public class GlobalsUtil {
    /**
     * json转List对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) throws Exception {
        List<T> lst = new ArrayList<T>();

        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            lst.add(new Gson().fromJson(elem, clazz));
        }

        return lst;
    }
    public static String genOutTradNo() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }
    /**
     * json转对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T fromJsonObject(String json, Class<T> clazz) throws Exception {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        return new Gson().fromJson(jsonObject, clazz);
    }

}
