package imarkusi.soc_net_project.models.api.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by markusi on 16/01/16.
 */
public class LoginResponse {

    @SerializedName("authToken")
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }
}
