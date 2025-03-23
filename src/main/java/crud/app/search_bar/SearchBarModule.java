package crud.app.search_bar;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import crud.app.search_bar.controllers.SearchBarMainController;
import crud.shared.constants.GuiceNames;

public class SearchBarModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(SearchBarMainController.class)
        .annotatedWith(Names.named(GuiceNames.MAIN_CONTROLLER.name()))
        .to(SearchBarMainController.class);
  }

}
