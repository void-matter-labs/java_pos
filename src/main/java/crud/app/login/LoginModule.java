package crud.app.login;

import com.google.inject.AbstractModule;

import crud.app.login.controllers.LoginMainController;

public class LoginModule extends AbstractModule{
  @Override
  protected void configure() {
    bind(LoginMainController.class);
  }
}
