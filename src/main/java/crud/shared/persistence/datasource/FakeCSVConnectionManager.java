package crud.shared.persistence.datasource;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Singleton;

@Singleton
public class FakeCSVConnectionManager implements ICSVConnectionManager {
  private final Map<String, String> fakeDataByTableName = new HashMap<>();

  private static final String  FAKE_USERS_DATA = """
    id,username,password
    1,johndoe,secret123
    2,janedoe,password456
    """;

  public FakeCSVConnectionManager() {
    this.fakeDataByTableName.put("users", FakeCSVConnectionManager.FAKE_USERS_DATA);
    this.fakeDataByTableName.put("sessions", "sessions.csv");
  }

  @Override
  public <T> T executeWithReader(String tableName, IReaderAction<T> action) throws Exception {

    try(Reader reader = new StringReader(fakeDataByTableName.get(tableName))){
      return action.execute(reader);
    }
  }

  @Override
  public <T> T executeWithWriter(String tableName, IWriterAction<T> action) throws Exception {
    return null;
  }

  @Override
  public void registerPath(String identifier, String path) {
    throw new UnsupportedOperationException("Not implemented");
  }

}
