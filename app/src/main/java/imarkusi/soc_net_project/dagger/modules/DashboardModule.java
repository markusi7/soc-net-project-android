package imarkusi.soc_net_project.dagger.modules;

import dagger.Module;
import dagger.Provides;
import imarkusi.soc_net_project.mvp.interactors.DashboardInteractor;
import imarkusi.soc_net_project.mvp.interactors.impl.DashboardInteractorImpl;
import imarkusi.soc_net_project.mvp.presenters.DashboardPresenter;
import imarkusi.soc_net_project.mvp.presenters.impl.DashboardPresenterImpl;
import imarkusi.soc_net_project.mvp.views.DashboardView;

/**
 * Created by markusi on 18/01/16.
 */
@Module
public class DashboardModule {

    final DashboardView view;

    public DashboardModule(DashboardView view) {
        this.view = view;
    }

    @Provides
    DashboardView provideView(){
        return view;
    }

    @Provides
    public DashboardPresenter providePresenter(DashboardPresenterImpl presenter){
        return presenter;
    }

    @Provides
    public DashboardInteractor provideInteractor(){
        return new DashboardInteractorImpl();
    }
}
