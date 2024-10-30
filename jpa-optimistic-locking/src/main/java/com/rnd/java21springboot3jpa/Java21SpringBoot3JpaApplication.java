package com.rnd.java21springboot3jpa;

import com.rnd.java21springboot3jpa.user.entity.Address;
import com.rnd.java21springboot3jpa.user.entity.Order;
import com.rnd.java21springboot3jpa.user.entity.User;
import com.rnd.java21springboot3jpa.user.service.UserService;
import io.micrometer.common.lang.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class Java21SpringBoot3JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java21SpringBoot3JpaApplication.class, args);
    }

}

@Component
class StartupApplicationListenerExample implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger =
            LoggerFactory.getLogger(StartupApplicationListenerExample.class);

    private final UserService userService;

    @Autowired
    StartupApplicationListenerExample(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        User user = userService.createUser("Fahim", "Fahad");
        Address address = userService.updateAddress(user.getId(), "Dhaka", "Dhaka");
        logger.info("Updating address done");

        Order order1 = userService.createOrder("Item1", 1, BigDecimal.valueOf(50), BigDecimal.valueOf(50), user.getId());
        Order order2 = userService.createOrder("Item2", 1, BigDecimal.valueOf(50), BigDecimal.valueOf(50), user.getId());

        User updatedUser = userService.getUserById(user.getId());

        logger.info("Getting address");
        Address updatedAddress = updatedUser.getAddress();
        logger.info("Getting address done");

        logger.info("Getting orders");
        List<Order> orders = updatedUser.getOrders();
        logger.info("Getting orders done");

        logger.info("DB operation completed");
    }
}