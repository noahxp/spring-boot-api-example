package tw.noah.spring.boot.api.example.entity;

import java.util.Date;
import lombok.Data;

@Data
public class User {

  /**
   * user's first-name
   */
  private String firstName;
  /**
   * user's family-name
   */
  private String familyName;
  /**
   * user's birthday
   */
  private Date birthday;
  /**
   * user's weight
   */
  private int weight;
}
