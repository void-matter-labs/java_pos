package crud.app.main;

import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import crud.app.inventory.application_services.IInventoryService;
import crud.app.inventory.application_services.InventoryService;
import crud.app.inventory.controllers.InventoryMainController;
import crud.app.inventory.persistence.IInventoryRepository;
import crud.app.inventory.persistence.InventoryRepository;
import crud.app.main.controllers.MainMainController;
import crud.app.product_form.controllers.ProductFormMainController;
import crud.app.sidebar.components.FxCustomButton;
import crud.app.sidebar.controllers.SidebarMainController;
import crud.app.sidebar.dtos.ButtonConfig;
import crud.shared.persistence.dao.ProductsCsvDao;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MainMainController.class);
        bind(SidebarMainController.class);
        bind(InventoryMainController.class);
        bind(ProductFormMainController.class);
        
        bind(IInventoryService.class).to(InventoryService.class);
        bind(IInventoryRepository.class).to(InventoryRepository.class);
        
        bind(ProductsCsvDao.class);
    }
    
    @Provides
    public List<ButtonConfig> provideButtonConfigs() {
        return java.util.Arrays.asList(
            new ButtonConfig("inventory", "Inventario", true),
            new ButtonConfig("product_form", "Agregar", false)
        );
    }
    
    @Provides
    public EventHandler<MouseEvent> provideClickHandler() {
        return event -> {
            FxCustomButton button = (FxCustomButton) event.getSource();
            System.out.println("Clic en botón: " + button.getCustomId());
        };
    }
}