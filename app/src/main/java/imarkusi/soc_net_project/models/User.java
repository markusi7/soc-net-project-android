package imarkusi.soc_net_project.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by markusi on 18/01/16.
 */
public class User {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("facebook")
    private FacebookData facebookData;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return facebookData.id;
    }

    static class FacebookData {

        @SerializedName("id")
        private String id;
    }
}
