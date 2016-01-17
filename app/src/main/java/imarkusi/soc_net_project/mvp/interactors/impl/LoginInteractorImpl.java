package imarkusi.soc_net_project.mvp.interactors.impl;

import imarkusi.soc_net_project.SocNetApp;
import imarkusi.soc_net_project.models.api.requests.LoginRequest;
import imarkusi.soc_net_project.models.api.responses.LoginResponse;
import imarkusi.soc_net_project.mvp.interactors.LoginInteractor;
import imarkusi.soc_net_project.networking.BaseListener;
import imarkusi.soc_net_project.networking.RetrofitInteractor;
import retrofit.Callback;

/**
 * Created by markusi on 17/01/16.
 */
public class LoginInteractorImpl extends RetrofitInteractor<String, LoginResponse, BaseListener<LoginResponse>> implements LoginInteractor {

    @Override
    public void loginUser(String authToken, BaseListener<LoginResponse> listener) {
        execute(listener, authToken);
    }

    @Override
    protected void execute(Callback<LoginResponse> userCallback, String... params) {
        SocNetApp.getInstance().getApiService().loginUser(new LoginRequest(params[0]), userCallback);
    }

    @Override
    protected void onSuccess(LoginResponse loginResponse, BaseListener<LoginResponse> listener) {
        listener.onSuccess(loginResponse);
    }
}
