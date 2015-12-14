package gjj.staytease.com.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import gjj.staytease.com.base.BaseApplication;

/**
 * Created by gaojiangjian on 15/11/28.
 */
public class ImageTool {
    /**
     * 缩放图片
     *
     * @param bmp
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bmp) {
        return scaleBitmap(bmp, (int) (ScreenUtils.getScreenWidth(BaseApplication.context) * 0.13));
    }

    /**
     * 缩放图片
     *
     * @param bmp
     * @param size
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bmp, int size) {
        return Bitmap.createScaledBitmap(bmp, size, size, true);
    }

    /**
     * 根据文件uri缩放图片
     *
     * @param uri
     * @return
     */
    public static Bitmap scaleBitmap(String uri, int size) {
        return scaleBitmap(BitmapFactory.decodeFile(uri), size);
    }

    /**
     * 根据文件uri缩放图片
     *
     * @param uri
     * @return
     */
    private static Bitmap scaleBitmap(String uri) {
        return scaleBitmap(BitmapFactory.decodeFile(uri));
    }

    /**
     * 缩放资源图片
     *
     * @param res
     * @return
     */
    public static Bitmap scaleBitmap(int res) {
        return scaleBitmap(BitmapFactory.decodeResource(
                BaseApplication.context.getResources(), res));
    }
}
