package crud.login.data;

import java.util.List;

import crud.login.dtos.FileSystemUserDTO;

@FunctionalInterface
public interface IUserFilteringStrategy {
  FileSystemUserDTO filter(List<FileSystemUserDTO> users, String identifier);
}
