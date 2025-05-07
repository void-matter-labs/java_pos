package crud.app.sidebar.components;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;

public class FxCustomButton extends Button {
  private boolean isActive;

  @Getter
  private String customId;
  
  private ImageView iconView;

  public FxCustomButton(String text, String customId) {
    super(text);

    getStyleClass().add("base-button");

    this.customId = customId;
    
    this.iconView = new ImageView();
    this.iconView.setFitHeight(24);
    this.iconView.setFitWidth(24);
    this.iconView.setPreserveRatio(true);
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
    updateStyle();
  }
  
  public void setIcon(String iconPath) {
    try {
      Image icon = new Image(getClass().getResourceAsStream(iconPath));
      this.iconView.setImage(icon);
      this.setGraphic(iconView);
      this.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
    } catch (Exception e) {
      System.err.println("Error al cargar el icono: " + iconPath);
      e.printStackTrace();
    }
  }

  private void updateStyle() {
    getStyleClass().remove("base-button-active");
    if (this.isActive) {
      getStyleClass().add("base-button-active");
    }
  }
}