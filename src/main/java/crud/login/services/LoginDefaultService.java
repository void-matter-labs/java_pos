package crud.login.services;

import com.google.inject.Inject;

import crud.login.models.FileSystemUser;
import crud.login.repository.IUserRepository;
import javafx.scene.control.TextFormatter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginDefaultService implements ILoginService {
  private IUserRepository userRepository;

  @Inject
  public LoginDefaultService(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean isButtonActive(String username, String password) {
    return username.length() > 4 && password.length() >4 ;
  }

  @Override
  public boolean isUserAvailable(String username) {
    return username.equals("admin");
  }

  @Override
  public boolean login(String username, String password) {
    return this
      .userRepository
      .getUserByName(username)
      .equalsByLoginData(new FileSystemUser(username, password))
    ;
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
