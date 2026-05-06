package com.softifyo.mgmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditorConfig {

	@Bean
	public AuditorAware<?> auditorAware() {
		return new AuditorAwareImpl();
	}
}
