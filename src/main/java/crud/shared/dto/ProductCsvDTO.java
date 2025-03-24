package crud.shared.dto;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCsvDTO {
  @CsvBindByName
  private String id;
  @CsvBindByName
  private String name;
  @CsvBindByName(column = "short_description")
  private String shortDescription;
  @CsvBindByName
  private double price;
  @CsvBindByName
  private String category;
  @CsvBindByName(column = "long_description")
  private String longDescription;
  @CsvBindByName
  private int quantity;
}
