package imarkusi.soc_net_project.networking;

import java.net.UnknownHostException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
public abstract class BaseCallback<Result> implements Callback<Result> {

    private boolean isCanceled;

    @Override
    public void success(Result result, Response response) {
        if (!isCanceled) {
            onSuccess(result);
        }
    }

    @Override
    public void failure(RetrofitError error) {
        if (isCanceled) {
            return;
        }
        Throwable cause = error.getCause();
        if (cause != null) {
            Timber.e(cause.getMessage());
        }

        if (cause instanceof UnknownHostException) {
            onNetworkError(error);
        } else {
            onUnknownError(error);
        }
    }

    protected abstract void onSuccess(Result result);

    public void cancel() {
        isCanceled = true;
    }

    public void reset() {
        isCanceled = false;
    }

    public abstract void onNetworkError(RetrofitError error);

    public abstract void onUnknownError(RetrofitError error);
}