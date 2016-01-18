package imarkusi.soc_net_project.mvp.interactors.impl;

import java.util.List;

import imarkusi.soc_net_project.SocNetApp;
import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.models.User;
import imarkusi.soc_net_project.mvp.interactors.DashboardInteractor;
import imarkusi.soc_net_project.networking.BaseListener;
import imarkusi.soc_net_project.networking.RetrofitInteractor;
import retrofit.Callback;

/**
 * Created by markusi on 18/01/16.
 */
public class DashboardInteractorImpl implements DashboardInteractor {

    private RetrofitInteractor<Void,List<Movie>,BaseListener<List<Movie>>> likedListIneractor =
            new RetrofitInteractor<Void, List<Movie>, BaseListener<List<Movie>>>() {
                @Override
                protected void execute(Callback<List<Movie>> callback, Void... params) {
                    SocNetApp.getInstance().getApiService().getLikedList(callback);
                }

                @Override
                protected void onSuccess(List<Movie> movies, BaseListener<List<Movie>> listener) {
                    listener.onSuccess(movies);
                }
            };

    private RetrofitInteractor<Void,User,BaseListener<User>> userInteractor =
            new RetrofitInteractor<Void, User, BaseListener<User>>() {
                @Override
                protected void execute(Callback<User> callback, Void... params) {
                    SocNetApp.getInstance().getApiService().getUser(callback);
                }

                @Override
                protected void onSuccess(User user, BaseListener<User> listener) {
                    listener.onSuccess(user);
                }
            };

    private RetrofitInteractor<Void,Void,BaseListener<Void>> logoutInteractor =
            new RetrofitInteractor<Void, Void, BaseListener<Void>>() {
                @Override
                protected void execute(Callback<Void> voidCallback, Void... params) {
                    SocNetApp.getInstance().getApiService().logout(voidCallback);
                }

                @Override
                protected void onSuccess(Void aVoid, BaseListener<Void> listener) {
                    listener.onSuccess(aVoid);
                }
            };

    @Override
    public void getUser(BaseListener<User> listener) {
        userInteractor.execute(listener);
    }

    @Override
    public void logout(BaseListener<Void> listener) {
        logoutInteractor.execute(listener);
    }

    @Override
    public void getLikedList(BaseListener<List<Movie>> listener) {
        likedListIneractor.execute(listener);
    }

    @Override
    public void cancel() {
        userInteractor.cancel();
        likedListIneractor.cancel();
        logoutInteractor.cancel();
    }

    @Override
    public void reset() {
        userInteractor.reset();
        likedListIneractor.reset();
        logoutInteractor.reset();
    }
}
