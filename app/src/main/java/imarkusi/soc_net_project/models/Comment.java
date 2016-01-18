package imarkusi.soc_net_project.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by markusi on 18/01/16.
 */
public class Comment {

    @SerializedName("comment")
    private String comment;

    @SerializedName("rating")
    private float rating;

    @SerializedName("user")
    private UserComment userComment;

    public String getComment() {
        return comment;
    }

    public float getRating() {
        return rating;
    }

    public String getUser() {
        return userComment.getName();
    }

    static class UserComment {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }
    }
}
