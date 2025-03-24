package crud.app.login.application_services;

import java.time.Instant;
import java.util.UUID;

import com.google.inject.Inject;

import crud.app.login.dtos.BaseAuthSession;
import crud.app.login.dtos.IAuthSession;
import crud.app.login.dtos.IAuthUser;
import crud.app.login.persistence.ILoginRepository;

public class LoginService {
  private final ILoginRepository loginRepository;

  @Inject
  public LoginService(ILoginRepository loginRepository) {
    this.loginRepository = loginRepository;
  }

  public String login(IAuthUser user) throws Exception {
    IAuthUser userData = loginRepository.getUserByUserName(user.getUsername());

    if (userData == null) {
      return null;
    }

    if (userData.getPassword().equals(user.getPassword())){
      IAuthSession session = new BaseAuthSession();
      session.setUserId(userData.getId());
      session.setId(UUID.randomUUID().toString());
      session.setCreatedAt(Instant.now());

      this.loginRepository.createSession(session);

      return userData.getId();
    }


    return null;
  }
}
