package crud.shared.persistence.datasource;

public interface ICSVConnectionManager {
  public void registerPath(String identifier, String path);

  public <T> T executeWithReader(String identifier, IReaderAction<T> action) throws Exception;
  public <T> T executeWithWriter(String identifier, IWriterAction<T> action) throws Exception;
}
