package crud.login.dtos;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileSystemUserDTO{
  @NonNull
  @CsvBindByName
  private String id;

  @NonNull
  @CsvBindByName
  private String username;

  @NonNull
  @CsvBindByName
  private String password;

  @NonNull
  @CsvBindByName
  private String permissionId;
}
