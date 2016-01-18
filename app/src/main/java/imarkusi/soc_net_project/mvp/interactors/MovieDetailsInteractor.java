package imarkusi.soc_net_project.mvp.interactors;

import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.networking.BaseListener;

/**
 * Created by markusi on 18/01/16.
 */
public interface MovieDetailsInteractor extends BaseInteractor {

    void getMovie(String movieId, BaseListener<Movie> listener);

    void postComment(String movieId, String comment, String rating, BaseListener<Void> listener);

    void removeFromWatchList(String movieId, BaseListener<Void> listener);

    void addToWatchList(String movieId, BaseListener<Void> listener);
}
