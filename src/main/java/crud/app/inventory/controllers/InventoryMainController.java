package crud.app.inventory.controllers;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.google.inject.Inject;

import crud.app.inventory.application_services.IInventoryService;
import crud.app.inventory.dtos.IInventoryItem;
import crud.app.inventory.view.FxInventoryItem;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class InventoryMainController {
  private static final Logger logger = Logger.getLogger(InventoryMainController.class.getName());
  private final IInventoryService inventoryService;
  private final javafx.event.EventHandler<MouseEvent> clickHandler;
  private Timer debounceTimer;

  @FXML
  private TextField searchBar;

  @FXML
  private FlowPane itemFlowPane;

  @Inject
  public InventoryMainController(IInventoryService inventoryService, EventHandler<MouseEvent> clickHandler) {
    this.inventoryService = inventoryService;
    this.clickHandler = clickHandler;
  }

  @FXML
  public void initialize() throws Exception {
    loadInventoryItems();

    searchBar.textProperty().addListener((observable, oldValue, newValue) -> debounceSearch(newValue));
    
    itemFlowPane.sceneProperty().addListener((obs, oldScene, newScene) -> {
      if (newScene != null) {
        newScene.windowProperty().addListener((obsWindow, oldWindow, newWindow) -> {
          if (newWindow != null) {
            newWindow.focusedProperty().addListener((obsFocus, oldFocus, newFocus) -> {
              if (newFocus) {
                try {
                  loadInventoryItems();
                } catch (Exception e) {
                  logger.severe("Error al recargar inventario: " + e.getMessage());
                }
              }
            });
          }
        });
      }
    });
  }

  private List<FxInventoryItem> transformToFxItems(List<IInventoryItem> items) {
    return items
      .stream()
      .map(item->new FxInventoryItem(
        item.getId(),
        item.getDescription(),
        item.getQuantity(),
        item.getName(),
        item.getPrice()
      ))
      .toList();
  }

  private void loadInventoryItems(String query) throws Exception {
    List<IInventoryItem> items = inventoryService.searchItemsByContent(query);
    List<FxInventoryItem> fxItems = transformToFxItems(items);

    itemFlowPane.getChildren().clear();

    for (FxInventoryItem item : fxItems) {
      item.setOnMouseClicked(clickHandler);
      itemFlowPane.getChildren().add(item);
    }
  }

  private void loadInventoryItems() throws Exception {
    List<IInventoryItem> items = inventoryService.getAllItems();
    List<FxInventoryItem> fxItems = transformToFxItems(items);

    itemFlowPane.getChildren().clear();

    for (FxInventoryItem item : fxItems) {
      item.setOnMouseClicked(clickHandler);
      itemFlowPane.getChildren().add(item);
    }
  }

  private void debounceSearch(String query) {
    if (debounceTimer != null) {
      debounceTimer.cancel();
    }

    debounceTimer = new Timer();
    debounceTimer.schedule(new TimerTask() {
      @Override
      public void run() {
        javafx.application.Platform.runLater(
          () -> {
            try {
              loadInventoryItems(query.toLowerCase());
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        );
      }
    }, 300); // 300ms debounce delay
  }
  
  // Método público para forzar una actualización
  public void refreshInventory() {
    try {
      loadInventoryItems();
    } catch (Exception e) {
      logger.severe("Error al refrescar inventario: " + e.getMessage());
    }
  }
}
