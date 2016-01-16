package imarkusi.soc_net_project.networking.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
public class GsonUtils {

    private GsonUtils() {
    }

    public static Gson getGson() {
        return new GsonBuilder().create();
    }
}
