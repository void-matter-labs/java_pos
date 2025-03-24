package crud.shared.context;

import com.google.inject.AbstractModule;

import crud.shared.persistence.datasource.FakeCSVConnectionManager;
import crud.shared.persistence.datasource.ICSVConnectionManager;

public class GlobalModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(ICSVConnectionManager.class)
    .to(FakeCSVConnectionManager.class);
  }

}
