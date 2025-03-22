package crud.login.models;

import java.util.List;

public interface IRole {
  String getId();
  String getName();
  String getDescription();
  List<IPermission> getPermissions();

  void setName(String name);
  void setDescription(String description);
  void setPermissions(List<IPermission> permissions);
}
