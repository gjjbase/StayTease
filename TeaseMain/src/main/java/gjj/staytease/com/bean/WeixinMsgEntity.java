package gjj.staytease.com.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojiangjian on 15/12/3.
 */
public class WeixinMsgEntity implements Parcelable {
    /**
     * openid : ojz00t99RkP6qw3A3XAxNmj0tif8
     * nickname : Hy
     * sex : 1
     * language : zh_CN
     * city : Wuhan
     * province : Hubei
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/Q3auHgzwzM7iaLsKu8uyYDtibeIicwiaibfZibOuxvxCkc2gwwasSokkXichEbalCzbFgHiaztthQUx6ESUb1kpvNa5xHJOhiaL0rbbTxYps9uBzeck0/0
     * privilege : []
     * unionid : oasR2wKzqI52vtdi8eXUgLpoDFok
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }

    public String getOpenid() {
        return openid;
    }

    public String getNickname() {
        return nickname;
    }

    public int getSex() {
        return sex;
    }

    public String getLanguage() {
        return language;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.openid);
        dest.writeString(this.nickname);
        dest.writeInt(this.sex);
        dest.writeString(this.language);
        dest.writeString(this.city);
        dest.writeString(this.province);
        dest.writeString(this.country);
        dest.writeString(this.headimgurl);
        dest.writeString(this.unionid);
        dest.writeList(this.privilege);
    }

    public WeixinMsgEntity() {
    }

    protected WeixinMsgEntity(Parcel in) {
        this.openid = in.readString();
        this.nickname = in.readString();
        this.sex = in.readInt();
        this.language = in.readString();
        this.city = in.readString();
        this.province = in.readString();
        this.country = in.readString();
        this.headimgurl = in.readString();
        this.unionid = in.readString();
        this.privilege = new ArrayList<>();
        in.readList(this.privilege, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<WeixinMsgEntity> CREATOR = new Parcelable.Creator<WeixinMsgEntity>() {
        public WeixinMsgEntity createFromParcel(Parcel source) {
            return new WeixinMsgEntity(source);
        }

        public WeixinMsgEntity[] newArray(int size) {
            return new WeixinMsgEntity[size];
        }
    };
}
