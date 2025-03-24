package crud.shared.persistence.dao;

import java.util.List;
import java.util.function.Predicate;

import com.google.inject.Inject;
import com.opencsv.bean.CsvToBeanBuilder;

import crud.shared.dto.UserCsvDTO;
import crud.shared.persistence.datasource.ICSVConnectionManager;

//TODO: refactor
public class UserCsvDAO extends BaseCsvDAO {
  @Inject
  public UserCsvDAO(ICSVConnectionManager csvConnectionManager) {
    super(csvConnectionManager);
  }

  @Override
  protected String getTableName() {
    return "users";
  }

  public UserCsvDTO getUserByUserName(String username) throws Exception {
    return this.executeWithReader((reader)->{
      List<UserCsvDTO> users = new CsvToBeanBuilder<UserCsvDTO>(reader)
        .withType(UserCsvDTO.class)
        .build()
        .parse();

      return users.stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst()
        .orElse(null);
    });
  }

  public UserCsvDTO getUserById(String id) throws Exception {
    return this.executeWithReader((reader)->{
      List<UserCsvDTO> users = new CsvToBeanBuilder<UserCsvDTO>(reader)
        .withType(UserCsvDTO.class)
        .build()
        .parse();

      return users.stream()
        .filter(user -> user.getId().equals(id))
        .findFirst()
        .orElse(null);
    });
  }

  public List<UserCsvDTO> getAllUsers() throws Exception {
    return this.executeWithReader((reader)->{
      return new CsvToBeanBuilder<UserCsvDTO>(reader)
        .withType(UserCsvDTO.class)
        .build()
        .parse();
    });
  }

  public void createUser(UserCsvDTO user) throws Exception {
    this.executeWithWriter((writer)->{
      throw new UnsupportedOperationException("Not implemented");
    });
  }

  public void updateUser(UserCsvDTO user) throws Exception {
    this.executeWithWriter((writer)->{
      throw new UnsupportedOperationException("Not implemented");
    });
  }

  public void deleteUser(UserCsvDTO user) throws Exception {
    this.executeWithWriter((writer)->{
      throw new UnsupportedOperationException("Not implemented");
    });
  }

  public void deleteUserById(String id) throws Exception {
    this.executeWithWriter((writer)->{
      throw new UnsupportedOperationException("Not implemented");
    });
  }

  public List<UserCsvDTO> getUsersByFiler(Predicate<UserCsvDTO> predicate) throws Exception {
    return this.executeWithReader((reader)->{
      List<UserCsvDTO> users = new CsvToBeanBuilder<UserCsvDTO>(reader)
        .withType(UserCsvDTO.class)
        .build()
        .parse();

      return users.stream()
        .filter(predicate)
        .toList();
    });
  }
}
