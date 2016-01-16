package imarkusi.soc_net_project.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import imarkusi.soc_net_project.activities.BaseActivity;
import imarkusi.soc_net_project.mvp.views.BaseView;

/**
 * Created on 14/08/15.
 *
 * @author markusi
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    BaseActivity baseActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof BaseActivity) {
            baseActivity = (BaseActivity) getActivity();
        } else {
            throw new ClassCastException("Base Fragment should be instantiated in Base Activity!");
        }
    }

    @Override
    public void showProgress() {
        if (!isDetached()) {
            baseActivity.showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (!isDetached()) {
            baseActivity.hideProgress();
        }
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        if (!isDetached()) {
            baseActivity.showErrorMessage(errorMessage);
        }
    }
}