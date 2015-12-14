package org.loader.view;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.loader.presenter.IPresenter;

import butterknife.ButterKnife;

/**
 * View层的基类
 * Created by qibin on 2015/11/15.
 */
public abstract class ViewImpl implements IView {

    /**
     * create方法生成的view
     */
    protected View mRootView;
    /**
     * 绑定的presenter
     */
    protected IPresenter mPresenter;

    @Override
    public View create(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        created();
        return mRootView;
    }

    @Override
    public void created() {

    }

    @Override
    public void bindPresenter(IPresenter presenter) {
        mPresenter = presenter;
    }

    /**
     * show toast
     *
     * @param msg
     */
    public void showToas(String msg) {
        //防止遮盖虚拟按键
        if (null != msg && !TextUtils.isEmpty(msg)) {

            Snackbar.make(mRootView.getRootView(), msg, Snackbar.LENGTH_SHORT).show();
        }
    }
}
