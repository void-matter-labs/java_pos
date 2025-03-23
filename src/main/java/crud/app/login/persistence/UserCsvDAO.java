package crud.app.login.persistence;

import java.util.List;
import java.util.function.Predicate;

import com.google.inject.Inject;
import com.opencsv.bean.CsvToBeanBuilder;

import crud.app.login.dtos.BaseAuthUser;
import crud.app.login.dtos.IAuthUser;
import crud.shared.persistence.datasource.ICSVConnectionManager;

public class UserCsvDAO extends BaseCsvDAO {
  @Inject
  public UserCsvDAO(ICSVConnectionManager csvConnectionManager) {
      super(csvConnectionManager);
  }

  @Override
  String getTableName() {
    return "users";
  }

  public IAuthUser getUserByUserName(String username) throws Exception {
    return findUser(user -> user.getUsername().equals(username));
  }

  public IAuthUser getUserById(String id) throws Exception {
    return findUser(user -> user.getId().equals(id));
  }

  private IAuthUser findUser(Predicate<BaseAuthUser> predicate) throws Exception {
    return this
      .executeWithReader(reader -> {
        List<BaseAuthUser> users = new CsvToBeanBuilder<BaseAuthUser>(reader)
          .withType(BaseAuthUser.class)
          .build()
          .parse();

        return users.stream()
          .filter(predicate)
          .findFirst()
          .orElse(null);
      });
  }
}
