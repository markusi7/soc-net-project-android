package imarkusi.soc_net_project.mvp.presenters.impl;

import java.util.List;

import javax.inject.Inject;

import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.mvp.interactors.SearchInteractor;
import imarkusi.soc_net_project.mvp.presenters.SearchPresenter;
import imarkusi.soc_net_project.mvp.views.SearchView;
import imarkusi.soc_net_project.networking.BaseListener;

/**
 * Created by markusi on 18/01/16.
 */
public class SearchPresenterImpl implements SearchPresenter {

    final SearchView view;

    final SearchInteractor interactor;

    private BaseListener<List<Movie>> listener = new BaseListener<List<Movie>>() {
        @Override
        public void onSuccess(List<Movie> response) {
            view.hideProgress();
            view.showMovies(response);
        }

        @Override
        public void onFailure(String errorMessage) {
            view.hideProgress();
            view.showErrorMessage(errorMessage);
        }
    };

    @Inject
    public SearchPresenterImpl(SearchView view, SearchInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void search(String query) {
        view.showProgress();
        interactor.search(query, listener);
    }
}
