package com.rnd.java21springboot3jpa;

import com.rnd.java21springboot3jpa.user.entity.User;
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

  //  @Bean(name = "taskExecutor")
  //  public Executor taskExecutor() {
  //    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
  //    executor.setCorePoolSize(10);
  //    executor.setMaxPoolSize(500);
  //    executor.setQueueCapacity(500);
  //    executor.setThreadNamePrefix("RndJpa-TaskExecutor-");
  //    executor.initialize();
  //    return executor;
  //  }
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
    userService.getData(user);
  }
}
