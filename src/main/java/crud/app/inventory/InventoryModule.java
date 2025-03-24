package crud.app.inventory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import crud.app.inventory.application_services.InventoryService;
import crud.app.inventory.application_services.IInventoryService;
import crud.app.inventory.controllers.InventoryMainController;
import crud.app.inventory.persistence.IInventoryRepository;
import crud.app.inventory.persistence.InventoryRepository;
import crud.app.inventory.view.FxInventoryItem;
import crud.shared.persistence.dao.ProductsCsvDao;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class InventoryModule extends AbstractModule {
    @Override
    protected void configure() {
      bind(InventoryMainController.class);

      bind(IInventoryService.class)
        .to(InventoryService.class);

      bind(IInventoryRepository.class)
        .to(InventoryRepository.class);

      bind(ProductsCsvDao.class);
    }

    @Provides
    @Singleton
    public EventHandler<MouseEvent> provideClickHandler() {
      return event -> {
        FxInventoryItem item = (FxInventoryItem) event.getSource();
        System.out.println("Mouse clicked on: " + item.getProductName());
      };
    }
}
