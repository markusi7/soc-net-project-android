package imarkusi.soc_net_project.mvp.presenters.impl;

import javax.inject.Inject;

import imarkusi.soc_net_project.helpers.PreferencesHelper;
import imarkusi.soc_net_project.models.api.responses.LoginResponse;
import imarkusi.soc_net_project.mvp.interactors.LoginInteractor;
import imarkusi.soc_net_project.mvp.presenters.LoginPresenter;
import imarkusi.soc_net_project.mvp.views.LoginView;
import imarkusi.soc_net_project.networking.BaseListener;

/**
 * Created by markusi on 17/01/16.
 */
public class LoginPresenterImpl implements LoginPresenter {

    final LoginView view;

    final LoginInteractor interactor;

    private BaseListener<LoginResponse> listener = new BaseListener<LoginResponse>() {
        @Override
        public void onSuccess(LoginResponse response) {
            PreferencesHelper.saveAuthToken(response.getAuthToken());
            view.onLoginSuccessful();
            view.hideProgress();
        }

        @Override
        public void onFailure(String errorMessage) {
            view.hideProgress();
            view.showErrorMessage(errorMessage);
        }
    };

    @Inject
    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void login(String authToken) {
        view.showProgress();
        interactor.loginUser(authToken, listener);
    }

    @Override
    public void cancel() {
        interactor.cancel();
    }
}
