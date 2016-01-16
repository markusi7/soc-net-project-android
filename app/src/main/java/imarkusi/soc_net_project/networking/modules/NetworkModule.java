package imarkusi.soc_net_project.networking.modules;

import dagger.Module;
import dagger.Provides;
import imarkusi.soc_net_project.networking.APIService;
import retrofit.Endpoint;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.Converter;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
@Module(includes = {
        ClientModule.class,
        GsonConverterModule.class,
        HostModule.class,
        LoggerModule.class,
        InterceptorModule.class})
public class NetworkModule {

    @Provides
    public APIService provideAPIService(Client client, Endpoint endpoint, Converter converter,
                                        RestAdapter.Log logger, RestAdapter.LogLevel logLevel, RequestInterceptor requestInterceptor) {
        RestAdapter.Builder builder = new RestAdapter.Builder().setClient(client).setEndpoint(endpoint).
                setConverter(converter).setLog(logger).setLogLevel(logLevel).setRequestInterceptor(requestInterceptor);
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(APIService.class);
    }
}