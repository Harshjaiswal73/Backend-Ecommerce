package com.example.shopping.management.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.shopping.management.Entity.Email;
import com.example.shopping.management.Entity.Order;
import com.example.shopping.management.Entity.User;
import com.example.shopping.management.Repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Value("${spring.mail.username}")
    private String fromEmail;
	
	public void sendOrderConfirmationEmail(User user, Order order) {
		
		String subject = "Order Confirmation";
		
		String body = """
				 Dear %s,

                Your order has been placed successfully.

                Order ID : %d

                Total Amount : ₹%.2f

                Status : %s

                Thank you for shopping with us.

                Regards,
                Shopping Management Team
                """
			    .formatted(
                        user.getFirstname(),
                        order.getId(),
                        order.getTotalAmount(),
                        order.getOrderStatus()
                );
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(user.getEmail());
		message.setSubject(subject);
		message.setText(body);
		
		Email email = new Email();
		email.setToEmail(user.getEmail());
		email.setSubject(subject);
		email.setBody(body);
		
		try{
			javaMailSender.send(message);
			email.setStatus("Success");
		}catch(Exception e) {
			email.setStatus("Failed");
		}
		emailRepository.save(email);
	}
	
}
