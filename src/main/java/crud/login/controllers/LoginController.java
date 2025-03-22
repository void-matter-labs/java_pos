package crud.login.controllers;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import crud.login.services.ILoginService;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

// TODO: singleton may be not the best fit bacuse
//this controller only helps us when we have a login page, that makes sense if
//login is something that we use too much and better memmoized this controller but
//if not how to clean this controller when Login page is not shown anymore
@Singleton
public class LoginController {
  private static final Logger logger = Logger.getLogger(LoginController.class.getName());

  private ILoginService service;

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Button loginButton;

  private final StringProperty username = new SimpleStringProperty();

  private final StringProperty password = new SimpleStringProperty();


  @Inject
  public LoginController(ILoginService service) {
    this.setService(service);
  }

  public void initialize() {
    Objects.requireNonNull(
        this.service,
        "you must set the service before initializing the controller");


    // We need a smart way to handle the remove listener in case this controller disappers
    username.bind(usernameField.textProperty());
    password.bind(passwordField.textProperty());

    usernameField.setTextFormatter(this.service.getUserNameFormatter());
    passwordField.setTextFormatter(this.service.getPasswordFormatter());

    BooleanBinding isDisabledBinding = Bindings.createBooleanBinding(
        this::areInputsInvalid,
        username,
        password);

    loginButton.disableProperty().bind(isDisabledBinding);
  }

  protected boolean areInputsInvalid() {
    return !this.service.isButtonActive(username.get(), password.get());
  }

  public LoginController setService(ILoginService service) {
    this.service = Objects.requireNonNull(service);
    return this;
  }

  @FXML
  private void handleLoginClick(MouseEvent event) throws IOException {
    if (Objects.equals(event.getButton(), MouseButton.PRIMARY)) {
      boolean isLogged = this.service.login(username.get(), password.get());
      logger.info("isLogged: " + isLogged);

      if(isLogged){
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/main_app/MainApp.fxml"));

          Parent protectedView = loader.load();

          Scene currentScene = ((javafx.scene.Node) event.getSource()).getScene();

           FadeTransition fadeOut = new FadeTransition(Duration.millis(500), currentScene.getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            // When fade-out is complete, switch the root and fade in
            fadeOut.setOnFinished(e -> {
                currentScene.setRoot(protectedView);

                // Create a fade-in transition for the new root
                FadeTransition fadeIn = new FadeTransition(Duration.millis(500), protectedView);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

          fadeOut.play();
      }
    }
  }
}
