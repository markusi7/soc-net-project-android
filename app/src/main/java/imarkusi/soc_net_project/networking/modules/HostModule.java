package imarkusi.soc_net_project.networking.modules;

import dagger.Module;
import dagger.Provides;
import imarkusi.soc_net_project.BuildConfig;
import retrofit.Endpoint;
import retrofit.Endpoints;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
@Module
public class HostModule {

    @Provides
    public Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(BuildConfig.API_URL);
    }
}
