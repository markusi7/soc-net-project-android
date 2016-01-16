package imarkusi.soc_net_project.mvp.views;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
public interface BaseView {

    void showProgress();

    void hideProgress();

    void showErrorMessage(String errorMessage);
}
