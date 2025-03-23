package crud.app.login.dtos;

public interface IAuthUser {
  String getUsername();
  String getPassword();
  String getId();

  void setId(String id);
  void setUsername(String username);
  void setPassword(String password);
}
