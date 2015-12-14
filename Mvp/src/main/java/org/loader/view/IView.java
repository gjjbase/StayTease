package org.loader.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.loader.presenter.IPresenter;

/**
 * View层的根接口 <br />
 * Created by qibin on 2015/11/15.
 */
public interface IView {
    /**
     * @param inflater
     * @param container
     * @return
     */
    View create(LayoutInflater inflater, ViewGroup container);

    /**
     * 当Activity的onCreate完毕后调用
     */
    void created();

    /**
     * 返回当前视图需要的layout的id
     *
     * @return
     */
    int getLayoutId();

    /**
     * 绑定Presenter
     *
     * @param presenter
     */
    void bindPresenter(IPresenter presenter);

    void showToas(String msg);
}
