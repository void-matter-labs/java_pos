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

  private static final String APP_BASE_PATH = "/app/";
  private static final String STYLESHEET_PATH = "/shared/styles/dark_theme.css";

  @Override
  public void start(Stage primaryStage) throws Exception {
    String moduleName = Objects
      .requireNonNull(System.getProperty("module"), "Module name must be provided");

    Module module = loadModule(moduleName);
    Injector injector = Guice.createInjector(module);

    Parent root = loadFXML(moduleName, injector);
    Scene scene = createScene(root);

    primaryStage.setTitle(moduleName + " Sandbox");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private Parent loadFXML(String moduleName, Injector injector) throws Exception {
    String resourcePath = buildFXMLPath(moduleName);
    FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
    loader.setControllerFactory(
        param -> injector.getInstance(Key.get(param, Names.named(GuiceNames.MAIN_CONTROLLER.name()))));

    return loader.load();
  }

  private Scene createScene(Parent root) {
    var bounds = ScreenUtils.getVisualBounds();
    double windowWidth = bounds.getWidth() * 0.8;
    double windowHeight = bounds.getHeight() * 0.8;

    Scene scene = new Scene(root, windowWidth, windowHeight);
    scene
      .getStylesheets()
      .add(getClass().getResource(STYLESHEET_PATH).toExternalForm());

    return scene;
  }


  private Module loadModule(String moduleName) {
    String className = buildModuleClassName(moduleName);
    try {
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

  private String buildFXMLPath(String moduleName) {
    return APP_BASE_PATH + toPascalCase(moduleName) + "/" + toCamelCase(moduleName) + ".fxml";
  }

  private String buildModuleClassName(String moduleName) {
    return "crud.app." + toPascalCase(moduleName) + "." + toCamelCase(moduleName) + "Module";
  }

  private static String toCamelCase(String input) {
    if (isBlank(input)) {
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

  private static String toPascalCase(String input) {
    if (isBlank(input)) {
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

  private static boolean isBlank(String input) {
    return Objects.requireNonNullElse(input, "").isBlank();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
