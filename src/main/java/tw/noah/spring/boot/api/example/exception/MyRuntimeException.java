package tw.noah.spring.boot.api.example.exception;

import org.springframework.http.HttpStatus;

/**
 * 從程式端帶入需要的 http Status ，達到客製 error status
 */
public class MyRuntimeException extends RuntimeException {
  private Object status;



  public MyRuntimeException(HttpStatus httpStatus){
    this.status = httpStatus;
  }


  public HttpStatus getStatus(){
    return (HttpStatus) status;
  }
}
