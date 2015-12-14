package gjj.staytease.com.ui.interactor;

import gjj.staytease.com.bean.PhotoEntity.ShowapiResBodyEntity;
import gjj.staytease.com.ui.view.photo.PhotoDetaActivity;

import java.util.ArrayList;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public interface PhotoDetaViewInteractor {
    void setMsg(PhotoDetaActivity photoDetaActivity,ArrayList<ShowapiResBodyEntity> showapiResBodyEntityList);
}
