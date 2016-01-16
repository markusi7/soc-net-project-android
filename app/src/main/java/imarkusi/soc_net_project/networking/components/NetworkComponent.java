package imarkusi.soc_net_project.networking.components;

import javax.inject.Singleton;

import dagger.Component;
import imarkusi.soc_net_project.SocNetApp;
import imarkusi.soc_net_project.networking.modules.NetworkModule;

/**
 * Created on 14/10/15.
 *
 * @author markusi
 */
@Component(modules = NetworkModule.class)
@Singleton
public interface NetworkComponent {

    void inject(SocNetApp periodDiaryApp);
}
