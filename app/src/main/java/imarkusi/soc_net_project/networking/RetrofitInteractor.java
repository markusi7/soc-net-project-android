package imarkusi.soc_net_project.networking;

import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.SocNetApp;
import imarkusi.soc_net_project.mvp.interactors.BaseInteractor;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
public abstract class RetrofitInteractor<Params, Result, Listener extends BaseListener> implements BaseInteractor {

    protected boolean isCanceled;

    protected Listener listener;

    private BaseCallback<Result> resultCallback = new BaseCallback<Result>() {
        @Override
        protected void onSuccess(Result result) {
            RetrofitInteractor.this.onSuccess(result, listener);
        }

        @Override
        public void onNetworkError(RetrofitError error) {
            RetrofitInteractor.this.onNetworkError(error);
        }

        @Override
        public void onUnknownError(RetrofitError error) {
            RetrofitInteractor.this.onUnknownError(error);
        }
    };

    @SafeVarargs
    public final void execute(Listener listener, Params... params) {
        reset();
        this.listener = listener;
        execute(resultCallback, params);
    }

    protected abstract void execute(Callback<Result> resultCallback, Params... params);

    protected abstract void onSuccess(Result result, Listener listener);

    protected void onUnknownError(RetrofitError error) {
        listener.onFailure(SocNetApp.getInstance().getString(R.string.error_unknown));
    }

    protected void onNetworkError(RetrofitError error) {
        listener.onFailure(SocNetApp.getInstance().getString(R.string.error_network_unavailable));
    }

    @Override
    public void cancel() {
        resultCallback.cancel();
        isCanceled = true;
    }

    @Override
    public void reset() {
        resultCallback.reset();
        isCanceled = false;
    }
}
