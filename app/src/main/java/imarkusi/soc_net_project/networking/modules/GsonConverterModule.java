package imarkusi.soc_net_project.networking.modules;

import dagger.Module;
import dagger.Provides;
import imarkusi.soc_net_project.networking.utilities.GsonUtils;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
@Module
public class GsonConverterModule {

    @Provides
    public Converter provideConverter() {
        return new GsonConverter(GsonUtils.getGson());
    }

}
