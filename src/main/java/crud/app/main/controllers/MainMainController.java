package crud.app.main.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Injector;

import crud.app.sidebar.controllers.SidebarMainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMainController {
    @FXML
    private BorderPane mainContainer;
    
    @FXML
    private VBox contentArea;
    
    @FXML
    private SidebarMainController sidebarController;
    
    private final Map<String, String> modulePathMap = new HashMap<>();
    
    private final Injector injector;
    
    @Inject
    public MainMainController(Injector injector) {
        this.injector = injector;
        
        modulePathMap.put("inventory", "/app/inventory/Inventory.fxml");
        modulePathMap.put("product_form", "/app/product_form/ProductForm.fxml");
    }
    
    @FXML
    public void initialize() {
        sidebarController.setCustomClickHandler(this::loadContent);
        
        loadContent("inventory");
    }
    
    public void loadContent(String moduleId) {
        try {
            String fxmlPath = modulePathMap.get(moduleId);
            if (fxmlPath != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                
                loader.setControllerFactory(controllerClass -> injector.getInstance(controllerClass));
                
                Parent moduleRoot = loader.load();
                
                contentArea.getChildren().clear();
                contentArea.getChildren().add(moduleRoot);
                
                System.out.println("Contenido cargado: " + moduleId);
            } else {
                System.err.println("No se encontró path para el módulo: " + moduleId);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar módulo " + moduleId + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
