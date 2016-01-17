package imarkusi.soc_net_project.networking;

import imarkusi.soc_net_project.models.api.requests.LoginRequest;
import imarkusi.soc_net_project.models.api.responses.LoginResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
public interface APIService {

    String LOGIN = "/auth/facebook/token";

    @POST(LOGIN)
    void loginUser(@Body LoginRequest loginBody, Callback<LoginResponse> callback);
}
