package imarkusi.soc_net_project.dagger.modules;

import dagger.Module;
import dagger.Provides;
import imarkusi.soc_net_project.mvp.interactors.MovieDetailsInteractor;
import imarkusi.soc_net_project.mvp.interactors.MovieDetailsInteractorImpl;
import imarkusi.soc_net_project.mvp.presenters.MovieDetailsPresenter;
import imarkusi.soc_net_project.mvp.presenters.impl.MovieDetailsPresenterImpl;
import imarkusi.soc_net_project.mvp.views.MovieDetailsView;

/**
 * Created by markusi on 18/01/16.
 */
@Module
public class MovieDetailsModule {

    private final MovieDetailsView view;

    public MovieDetailsModule(MovieDetailsView view) {
        this.view = view;
    }

    @Provides
    public MovieDetailsView provideView() {
        return view;
    }

    @Provides
    public MovieDetailsPresenter providePresenter(MovieDetailsPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    public MovieDetailsInteractor provideInteractor() {
        return new MovieDetailsInteractorImpl();
    }
}
