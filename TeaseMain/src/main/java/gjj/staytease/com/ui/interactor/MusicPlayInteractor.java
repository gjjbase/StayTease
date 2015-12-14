package gjj.staytease.com.ui.interactor;

import gjj.staytease.com.ui.view.music.MusicPlayActivity;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public interface MusicPlayInteractor {

    void sBind(MusicPlayActivity musicPlayActivity);
    void startMusic();
    void setPre();
    void setNext();
    void setSeek(int seek);
    void setItem(int item);

    void unBind(MusicPlayActivity musicPlayActivity);
}
