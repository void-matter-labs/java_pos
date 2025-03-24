package crud.shared.persistence.datasource;

import java.io.Reader;

@FunctionalInterface
public interface IWriterAction<T> {
 T execute(Reader reader) throws Exception;
}
