package imarkusi.soc_net_project.networking.modules;

import com.squareup.okhttp.OkHttpClient;

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

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    public Client provideClient() {
        return new OkClient(getOkHttpClient());
    }
}
