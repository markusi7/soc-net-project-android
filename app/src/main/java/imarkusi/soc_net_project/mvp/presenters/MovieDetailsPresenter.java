package imarkusi.soc_net_project.mvp.presenters;

/**
 * Created by markusi on 18/01/16.
 */
public interface MovieDetailsPresenter extends BasePresenter {

    void getMovieDetails(String movieId);

    void postComment(String movieId, String comment, String  rating);
}
