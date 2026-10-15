package com.rnd.java21springboot3jpa.user.db.configuration;

import java.time.Instant;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorResolver implements AuditorAware<String> {

  private static final Logger logger = LoggerFactory.getLogger(AuditorResolver.class);

  @Override
  public Optional<String> getCurrentAuditor() {
    //    logger.info("Getting auditor user");
    // TODO: GET current user from security context
    return Optional.of("Admin " + Instant.now().toString());
  }
}
