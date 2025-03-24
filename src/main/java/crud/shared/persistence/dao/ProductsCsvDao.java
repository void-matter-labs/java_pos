package crud.shared.persistence.dao;

import java.util.List;

import com.google.inject.Inject;
import com.opencsv.bean.CsvToBeanBuilder;

import crud.shared.dto.ProductCsvDTO;
import crud.shared.persistence.datasource.ICSVConnectionManager;

public class ProductsCsvDao extends BaseCsvDAO {

  @Inject
  protected ProductsCsvDao(ICSVConnectionManager csvConnectionManager) {
    super(csvConnectionManager);
  }

  @Override
  protected String getTableName() {
    return "products";
  }

  public List<ProductCsvDTO> getAllProducts() throws Exception {
    return this.executeWithReader((reader)->{
      CsvToBeanBuilder<ProductCsvDTO> builder = new CsvToBeanBuilder<>(reader);

      return builder
        .withType(ProductCsvDTO.class)
        .build()
        .parse();
    });
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
