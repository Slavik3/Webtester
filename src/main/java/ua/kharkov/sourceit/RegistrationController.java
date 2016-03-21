package ua.kharkov.sourceit;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.kharkov.sourceit.Webtester.entity.AccauntRegistration;
import ua.kharkov.sourceit.Webtester.entity.Account;
import ua.kharkov.sourceit.Webtester.entity.AccauntRole;

@Controller
public class RegistrationController {
	
	static Logger log = Logger.getLogger(RegistrationController.class.getName());
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView  registration(@ModelAttribute Account account) {
		log.info("start registration");
		log.info("account==> " + account);
		final int ROLE_STUDENT = 4;
		String password = account.getPassword();
		String hashedPassword = BcryptHashing.BcryptHash(password);
		account.setPassword(hashedPassword);
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.addObject("user", account);
		
		Session session = sessionFactory.openSession(); 
		
		int accFromDB; 
		try {
			session.beginTransaction();
			session.save(account);
			Account accauntFromDB = (Account) session.createQuery("from Account as account where login= '"+account.getLogin()+"'").uniqueResult();
			AccauntRole accauntRole = new AccauntRole();
			accFromDB = accauntFromDB.getId();
			accauntRole.setIdAccount(accFromDB);
			accauntRole.setIdRole(ROLE_STUDENT);
			session.save(accauntRole);
		}
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			modelAndView.addObject("userExist","������������ � ����� ������� ��� ����������");
			modelAndView.setViewName("registrationUser");
			return modelAndView;
		}
		UUID uuid = UUID.randomUUID();
		log.info("uuid==> " + uuid);
		//insert uuid into table
		AccauntRegistration accountRegistration = new AccauntRegistration();
		accountRegistration.setIdAccount(accFromDB);
		accountRegistration.setHash(uuid.toString());
		System.out.println("accountRegistration==> "+accountRegistration);
		session.save(accountRegistration);
		session.getTransaction().commit();
		
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
		String message = "For the confirmation of this account, please, follow this link";
		SendMessage.send(account, uuid, "Registration", fullURL, message);
		modelAndView.addObject("userAdded","������������ ��������");
		modelAndView.setViewName("login");
		session.close();
		log.info("end registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/confirm/{uuid}")
	public ModelAndView  confirm(@PathVariable("uuid") String uuid) {
		System.out.println("uuid "+uuid);
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		AccauntRegistration accountRegistration = (AccauntRegistration) session.createQuery("from AccauntRegistration as accaunt_registration where hash = '"+uuid+"'").uniqueResult();
		System.out.println("accauntRegistration "+accountRegistration);
		int idAccount = accountRegistration.getIdAccount();
		Account account = (Account) session.createQuery("from Account as account where id = " + idAccount).uniqueResult();
		System.out.println("account"+account);
		account.setIsActive(true);
		System.out.println("account"+account);
		session.getTransaction().commit(); 
    	session.close();
		
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("ConfirmEmail","�� ����������� email");
		modelAndView.setViewName("login");
		return modelAndView;		
	}
	
	

}
