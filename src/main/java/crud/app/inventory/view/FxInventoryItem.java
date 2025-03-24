package crud.app.inventory.view;

import java.util.function.Predicate;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;

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
    setPrefWidth(200);

    getChildren().addAll(
      new Label(itemId),
      new Label(description),
      new Label("Quantity: " + quantity),
      new Label("Product: " + productName),
      new Label(String.format("$%.2f", price))
    );
  }
}
