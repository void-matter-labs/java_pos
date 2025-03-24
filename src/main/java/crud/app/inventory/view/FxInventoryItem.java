package crud.app.inventory.view;



import javafx.scene.control.Label;

import javafx.scene.layout.VBox;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FxInventoryItem extends VBox {
  private String itemId;
  private String description;
  private int quantity;
  private String productName;
  private double price;


  public FxInventoryItem(String itemId, String description, int quantity, String productName, double price) {
    this.itemId = itemId;
    this.description = description;
    this.quantity = quantity;
    this.productName = productName;
    this.price = price;

    setSpacing(10);
    setPadding(new javafx.geometry.Insets(10));
    setPrefWidth(300);

    getChildren().addAll(
      new Label("Id: " +itemId),
      new Label(productName),
      new Label(description),
      new Label("Quantity: " + quantity),
      new Label(String.format("$%.2f", price))
    );
  }
}
