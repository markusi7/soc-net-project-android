package imarkusi.soc_net_project.dagger.components;

import dagger.Component;
import imarkusi.soc_net_project.dagger.modules.DashboardModule;
import imarkusi.soc_net_project.fragments.DashboardFragment;

/**
 * Created by markusi on 18/01/16.
 */
@Component(modules = DashboardModule.class)
public interface DashboardComponent {

    void inject(DashboardFragment dashboardFragment);
}
