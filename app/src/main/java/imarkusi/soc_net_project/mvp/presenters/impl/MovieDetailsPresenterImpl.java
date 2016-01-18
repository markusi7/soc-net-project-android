package imarkusi.soc_net_project.mvp.presenters.impl;

import javax.inject.Inject;

import imarkusi.soc_net_project.adapters.MoviesAdapter;
import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.mvp.interactors.MovieDetailsInteractor;
import imarkusi.soc_net_project.mvp.presenters.MovieDetailsPresenter;
import imarkusi.soc_net_project.mvp.views.MovieDetailsView;
import imarkusi.soc_net_project.networking.BaseListener;

/**
 * Created by markusi on 18/01/16.
 */
public class MovieDetailsPresenterImpl implements MovieDetailsPresenter {

    final MovieDetailsView view;

    final MovieDetailsInteractor interactor;

    private BaseListener<Movie> movieListener = new BaseListener<Movie>() {
        @Override
        public void onSuccess(Movie response) {
            view.hideProgress();
            if (response.getTitle() != null && !response.getTitle().isEmpty()) {
                view.setTitle(response.getTitle());
            }
            if (response.getReleaseDate() != null && !response.getReleaseDate().isEmpty()) {
                String[] data = response.getReleaseDate().split("-");
                String date = data[2] + "." + data[1] + data[0];
                view.setDate(date);
            }
            if (response.getGenres() != null && !response.getGenres().isEmpty()) {
                view.setGenres(MoviesAdapter.generateGenresText(response.getGenres()));
            }
            if (response.getPosterUrl() != null && !response.getPosterUrl().isEmpty()) {
                view.setPoster(response.getPosterUrl());
            }
            if (response.getOverview() != null && !response.getOverview().isEmpty()) {
                view.setOverview(response.getOverview());
            }
        }

        @Override
        public void onFailure(String errorMessage) {
            view.hideProgress();
            view.showErrorMessage(errorMessage);
        }
    };

    @Inject
    public MovieDetailsPresenterImpl(MovieDetailsView view, MovieDetailsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getMovieDetails(String movieId) {
        view.showProgress();
        interactor.getMovie(movieId, movieListener);
    }

    @Override
    public void cancel() {
        interactor.cancel();
    }
}
