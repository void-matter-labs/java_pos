package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import crud.login.data.FileSystemUserDAO;
import crud.login.dtos.FileSystemUserDTO;
import utils.BaseCSVTester;

class FileSystemUserDAOReadTest extends BaseCSVTester {

  @Override
  protected void initialize() {
    this.setCsvPath(this.getFullCSVPath("crud/fake_data/users.csv"));
  }

  @Test
  void getUsers(){
    FileSystemUserDAO fileSystemUserDAO = new FileSystemUserDAO(this.getCsvPath());
    List<FileSystemUserDTO> users = fileSystemUserDAO.getAllUsers();

    assertEquals(3, users.size());
  }
}
