package crud.login.repository;

import java.util.List;

import crud.login.models.IRole;
import crud.login.models.IUser;

public interface IUserRepository {
  public IUser getUserById(String id);
  public IUser getUserByName(String username);
  public List<IUser> listUsers();
  public List<IUser> listUsersByRoles(List<IRole> role);
  public void addUser(IUser user);
  public void updateUser(IUser user);
  public void deleteUser(IUser user);
}
