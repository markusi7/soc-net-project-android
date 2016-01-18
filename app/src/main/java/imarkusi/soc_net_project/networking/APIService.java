package imarkusi.soc_net_project.networking;

import java.util.List;

import imarkusi.soc_net_project.models.Movie;
import imarkusi.soc_net_project.models.User;
import imarkusi.soc_net_project.models.api.requests.LoginRequest;
import imarkusi.soc_net_project.models.api.responses.LoginResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
public interface APIService {

    String LOGIN = "/auth/facebook/token";
    String LOGOUT = "/logout";
    String USERS = "/users/me";
    String MOVIES = "/movies";
    String SEARCH = MOVIES + "/search";
    String WATCH_LIST = MOVIES + "/watchlist";
    String WATCHED_LIST = MOVIES + "/watched";
    String RECOMMENDED = MOVIES + "/recommended";
    String LIKED_LIST = MOVIES + "/liked";

    @POST(LOGIN)
    void loginUser(@Body LoginRequest loginBody, Callback<LoginResponse> callback);

    @GET(LOGOUT)
    void logout(Callback<Void> callback);

    @GET(USERS)
    void getUser(Callback<User> callback);

    @GET(SEARCH)
    void search(@Query("query") String query, Callback<List<Movie>> callback);

    @GET(WATCH_LIST)
    void getWatchList(Callback<List<Movie>> callback);

    @GET(LIKED_LIST)
    void getLikedList(Callback<List<Movie>> callback);

    @GET(RECOMMENDED)
    void getRecommendedMovies(Callback<List<Movie>> callback);
}
