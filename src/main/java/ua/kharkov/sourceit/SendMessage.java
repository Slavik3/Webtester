package ua.kharkov.sourceit;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.web.servlet.ModelAndView;

import ua.kharkov.sourceit.Webtester.entity.Account;

public class SendMessage {
	
	static Logger log = Logger.getLogger(SendMessage.class.getName());
	
	public static String send(Account accaunt, UUID uuid, String subject){

		log.debug("start send");
		ModelAndView modelAndView = new ModelAndView();	
		
		modelAndView.addObject("user", accaunt);
		String login = accaunt.getLogin();
		
		
		
		log.debug("accauntDB "+accaunt);
		String emailTo = accaunt.getEmail();
		String passwordBCryptRec = accaunt.getPassword();
		String nameRec = accaunt.getLogin();
		System.out.println("emailTo "+emailTo);
		final String username = "slavikkozlov3@gmail.com";
		final String password = "682440qwerty";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		javax.mail.Session mailSession = javax.mail.Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress("slavikkozlov3@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailTo));
			message.setSubject(subject);
			String link = "\n http://Webtester/confirm/"+uuid;
			message.setText("Dear "+nameRec+", "
				+ "\nUour password is "+ passwordBCryptRec + "For the confirmation of this account, please, follow this link" + link);
//String content="<html><body><a href='www.abc.com\\activation?hash="+i+"\">click here</a> </body></html>"
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		modelAndView.setViewName("login");
		log.debug("password has been sent to email");
	
		
		return ""; 		
	}

}
