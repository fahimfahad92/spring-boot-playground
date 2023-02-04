package com.jonak.java17sb3.controller;

import com.jonak.java17sb3.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
Fahim created at 1/11/2023
*/
@RestController
@RequestMapping("/welcome")
public class WelcomeController {
  private final WelcomeService welcomeService;

  @Autowired
  public WelcomeController(WelcomeService welcomeService) {
    this.welcomeService = welcomeService;
  }

  @GetMapping
  public ResponseEntity<String> welcome(@RequestParam("name") String name) {
    return ResponseEntity.ok(welcomeService.welcome(name));
  }

  @GetMapping("/noObserve")
  public ResponseEntity<String> welcomeNoObserve(@RequestParam("name") String name) {
    return ResponseEntity.ok(welcomeService.welcomeWithNoObserver(name));
  }
}
