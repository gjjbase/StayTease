package gjj.staytease.com.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gaojiangjian on 15/11/29.
 */
public class PhotoEntity implements Parcelable {

    /**
     * thumb : http://zzssa.b0.upaiyun.com/Uploads/2015-11-26/0060lm7Tgw1eyc0tv90y1j30ch0gcgmq.jpg
     * title : 我觉着爆照确实挺简单
     * url : http://www.hbmeinv.com/show-34-28450-1.html
     */

    private List<ShowapiResBodyEntity> showapi_res_body;

    public void setShowapi_res_body(List<ShowapiResBodyEntity> showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public List<ShowapiResBodyEntity> getShowapi_res_body() {
        return showapi_res_body;
    }

    public static class ShowapiResBodyEntity implements Parcelable {
        private String thumb;
        private String title;
        private String url;

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumb() {
            return thumb;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.thumb);
            dest.writeString(this.title);
            dest.writeString(this.url);
        }

        public ShowapiResBodyEntity() {
        }

        protected ShowapiResBodyEntity(Parcel in) {
            this.thumb = in.readString();
            this.title = in.readString();
            this.url = in.readString();
        }

        public static final Creator<ShowapiResBodyEntity> CREATOR = new Creator<ShowapiResBodyEntity>() {
            public ShowapiResBodyEntity createFromParcel(Parcel source) {
                return new ShowapiResBodyEntity(source);
            }

            public ShowapiResBodyEntity[] newArray(int size) {
                return new ShowapiResBodyEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.showapi_res_body);
    }

    public PhotoEntity() {
    }

    protected PhotoEntity(Parcel in) {
        this.showapi_res_body = new ArrayList<ShowapiResBodyEntity>();
        in.readList(this.showapi_res_body, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<PhotoEntity> CREATOR = new Parcelable.Creator<PhotoEntity>() {
        public PhotoEntity createFromParcel(Parcel source) {
            return new PhotoEntity(source);
        }

        public PhotoEntity[] newArray(int size) {
            return new PhotoEntity[size];
        }
    };
}
