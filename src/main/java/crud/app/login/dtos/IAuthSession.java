package crud.app.login.dtos;

import java.time.Instant;

public interface IAuthSession {
  public String getId();
  public Instant getCreatedAt();
  public String getUserId();

  public void setId(String id);
  public void setCreatedAt(Instant createdAt);
  public void setUserId(String userId);
}
