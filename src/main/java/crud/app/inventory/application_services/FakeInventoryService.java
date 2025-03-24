package crud.app.inventory.application_services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import crud.app.inventory.dtos.IInventoryItem;
import crud.app.inventory.dtos.InventoryItem;

public class FakeInventoryService implements IInventoryService {
  private final List<IInventoryItem> items;

  public FakeInventoryService() {
    items = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      items.add(new InventoryItem(
        "item" + i,
        "Item " + i,
        "Default description for Item " + i,
        10.0 * i, // Example price
        100 // Example quantity
      ));
    }
  }

  public List<IInventoryItem> getAllItems() {
    return this.items;
  }

  public List<IInventoryItem> getItemsByFilter(Predicate<IInventoryItem> filter) {
    return this.items
      .stream()
      .filter(filter)
      .toList();
  }
}
