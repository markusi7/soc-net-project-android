package imarkusi.soc_net_project.mvp.interactors;

import java.util.List;

import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.models.User;
import imarkusi.soc_net_project.networking.BaseListener;

/**
 * Created by markusi on 18/01/16.
 */
public interface DashboardInteractor extends BaseInteractor {

    void getUser(BaseListener<User> listener);

    void logout(BaseListener<Void> listener);

    void getWatchList(BaseListener<List<Movie>> listener);

    void getLikedList(BaseListener<List<Movie>> listener);
}
