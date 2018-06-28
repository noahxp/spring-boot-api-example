package tw.noah.spring.boot.api.example.service.impl;

import org.springframework.stereotype.Service;
import tw.noah.spring.boot.api.example.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

  public String sayHi(String name) {
    return "Hi," + name;
  }
}
