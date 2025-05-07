package crud.app.sidebar;

import java.util.Arrays;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import crud.app.sidebar.components.FxCustomButton;
import crud.app.sidebar.dtos.ButtonConfig;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SidebarModule extends AbstractModule {
    private static final String INVENTORY_ID = "inventory";
    private static final String PRODUCT_FORM_ID = "product_form";
    
    @Override
    protected void configure() {
    }

    @Provides
    public List<ButtonConfig> provideButtonConfigs() {
        return Arrays.asList(
            new ButtonConfig(INVENTORY_ID, "Inventario", true),
            new ButtonConfig(PRODUCT_FORM_ID, "Agregar", false)
        );
    }

    @Provides
    @Singleton
    public EventHandler<MouseEvent> provideClickHandler() {
        return event -> {
            try {
                FxCustomButton button = (FxCustomButton) event.getSource();
                String moduleId = button.getCustomId();
                
                Scene scene = button.getScene();
                Stage stage = (Stage) scene.getWindow();
                
                stage.close();
                
                String[] args = {};
                System.setProperty("module", moduleId);
                crud.FeatureModuleLoader.main(args);
                
                System.out.println("Navegando a módulo: " + moduleId);
            } catch (Exception e) {
                System.err.println("Error al navegar: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
