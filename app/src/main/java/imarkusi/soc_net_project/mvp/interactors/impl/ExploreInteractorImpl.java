package imarkusi.soc_net_project.mvp.interactors.impl;

import imarkusi.soc_net_project.SocNetApp;
import imarkusi.soc_net_project.models.api.responses.RecommendedMoviesResponse;
import imarkusi.soc_net_project.mvp.interactors.ExploreInteractor;
import imarkusi.soc_net_project.networking.BaseListener;
import imarkusi.soc_net_project.networking.RetrofitInteractor;
import retrofit.Callback;

/**
 * Created by markusi on 18/01/16.
 */
public class ExploreInteractorImpl extends RetrofitInteractor<Void, RecommendedMoviesResponse, BaseListener<RecommendedMoviesResponse>> implements ExploreInteractor {

    @Override
    protected void execute(Callback<RecommendedMoviesResponse> recommendedMoviesResponseCallback, Void... params) {
        SocNetApp.getInstance().getApiService().getRecommendedMovies(recommendedMoviesResponseCallback);
    }

    @Override
    public void getRecommendedMovies(BaseListener<RecommendedMoviesResponse> listener) {
        execute(listener);
    }

    @Override
    protected void onSuccess(RecommendedMoviesResponse recommendedMoviesResponse, BaseListener<RecommendedMoviesResponse> listener) {
        listener.onSuccess(recommendedMoviesResponse);
    }
}
