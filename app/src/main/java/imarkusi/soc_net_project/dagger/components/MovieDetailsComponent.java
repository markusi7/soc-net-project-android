package imarkusi.soc_net_project.dagger.components;

import dagger.Component;
import imarkusi.soc_net_project.activities.MovieDetailsActivity;
import imarkusi.soc_net_project.dagger.modules.MovieDetailsModule;

/**
 * Created by markusi on 18/01/16.
 */
@Component(modules = MovieDetailsModule.class)
public interface MovieDetailsComponent {

    void inject(MovieDetailsActivity movieDetailsActivity);
}
