package imarkusi.soc_net_project.models.api.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by markusi on 18/01/16.
 */
public class CommentBody {

    @SerializedName("comment")
    private String comment;

    @SerializedName("rating")
    private float rating;

    public CommentBody(String comment, float rating) {
        this.comment = comment;
        this.rating = rating;
    }
}
