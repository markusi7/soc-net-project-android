package imarkusi.soc_net_project.views;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import imarkusi.soc_net_project.R;

/**
 * Created on 20/10/15.
 *
 * @author markusi
 */
public class CustomProgressDialog extends Dialog {

    @Bind(R.id.dolcela_moments_loader_text)
    TextView loaderText;

    boolean showLoaderText;

    public CustomProgressDialog(Context context) {
        super(context, R.style.TransparentAlertDialog);
        setContentView(new CustomProgressView(getContext()));
        ButterKnife.bind(this);
        setCancelable(false);
    }

    @Override
    public void show() {
        loaderText.setVisibility(showLoaderText ? View.VISIBLE : View.GONE);
        super.show();
    }

    @Override
    public void dismiss() {
        showLoaderText = false;
        super.dismiss();
    }
}