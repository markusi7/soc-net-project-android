package imarkusi.soc_net_project;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.facebook.FacebookSdk;

import javax.inject.Inject;

import imarkusi.soc_net_project.custom.SimpleActivityLifecycleCallbacks;
import imarkusi.soc_net_project.networking.APIService;
import imarkusi.soc_net_project.networking.components.DaggerNetworkComponent;
import timber.log.Timber;

/**
 * Created by markusi on 16/01/16.
 */
public class SocNetApp extends Application {

    private static SocNetApp instance;

    @Inject
    APIService apiService;

    public APIService getApiService() {
        return apiService;
    }

    public static SocNetApp getInstance() {
        return instance;
    }

    public static void setInstance(SocNetApp instance) {
        SocNetApp.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        DaggerNetworkComponent.create().inject(this);
        FacebookSdk.sdkInitialize(this);
        Timber.plant(new Timber.DebugTree());
        registerActivityLifecycleCallbacks(new SimpleActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            }
        });
    }
}
