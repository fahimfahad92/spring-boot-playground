package com.jonak.java17sb3.service;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;

/*
Fahim created at 1/29/2023
*/
@Service
public class WelcomeService {

  @Observed(name = "welcomeMethod")
  public String welcome(String name) {
    return String.format("Hello %s", name);
  }

  public String welcomeWithNoObserver(String name) {
    return String.format("Hello %s", name);
  }
}
