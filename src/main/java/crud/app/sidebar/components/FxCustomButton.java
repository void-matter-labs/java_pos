package crud.app.sidebar.components;

import javafx.scene.control.Button;
import lombok.Getter;

public class FxCustomButton extends Button {
  private boolean isActive;

  @Getter
  private String customId;

  public FxCustomButton(String text, String customId) {
    super(text);

    getStyleClass().add("base-button");

    this.customId = customId;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
    updateStyle();
  }

  private void updateStyle() {
    getStyleClass().remove("base-button-active");
    if (this.isActive) {
      getStyleClass().add("base-button-active");
    }
  }

} 