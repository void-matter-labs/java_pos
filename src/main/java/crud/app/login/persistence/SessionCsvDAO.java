package crud.app.login.persistence;

import com.google.inject.Inject;

import crud.app.login.dtos.IAuthSession;
import crud.shared.persistence.datasource.ICSVConnectionManager;

public class SessionCsvDAO extends BaseCsvDAO{
  @Inject
  public SessionCsvDAO(ICSVConnectionManager csvConnectionManager) {
    super(csvConnectionManager);
  }

  @Override
  String getTableName() {
    return "sessions";
  }

  public void createSession(IAuthSession session) throws Exception {
    this.executeWithWriter(writer -> {
      return null;
    });
  }
}
