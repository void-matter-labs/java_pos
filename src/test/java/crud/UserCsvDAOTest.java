package crud;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.StringReader;

import crud.app.login.dtos.IAuthUser;
import crud.shared.dto.UserCsvDTO;
import crud.shared.persistence.dao.UserCsvDAO;
import crud.shared.persistence.datasource.ICSVConnectionManager;
import crud.shared.persistence.datasource.IReaderAction;

class UserCsvDAOTest {
  @Mock
  private ICSVConnectionManager csvConnectionManager;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getsUser() throws Exception {
    csvConnectionManager = mock(ICSVConnectionManager.class);

    String fakeCsvData = """
        id,username,password,last_name,first_name
        1,johndoe,secret123,doe,john
        2,janedoe,password456,doe,jane
        """;

    when(csvConnectionManager.executeWithReader(eq("users"), any()))
      .thenAnswer(invocation -> {

        return invocation
          .<IReaderAction<IAuthUser>>getArgument(1)
          .execute(new StringReader(fakeCsvData));
      });

    UserCsvDAO userCsvDAO = new UserCsvDAO(csvConnectionManager);

    UserCsvDTO user = userCsvDAO.getUserByUserName("johndoe");

    assertEquals("johndoe", user.getUsername());
    assertEquals("secret123", user.getPassword());
    assertEquals("1", user.getId());
  }
}
