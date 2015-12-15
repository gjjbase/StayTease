package gjj.staytease.com.config;

/**
 * Created by gaojiangjian on 15/11/20.
 */
public class ApiConstants {

    public static final String WEIXINAPP_ID = "wx33c87a81a8e3f999";
    //商户号
    public static final String MCH_ID = "";
    //  API密钥，在商户平台设置
    public static final String API_KEY = "";
    // 自己微信应用的 appSecret
    public static final String WX_SECRET = "d4624c36b6795d1d99dcf0547af5443d";
    /**
     * QQ登录
     */
    public static final String QQAPP_ID = "1105002668";


    /**
     * 微信登录请求地址
     */
    public static final String LOGINWEIXIN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 获取微信登录的信息
     */
    public static final String URL_MSGWEIXIN = "https://api.weixin.qq.com/sns/userinfo";
    /**
     * 新闻频道请求接口
     */
    public static String NEWLIST_TITILE = "109-34";
    public static String NEWLIST_URL = Constants.URL_CONTEXTPATH + NEWLIST_TITILE;
    /**
     * 新闻信息接口
     */
    public static String NEWDETAIL_TITLE = "109-35";
    public static String NEWDETAIL_URL = Constants.URL_CONTEXTPATH + NEWDETAIL_TITLE;
    /**
     * 图片接口
     */
    public static String NUM ="30";
    public static String PHOTO_TITLE = "819-1";
    public static String PHOTO_URL = Constants.URL_CONTEXTPATH + PHOTO_TITLE;
    /**
     * 音乐接口
     */
    public static String MUSIC_TITLE="213-4";
    public static String MUSIC_URL=Constants.URL_CONTEXTPATH + MUSIC_TITLE;

    public static final String PLAY_POS = "playing_position";//播放位置

}
