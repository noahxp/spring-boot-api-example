package tw.noah.spring.boot.api.example.control;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tw.noah.spring.boot.api.example.exception.MyRuntimeException;
import tw.noah.spring.boot.api.example.model.JsonModel;

/**
 * global error handler
 * 
 */
@RestController
public class ExceptionHandlerController {
  // if environment eq "dev" or "staging" , get detail exception.
	@Value("${my.environment}")
	private String environment;


	// internal 500 exception
	@RestControllerAdvice
	class InternalErrorException {
//		@ExceptionHandler(value = { Exception.class })
//		@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//		public JsonModel unknownException(Exception ex) {
//			JsonModel jsonModel;
//			if ("dev".equals(environment) || "staging".equals(environment)){
//				jsonModel = new JsonModel(ExceptionUtils.getStackTrace(ex));
//			}else{
//				jsonModel = new JsonModel("ERROR.");
//			}
//
//			return jsonModel;
//		}

		@ExceptionHandler(value = { Exception.class,MyRuntimeException.class,ServletRequestBindingException.class,MethodArgumentTypeMismatchException.class})
		public final ResponseEntity<JsonModel> handleAllExceptions(Exception ex) {
			JsonModel jsonModel;
			if ("dev".equals(environment) || "staging".equals(environment)){
				jsonModel = new JsonModel(ExceptionUtils.getStackTrace(ex));
			}else {
				jsonModel = new JsonModel("ERROR.");
			}

			if (ex instanceof MyRuntimeException){
				MyRuntimeException c = (MyRuntimeException)ex;
				return new ResponseEntity<>(jsonModel, c.getStatus());
			}else if (ex instanceof ServletRequestBindingException || ex instanceof  MethodArgumentTypeMismatchException){
				return new ResponseEntity<>(jsonModel, HttpStatus.BAD_REQUEST);
			}


			return new ResponseEntity<>(jsonModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 404 not found.
	@RestController
	class NotFoundException implements ErrorController {
		private static final String PATH = "/error";

		@RequestMapping(value = PATH)
		public JsonModel error() {
			return new JsonModel("404 Not Found!");
		}

		@Override
		public String getErrorPath() {
			return PATH;
		}
	}
}
