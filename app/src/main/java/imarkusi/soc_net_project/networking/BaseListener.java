package imarkusi.soc_net_project.networking;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
public interface BaseListener<T> {

    void onSuccess(T response);

    void onFailure(String errorMessage);
}
