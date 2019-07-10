package com.leninmuller.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.leninmuller.cursomc.security.UseSS;

public class UserService {

	public static UseSS authenticated() {
		try {
			return (UseSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
