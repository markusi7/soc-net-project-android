package imarkusi.soc_net_project.networking;

import java.util.List;

import imarkusi.soc_net_project.models.Movie;
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
    String MOVIES = "/movies";
    String SEARCH = MOVIES + "/search";

    @POST(LOGIN)
    void loginUser(@Body LoginRequest loginBody, Callback<LoginResponse> callback);

    @GET(SEARCH)
    void search(@Query("query") String query, Callback<List<Movie>> callback);
}
