package crud.login.services;

import javafx.scene.control.TextFormatter;

public class LoginDefaultService implements ILoginService {

  @Override
  public boolean isButtonActive(String username, String password) {
    return !username.isEmpty() && !password.isEmpty();
  }

  @Override
  public boolean isUserAvailable(String username) {
    return username.equals("admin");
  }

  @Override
  public boolean login(String username, String password) {
    return username.equals("admin") && password.equals("admin");
  }

  @Override
  public TextFormatter<String> getUserNameFormatter() {
    return new TextFormatter<>(change -> {
      String newText = change.getControlNewText();
      if (newText.matches("[a-zA-Z]*")) {
        return change;
      }
      return null;
    });
  }

  @Override
  public TextFormatter<String> getPasswordFormatter() {
    return new TextFormatter<>(change -> {
      String newText = change.getControlNewText();
      if (newText.matches("[a-zA-Z0-9]*")) {
        return change;
      }
      return null;
    });
  }

}
