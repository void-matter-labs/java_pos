package utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseWriteCSVTester extends BaseCSVTester {
  private String originalFileContent;

  @BeforeEach
  void loadOriginalFileContent() throws IOException {
    try (FileReader fileReader = new FileReader(this.getCsvPath())) {
      StringWriter stringWriter = new StringWriter();
      int character;
      while ((character = fileReader.read()) != -1) {
        stringWriter.write(character);
      }
      originalFileContent = stringWriter.toString();
    }
  }

  @AfterEach
  void restoreOriginalFileContent() throws IOException {
    try (FileWriter fileWriter = new FileWriter(this.getCsvPath())) {
      fileWriter.write(originalFileContent);
    }
  }  
}
