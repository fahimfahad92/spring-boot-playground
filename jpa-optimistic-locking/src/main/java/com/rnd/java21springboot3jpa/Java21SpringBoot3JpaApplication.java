package com.rnd.java21springboot3jpa;

import com.rnd.java21springboot3jpa.user.entity.User;
import com.rnd.java21springboot3jpa.user.service.UserService;
import io.micrometer.common.lang.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
    User user = userService.prepareData();
    int size = 1_000;
    List<Future> futures = new ArrayList<>();
    long startTime;
    try (ExecutorService vExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
      startTime = System.currentTimeMillis();
      for (int i = 0; i < size; i++) {
        int finalI = i;

        futures.add(vExecutor.submit(() -> userService.createOrder(user, finalI)));
      }
    }



    for (int i = 0; i < size; i++) {
      try {
        futures.get(i).get();
      } catch (ExecutionException | InterruptedException e) {
        System.out.println("Error for " + i);
      }
    }

    long endTime = System.currentTimeMillis();

    System.out.println("Total time " + (endTime - startTime) + " ms");

    //    userService.getData(user);
  }
}
