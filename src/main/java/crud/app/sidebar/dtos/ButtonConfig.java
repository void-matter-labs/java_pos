package crud.app.sidebar.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ButtonConfig {
  private String id;
  private String text;
  private boolean isActive;
}
