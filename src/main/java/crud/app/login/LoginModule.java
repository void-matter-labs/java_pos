package crud.app.login;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import crud.app.login.application_services.LoginService;
import crud.app.login.controllers.LoginMainController;
import crud.app.login.persistence.ILoginRepository;
import crud.app.login.persistence.NullLoginRepository;
import crud.shared.constants.GuiceNames;

public class LoginModule extends AbstractModule{
  @Override
  protected void configure() {
    bind(LoginMainController.class)
      .annotatedWith(Names.named(GuiceNames.MAIN_CONTROLLER.name()))
      .to(LoginMainController.class);

    bind(LoginService.class);

    bind(ILoginRepository.class)
      .to(NullLoginRepository.class);
  }
}
