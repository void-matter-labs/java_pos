package crud;

import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import crud.app.login.controllers.LoginMainController;
import crud.shared.utils.ScreenUtils;



public class FeatureModuleLoader extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    String moduleName = Objects.requireNonNull(System.getProperty("module"));

    String capitalizedModuleName = FeatureModuleLoader.capitalize(moduleName);
    String lowerCaseModuleName = moduleName.toLowerCase();
    String resourcePath = "/"
      + lowerCaseModuleName
      + "/"
      + capitalizedModuleName
      + ".fxml";

    Module module = loadModule(capitalizedModuleName);

    Injector injector = Guice.createInjector(module);

    FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));

    loader
      .setControllerFactory(
        param -> injector.getInstance(LoginMainController.class)
      );

    Parent root = loader.load();
    var bounds = ScreenUtils.getVisualBounds();

    double windowWidth = bounds.getWidth() * 0.8;
    double windowHeight = bounds.getHeight() * 0.8;

    Scene scene = new Scene(root, windowWidth, windowHeight);

    primaryStage.setTitle(moduleName + " Sandbox");
    primaryStage.setScene(scene);
    primaryStage.show();
}
  private static  String capitalize(String moduleName){
    return moduleName
      .substring(0, 1)
      .toUpperCase() + moduleName
      .substring(1)
      .toLowerCase();
  }

  private Module loadModule(String moduleName) throws IllegalArgumentException {
    try {
      moduleName = capitalize(moduleName);
      String className = "crud."
        + "app."
        + moduleName.toLowerCase()
        +"."
        + moduleName
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
