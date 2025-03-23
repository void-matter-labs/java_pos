package crud;

import java.util.Objects;

import com.google.inject.Guice;
import com.google.inject.Injector;

import crud.login.controllers.LoginController;
import crud.shared.module.AppModule;
import crud.shared.utils.ScreenUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainApp extends Application {
  private static Injector injector;

  public static void setInjector(Injector injector) {
    MainApp.injector = Objects.requireNonNull(injector);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/Login.fxml"));

      loader
          .setControllerFactory(
              param -> injector.getInstance(LoginController.class));

    Parent root = loader.load();
    var bounds = ScreenUtils.getVisualBounds();

    double windowWidth = bounds.getWidth() * 0.8;
    double windowHeight = bounds.getHeight() * 0.8;

    Scene scene = new Scene(root, windowWidth, windowHeight);
    primaryStage.setTitle("Login");
    primaryStage.setScene(scene);
    primaryStage.setMaximized(true);
    primaryStage.show();
  }


  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new AppModule());

    setInjector(injector);
    launch(args);
  }
}
