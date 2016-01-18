package imarkusi.soc_net_project.activities;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import imarkusi.soc_net_project.R;
import imarkusi.soc_net_project.dagger.components.DaggerLoginComponent;
import imarkusi.soc_net_project.dagger.modules.LoginModule;
import imarkusi.soc_net_project.helpers.PreferencesHelper;
import imarkusi.soc_net_project.mvp.presenters.LoginPresenter;
import imarkusi.soc_net_project.mvp.views.LoginView;
import timber.log.Timber;

/**
 * Created by markusi on 16/01/16.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenter presenter;

    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(final LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                    presenter.login(loginResult.getAccessToken().getToken());
                }
            });
            request.setVersion(getString(R.string.facebook_api_version));
            request.executeAsync();
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onError(FacebookException error) {
            Timber.e(error.getMessage());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onLoginSuccessful() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.button_login)
    void login() {
        if (AccessToken.getCurrentAccessToken() != null && AccessToken.getCurrentAccessToken().getToken() != null) {
            PreferencesHelper.saveFacebookToken(AccessToken.getCurrentAccessToken().getToken());
            presenter.login(AccessToken.getCurrentAccessToken().getToken());
        } else {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email", "user_likes"));
        }
    }
}
