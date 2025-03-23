package crud.app.login.dtos;

import lombok.Data;


@Data
public class BaseAuthUser implements IAuthUser {
  protected String id;
  protected String username;
  protected String password;
}
