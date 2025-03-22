package crud.login.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import crud.login.dtos.FileSystemUserDTO;
import crud.login.models.FileSystemUser;
@Mapper
public interface FileSystemUserMapper {
  FileSystemUserMapper INSTANCE = Mappers.getMapper(FileSystemUserMapper.class);

  @Mapping(target = "role", ignore = true)
  FileSystemUser toEntity(FileSystemUserDTO dto);

  FileSystemUserDTO toDTO(FileSystemUser entity);
}
