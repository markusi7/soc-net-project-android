package imarkusi.soc_net_project.mvp.interactors;

import java.util.List;

import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.networking.BaseListener;

/**
 * Created by markusi on 18/01/16.
 */
public interface SearchInteractor {

    void search(String query, BaseListener<List<Movie>> listener);
}
