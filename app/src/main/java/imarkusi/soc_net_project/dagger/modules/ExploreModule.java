package imarkusi.soc_net_project.dagger.modules;

import dagger.Module;
import dagger.Provides;
import imarkusi.soc_net_project.mvp.interactors.ExploreInteractor;
import imarkusi.soc_net_project.mvp.interactors.impl.ExploreInteractorImpl;
import imarkusi.soc_net_project.mvp.presenters.ExplorePresenter;
import imarkusi.soc_net_project.mvp.presenters.impl.ExplorePresenterImpl;
import imarkusi.soc_net_project.mvp.views.ExploreView;

/**
 * Created by markusi on 18/01/16.
 */
@Module
public class ExploreModule {

    final ExploreView view;

    public ExploreModule(ExploreView view) {
        this.view = view;
    }

    @Provides
    ExploreView provideView(){
        return view;
    }

    @Provides
    public ExplorePresenter providePresenter(ExplorePresenterImpl presenter){
        return presenter;
    }

    @Provides
    public ExploreInteractor provideInteractor(){
        return new ExploreInteractorImpl();
    }
}
