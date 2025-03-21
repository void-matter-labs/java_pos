package crud.shared.module;

import com.google.inject.AbstractModule;

import crud.login.controllers.LoginController;
import crud.login.services.ILoginService;
import crud.login.services.NullLoginService;

public class AppModule extends AbstractModule {
  @Override
  protected void configure() {
    this
      .bind(ILoginService.class)
      .to(NullLoginService.class);

    this
      .bind(LoginController.class);

  }
}
