package crud.login.data;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

import crud.login.dtos.FileSystemRoleDTO;

public class FileSystemRoleDAO extends BaseFileSystemDAO {

  public FileSystemRoleDAO(String csvPath) {
    this.csvPath = Objects.requireNonNull(csvPath);
    this.ensureFileEndsWithNewline();
  }

  public List<FileSystemRoleDTO> getAllRoles() {
    try (FileReader reader = new FileReader(csvPath)) {
      return new CsvToBeanBuilder<FileSystemRoleDTO>(reader)
          .withType(FileSystemRoleDTO.class)
          .build()
          .parse();

    } catch (Exception e) {
      throw new RuntimeException("Error reading csv file", e);
    }    
  }

  public void addNewRole(FileSystemRoleDTO role) {
    try (FileWriter writer = new FileWriter(csvPath, true);
      CSVWriter csvWriter = new CSVWriter(writer)) {
      String[] csvRow = {
        role.getId(),
        role.getName(),
        role.getDescription()
      };

      csvWriter.writeNext(csvRow);
    } catch (Exception e) {
      throw new RuntimeException("Error appending role to CSV file", e);
    }
  }

}
