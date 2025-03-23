package crud.shared.persistence.datasource;

import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class CSVConnectionManager implements ICSVConnectionManager {
  private final Map<String, String> pathRegistry = new HashMap<>();

  public void registerPath(String identifier, String path) {
      pathRegistry.put(identifier, path);
  }

  public <T> T executeWithReader(String identifier, IReaderAction<T> action) throws Exception {
    String path = pathRegistry.get(identifier);
    if (path == null) {
        throw new IllegalArgumentException("No path registered for identifier: " + identifier);
    }

    try (Reader reader = new FileReader(path)) {
      return action.execute(reader);
    }
  }

  @Override
  public <T> T executeWithWriter(String identifier, IWriterAction<T> action) throws Exception {
    String path = pathRegistry.get(identifier);
    if (path == null) {
        throw new IllegalArgumentException("No path registered for identifier: " + identifier);
    }

    try (Reader reader = new FileReader(path)) {
      return action.execute(reader);
    }
  }
}
