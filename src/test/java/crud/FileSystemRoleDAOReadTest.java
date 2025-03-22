package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import crud.login.data.FileSystemRoleDAO;
import crud.login.dtos.FileSystemRoleDTO;
import utils.BaseCSVTester;

public class FileSystemRoleDAOReadTest extends BaseCSVTester {
  @Override
  protected void initialize() {
    this.setCsvPath(this.getFullCSVPath("crud/fake_data/roles.csv"));
  }

  @Test
  void getAllRoles() {
    FileSystemRoleDAO fileSystemRoleDAO = new FileSystemRoleDAO(this.getCsvPath());
    List<FileSystemRoleDTO> roles = fileSystemRoleDAO.getAllRoles();

    assertEquals(3, roles.size());
  } 
}
