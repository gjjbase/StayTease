package gjj.staytease.com.interactor.base;

/**
 * Created by gaojiangjian on 15/11/20.
 */
public interface BaseView {

    /**
     * show loading message
     *
     * @param msg
     */
    void showLoading(String msg);

    /**
     * hide loading
     */
    void hideLoading();

    /**
     * show error message
     */
    void showError(String msg);

    /**
     * show exception message
     */
    void showException(String msg);

    /**
     * show net error
     */
    void showNetError();
}
