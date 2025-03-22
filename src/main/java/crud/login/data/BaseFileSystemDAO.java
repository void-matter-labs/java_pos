package crud.login.data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class BaseFileSystemDAO {
  protected String csvPath;

  protected void ensureFileEndsWithNewline() {
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
}
