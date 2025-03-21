package crud.login.controllers;

import java.util.Objects;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import crud.login.services.ILoginService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

@Singleton
public class LoginController {
  private static final Logger logger = Logger.getLogger(LoginController.class.getName());

  protected static TextFormatter.Change defaultValidateUsernameChange(TextFormatter.Change change) {
    if (change.getControlNewText().length() > 10) {
      return null;
    }
    return change;
  }

  protected static TextFormatter.Change defaultValidatePasswordChange(TextFormatter.Change change) {
    if (change.getControlNewText().length() > 15) {
      return null;
    }
    return change;
  }

  private ILoginService service;

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Button loginButton;

  private final StringProperty username = new SimpleStringProperty();
  private final StringProperty password = new SimpleStringProperty();

  private TextFormatter<String> userNameFormatter = new TextFormatter<>(LoginController::defaultValidateUsernameChange);
  private TextFormatter<String> passwordFormatter = new TextFormatter<>(LoginController::defaultValidatePasswordChange);

  @Inject
  public LoginController(ILoginService service) {
    this.setService(service);
  }

  public void initialize() {
    Objects.requireNonNull(
        this.service,
        "you must set the service before initializing the controller");

    username.bind(usernameField.textProperty());
    password.bind(passwordField.textProperty());

    usernameField.setTextFormatter(this.userNameFormatter);
    passwordField.setTextFormatter(this.passwordFormatter);

    BooleanBinding isDisabledBinding = Bindings.createBooleanBinding(
        this::areInputsInvalid,
        username,
        password);

    loginButton.disableProperty().bind(isDisabledBinding);
  }

  protected boolean areInputsInvalid() {
    return username.get().isEmpty() || password.get().isEmpty() || password.get().length() < 6;
  }

  public LoginController setService(ILoginService service) {
    this.service = Objects.requireNonNull(service);
    return this;
  }

  @FXML
  private void handleLoginClick(MouseEvent event) {
    if (Objects.equals(event.getButton(), MouseButton.PRIMARY)) {
      logger.info("Login button clicked!");
    }
  }
}
