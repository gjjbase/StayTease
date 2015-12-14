package gjj.staytease.com.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gaojiangjian on 15/12/5.
 */
public class QQEntity implements Parcelable {
    /**
     * ret : 0
     * msg :
     * is_lost : 0
     * nickname : De
     * gender : 男
     * province : 湖北
     * city : 荆州
     * figureurl : http://qzapp.qlogo.cn/qzapp/1105002668/D7AA6A91C5502535D685CB2428D2C70F/30
     * figureurl_1 : http://qzapp.qlogo.cn/qzapp/1105002668/D7AA6A91C5502535D685CB2428D2C70F/50
     * figureurl_2 : http://qzapp.qlogo.cn/qzapp/1105002668/D7AA6A91C5502535D685CB2428D2C70F/100
     * figureurl_qq_1 : http://q.qlogo.cn/qqapp/1105002668/D7AA6A91C5502535D685CB2428D2C70F/40
     * figureurl_qq_2 : http://q.qlogo.cn/qqapp/1105002668/D7AA6A91C5502535D685CB2428D2C70F/100
     * is_yellow_vip : 0
     * vip : 0
     * yellow_vip_level : 0
     * level : 0
     * is_yellow_year_vip : 0
     */

    private int ret;
    private String msg;
    private int is_lost;
    private String nickname;
    private String gender;
    private String province;
    private String city;
    private String figureurl;
    private String figureurl_1;
    private String figureurl_2;
    private String figureurl_qq_1;
    private String figureurl_qq_2;
    private String is_yellow_vip;
    private String vip;
    private String yellow_vip_level;
    private String level;
    private String is_yellow_year_vip;

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setIs_lost(int is_lost) {
        this.is_lost = is_lost;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFigureurl(String figureurl) {
        this.figureurl = figureurl;
    }

    public void setFigureurl_1(String figureurl_1) {
        this.figureurl_1 = figureurl_1;
    }

    public void setFigureurl_2(String figureurl_2) {
        this.figureurl_2 = figureurl_2;
    }

    public void setFigureurl_qq_1(String figureurl_qq_1) {
        this.figureurl_qq_1 = figureurl_qq_1;
    }

    public void setFigureurl_qq_2(String figureurl_qq_2) {
        this.figureurl_qq_2 = figureurl_qq_2;
    }

    public void setIs_yellow_vip(String is_yellow_vip) {
        this.is_yellow_vip = is_yellow_vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public void setYellow_vip_level(String yellow_vip_level) {
        this.yellow_vip_level = yellow_vip_level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setIs_yellow_year_vip(String is_yellow_year_vip) {
        this.is_yellow_year_vip = is_yellow_year_vip;
    }

    public int getRet() {
        return ret;
    }

    public String getMsg() {
        return msg;
    }

    public int getIs_lost() {
        return is_lost;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGender() {
        return gender;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getFigureurl() {
        return figureurl;
    }

    public String getFigureurl_1() {
        return figureurl_1;
    }

    public String getFigureurl_2() {
        return figureurl_2;
    }

    public String getFigureurl_qq_1() {
        return figureurl_qq_1;
    }

    public String getFigureurl_qq_2() {
        return figureurl_qq_2;
    }

    public String getIs_yellow_vip() {
        return is_yellow_vip;
    }

    public String getVip() {
        return vip;
    }

    public String getYellow_vip_level() {
        return yellow_vip_level;
    }

    public String getLevel() {
        return level;
    }

    public String getIs_yellow_year_vip() {
        return is_yellow_year_vip;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ret);
        dest.writeString(this.msg);
        dest.writeInt(this.is_lost);
        dest.writeString(this.nickname);
        dest.writeString(this.gender);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.figureurl);
        dest.writeString(this.figureurl_1);
        dest.writeString(this.figureurl_2);
        dest.writeString(this.figureurl_qq_1);
        dest.writeString(this.figureurl_qq_2);
        dest.writeString(this.is_yellow_vip);
        dest.writeString(this.vip);
        dest.writeString(this.yellow_vip_level);
        dest.writeString(this.level);
        dest.writeString(this.is_yellow_year_vip);
    }

    public QQEntity() {
    }

    protected QQEntity(Parcel in) {
        this.ret = in.readInt();
        this.msg = in.readString();
        this.is_lost = in.readInt();
        this.nickname = in.readString();
        this.gender = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.figureurl = in.readString();
        this.figureurl_1 = in.readString();
        this.figureurl_2 = in.readString();
        this.figureurl_qq_1 = in.readString();
        this.figureurl_qq_2 = in.readString();
        this.is_yellow_vip = in.readString();
        this.vip = in.readString();
        this.yellow_vip_level = in.readString();
        this.level = in.readString();
        this.is_yellow_year_vip = in.readString();
    }

    public static final Parcelable.Creator<QQEntity> CREATOR = new Parcelable.Creator<QQEntity>() {
        public QQEntity createFromParcel(Parcel source) {
            return new QQEntity(source);
        }

        public QQEntity[] newArray(int size) {
            return new QQEntity[size];
        }
    };
}
