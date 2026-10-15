package com.rnd.java21springboot3jpa.user.controller;

import com.rnd.java21springboot3jpa.user.entity.Order;
import com.rnd.java21springboot3jpa.user.service.UserService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final UserService userService;

  @Autowired
  public OrderController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{userId}")
  public ResponseEntity<BigDecimal> getUserBalance(@PathVariable(name = "userId") Long userId) {
    return ResponseEntity.ok(userService.getUserBalance(userId));
  }

  @PostMapping("/{userId}/{orderId}")
  public ResponseEntity<Order> createOrderWithBalanceCheck(
      @PathVariable(name = "userId") Long userId, @PathVariable(name = "orderId") Long orderId) {
    return ResponseEntity.ok(userService.createOrderWithBalanceCheck(userId, orderId));
  }

  @PostMapping("/loadTest/{userId}/{orderId}")
  public ResponseEntity<Order> createOrderForLoadTest(
      @PathVariable(name = "userId") Long userId, @PathVariable(name = "orderId") Long orderId) {
    return ResponseEntity.ok(userService.createOrderForLoadTest(userId, orderId));
  }
}
