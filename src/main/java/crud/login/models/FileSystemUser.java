package crud.login.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileSystemUser implements IUser {
  public FileSystemUser(String username, String password){
    this.username = username;
    this.password = password;
  }

  private String id;
  private String username;
  private String password;
  private IRole role;
  private String firstName;
  private String lastName;

  public boolean equalsByLoginData(FileSystemUser user){
    return this.username.equals(user.getUsername())
      && this.password.equals(user.getPassword());
  }
}
