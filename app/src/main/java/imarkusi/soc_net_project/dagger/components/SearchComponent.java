package imarkusi.soc_net_project.dagger.components;

import dagger.Component;
import imarkusi.soc_net_project.activities.SearchActivity;
import imarkusi.soc_net_project.dagger.modules.SearchModule;

/**
 * Created by markusi on 18/01/16.
 */
@Component(modules = SearchModule.class)
public interface SearchComponent {

    void inject(SearchActivity searchActivity);
}
