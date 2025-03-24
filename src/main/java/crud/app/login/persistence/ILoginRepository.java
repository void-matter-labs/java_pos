package crud.app.login.persistence;

import crud.app.login.dtos.IAuthSession;
import crud.app.login.dtos.IAuthUser;

public interface ILoginRepository {
  public IAuthUser getUserByUserName(String username) throws Exception;
  public IAuthUser getUserById(String id) throws Exception;
  public void createSession(IAuthSession session) throws Exception;
  public void createSession(IAuthUser user, IAuthSession session) throws Exception;
}
