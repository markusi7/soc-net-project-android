package imarkusi.soc_net_project.dagger.components;

import dagger.Component;
import imarkusi.soc_net_project.activities.LoginActivity;
import imarkusi.soc_net_project.dagger.modules.LoginModule;

/**
 * Created by markusi on 17/01/16.
 */
@Component(modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);
}
