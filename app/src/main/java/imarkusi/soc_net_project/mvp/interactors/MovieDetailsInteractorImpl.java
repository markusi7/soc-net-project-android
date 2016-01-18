package imarkusi.soc_net_project.mvp.interactors;

import imarkusi.soc_net_project.SocNetApp;
import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.networking.BaseListener;
import imarkusi.soc_net_project.networking.RetrofitInteractor;
import retrofit.Callback;

/**
 * Created by markusi on 18/01/16.
 */
public class MovieDetailsInteractorImpl implements MovieDetailsInteractor {

    private RetrofitInteractor<String,Movie,BaseListener<Movie>> movieInteractor =
            new RetrofitInteractor<String, Movie, BaseListener<Movie>>() {
                @Override
                protected void execute(Callback<Movie> callback, String... params) {
                    SocNetApp.getInstance().getApiService().getMovie(params[0], callback);
                }

                @Override
                protected void onSuccess(Movie movie, BaseListener<Movie> listener) {
                    listener.onSuccess(movie);
                }
            };

    @Override
    public void getMovie(String movieId, BaseListener<Movie> listener) {
        movieInteractor.execute(listener,movieId);
    }

    @Override
    public void cancel() {
        movieInteractor.cancel();
    }

    @Override
    public void reset() {
        movieInteractor.reset();
    }
}
