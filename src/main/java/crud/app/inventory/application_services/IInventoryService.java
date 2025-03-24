package crud.app.inventory.application_services;

import java.util.List;
import java.util.function.Predicate;

import crud.app.inventory.dtos.IInventoryItem;

public interface IInventoryService {
  public List<IInventoryItem> getAllItems();
  public List<IInventoryItem> getItemsByFilter(Predicate<IInventoryItem> filter);
}
