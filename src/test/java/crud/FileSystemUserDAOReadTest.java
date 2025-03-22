package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import crud.login.data.FileSystemUserDAO;
import crud.login.dtos.FileSystemUserDTO;

class FileSystemUserDAOReadTest {
  @Test
  void getUsers(){
    String csvPath = getClass()
      .getClassLoader()
      .getResource("crud/fake_data/users.csv").getPath();

    FileSystemUserDAO fileSystemUserDAO = new FileSystemUserDAO(csvPath);
    List<FileSystemUserDTO> users = fileSystemUserDAO.getAllUsers();


    assertEquals(3, users.size());
  }
}
