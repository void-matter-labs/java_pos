package crud;

import com.google.inject.Guice;
import com.google.inject.Injector;

import crud.login.controllers.LoginController;
import crud.login.services.NullLoginService;
import crud.shared.module.AppModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Rectangle2D getVisualBounds(){
      return Screen.getPrimary().getVisualBounds();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/Login.fxml"));

        Injector injector = Guice.createInjector(new AppModule());

        loader
          .setControllerFactory(
            param->
             injector.getInstance(LoginController.class)
            );


        Parent root = loader.load();
        var bounds = this.getVisualBounds();

        double windowWidth = bounds.getWidth() * 0.8;
        double windowHeight = bounds.getHeight() * 0.8;

        Scene scene = new Scene(root, windowWidth, windowHeight);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
