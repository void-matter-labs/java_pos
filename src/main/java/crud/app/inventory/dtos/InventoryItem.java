package crud.app.inventory.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItem implements IInventoryItem {
  private String id;
  private String name;
  private String description;
  private double price;
  private int quantity;
}
