package com.rnd.java21springboot3jpa;

import com.rnd.java21springboot3jpa.user.service.UserService;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Java21SpringBoot3JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java21SpringBoot3JpaApplication.class, args);
    }

}

@Component
class StartupApplicationListenerExample implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;

    @Autowired
    StartupApplicationListenerExample(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        userService.process();
        userService.getData();
    }
}