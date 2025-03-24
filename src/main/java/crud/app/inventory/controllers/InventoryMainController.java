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
  public void initialize() {
    loadInventoryItems();

    searchBar.textProperty().addListener((observable, oldValue, newValue) -> debounceSearch(newValue));
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

  private void loadInventoryItems(Predicate<IInventoryItem> query) {
    List<IInventoryItem> items = inventoryService.getItemsByFilter(query);
    List<FxInventoryItem> fxItems = transformToFxItems(items);

    itemFlowPane.getChildren().clear();

    for (FxInventoryItem item : fxItems) {
      item.setOnMouseClicked(clickHandler);
      itemFlowPane.getChildren().add(item);
    }
  }

  private void loadInventoryItems() {
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
          () -> loadInventoryItems(item-> {
            String lowerCasedQuery = query.toLowerCase();
            return item.getName().toLowerCase().contains(lowerCasedQuery)
              || item.getDescription().toLowerCase().contains(lowerCasedQuery);
          })
        );
      }
    }, 300); // 300ms debounce delay
  }
}
