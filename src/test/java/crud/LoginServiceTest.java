package crud;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import crud.login.services.ILoginService;
import crud.login.services.LoginDefaultService;

class LoginServiceTest {
  @Test
  @Disabled("We need to mock the repository")
  void login(){
    ILoginService loginService = new LoginDefaultService();
    boolean isLogged = loginService.login("admin", "admin");

    assertTrue(isLogged);
  }

  @Test
  void isUserAvailable(){
    ILoginService loginService = new LoginDefaultService();
    boolean isUserAvailable = loginService.isUserAvailable("admin");

    assertTrue(isUserAvailable);
  }

  @Test
  void isButtonActive(){
    ILoginService loginService = new LoginDefaultService();
    boolean isButtonActive = loginService.isButtonActive("admin", "admin");

    assertTrue(isButtonActive);
  }
}
