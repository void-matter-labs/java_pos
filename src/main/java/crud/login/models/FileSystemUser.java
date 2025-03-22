package crud.login.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileSystemUser implements IUser {
  private String id;
  private String username;
  private String password;
  private IRole role;
  private String firstName;
  private String lastName;


}
