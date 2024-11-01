package com.rnd.java21springboot3jpa.user.db.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorResolver")
public class AuditingConfig {}
