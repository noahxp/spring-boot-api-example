package tw.noah.spring.boot.api.example.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class MyRuntimeException extends RuntimeException {
  private Object status;



  public MyRuntimeException(HttpStatus httpStatus){
    this.status = httpStatus;
  }


  public HttpStatus getStatus(){
    return (HttpStatus) status;
  }
}
