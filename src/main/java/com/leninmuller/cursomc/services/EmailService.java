package com.leninmuller.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.leninmuller.cursomc.domain.Pedido;

public interface EmailService {

	void sendConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);	
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
}
