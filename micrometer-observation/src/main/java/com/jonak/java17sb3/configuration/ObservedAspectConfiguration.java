package com.jonak.java17sb3.configuration;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Fahim created at 1/29/2023
*/
@Configuration
public class ObservedAspectConfiguration {

  @Bean
  public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
    observationRegistry.observationConfig().observationHandler(new SimpleLoggingHandler());

    return new ObservedAspect(observationRegistry);
  }
}
