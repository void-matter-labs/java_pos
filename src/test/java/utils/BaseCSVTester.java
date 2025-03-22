package utils;

import lombok.Getter;
import lombok.Setter;

public abstract class BaseCSVTester {

  @Setter
  @Getter
  private String csvPath;

  public BaseCSVTester() {
    initialize();
  }

  public String getFullCSVPath(String csvFileName) {
    return getClass()
      .getClassLoader()
      .getResource(csvFileName)
      .getPath();
  }

  abstract protected void initialize();

}
