package imarkusi.soc_net_project.networking.modules;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import timber.log.Timber;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
@Module
public class LoggerModule implements RestAdapter.Log {

    @Override
    public void log(String message) {
        Timber.d(message);
    }

    @Provides
    public RestAdapter.Log provideLogger() {
        return this;
    }

    @Provides
    public RestAdapter.LogLevel provideLogLevel() {
        return RestAdapter.LogLevel.FULL;
    }
}
