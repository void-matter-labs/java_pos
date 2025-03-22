package crud.login.data;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;
import java.io.File;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import crud.login.dtos.FileSystemUserDTO;
import lombok.Setter;

public class FileSystemUserDAO {
  private static final IUserFilteringStrategy defaultFilteringByIdStrategy = (users, id) -> users
      .stream()
      .filter(user -> user.getId().equals(id))
      .findFirst()
      .orElse(null);

  private static final IUserFilteringStrategy defaultFilteringByNameStrategy = (users, username) -> users
      .stream()
      .filter(user -> user.getUsername().equals(username))
      .findFirst()
      .orElse(null);

  private final String csvPath;

  @Setter
  private IUserFilteringStrategy filteringByIdStrategy = defaultFilteringByIdStrategy;

  @Setter
  private IUserFilteringStrategy filteringByNameStrategy = defaultFilteringByNameStrategy;

  @Inject
  public FileSystemUserDAO(@Named("FileSystemUserCSV") String csvPath) {
    this.csvPath = Objects.requireNonNull(csvPath);

    // Ensure the file ends with a newline
    ensureFileEndsWithNewline();
  }

  private void ensureFileEndsWithNewline() {
    try (FileReader fileReader = new FileReader(csvPath)) {
      long fileLength = new File(csvPath).length();
      if (fileLength > 0) {
        fileReader.skip(fileLength - 1); // Move to the last character
        int lastChar = fileReader.read();
        if (lastChar != '\n') {
          try (FileWriter writer = new FileWriter(csvPath, true)) {
            writer.write(System.lineSeparator()); // Append a newline
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Error ensuring file ends with a newline", e);
    }
  }

  public List<FileSystemUserDTO> getAllUsers() {
    try (FileReader reader = new FileReader(csvPath)) {
      return new CsvToBeanBuilder<FileSystemUserDTO>(reader)
          .withType(FileSystemUserDTO.class)
          .build()
          .parse();

    } catch (Exception e) {
      throw new RuntimeException("Error reading csv file", e);
    }
  }

  public FileSystemUserDTO getUserById(String id) {
    return this.filteringByIdStrategy.filter(getAllUsers(), id);
  }

  public FileSystemUserDTO getUserByName(String username) {
    return this.filteringByNameStrategy.filter(getAllUsers(), username);
  }

  public void addNewUser(FileSystemUserDTO user) {
    try (FileWriter writer = new FileWriter(csvPath, true);
        CSVWriter csvWriter = new CSVWriter(writer)) {
      String[] csvRow = {
          user.getId(),
          user.getUsername(),
          user.getPassword(),
          user.getPermissionId()
      };

      csvWriter.writeNext(csvRow);
    } catch (Exception e) {
      throw new RuntimeException("Error appending user to CSV file", e);
    }
  }

  public void updateUser(FileSystemUserDTO updatedUser) {
    try {
      List<FileSystemUserDTO> users = getAllUsers();

      boolean userFound = false;
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getId().equals(updatedUser.getId())) {
          users.set(i, updatedUser);
          userFound = true;
          break;
        }
      }

      if (!userFound) {
        throw new RuntimeException("User with ID " + updatedUser.getId() + " not found.");
      }

      try (FileWriter writer = new FileWriter(csvPath)) {
        StatefulBeanToCsvBuilder<FileSystemUserDTO> builder = new StatefulBeanToCsvBuilder<>(writer);
        builder
            .withSeparator(',')
            .withApplyQuotesToAll(false)
            .build()
            .write(users);
      }
    } catch (Exception e) {
      throw new RuntimeException("Error updating user in CSV file", e);
    }
  }
}
