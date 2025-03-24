package crud.app.inventory.domain_services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import crud.app.inventory.dtos.InventoryItem;
import crud.shared.dto.ProductCsvDTO;

@Mapper
public interface IProductCSVSerializer {
  IProductCSVSerializer INSTANCE = Mappers.getMapper(IProductCSVSerializer.class);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "shortDescription", target = "description")
  @Mapping(source = "price", target = "price")
  InventoryItem toDTO(ProductCsvDTO product);
}
