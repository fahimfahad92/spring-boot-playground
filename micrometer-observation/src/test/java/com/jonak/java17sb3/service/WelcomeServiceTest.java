package com.jonak.java17sb3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WelcomeServiceTest {
  WelcomeService welcomeService = new WelcomeService();

  @Test
  void testWelcome() {
    String result = welcomeService.welcome("Fahim");
    Assertions.assertEquals("Hello Fahim", result);
  }

  @Test
  void testWelcomeWithNoObserver() {
    String result = welcomeService.welcome("Fahim");
    Assertions.assertEquals("Hello Fahim", result);
  }
}


