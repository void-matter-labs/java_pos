package crud.app.inventory.persistence;

import java.util.List;
import java.util.function.Predicate;

import crud.app.inventory.dtos.IInventoryItem;

public interface IInventoryRepository {
  public List<IInventoryItem> getItemsByPriceRange(double lowerLimit, double upperLimit) throws Exception;
  public List<IInventoryItem> getItemsByFilter(Predicate<IInventoryItem> query) throws Exception;
  public List<IInventoryItem> getAllItems() throws Exception;
  public List<IInventoryItem> searchItemsByContent(String content) throws Exception;
}
