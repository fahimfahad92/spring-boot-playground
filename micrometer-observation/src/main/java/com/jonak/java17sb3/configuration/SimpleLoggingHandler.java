package com.jonak.java17sb3.configuration;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/*
Fahim created at 1/29/2023
*/
public class SimpleLoggingHandler implements ObservationHandler<Observation.Context> {

  private static final Logger log = LoggerFactory.getLogger(SimpleLoggingHandler.class);

  @Override
  public boolean supportsContext(Observation.Context context) {
    return true;
  }

  @Override
  public void onStart(Observation.Context context) {
     log.info("Starting " + context.getName());
    context.put("time", System.currentTimeMillis());
  }

  @Override
  public void onScopeOpened(Observation.Context context) {
     log.info("Scope opened  " + context.getName());
  }

  @Override
  public void onScopeClosed(Observation.Context context) {
     log.info("Scope closed " + context.getName());
  }

  @Override
  public void onStop(Observation.Context context) {
    log.info(
        "Stopping "
            + context.getName()
            + " duration "
            + (System.currentTimeMillis() - context.getOrDefault("time", 0L)));
  }

  @Override
  public void onError(Observation.Context context) {
    log.error("Error " + Objects.requireNonNull(context.getError()).getMessage());
  }
}
