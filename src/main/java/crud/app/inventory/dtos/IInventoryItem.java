package crud.app.inventory.dtos;

public interface IInventoryItem {
  String getId();
  String getName();
  String getDescription();
  int getQuantity();
  double getPrice();

  void setId(String id);
  void setName(String name);
  void setDescription(String description);
  void setQuantity(int quantity);
  void setPrice(double price);
}
