package gjj.staytease.com.interactor;

/**
 * @author bian.xd
 */
public interface PullCallback {

    void onLoadMore();

    void onRefresh();

    boolean isLoading();

    boolean hasLoadedAllItems();
}
