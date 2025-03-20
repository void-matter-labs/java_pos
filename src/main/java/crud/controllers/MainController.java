package crud.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainController implements Initializable {
    @FXML
    private Label helloLabel;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        helloLabel.setText(
          "Hello, Paulsito from controller!" +
          "(delete in fxml the line that connects to the controller if"+
          "you want to see changes you do in the label of the fxml," +
          "since the controllers/MainController is setting  the content here)"
        );
    }
}
