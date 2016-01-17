package imarkusi.soc_net_project.mvp.interactors;

import imarkusi.soc_net_project.models.api.responses.LoginResponse;
import imarkusi.soc_net_project.networking.BaseListener;

/**
 * Created by markusi on 16/01/16.
 */
public interface LoginInteractor extends BaseInteractor {

    void loginUser(String authToken, BaseListener<LoginResponse> listener);
}
