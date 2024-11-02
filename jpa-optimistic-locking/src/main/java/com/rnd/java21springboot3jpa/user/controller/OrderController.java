package com.rnd.java21springboot3jpa.user.controller;

import com.rnd.java21springboot3jpa.user.service.UserService;
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

    @PostMapping("/{userId}/{orderId}")
    public ResponseEntity<Boolean> createOrder(@PathVariable(name = "userId") Long userId, @PathVariable(name = "orderId") Long orderId) {
        return ResponseEntity.ok(userService.createOrder(userId, orderId));
    }
}
