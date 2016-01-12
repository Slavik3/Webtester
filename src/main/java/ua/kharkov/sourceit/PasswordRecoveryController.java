package ua.kharkov.sourceit;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.kharkov.sourceit.Webtester.entity.Account;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class PasswordRecoveryController {
	
	static Logger log = Logger.getLogger(PasswordRecoveryController.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@RequestMapping(value = "/passwordRecoveryPage", method = RequestMethod.GET)
	public ModelAndView  passwordRecoveryPage(@ModelAttribute Account accaunt) {
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("passwordRecoveryPage");
		modelAndView.addObject("user", accaunt);//?
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/passwordRecovery", method = RequestMethod.GET)
	public ModelAndView  passwordRecovery(@ModelAttribute Account accaunt, ModelMap model) {
		log.debug("start recovery");
		ModelAndView modelAndView = new ModelAndView();	
		
		modelAndView.addObject("user", accaunt);
		String login = accaunt.getLogin();
		
		Session session = sessionFactory.openSession(); 
		Account accauntRecovery = null;
		accauntRecovery = (Account) session.createQuery("from Account as account where login= '"+login+"'").uniqueResult();
		log.debug("accauntDB "+accauntRecovery);
		String emailTo = accauntRecovery.getEmail();
		String passwordBCryptRec = accauntRecovery.getPassword();
		String nameRec = accauntRecovery.getLogin();
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
			message.setSubject("Password recovery");
			message.setText("Dear "+nameRec+", "
				+ "\nUour password is "+ passwordBCryptRec);

			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		modelAndView.setViewName("login");
		log.debug("password has been sent to email");
		model.addAttribute("passwordRecovery", "true");
		return modelAndView;
	}

}
