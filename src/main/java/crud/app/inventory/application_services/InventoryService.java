package crud.app.inventory.application_services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.google.inject.Inject;

import crud.app.inventory.dtos.IInventoryItem;

import crud.app.inventory.persistence.IInventoryRepository;

public class InventoryService implements IInventoryService {
  private IInventoryRepository repository;

  @Inject
  public InventoryService(IInventoryRepository repository) {
    this.repository = repository;
  }


  public List<IInventoryItem> getAllItems() throws Exception {
    return repository.getAllItems();
  }

  public List<IInventoryItem> getItemsByFilter(Predicate<IInventoryItem> filter) throws Exception {
    return repository.getItemsByFilter(filter);
  }

  public List<IInventoryItem> searchItemsByContent(String content) throws Exception {
    return repository.searchItemsByContent(content);
  }
}
