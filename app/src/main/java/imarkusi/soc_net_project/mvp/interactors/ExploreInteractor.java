package imarkusi.soc_net_project.mvp.interactors;

import imarkusi.soc_net_project.models.api.responses.RecommendedMoviesResponse;
import imarkusi.soc_net_project.networking.BaseListener;

/**
 * Created by markusi on 18/01/16.
 */
public interface ExploreInteractor extends BaseInteractor {

    void getRecommendedMovies(BaseListener<RecommendedMoviesResponse> listener);
}
