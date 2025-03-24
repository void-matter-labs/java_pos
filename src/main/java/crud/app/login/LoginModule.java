package crud.app.login;

import com.google.inject.AbstractModule;

import crud.app.login.application_services.LoginService;
import crud.app.login.controllers.LoginMainController;
import crud.app.login.dtos.BaseAuthSession;
import crud.app.login.dtos.BaseAuthUser;
import crud.app.login.persistence.ILoginRepository;
import crud.app.login.persistence.LoginRepository;


public class LoginModule extends AbstractModule{
  @Override
  protected void configure() {
    bind(LoginMainController.class);

    bind(LoginService.class);

    bind(ILoginRepository.class)
      .to(LoginRepository.class);

    bind(BaseAuthSession.class);
    bind(BaseAuthUser.class);
  }
}
