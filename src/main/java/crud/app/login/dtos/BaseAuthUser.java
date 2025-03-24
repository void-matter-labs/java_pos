package crud.app.login.dtos;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;


@Data
public class BaseAuthUser implements IAuthUser {
  @CsvBindByName
  protected String id;
  @CsvBindByName
  protected String username;
  @CsvBindByName
  protected String password;
}
