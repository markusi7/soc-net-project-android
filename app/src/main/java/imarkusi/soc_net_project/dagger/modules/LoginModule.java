package imarkusi.soc_net_project.dagger.modules;

import dagger.Module;
import dagger.Provides;
import imarkusi.soc_net_project.mvp.interactors.LoginInteractor;
import imarkusi.soc_net_project.mvp.interactors.impl.LoginInteractorImpl;
import imarkusi.soc_net_project.mvp.presenters.LoginPresenter;
import imarkusi.soc_net_project.mvp.presenters.impl.LoginPresenterImpl;
import imarkusi.soc_net_project.mvp.views.LoginView;

/**
 * Created by markusi on 17/01/16.
 */
@Module
public class LoginModule {

    private final LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    public LoginView provideView(){
        return view;
    }

    @Provides
    public LoginPresenter providePresenter(LoginPresenterImpl loginPresenter){
        return loginPresenter;
    }

    @Provides
    public LoginInteractor provideInteractor(){
        return new LoginInteractorImpl();
    }
}
