package crud;

import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.name.Names;

import crud.shared.constants.GuiceNames;
import crud.shared.utils.ScreenUtils;

public class FeatureModuleLoader extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    String moduleName = Objects.requireNonNull(System.getProperty("module"));

    String capitalizedModuleName = FeatureModuleLoader.capitalize(moduleName);
    String pascalizedModuleName = FeatureModuleLoader.pascalize(moduleName);
    String resourcePath = "/app/"
        + pascalizedModuleName
        + "/"
        + capitalizedModuleName
        + ".fxml";

    Module module = loadModule(moduleName);

    Injector injector = Guice.createInjector(module);

    FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));

    loader
      .setControllerFactory(
        param -> injector.getInstance(Key.get(param, Names.named(GuiceNames.MAIN_CONTROLLER.name())))
      );

    Parent root = loader.load();
    var bounds = ScreenUtils.getVisualBounds();

    double windowWidth = bounds.getWidth() * 0.8;
    double windowHeight = bounds.getHeight() * 0.8;

    Scene scene = new Scene(root, windowWidth, windowHeight);

    scene.getStylesheets().add(getClass().getResource("/shared/styles/dark_theme.css").toExternalForm());

    primaryStage.setTitle(moduleName + " Sandbox");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private static boolean isBlank(String input) {
    return Objects.requireNonNullElse(input, "").isBlank();
  }

  private static String capitalize(String input) {
    if (FeatureModuleLoader.isBlank(input)) {
      return input;
    }
    String[] parts = input.split("_");
    StringBuilder result = new StringBuilder();
    for (String part : parts) {
      result.append(part.substring(0, 1).toUpperCase())
          .append(part.substring(1).toLowerCase());
    }
    return result.toString();
  }

  private static String pascalize(String input) {
    if (FeatureModuleLoader.isBlank(input)) {
      return input;
    }
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (Character.isUpperCase(c) && i > 0) {
        result.append("_");
      }
      result.append(Character.toLowerCase(c));
    }
    return result.toString();
  }

  private Module loadModule(String moduleName) throws IllegalArgumentException {
    try {
      String className = "crud."
          + "app."
          + FeatureModuleLoader.pascalize(moduleName)
          + "."
          + FeatureModuleLoader.capitalize(moduleName)
          + "Module";

      Class<?> clazz = Class.forName(className);

      if (!Module.class.isAssignableFrom(clazz)) {
        throw new IllegalArgumentException(className + " does not implement Module.");
      }

      return (Module) clazz.getDeclaredConstructor().newInstance();
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("Module " + moduleName + " not found.", e);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to load module " + moduleName, e);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
