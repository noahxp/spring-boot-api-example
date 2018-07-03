package tw.noah.spring.boot.api.example.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.spring.boot.api.example.entity.User;
import tw.noah.spring.boot.api.example.exception.DataNotFoundException;
import tw.noah.spring.boot.api.example.model.JsonModel;
import tw.noah.spring.boot.api.example.model.JsonMsg;
import tw.noah.spring.boot.api.example.service.CustomerService;

@Api(value = "所有的 api 請從 /apis 進入")
@RestController
@RequestMapping("/apis")
@Log4j2
public class CustomerController {

  @Resource
  private CustomerService customerService;


  // RequestMapping for all Http method or assign method by RequestMethod.XXXX
  // every method the default produces is MediaType.APPLICATION_JSON_VALUE , add the produces = MediaType.APPLICATION_JSON_UTF8_VALUE is for the swagger ui

  // @RequestMapping(value="/hi", produces="application/json; charset=UTF-8")
  // client example : http://localhost:8080/apis/hi?user-name=noah
  @ApiOperation(value = "跟 user-name 說 Hi" ,notes = "來說 hi 吧")

  @GetMapping(value ="/hi",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel sayHi(@RequestParam("user-name") String userName,@RequestParam(value = "age" ,required = false, defaultValue = "0") int age){
    JsonModel ret = new JsonModel();
    ret.setResult(customerService.sayHi(userName) + " , my age is " + age);
    ret.setMsg(JsonMsg.Success);

    return ret;
  }


  // client example : curl http://localhost:8080/apis/hi/noah
  // curl http://localhost:8080/apis/hi/ -> get Http 400
  //@RequestMapping(value="/hi/{user-name}", produces="application/json; charset=UTF-8")
  @GetMapping(value="/hi/{user-name}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel sayHello(@PathVariable(name = "user-name") String userName){
    JsonModel ret = new JsonModel();
    ret.setResult(customerService.sayHi(userName));
    ret.setMsg(JsonMsg.Success);
    return ret;
  }

  // curl http://localhost:8080/apis/age/aa -> get Http 400
  @GetMapping(value="/age/{age}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel sayAge(@PathVariable(name = "age") int age){
    JsonModel ret = new JsonModel();
    ret.setResult("my age is " + age);
    ret.setMsg(JsonMsg.Success);
    return ret;
  }


  // client example : curl http://localhost:8080/apis/hi/noah
  // curl http://localhost:8080/apis/hi/ -> get Http 400
  //@RequestMapping(value="/hi/{user-name}", produces="application/json; charset=UTF-8")
  @GetMapping(value="/ha",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel sayHa(){
    log.info("sayHa()..");
    throw new DataNotFoundException("Data Not Found!");
  }




  // client example : curl -X POST -H "Content-Type: application/json" -d "{\"firstName\":\"noah\",\"familyName\":\"liu\"}" http://localhost:8080/apis/hi
  @PostMapping(value="/hi",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel createHello(@RequestBody User user){
    JsonModel ret = new JsonModel();
    ret.setResult(user.getFirstName() + " " + user.getFamilyName() + " was born in " + user.getBirthday() + ", the weight is " + user.getWeight() +  " , the data was created.");
    ret.setMsg(JsonMsg.Success);

    return ret;
  }

  // client example : curl -X DEL -H "Content-Type: application/json" http://localhost:8080/apis/hi/peter
  @DeleteMapping(value="/hi/{user-name}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel deleteHello(@PathVariable(name = "user-name") String userName){
    JsonModel ret = new JsonModel();
    ret.setResult(userName + " has been deleted.");
    ret.setMsg(JsonMsg.Success);

    return ret;
  }
}
