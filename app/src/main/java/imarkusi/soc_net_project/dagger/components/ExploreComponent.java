package imarkusi.soc_net_project.dagger.components;

import dagger.Component;
import imarkusi.soc_net_project.dagger.modules.ExploreModule;
import imarkusi.soc_net_project.fragments.ExploreFragment;

/**
 * Created by markusi on 18/01/16.
 */
@Component(modules = ExploreModule.class)
public interface ExploreComponent {

    void inject(ExploreFragment exploreFragment);
}
