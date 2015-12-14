package gjj.staytease.com.ui.interactor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import gjj.staytease.com.bean.WeixinMsgEntity;
import gjj.staytease.com.ui.view.MainActivity;

/**
 * Created by gaojiangjian on 15/11/27.
 */
public interface MainViewInteractor {


    void setMsg(MainActivity mainActivity,int currentTab,int id);
    <T>  void setMsg(MainActivity mainActivity,T t);

    void showTab(int idx, MainActivity mainActivity);

    FragmentTransaction obtainFragmentTransaction(MainActivity activity,int index);

    Fragment getCurrentFragment();
}
