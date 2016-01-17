package imarkusi.soc_net_project.models.api.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by markusi on 17/01/16.
 */
public class LoginRequest {

    @SerializedName("access_token")
    private String accessToken;

    public LoginRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}
