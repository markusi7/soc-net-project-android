package imarkusi.soc_net_project.mvp.views;

import java.util.List;

import imarkusi.soc_net_project.models.Movie;

/**
 * Created by markusi on 18/01/16.
 */
public interface ExploreView extends BaseView {

    void showRecommendedMovies(List<Movie> topRatedMovies, List<Movie> recommendedMovies);
}
