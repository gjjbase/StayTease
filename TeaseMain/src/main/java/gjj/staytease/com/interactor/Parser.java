package gjj.staytease.com.interactor;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by gaojiangjian on 15/11/21.
 */
public interface Parser<T> {
    T parse(Response response) throws IOException;
}
