package com.softifyo.mgmt.config;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<User>{

	@Override
	public Optional<User> getCurrentAuditor() {
		//return ((Optional<User>) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return null;
	}

}
