package tw.noah.spring.boot.api.example.entity;

import java.util.Date;
import lombok.Data;

@Data
public class User {

  private String firstName;
  private String familyName;
  private Date birthday;
  private int weight;
}
