package crud.shared.persistence.dao;

import java.util.List;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.opencsv.bean.CsvToBeanBuilder;

import crud.shared.dto.ProductCsvDTO;
import crud.shared.persistence.datasource.ICSVConnectionManager;

@Singleton
public class ProductsCsvDao extends BaseCsvDAO {

  private List<ProductCsvDTO> productsCache;

  @Inject
  protected ProductsCsvDao(ICSVConnectionManager csvConnectionManager) {
    super(csvConnectionManager);
    this.productsCache = new ArrayList<>();
    try {
      this.productsCache = getAllProducts();
    } catch (Exception e) {
      System.err.println("Error loading initial products: " + e.getMessage());
    }
  }

  @Override
  protected String getTableName() {
    return "products";
  }

  public List<ProductCsvDTO> getAllProducts() throws Exception {
    if (!productsCache.isEmpty()) {
      return new ArrayList<>(productsCache);
    }
    
    return this.executeWithReader((reader)->{
      CsvToBeanBuilder<ProductCsvDTO> builder = new CsvToBeanBuilder<>(reader);

      return builder
        .withType(ProductCsvDTO.class)
        .build()
        .parse();
    });
  }

  public boolean saveProduct(ProductCsvDTO product) {
    try {
      // En un entorno real, esto escribiría al CSV
      // Pero como estamos usando un CSV fake, lo guardamos en memoria
      
      for (int i = 0; i < productsCache.size(); i++) {
        if (productsCache.get(i).getId().equals(product.getId())) {
          productsCache.set(i, product);
          return true;
        }
      }
      
      productsCache.add(product);
      return true;
    } catch (Exception e) {
      System.err.println("Error saving product: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  public List<ProductCsvDTO> searchProductsByName(String name) throws Exception {
    return this.executeWithReader((reader)->{
      CsvToBeanBuilder<ProductCsvDTO> products = new CsvToBeanBuilder<>(reader);

      List<ProductCsvDTO> productsList = products
        .withType(ProductCsvDTO.class)
        .build()
        .parse();


      return productsList.stream()
        .filter(product -> product.getName().equals(name))
        .toList();
    });
  }

  public ProductCsvDTO getProductById(String id) throws Exception {
    return this.executeWithReader((reader)->{
      List<ProductCsvDTO> products = new CsvToBeanBuilder<ProductCsvDTO>(reader)
        .withType(ProductCsvDTO.class)
        .build()
        .parse();

      return products.stream()
        .filter(product -> product.getId().equals(id))
        .findFirst()
        .orElse(null);
    });
  }

}
