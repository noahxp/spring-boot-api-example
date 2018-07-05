package tw.noah.spring.boot.api.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value= "Response Format" ,description = "回傳固定結構")
public class JsonModel {
  /**
   * 結果資料集
   */
  @ApiModelProperty(value="執行結果集",required = false)
  private Object result;
  /**
   * 訊息, 可用 JsonMsg 正規
   */
  @ApiModelProperty(value="執行結果訊息",required = true)
  private String msg;

  public JsonModel() {
  }

  /**
   * @param msg 訊息串
   */
  public JsonModel(String msg) {
      this.msg = msg;
  }

  /**
   * @param msg    訊息串
   * @param result 結果資料集
   */
  public JsonModel(String msg, Object result) {
      this.msg = msg;
      this.result = result;
  }

  /**
   * @param msg    使用制式訊息串
   * @param result 結果資料集
   */
  public JsonModel(JsonMsg msg, Object result) {
      this.msg = msg.getDesc();
      this.result = result;
  }


  /**
   * 格式化訊息
   * @param msg
   */
  public void setMsg(JsonMsg msg){
    this.msg = msg.getDesc();
  }
}
