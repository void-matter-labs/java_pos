package crud.app.inventory.persistence;

import java.util.List;
import java.util.function.Predicate;

import com.google.inject.Inject;

import crud.app.inventory.domain_services.IProductCSVSerializer;
import crud.app.inventory.dtos.IInventoryItem;
import crud.app.inventory.dtos.InventoryItem;
import crud.shared.persistence.dao.ProductsCsvDao;

public class InventoryRepository implements IInventoryRepository {
  private ProductsCsvDao productsCsvDao;

  @Inject
  public InventoryRepository(ProductsCsvDao productsCsvDao) {
    this.productsCsvDao = productsCsvDao;
  }

  @Override
  public List<IInventoryItem> getItemsByPriceRange(double lowerLimit, double upperLimit) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getItemsByPriceRange'");
  }

  @Override
  public List<IInventoryItem> getItemsByFilter(Predicate<IInventoryItem> query) throws Exception {
   return this.getAllItems().stream()
      .filter(query)
      .toList();
  }

  @Override
  public List<IInventoryItem> getAllItems() throws Exception {
    return this.productsCsvDao.getAllProducts().stream()
      .map(product->(IInventoryItem) IProductCSVSerializer.INSTANCE.toDTO(product))
      .toList();
  }

  @Override
  public List<IInventoryItem> searchItemsByContent(String content) throws Exception {
    return this.productsCsvDao.getAllProducts().stream()
      .filter(item -> {
        String lowerContent = content.toLowerCase();
        return item.getName().toLowerCase().contains(lowerContent)
          || item.getShortDescription().toLowerCase().contains(lowerContent)
          || item.getId().toLowerCase().contains(lowerContent);
      })
      .map(product -> (IInventoryItem) IProductCSVSerializer.INSTANCE.toDTO(product))
      .toList();
  }


}
