package tw.noah.spring.boot.api.example.control;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.spring.boot.api.example.model.JsonModel;
import tw.noah.spring.boot.api.example.model.JsonMsg;
import tw.noah.spring.boot.api.example.service.CustomerService;

@RestController
@RequestMapping("/apis")
public class CustomerController {

  @Resource
  private CustomerService customerService;


  // request url example : http://localhost:8080/apis/hi?user-name=noah
  @RequestMapping(value="/hi", produces="application/json; charset=UTF-8")
  @ResponseBody
  public JsonModel sayHi(@RequestParam("user-name") String userName){
    JsonModel ret = new JsonModel();
    ret.setResult(customerService.sayHi(userName));
    ret.setMsg(JsonMsg.Success);

    return ret;
  }

  // request url example : http://localhost:8080/apis/hi/noah
  @RequestMapping(value="/hi/{user-name}", produces="application/json; charset=UTF-8")
  @ResponseBody
  public JsonModel sayHello(@PathVariable("user-name") String userName){
    JsonModel ret = new JsonModel();
    ret.setResult(customerService.sayHi(userName));
    ret.setMsg(JsonMsg.Success);

    return ret;
  }
}
