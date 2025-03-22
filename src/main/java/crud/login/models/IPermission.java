package crud.login.models;

public interface IPermission {
  String getId();
  String getScope();
  String getAuthorization();

  void setScope(String scope);
  void setAuthorization(String authorization);
  void setId(String id);
}
