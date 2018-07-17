package tw.noah.spring.boot.api.example.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.spring.boot.api.example.entity.User;
import tw.noah.spring.boot.api.example.exception.DataNotFoundException;
import tw.noah.spring.boot.api.example.model.JsonModel;
import tw.noah.spring.boot.api.example.model.JsonMsg;
import tw.noah.spring.boot.api.example.service.CustomerService;

/**
 * swagger 的 annotation 官方文件 : https://github.com/swagger-api/swagger-core/wiki/annotations-1.5.x#api
 * RequestMapping 可以支援所有的 method , 可用 RequestMethod 設定，等同 XXXMethod , 如 RequestMethod.GET 同 @GetMapping
 * 預設的 RESTful content-type 為 : MediaType.APPLICATION_JSON_VALUE ，但為 swagger 有完整格式判斷，還是會建議加上 produces = MediaType.APPLICATION_JSON_UTF8_VALUE 的設定
 */
@Api(description = "主要 apis ")
@RestController
@RequestMapping("/apis")
@Log4j2
public class CustomerController {

  @Resource
  private CustomerService customerService;



  @ApiOperation(value = "跟 user-name 說 Hi(query-string example)" ,notes = "用 query-string 說 hi 吧")
//  @ApiImplicitParams({  //ApiImplicitParams 可用 @ApiParam 取代
//      @ApiImplicitParam(name = "user-name", value = "使用者名稱", required = true, dataType = "string", paramType = "query"),
//      @ApiImplicitParam(name = "age", value = "使用者年齡", required = false, dataType = "int", paramType = "query")
//  })
  @GetMapping(value ="/hi",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel sayHi(@ApiParam(value="user's name",required = true) @RequestParam("user-name") String userName
                          ,@ApiParam(value="user's age") @RequestParam(value = "age" ,required = false, defaultValue = "0") int age){
    JsonModel ret = new JsonModel();
    ret.setResult(customerService.sayHi(userName) + " , my age is " + age);
    ret.setMsg(JsonMsg.Success);

    return ret;
  }


  @ApiOperation(value = "跟 user-name 說 Hi (path param example" ,notes = "用 path param 說 hi 吧")
  @GetMapping(value="/hi/{user-name}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel sayHello(@ApiParam(value="user's name",required = true) @PathVariable(name = "user-name") String userName){
    if ("joe".equals(userName)){
      throw new DataNotFoundException(userName + " is not found");
    }

    JsonModel ret = new JsonModel();
    ret.setResult(customerService.sayHi(userName));
    ret.setMsg(JsonMsg.Success);
    return ret;
  }

  @ApiOperation(value="年齡宣告!",notes = "跟你說你幾歲，我就會回答你幾歲")
  @GetMapping(value="/age/{age}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel sayAge(@ApiParam(value="your's age") @PathVariable(name = "age") int age){
    JsonModel ret = new JsonModel();
    ret.setResult("my age is " + age);
    ret.setMsg(JsonMsg.Success);
    return ret;
  }

  @ApiOperation(value="生日計算年齡!",notes = "跟你說你你的生日，我就會回答你年齡")
  @GetMapping(value="/birthday/{birthday}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel sayBirthday(@ApiParam(value="your's birthday", example = "yyyy-MM-dd") @PathVariable(name = "birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday){

    log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(birthday));

    int year = Period.between(birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ).getYears();
    Map<String , Object> map = new HashMap<>();
    map.put("age",year);
    map.put("birthday",birthday);

    JsonModel ret = new JsonModel();
//    ret.setResult("your age is " + year);
    ret.setResult(map);
    ret.setMsg(JsonMsg.Success);
    return ret;
  }


  @ApiOperation(value="Ha一下",notes = "除了 Hi ，還有 Ha")
  @ApiResponses(value = {
      @ApiResponse(code = 404, message = "Data not found") })
  @GetMapping(value="/ha",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel sayHa(){
    log.info("sayHa()..");
    throw new DataNotFoundException("Data Not Found!");
  }



  @ApiOperation(value="用hi建資料",notes = "hi也能建資料?")
  @PostMapping(value="/hi",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel createHello(@ApiParam(value="使用者資料",required = true) @RequestBody User user){
    JsonModel ret = new JsonModel();
    ret.setResult(user.getFirstName() + " " + user.getFamilyName() + " was born in " + user.getBirthday() + ", the weight is " + user.getWeight() +  " , the data was created.");
    ret.setMsg(JsonMsg.Success);

    return ret;
  }

  @ApiOperation(value="會員資料修改",notes = "hi也能修改資料?")
  @PutMapping(value="/hi",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel modifyHello(@ApiParam(value="使用者資料",required = true) @RequestBody User user){
    JsonModel ret = new JsonModel();
    ret.setResult(user.getFirstName() +  " 資料修改完成!");
    ret.setMsg(JsonMsg.Success);

    return ret;
  }

  @ApiOperation(value="刪除使用者資料",notes = "請輸入要刪除的使用者名稱")
  @DeleteMapping(value="/hi/{user-name}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel deleteHello(@ApiParam(value="要刪的會員名稱") @PathVariable(name = "user-name") String userName){

    if ("joe".equals(userName)){
      throw new DataNotFoundException("Data not found. can't delete...");
    }

    JsonModel ret = new JsonModel();
    ret.setResult(userName + " has been deleted.");
    ret.setMsg(JsonMsg.Success);

    return ret;
  }
}
