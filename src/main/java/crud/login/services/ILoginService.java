package crud.login.services;

import javafx.scene.control.TextFormatter;

public interface ILoginService {
  TextFormatter<String> getUserNameFormatter();
  TextFormatter<String> getPasswordFormatter();
  // TODO: Is this primitive obsession?
  boolean isButtonActive(String username, String password);
  boolean isUserAvailable(String username);
  boolean login(String username, String password);
}
