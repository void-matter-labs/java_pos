package crud.app.product_form.controllers;

import java.util.Arrays;
import java.util.logging.Logger;

import com.google.inject.Inject;

import crud.shared.dto.ProductCsvDTO;
import crud.shared.persistence.dao.ProductsCsvDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProductFormMainController {
  private static final Logger logger = Logger.getLogger(ProductFormMainController.class.getName());
  
  @FXML
  private TextField idField;
  
  @FXML
  private TextField nameField;
  
  @FXML
  private TextField descriptionField;
  
  @FXML
  private TextField priceField;
  
  @FXML
  private ComboBox<String> categoryComboBox;
  
  @FXML
  private TextField quantityField;
  
  @FXML
  private Button saveButton;
  
  @FXML
  private Label statusLabel;
  
  private final ProductsCsvDao productsCsvDao;
  
  @Inject
  public ProductFormMainController(ProductsCsvDao productsCsvDao) {
    this.productsCsvDao = productsCsvDao;
  }
  
  @FXML
  public void initialize() {
    categoryComboBox.getItems().addAll(Arrays.asList(
      "Electronics", "Sports", "Home", "Computers", "Kitchen", 
      "Travel", "Health", "Food", "Gaming", "Transportation"
    ));
    
    statusLabel.setText("");
  }
  
  @FXML
  private void handleSaveClick(MouseEvent event) {
    try {
      if (isFormValid()) {
        ProductCsvDTO newProduct = new ProductCsvDTO(
          idField.getText(),
          nameField.getText(),
          descriptionField.getText(),
          Double.parseDouble(priceField.getText()),
          categoryComboBox.getValue(),
          descriptionField.getText(),
          Integer.parseInt(quantityField.getText())
        );
        
        boolean saved = productsCsvDao.saveProduct(newProduct);
        
        if (saved) {
          statusLabel.setText("¡Producto guardado correctamente!");
          statusLabel.setStyle("-fx-text-fill: #4CAF50;");
          
          clearForm();
          
          logger.info("Nuevo producto guardado: " + newProduct);
        } else {
          statusLabel.setText("Error al guardar el producto");
          statusLabel.setStyle("-fx-text-fill: #F44336;");
          logger.severe("Error al guardar producto");
        }
      }
    } catch (NumberFormatException e) {
      statusLabel.setText("Error en los campos numéricos");
      statusLabel.setStyle("-fx-text-fill: #F44336;");
      logger.severe("Error en formato de números: " + e.getMessage());
    } catch (Exception e) {
      statusLabel.setText("Error al guardar el producto");
      statusLabel.setStyle("-fx-text-fill: #F44336;");
      logger.severe("Error al guardar producto: " + e.getMessage());
    }
  }
  
  private boolean isFormValid() {
    statusLabel.setText("");
    
    if (idField.getText().isEmpty() || 
        nameField.getText().isEmpty() || 
        descriptionField.getText().isEmpty() ||
        priceField.getText().isEmpty() ||
        categoryComboBox.getValue() == null ||
        quantityField.getText().isEmpty()) {
      
      statusLabel.setText("Todos los campos son obligatorios");
      statusLabel.setStyle("-fx-text-fill: #F44336;");
      return false;
    }
    
    try {
      double price = Double.parseDouble(priceField.getText());
      int quantity = Integer.parseInt(quantityField.getText());
      
      if (price <= 0) {
        statusLabel.setText("El precio debe ser mayor que cero");
        statusLabel.setStyle("-fx-text-fill: #F44336;");
        return false;
      }
      
      if (quantity <= 0) {
        statusLabel.setText("La cantidad debe ser mayor que cero");
        statusLabel.setStyle("-fx-text-fill: #F44336;");
        return false;
      }
    } catch (NumberFormatException e) {
      statusLabel.setText("El precio y la cantidad deben ser números válidos");
      statusLabel.setStyle("-fx-text-fill: #F44336;");
      return false;
    }
    
    return true;
  }
  
  private void clearForm() {
    idField.clear();
    nameField.clear();
    descriptionField.clear();
    priceField.clear();
    categoryComboBox.setValue(null);
    quantityField.clear();
  }
}