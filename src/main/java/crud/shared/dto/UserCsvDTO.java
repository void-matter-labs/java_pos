package crud.shared.dto;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class UserCsvDTO {
  @CsvBindByName
  private String id;
  @CsvBindByName
  private String username;
  @CsvBindByName
  private String password;
  @CsvBindByName(column = "last_name")
  private String lastName;
  @CsvBindByName(column = "first_name")
  private String firstName;
}
