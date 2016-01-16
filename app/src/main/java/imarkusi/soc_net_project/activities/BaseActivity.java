package imarkusi.soc_net_project.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.mvp.views.BaseView;
import imarkusi.soc_net_project.views.CustomProgressDialog;

/**
 * Created on 17/08/15.
 *
 * @author markusi
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected CustomProgressDialog progressDialog;
    private AlertDialog errorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new CustomProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void showProgress() {
        if (!isFinishing() && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (!isFinishing() && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        if (!isFinishing()) {
            if (errorDialog == null) {
                errorDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.error)
                        .setMessage(errorMessage)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();

            } else if (!errorDialog.isShowing()) {
                errorDialog.setMessage(errorMessage);
            } else {
                return; // Don't want to override or show double error dialog
            }
            errorDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }
}
