package crud.shared.persistence.dao;

import crud.shared.persistence.datasource.ICSVConnectionManager;
import crud.shared.persistence.datasource.IReaderAction;
import crud.shared.persistence.datasource.IWriterAction;

public abstract class BaseCsvDAO {
  protected final ICSVConnectionManager csvConnectionManager;
  protected final String tableName;

  protected BaseCsvDAO(ICSVConnectionManager csvConnectionManager) {
    this.csvConnectionManager = csvConnectionManager;
    this.tableName = this.getTableName();
  }

  protected abstract  String getTableName();

  protected  <T> T executeWithReader(IReaderAction<T> action) throws Exception{
    return this.csvConnectionManager.executeWithReader(tableName, action);
  }

  protected  <T> T executeWithWriter(IWriterAction<T> action) throws Exception{
    return this.csvConnectionManager.executeWithWriter(tableName, action);
  }
}
