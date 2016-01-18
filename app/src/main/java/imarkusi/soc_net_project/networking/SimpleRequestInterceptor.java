package imarkusi.soc_net_project.networking;

import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.SocNetApp;
import imarkusi.soc_net_project.helpers.PreferencesHelper;
import retrofit.RequestInterceptor;

/**
 * Created on 04/01/16.
 *
 * @author markusi
 */
public class SimpleRequestInterceptor implements RequestInterceptor {

    public static final String AUTHORIZATION = "Authorization";

    @Override
    public void intercept(RequestFacade request) {
        String authToken = PreferencesHelper.getAuthToken();
        if (authToken != null) {
            String bearer = String.format(SocNetApp.getInstance().getString(R.string.bearer), authToken);
            request.addHeader(AUTHORIZATION, bearer);
        }
    }
}
