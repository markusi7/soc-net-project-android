package imarkusi.soc_net_project.networking.modules;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
@Module
public class ClientModule {

    public static final int TIMEOUT = 30;

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        return okHttpClient;
    }

    @Provides
    public Client provideClient() {
        return new OkClient(getOkHttpClient());
    }
}
