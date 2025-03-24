package crud.app.login.persistence;

import com.google.inject.Inject;

import crud.app.login.domain_services.IUserCSVSerializer;
import crud.app.login.dtos.IAuthSession;
import crud.app.login.dtos.IAuthUser;
import crud.shared.persistence.dao.UserCsvDAO;

public class LoginRepository implements ILoginRepository {
  private UserCsvDAO userCsvDAO;
  private SessionCsvDAO sessionCsvDAO;

  @Inject
  public LoginRepository(UserCsvDAO userCsvDAO, SessionCsvDAO sessionCsvDAO) {
    this.userCsvDAO = userCsvDAO;
    this.sessionCsvDAO = sessionCsvDAO;
  }

  @Override
  public IAuthUser getUserByUserName(String username) throws Exception {
    return IUserCSVSerializer
      .INSTANCE
      .toDTO(this.userCsvDAO.getUserByUserName(username));
  }

  @Override
  public IAuthUser getUserById(String id) throws Exception {
    return IUserCSVSerializer
      .INSTANCE
      .toDTO(this.userCsvDAO.getUserById(id));
  }

  @Override
  public void createSession(IAuthSession session) throws Exception {
    this.sessionCsvDAO.createSession(session);
  }

  @Override
  public void createSession(IAuthUser user, IAuthSession session) throws Exception {
    this.sessionCsvDAO.createSession(session);
  }

}
