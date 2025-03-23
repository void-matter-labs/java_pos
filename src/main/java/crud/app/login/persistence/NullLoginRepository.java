package crud.app.login.persistence;

import crud.app.login.dtos.IAuthSession;
import crud.app.login.dtos.IAuthUser;

public class NullLoginRepository implements ILoginRepository {
  @Override
  public IAuthUser getUserByUserName(String username) {
    return null;
  }

  @Override
  public void createSession(IAuthSession session) {
     // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createSession'");
  }

  @Override
  public IAuthUser getUserById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
  }

  @Override
  public void createSession(IAuthUser user, IAuthSession session) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createSession'");
  }

}
