package crud.login.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class FileSystemPermission {
  private String id;
  private String scope;
  private String authorization;
}
