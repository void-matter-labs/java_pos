package crud.app.search_bar;

import com.google.inject.AbstractModule;

import crud.app.search_bar.controllers.SearchBarMainController;

public class SearchBarModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(SearchBarMainController.class);
  }

}
