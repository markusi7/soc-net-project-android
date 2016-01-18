package imarkusi.soc_net_project.models.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import imarkusi.soc_net_project.models.Movie;

/**
 * Created by markusi on 18/01/16.
 */
public class RecommendedMoviesResponse {

    @SerializedName("top_rated")
    private List<Movie> topRatedMovies;

    @SerializedName("recommended")
    private List<Movie> recommendedMovies;

    public List<Movie> getTopRatedMovies() {
        return topRatedMovies;
    }

    public List<Movie> getRecommendedMovies() {
        return recommendedMovies;
    }
}
