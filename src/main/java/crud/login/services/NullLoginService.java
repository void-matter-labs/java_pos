package crud.login.services;

import javafx.scene.control.TextFormatter;

public class NullLoginService implements ILoginService {

  @Override
  public TextFormatter<String> getUserNameFormatter() {
    throw new UnsupportedOperationException("Unimplemented method 'getUserNameFormatter'");
  }

  @Override
  public TextFormatter<String> getPasswordFormatter() {
    throw new UnsupportedOperationException("Unimplemented method 'getPasswordFormatter'");
  }

  @Override
  public boolean isButtonActive(String username, String password) {
    throw new UnsupportedOperationException("Unimplemented method 'isButtonActive'");
  }

  @Override
  public boolean isUserAvailable(String username) {
    throw new UnsupportedOperationException("Unimplemented method 'isUserAvailable'");
  }

  @Override
  public boolean login(String username, String password) {
    throw new UnsupportedOperationException("Unimplemented method 'login'");
  }

}
