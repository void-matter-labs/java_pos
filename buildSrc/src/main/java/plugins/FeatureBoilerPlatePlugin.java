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
  private static boolean checkIfEmpty(String string) {
    return Objects.requireNonNullElse(string, "").isBlank();
  }

  @Override
  public void apply(Project project) {
    project.getTasks().register("createFeat", task -> {
      task.doLast(innerTask -> {
        String featureName = (String) project.findProperty("featName");
        System.out.println("Feature name provided: " + featureName);
        if (FeatureBoilerPlatePlugin.checkIfEmpty(featureName)) {
          throw new GradleException("Please provide a feature name using -PfeatureName=<featureName>");
        }

        String featureNameCapitalized = capitalize(featureName);

        String baseJavaPath = "src/main/java/crud/app/" + featureName.toLowerCase();
        String baseResourcesPath = "src/main/resources/" + featureName.toLowerCase();

        new File(baseJavaPath).mkdirs();
        new File(baseResourcesPath).mkdirs();

        Map<String, String> boilerplates = new HashMap<>();
        boilerplates.put("boilerplates/Module.java.template",
            baseJavaPath + "/" + featureNameCapitalized + "Module.java");
        boilerplates.put("boilerplates/View.fxml.template", baseResourcesPath + "/" + featureNameCapitalized + ".fxml");
        boilerplates.put("boilerplates/styles.css.template", baseResourcesPath + "/styles.css");

        boilerplates.forEach((templatePath, destinationPath) -> {
          try (InputStream templateStream = getClass().getClassLoader().getResourceAsStream(templatePath)) {
            if (templateStream == null) {
              throw new GradleException("Template file not found in resources: " + templatePath);
            }

            String content = new String(templateStream.readAllBytes())
                .replace("${featureName}", featureName)
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

  private String capitalize(String input) {
    if (FeatureBoilerPlatePlugin.checkIfEmpty(input)) {
      return input;
    }
    return input.substring(0, 1).toUpperCase() + input.substring(1);
  }
}
