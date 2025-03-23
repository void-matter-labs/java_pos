package crud.shared.utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenUtils {
  private ScreenUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static Rectangle2D getVisualBounds() {
    return Screen.getPrimary().getVisualBounds();
  }
}
