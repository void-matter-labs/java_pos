package crud.app.login.controllers;

import java.util.logging.Logger;

import com.google.inject.Inject;

import crud.app.login.application_services.LoginService;
import crud.app.login.dtos.BaseAuthUser;
import crud.app.login.dtos.IAuthUser;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginMainController {
  private final LoginService loginService;

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @Inject
  public LoginMainController(LoginService loginService) {
    this.loginService = loginService;
  }

  private static  final Logger logger = Logger.getLogger(LoginMainController.class.getName());

  @FXML
  private void handleLoginClick(MouseEvent event) throws Exception {
    logger.info("Login button clicked");
    IAuthUser user = new BaseAuthUser();
    user.setUsername(usernameField.getText());
    user.setPassword(passwordField.getText());

    String userId = loginService.login(user);

    if(userId == null){
      logger.severe("username or password is incorrect");
    }else{
      logger.info("User logged in with id: " + userId);
    }
  }
}
