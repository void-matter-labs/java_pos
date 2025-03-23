package crud.shared.persistence.datasource;

import java.io.Reader;

@FunctionalInterface
public interface IReaderAction <T>{
  T execute(Reader reader) throws Exception;
}
