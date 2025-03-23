package crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import crud.app.login.application_services.LoginService;
import crud.app.login.dtos.IAuthUser;
import crud.app.login.persistence.ILoginRepository;

class LoginServiceTest {
  private LoginService loginService;

  @Mock
  private ILoginRepository loginRepository;

  @BeforeEach
  void setUp(){
    MockitoAnnotations.openMocks(this);
    loginService = new LoginService(loginRepository);
  }

  private IAuthUser createMockedUser(String username, String password){
    IAuthUser mockUser = mock(IAuthUser.class);
    when(mockUser.getUsername()).thenReturn(username);
    when(mockUser.getPassword()).thenReturn(password);

    return mockUser;
  }

  @Test
  void testLogin(){
    IAuthUser mockUser = this
      .createMockedUser(
        "testUser",
        "password123"
      );

    IAuthUser userFromRepo = this
      .createMockedUser(
        "testUser",
        "password123"
      );

    when(userFromRepo.getId()).thenReturn("user123");

    when(loginRepository.getUserByUserName("testUser"))
      .thenReturn(userFromRepo);

    String result = loginService.login(mockUser);

    assertEquals("user123", result);
    verify(loginRepository, times(1))
      .createSession(any());
  }
}
