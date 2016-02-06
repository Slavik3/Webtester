package ua.kharkov.sourceit;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.kharkov.sourceit.Webtester.entity.AccauntRegistration;
import ua.kharkov.sourceit.Webtester.entity.Account;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PasswordRecoveryController {
	
	static Logger log = Logger.getLogger(PasswordRecoveryController.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@Autowired
	private HttpServletRequest context;
	
	@RequestMapping(value = "/passwordRecoveryPage", method = RequestMethod.GET)
	public ModelAndView  passwordRecoveryPage() {
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("passwordRecoveryPage");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/passwordRecoveryMsg", method = RequestMethod.GET)
	public ModelAndView  passwordRecoveryMsg(@ModelAttribute Account accaunt) {
		log.debug("start recovery");
		ModelAndView modelAndView = new ModelAndView();	
		
		modelAndView.addObject("user", accaunt);
		String login = accaunt.getLogin();
		
		Session session = sessionFactory.openSession(); 
		Account accauntRecovery = (Account) session.createQuery("from Account as account where login= '"+login+"'").uniqueResult();
		log.debug("accauntDB "+accauntRecovery);
		
		UUID uuid = UUID.randomUUID();
		log.info("uuid==> " + uuid);
		
		AccauntRegistration accauntRegistration = new AccauntRegistration();
		accauntRegistration.setIdAccount(accauntRecovery.getId());
		accauntRegistration.setHash(uuid.toString());
		log.info("accauntRegistration==> " + accauntRegistration);
		session.save(accauntRegistration);
		
		String host = "";
		String url="";
		String port;
		host = context.getServerName()+":";
		log.info("host==> " + host);
		port = context.getServerPort()+"";
		log.info("port==> " + port);
		url = context.getContextPath()+"/";
		log.info("url==> " + url);
		String fullURL = "http://" + host + port + url + "confirm/";
		log.info("fullURL==> " + fullURL);
		String message = "To set a new password click on the link";
		SendMessage.send(accaunt, uuid, "Registration", fullURL, message);
		
		modelAndView.setViewName("login");
		log.info("Instructions for the password recovering was sent on your email");
		return modelAndView;
	}
	
	@RequestMapping(value = "/passwordRecovery", method = RequestMethod.GET)
	public ModelAndView  passwordRecovery() {//TODO
		return null;		
	}
	
	

}
