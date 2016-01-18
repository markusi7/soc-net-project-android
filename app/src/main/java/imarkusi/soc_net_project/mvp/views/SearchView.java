package imarkusi.soc_net_project.mvp.views;

import java.util.List;

import imarkusi.soc_net_project.models.Movie;

/**
 * Created by markusi on 17/01/16.
 */
public interface SearchView extends BaseView {

    void showMovies(List<Movie> movies);

    void onNoMoviesFound();
}
