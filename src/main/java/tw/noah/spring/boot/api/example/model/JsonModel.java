package tw.noah.spring.boot.api.example.model;

public class JsonModel {
    // 結果資料集
    private Object result;
    // 訊息
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
     * 取得結果資料集
     *
     * @return
     */
    public Object getResult() {
        return result;
    }

    /**
     * @param result 結果資料集
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 取得訊息
     *
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg 訊息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @param msg 訊息
     */
    public void setMsg(JsonMsg jsonMsg) {
        this.msg = jsonMsg.toString();
    }
}
