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
import org.springframework.web.servlet.ModelAndView;

import ua.kharkov.sourceit.Webtester.entity.Account;

public class SendMessage {
	
	static Logger log = Logger.getLogger(SendMessage.class.getName());
	
	public static String send(Account accaunt, UUID uuid, String subject, String fullURL, String message){
		log.debug("start send");
		ModelAndView modelAndView = new ModelAndView();	
		
		modelAndView.addObject("user", accaunt);
		
		log.debug("accauntDB "+accaunt);
		String emailTo = accaunt.getEmail();
		String nameRec = accaunt.getLogin();
		System.out.println("emailTo "+emailTo);
		final String username = "slavikkozlov3@gmail.com";
		final String password = "";

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
			Message mMessage = new MimeMessage(mailSession);
			mMessage.setFrom(new InternetAddress("slavikkozlov3@gmail.com"));
			mMessage.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailTo));
			mMessage.setSubject(subject);
			String link = "\n" + fullURL + uuid;
			mMessage.setText("Dear "+nameRec+", "
				+ "\n"+ message + link);
			Transport.send(mMessage);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		modelAndView.setViewName("login");
		log.debug("password has been sent to email");
	
		
		return ""; 		
	}

}
