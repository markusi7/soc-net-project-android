package imarkusi.soc_net_project.models.api.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by markusi on 19/01/16.
 */
public class BlankBody {

    @SerializedName("y_i_do_dis")
    private String blank;

    public BlankBody(String blank) {
        this.blank = blank;
    }
}
