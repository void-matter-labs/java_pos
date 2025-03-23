package crud.app.login.controllers;

import java.util.logging.Logger;

import javafx.fxml.FXML;

public class LoginMainController {
  private static  final Logger logger = Logger.getLogger(LoginMainController.class.getName());
  @FXML
  private void handleLoginClick(){
    logger.info("Login button clicked");
  }
}
