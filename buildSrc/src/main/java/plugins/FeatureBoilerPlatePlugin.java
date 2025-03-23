package plugins;

import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FeatureBoilerPlatePlugin implements Plugin<Project> {
  @Override
  public void apply(Project project) {
    project.getTasks().register("createFeat", task -> {
      task.doLast(innerTask -> {
        String featureName = (String) project.findProperty("featName");
        System.out.println("Feature name provided: " + featureName);
        if (FeatureBoilerPlatePlugin.isBlank(featureName)) {
          throw new GradleException("Please provide a feature name using -PfeatureName=<featureName>");
        }

        String featureNameCapitalized = FeatureBoilerPlatePlugin.capitalize(featureName);
        String featureNamePascalized = FeatureBoilerPlatePlugin.pascalize(featureName);

        String baseJavaPath = "src/main/java/crud/app/" + featureNamePascalized;
        String baseResourcesPath = "src/main/resources/app/" + featureNamePascalized;
        String baseControllerPath = baseJavaPath + "/controllers";

        new File(baseJavaPath).mkdirs();
        new File(baseResourcesPath).mkdirs();
        new File(baseControllerPath).mkdirs();

        Map<String, String> boilerplates = new HashMap<>();
        boilerplates.put("boilerplates/Module.java.template",
            baseJavaPath + "/" + featureNameCapitalized + "Module.java");
        boilerplates.put("boilerplates/MainController.java.template",
            baseControllerPath + "/" + featureNameCapitalized + "MainController.java");
        boilerplates.put("boilerplates/View.fxml.template", baseResourcesPath + "/" + featureNameCapitalized + ".fxml");
        boilerplates.put("boilerplates/styles.css.template", baseResourcesPath + "/styles.css");

        boilerplates.forEach((templatePath, destinationPath) -> {
          try (InputStream templateStream = getClass().getClassLoader().getResourceAsStream(templatePath)) {
            if (templateStream == null) {
              throw new GradleException("Template file not found in resources: " + templatePath);
            }

            String content = new String(templateStream.readAllBytes())
                .replace("${featureName}", featureNamePascalized )
                .replace("${featureNameCapitalized}", featureNameCapitalized);

            File destinationFile = project.file(destinationPath);
            Files.write(destinationFile.toPath(), content.getBytes(), StandardOpenOption.CREATE);
            System.out.println("Created: " + destinationFile);
          } catch (IOException e) {
            throw new GradleException("Error processing template file: " + templatePath, e);
          }
        });
      });
    });
  }

  private static boolean isBlank(String input) {
    return Objects.requireNonNullElse(input, "").isBlank();
  }

  private static String capitalize(String input) {
    if (FeatureBoilerPlatePlugin.isBlank(input)) {
      return input;
    }
    String[] parts = input.split("_");
    StringBuilder result = new StringBuilder();
    for (String part : parts) {
      result.append(part.substring(0, 1).toUpperCase())
          .append(part.substring(1).toLowerCase());
    }
    return result.toString();
  }

  private static String pascalize(String input) {
    if (FeatureBoilerPlatePlugin.isBlank(input)) {
      return input;
    }
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (Character.isUpperCase(c) && i > 0) {
        result.append("_");
      }
      result.append(Character.toLowerCase(c));
    }
    return result.toString();
  }
}
