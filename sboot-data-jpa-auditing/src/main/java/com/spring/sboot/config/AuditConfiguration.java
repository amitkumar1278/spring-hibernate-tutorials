package com.spring.sboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.spring.sboot.model.EntityAuditorAware;

@Configuration
@EnableJpaAuditing
public class AuditConfiguration {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new EntityAuditorAware();
	}
}
