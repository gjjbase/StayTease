package gjj.staytease.com.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 *
 * @author Hy
 */
@SuppressLint("SimpleDateFormat")
public class DateTools {


    // 获取当前时间
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = formatter.format(System.currentTimeMillis());// 获取当前时间
        return str;
    }

    //    字符类
//    [abc]	a, b 或 c（简单类）
//            [^abc]	除 a, b 或 c 之外的任意字符（取反）
//            [a-zA-Z]	a 到 z，或 A 到 Z，包括（范围）
//            [a-d[m-p]]	a 到 d，或 m 到 p：[a-dm-p]（并集）
//            [a-z&&[def]]	d，e 或 f（交集）
//            [a-z&&[^bc]]	除 b 和 c 之外的 a 到 z 字符：[ad-z]（差集）
//            [a-z&&[^m-p]]	a 到 z，并且不包括 m 到 p：[a-lq-z]（差集）
    public static String getCurrentTimeNum() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(DateTools.getCurrentTime());
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find())
            stringBuffer.append(matcher.group().toString());
        return stringBuffer.toString();
    }

}
