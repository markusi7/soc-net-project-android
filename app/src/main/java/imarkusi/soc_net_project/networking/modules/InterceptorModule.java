package imarkusi.soc_net_project.networking.modules;

import dagger.Module;
import dagger.Provides;
import imarkusi.soc_net_project.networking.SimpleRequestInterceptor;
import retrofit.RequestInterceptor;

/**
 * Created on 04/01/16.
 *
 * @author markusi
 */
@Module
public class InterceptorModule {

    @Provides
    public RequestInterceptor provideInterceptor() {
        return new SimpleRequestInterceptor();
    }
}
