package tw.noah.spring.boot.api.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 客製資料找不到錯誤
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {
  public DataNotFoundException(String msg){
    super(msg);
  }
}
