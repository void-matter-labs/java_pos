package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import crud.login.data.FileSystemRoleDAO;
import crud.login.dtos.FileSystemRoleDTO;
import utils.BaseWriteCSVTester;

public class FileSystemRoleDAOWriteTest extends BaseWriteCSVTester {
  @Override
  protected void initialize() {
    this.setCsvPath(this.getFullCSVPath("crud/fake_data/roles.csv"));
  }

  @Test
  void addUser()  {
    FileSystemRoleDAO roleDAO = new FileSystemRoleDAO(this.getCsvPath());
    FileSystemRoleDTO newRole = new FileSystemRoleDTO("4", "new_role", "gaaaaaaaaa");
    roleDAO.addNewRole(newRole);

    List<FileSystemRoleDTO> roles = roleDAO.getAllRoles();
    assertEquals(4, roles.size());
    assertEquals("new_role", roles.get(3).getName());
  }
}
