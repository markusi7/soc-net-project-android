package imarkusi.soc_net_project.mvp.interactors.impl;

import java.util.List;

import imarkusi.soc_net_project.SocNetApp;
import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.mvp.interactors.SearchInteractor;
import imarkusi.soc_net_project.networking.BaseListener;
import imarkusi.soc_net_project.networking.RetrofitInteractor;
import retrofit.Callback;

/**
 * Created by markusi on 18/01/16.
 */
public class SearchInteractorImpl extends RetrofitInteractor<String, List<Movie>, BaseListener<List<Movie>>> implements SearchInteractor {

    @Override
    protected void execute(Callback<List<Movie>> listCallback, String... params) {
        SocNetApp.getInstance().getApiService().search(params[0], listCallback);
    }

    @Override
    protected void onSuccess(List<Movie> movies, BaseListener<List<Movie>> listener) {
        listener.onSuccess(movies);
    }

    @Override
    public void search(String query, BaseListener<List<Movie>> listener) {
        execute(listener, query);
    }
}
