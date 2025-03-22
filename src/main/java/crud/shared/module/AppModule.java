package crud.shared.module;

import com.google.inject.AbstractModule;

import crud.login.controllers.LoginController;
import crud.login.services.ILoginService;
import crud.login.services.LoginDefaultService;

public class AppModule extends AbstractModule {
  @Override
  protected void configure() {
    this
      .bind(ILoginService.class)
      .to(LoginDefaultService.class);

    this
      .bind(LoginController.class);

  }
}
