package crud.shared.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import crud.login.controllers.LoginController;
import crud.login.data.FileSystemRoleDAO;
import crud.login.data.FileSystemUserDAO;
import crud.login.repository.FileSystemUserRepository;
import crud.login.repository.IUserRepository;
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

    this
      .bind(IUserRepository.class)
      .to(FileSystemUserRepository.class);

    this
      .bind(FileSystemUserDAO.class);

    this
      .bind(FileSystemRoleDAO.class);

    this
      .bind(String.class)
      .annotatedWith(Names.named("FileSystemUserCSV"))
      .toInstance(getClass().getResource("/demo/users.csv").getPath());

  }
}
