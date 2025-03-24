package crud.app.login.domain_services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import crud.app.login.dtos.BaseAuthUser;
import crud.shared.dto.UserCsvDTO;

@Mapper
public interface IUserCSVSerializer {
  IUserCSVSerializer INSTANCE = Mappers.getMapper(IUserCSVSerializer.class);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "username", target = "username")
  @Mapping(source = "password", target = "password")
  BaseAuthUser toDTO(UserCsvDTO user);
}
