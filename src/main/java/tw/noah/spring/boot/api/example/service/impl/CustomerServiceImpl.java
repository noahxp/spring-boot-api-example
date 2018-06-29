package tw.noah.spring.boot.api.example.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tw.noah.spring.boot.api.example.service.CustomerService;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {

  public String sayHi(String name) {
    log.info("CustomerService.sayHi have been call.[" + name + "]");
    return "Hi," + name;
  }
}
