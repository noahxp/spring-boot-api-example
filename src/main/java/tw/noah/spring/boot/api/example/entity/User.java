package tw.noah.spring.boot.api.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@ApiModel(value= "User Data" ,description = "使用者資料")
public class User {

  /**
   * user's first-name
   */
  @ApiModelProperty(value="user first name",required = true)
  private String firstName;
  /**
   * user's family-name
   */
  @ApiModelProperty(value="user family name",required = true)
  private String familyName;
  /**
   * user's birthday
   */
  @ApiModelProperty(value="user birthday",required = false ,example = "yyyy-mm-dd")
  private Date birthday;
  /**
   * user's weight
   */
  @ApiModelProperty(value="user weight",required = false)
  private int weight;
}
