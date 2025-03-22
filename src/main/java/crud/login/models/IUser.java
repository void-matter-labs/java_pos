package crud.login.models;

public interface IUser {
  String getId();
  String getUsername();
  String getPassword();
  IRole getRole();
  String getFirstName();
  String getLastName();

  public boolean equalsByLoginData(FileSystemUser user);

  void setUsername(String username);
  void setPassword(String password);
  void setRole(IRole role);
  void setFirstName(String firstName);
  void setLastName(String lastName);
}
