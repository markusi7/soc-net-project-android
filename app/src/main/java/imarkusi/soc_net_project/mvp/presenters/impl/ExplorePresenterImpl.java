package imarkusi.soc_net_project.mvp.presenters.impl;

import javax.inject.Inject;

import imarkusi.soc_net_project.models.api.responses.RecommendedMoviesResponse;
import imarkusi.soc_net_project.mvp.interactors.ExploreInteractor;
import imarkusi.soc_net_project.mvp.presenters.ExplorePresenter;
import imarkusi.soc_net_project.mvp.views.ExploreView;
import imarkusi.soc_net_project.networking.BaseListener;

/**
 * Created by markusi on 18/01/16.
 */
public class ExplorePresenterImpl implements ExplorePresenter {

    final ExploreView view;

    final ExploreInteractor interactor;

    private BaseListener<RecommendedMoviesResponse> moviesListener = new BaseListener<RecommendedMoviesResponse>() {

        @Override
        public void onSuccess(RecommendedMoviesResponse response) {
            view.hideProgress();
            if (response != null && !response.getTopRatedMovies().isEmpty()) {
                view.showRecommendedMovies(response.getTopRatedMovies(), response.getRecommendedMovies());
            }
        }

        @Override
        public void onFailure(String errorMessage) {
            view.hideProgress();
            view.showErrorMessage(errorMessage);
        }
    };

    @Inject
    public ExplorePresenterImpl(ExploreInteractor interactor, ExploreView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void getRecommendedMovies() {
        interactor.getRecommendedMovies(moviesListener);
    }

    @Override
    public void cancel() {
        interactor.cancel();
    }
}
