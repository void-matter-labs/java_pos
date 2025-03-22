package crud.login.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import crud.login.data.FileSystemRoleDAO;
import crud.login.data.FileSystemUserDAO;
import crud.login.dtos.FileSystemUserDTO;
import crud.login.models.IRole;
import crud.login.models.IUser;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
public class FileSystemUserRepository implements IUserRepository {
  @Setter
  @NonNull
  private FileSystemUserDAO userDAO;

  @Setter
  @NonNull
  private FileSystemRoleDAO roleDAO;

  @Inject
  public FileSystemUserRepository(
    FileSystemUserDAO userDAO,
    FileSystemRoleDAO roleDAO
  ){
    this.userDAO = Objects.requireNonNull(userDAO);
    this.roleDAO = Objects.requireNonNull(roleDAO);
  }

  public IUser getUserById(String id) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public IUser getUserByName(String username) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public List<IUser> listUsers() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public List<IUser> listUsersByRoles(List<IRole> roles) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public void addUser(IUser user) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public void updateUser(IUser user) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public void deleteUser(IUser user) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
