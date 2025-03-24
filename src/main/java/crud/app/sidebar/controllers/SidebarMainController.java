package crud.app.sidebar.controllers;

import crud.app.sidebar.components.FxCustomButton;
import crud.app.sidebar.dtos.ButtonConfig;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

public class SidebarMainController {
  @FXML
  private VBox sidebar;

  private List<ButtonConfig> buttonConfigs;

  private FxCustomButton activeButton;

  private List<FxCustomButton> buttons;

  private EventHandler<MouseEvent> clickHandler;

  @Inject
  public SidebarMainController(List<ButtonConfig> buttonConfigs, EventHandler<MouseEvent> clickHandler) {
    this.buttonConfigs = buttonConfigs;
    this.clickHandler = clickHandler;
  }

  @FXML
  public void initialize() {
    loadButtonItems();
  }

  public void setActiveButton(FxCustomButton newActiveButton) {
    if (this.activeButton != null)
      this.activeButton.setActive(false);

    this.activeButton = newActiveButton;

    newActiveButton.setActive(true);
  }

  private List<FxCustomButton> transformToFxCustomButton() {
    List<FxCustomButton> buttons = new ArrayList<>();

    for (ButtonConfig buttonConfig : buttonConfigs) {

      FxCustomButton button = new FxCustomButton(
        buttonConfig.getText(), 
        buttonConfig.getId()
      );


      if(buttonConfig.isActive()) {
        this.setActiveButton(button);
      }

      buttons.add(button);
    }

    return buttons;
  }

  private void loadButtonItems() {
    this.buttons = this.transformToFxCustomButton();

    for (FxCustomButton button : buttons) {
      button.setOnMouseClicked(this::handleButtonClick);
      sidebar.getChildren().add(button);
    }
  }

  private void handleButtonClick(MouseEvent event) {
    FxCustomButton newActiveButton = (FxCustomButton) event.getSource();
    setActiveButton(newActiveButton);

    clickHandler.handle(event);
  }
}