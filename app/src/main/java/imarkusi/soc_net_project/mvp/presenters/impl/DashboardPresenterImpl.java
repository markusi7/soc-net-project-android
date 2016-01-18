package imarkusi.soc_net_project.mvp.presenters.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.SocNetApp;
import imarkusi.soc_net_project.helpers.PreferencesHelper;
import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.models.User;
import imarkusi.soc_net_project.mvp.interactors.DashboardInteractor;
import imarkusi.soc_net_project.mvp.presenters.DashboardPresenter;
import imarkusi.soc_net_project.mvp.views.DashboardView;
import imarkusi.soc_net_project.networking.BaseListener;

/**
 * Created by markusi on 18/01/16.
 */
public class DashboardPresenterImpl implements DashboardPresenter {

    private static final int API_CALLS = 2;
    final DashboardView view;
    final DashboardInteractor interactor;
    private AtomicInteger counter = new AtomicInteger();

    private BaseListener<User> userListener = new BaseListener<User>() {
        @Override
        public void onSuccess(User response) {
            onCallFinished();
            if (response.getId() != null && !response.getId().isEmpty()) {
                PreferencesHelper.saveUserId(response.getId());
            }
            if (response.getId() != null && !response.getId().isEmpty()) {
                String profilePictureUrl = String.format(SocNetApp.getInstance().getString(R.string.facebook_image_url), response.getId());
                view.showProfilePicture(profilePictureUrl);
            }
            if (response.getName() != null && !response.getName().isEmpty()) {
                view.showName(response.getName());
            }
            if (response.getEmail() != null && !response.getEmail().isEmpty()) {
                view.showEmail(response.getEmail());
            }
            if (response.getName() != null && !response.getName().isEmpty()) {
                view.showName(response.getName());
            }
            if (response.getWatchlist() != null && !response.getWatchlist().isEmpty()) {
                view.showWatchList(response.getWatchlist());
            }
        }

        @Override
        public void onFailure(String errorMessage) {
            onCallFinished();
            view.showErrorMessage(errorMessage);
        }
    };

    private BaseListener<List<Movie>> watchListListener = new BaseListener<List<Movie>>() {
        @Override
        public void onSuccess(List<Movie> response) {
            onCallFinished();
            if (response != null && !response.isEmpty()) {
                view.showWatchList(response);
            }
        }

        @Override
        public void onFailure(String errorMessage) {
            onCallFinished();
            view.showErrorMessage(errorMessage);
        }
    };

    private BaseListener<List<Movie>> likedListListener = new BaseListener<List<Movie>>() {
        @Override
        public void onSuccess(List<Movie> response) {
            onCallFinished();
            if (response != null && !response.isEmpty()) {
                view.showLikedList(response);
            }
        }

        @Override
        public void onFailure(String errorMessage) {
            onCallFinished();
            view.showErrorMessage(errorMessage);
        }
    };

    private BaseListener<Void> logoutListener = new BaseListener<Void>() {
        @Override
        public void onSuccess(Void response) {
            PreferencesHelper.deleteAllSharedPreferences();
            view.onLogoutSuccessful();
        }

        @Override
        public void onFailure(String errorMessage) {
            onCallFinished();
            view.showErrorMessage(errorMessage);
        }
    };

    @Inject
    public DashboardPresenterImpl(DashboardView view, DashboardInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void init() {
        view.showProgress();
        counter.set(API_CALLS);
        interactor.getUser(userListener);
        interactor.getLikedList(likedListListener);
    }

    @Override
    public void logout() {
        interactor.logout(logoutListener);
    }

    @Override
    public void cancel() {
        interactor.cancel();
    }

    private void onCallFinished() {
        if (counter.decrementAndGet() == 0) {
            view.hideProgress();
        }
    }
}
