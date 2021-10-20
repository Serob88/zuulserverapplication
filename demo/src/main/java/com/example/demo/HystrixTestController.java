package com.example.demo;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableHystrix
@RestController
public class HystrixTestController {

  @RequestMapping(value = "/hystrix-test")
  @HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
  })
  public String hello() throws InterruptedException {
    Thread.sleep(300);
    return "Welcome Hystrix";
  }

  private String fallback_hello() {
    return "Request fails. It takes long time to response";
  }
}
