package imarkusi.soc_net_project.mvp.views;

import java.util.List;

import imarkusi.soc_net_project.models.Movie;

/**
 * Created by markusi on 18/01/16.
 */
public interface DashboardView extends BaseView {

    void showName(String username);

    void showEmail(String email);

    void showProfilePicture(String profilePictureUrl);

    void showWatchList(List<Movie> movies);

    void showLikedList(List<Movie> movies);

    void onLogoutSuccessful();

    void onWatchListEmpty();
}
