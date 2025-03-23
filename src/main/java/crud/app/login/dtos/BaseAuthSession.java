package crud.app.login.dtos;

import java.time.Instant;

import lombok.Data;

@Data
public class BaseAuthSession implements IAuthSession {
  private String id;
  private String userId;
  private Instant createdAt;
}
