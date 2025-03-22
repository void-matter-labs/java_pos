package crud;

import crud.login.data.FileSystemUserDAO;
import crud.login.dtos.FileSystemUserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileSystemUserDAOWriteTest {

  private String originalFileContent;
  private final String csvPath = getClass().getClassLoader().getResource("crud/fake_data/users.csv").getPath();

  @BeforeEach
  void loadOriginalFileContent() throws IOException {
    try (FileReader fileReader = new FileReader(csvPath)) {
      StringWriter stringWriter = new StringWriter();
      int character;
      while ((character = fileReader.read()) != -1) {
        stringWriter.write(character);
      }
      originalFileContent = stringWriter.toString();
    }
  }

  @AfterEach
  void restoreOriginalFileContent() throws IOException {
    try (FileWriter fileWriter = new FileWriter(csvPath)) {
      fileWriter.write(originalFileContent);
    }
  }

  @Test
  void addUser()  {
    FileSystemUserDAO userDAO = new FileSystemUserDAO(csvPath);
    FileSystemUserDTO newUser = new FileSystemUserDTO("4", "new_user", "new_password", "103");
    userDAO.addNewUser(newUser);

    List<FileSystemUserDTO> users = userDAO.getAllUsers();
    assertEquals(4, users.size());
    assertEquals("new_user", users.get(3).getUsername());
  }

  @Test
  void updateUser()  {
    FileSystemUserDAO userDAO = new FileSystemUserDAO(csvPath);
    FileSystemUserDTO updatedUser = new FileSystemUserDTO("2", "jane_doe_updated", "new_password456", "102");
    userDAO.updateUser(updatedUser);

    List<FileSystemUserDTO> users = userDAO.getAllUsers();
    assertEquals(3, users.size());
    assertEquals("jane_doe_updated", users.get(1).getUsername());
    assertEquals("new_password456", users.get(1).getPassword());
  }
}
