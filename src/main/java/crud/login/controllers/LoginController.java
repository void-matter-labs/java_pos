package crud.login.controllers;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LoginController {
  private static final Logger logger = Logger.getLogger(LoginController.class.getName());

  @FXML
  private void handleLoginClick(MouseEvent  event){
    if(!event.isPrimaryButtonDown()){
      logger.info("Login button clicked!");
    }
  }

  @FXML
  private void handleUserNameChange(){
    logger.info("Username Change");
  }

  @FXML
  private void handlePasswordChange(){
    logger.info("Password Change");
  }
}
