package crud;

import crud.login.data.FileSystemUserDAO;
import crud.login.dtos.FileSystemUserDTO;
import utils.BaseWriteCSVTester;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileSystemUserDAOWriteTest extends BaseWriteCSVTester {

  @Override
  protected void initialize() {
    this.setCsvPath(this.getFullCSVPath("crud/fake_data/users.csv"));
  }

  @Test
  void addUser()  {
    FileSystemUserDAO userDAO = new FileSystemUserDAO(this.getCsvPath());
    FileSystemUserDTO newUser = new FileSystemUserDTO("4", "new_user", "new_password", "103");
    userDAO.addNewUser(newUser);

    List<FileSystemUserDTO> users = userDAO.getAllUsers();
    assertEquals(4, users.size());
    assertEquals("new_user", users.get(3).getUsername());
  }

  @Test
  void updateUser()  {
    FileSystemUserDAO userDAO = new FileSystemUserDAO(this.getCsvPath());
    FileSystemUserDTO updatedUser = new FileSystemUserDTO("2", "jane_doe_updated", "new_password456", "102");
    userDAO.updateUser(updatedUser);

    List<FileSystemUserDTO> users = userDAO.getAllUsers();
    assertEquals(3, users.size());
    assertEquals("jane_doe_updated", users.get(1).getUsername());
    assertEquals("new_password456", users.get(1).getPassword());
  }
}
