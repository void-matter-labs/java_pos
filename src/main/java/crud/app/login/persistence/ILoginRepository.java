package crud.app.login.persistence;

import crud.app.login.dtos.IAuthSession;
import crud.app.login.dtos.IAuthUser;

public interface ILoginRepository {
  public IAuthUser getUserByUserName(String username);
  public IAuthUser getUserById(String id);
  public void createSession(IAuthSession session);
  public void createSession(IAuthUser user, IAuthSession session);
}
