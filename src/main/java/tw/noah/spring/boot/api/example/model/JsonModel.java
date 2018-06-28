package tw.noah.spring.boot.api.example.model;

import lombok.Data;

@Data
public class JsonModel {
  /**
   * 結果資料集
   */
  private Object result;
  /**
   * 訊息, 可用 JsonMsg 正規
   */
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
