package crud.login.dtos;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileSystemRoleDTO {
  @NonNull
  @CsvBindByName  
  private String id;

  @NonNull
  @CsvBindByName
  private String name;

  @NonNull
  @CsvBindByName
  private String description;
}
