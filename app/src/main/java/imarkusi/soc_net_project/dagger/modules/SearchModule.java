package imarkusi.soc_net_project.dagger.modules;

import dagger.Module;
import dagger.Provides;
import imarkusi.soc_net_project.mvp.interactors.SearchInteractor;
import imarkusi.soc_net_project.mvp.interactors.impl.SearchInteractorImpl;
import imarkusi.soc_net_project.mvp.presenters.SearchPresenter;
import imarkusi.soc_net_project.mvp.presenters.impl.SearchPresenterImpl;
import imarkusi.soc_net_project.mvp.views.SearchView;

/**
 * Created by markusi on 18/01/16.
 */
@Module
public class SearchModule {

    private final SearchView view;

    public SearchModule(SearchView view) {
        this.view = view;
    }

    @Provides
    public SearchView provideView(){
        return view;
    }

    @Provides
    public SearchPresenter providePresenter(SearchPresenterImpl searchPresenter){
        return searchPresenter;
    }

    @Provides
    public SearchInteractor provideInteractor(){
        return new SearchInteractorImpl();
    }
}
