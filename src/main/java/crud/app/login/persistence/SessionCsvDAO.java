package crud.app.login.persistence;

import com.google.inject.Inject;

import crud.app.login.dtos.IAuthSession;
import crud.shared.persistence.dao.BaseCsvDAO;
import crud.shared.persistence.datasource.ICSVConnectionManager;

public class SessionCsvDAO extends BaseCsvDAO{
  @Inject
  public SessionCsvDAO(ICSVConnectionManager csvConnectionManager) {
    super(csvConnectionManager);
  }

  @Override
  protected String getTableName() {
    return "sessions";
  }

  public void createSession(IAuthSession session) throws Exception {
    this.executeWithWriter(writer -> {
      return null;
    });
  }
}
