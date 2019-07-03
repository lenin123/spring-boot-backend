package com.leninmuller.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.leninmuller.cursomc.domain.Pedido;

public interface EmailService {

	void sendConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);	
	
}
