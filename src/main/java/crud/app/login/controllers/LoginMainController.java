package crud.app.login.controllers;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class LoginMainController {
  private static  final Logger logger = Logger.getLogger(LoginMainController.class.getName());
  @FXML
  private void handleLoginClick(MouseEvent event){
    logger.info("Login button clicked");

    var sourceNode = (Node) event.getSource();
    Scene scene = sourceNode.getScene();
    scene.getStylesheets().clear();
    scene.getStylesheets().add(getClass().getResource("/shared/styles/ligth_theme.css").toExternalForm());
  }
}
