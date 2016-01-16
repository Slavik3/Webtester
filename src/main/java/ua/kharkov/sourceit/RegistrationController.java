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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.kharkov.sourceit.Webtester.entity.AccauntRegistration;
import ua.kharkov.sourceit.Webtester.entity.Account;
import ua.kharkov.sourceit.Webtester.entity.AccauntRole;
import ua.kharkov.sourceit.Webtester.entity.Role;

@Controller
public class RegistrationController {
	
	static Logger log = Logger.getLogger(RegistrationController.class.getName());
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView  registration(@ModelAttribute Account accaunt, @RequestParam("role") String role) {
		log.info("start registration");
		log.info("accaunt==> " + accaunt);
		log.info("role==> " + role);
		String password = accaunt.getPassword();
		String hashedPassword = BcryptHashing.BcryptHash(password);
		accaunt.setPassword(hashedPassword);
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.addObject("user", accaunt);
		
		Session session = sessionFactory.openSession(); 
		
		//Role roleDB = (Role) session.createQuery("from Role as role where name= '"+role+"'").uniqueResult();
		Role roleDB = (Role) session.createQuery("from Role as role where name like '%"+role+"'").uniqueResult();
		log.debug("roleDB==> " + roleDB);
		int accFromDB; 
		try {
			session.beginTransaction();
			session.save(accaunt);
			Account accauntFromDB = (Account) session.createQuery("from Account as account where login= '"+accaunt.getLogin()+"'").uniqueResult();
			AccauntRole accauntRole = new AccauntRole();
			accFromDB = accauntFromDB.getId();
			accauntRole.setIdAccount(accFromDB);
			accauntRole.setIdRole(roleDB.getId());
			session.save(accauntRole);
		}
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			modelAndView.addObject("userExist","Пользователь с таким логином уже существует");
			modelAndView.setViewName("registrationUser");
			return modelAndView;
		}
		UUID uuid = UUID.randomUUID();
		log.info("uuid==> " + uuid);
		//insert uuid into table
		AccauntRegistration accauntRegistration = new AccauntRegistration();
		accauntRegistration.setIdAccount(accFromDB);
		accauntRegistration.setHash(uuid.toString());
		System.out.println("accauntRegistration==> "+accauntRegistration);
		session.save(accauntRegistration);
		session.getTransaction().commit();
		System.out.println("context "+context.getRequestURI());//
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
		SendMessage.send(accaunt, uuid, "Registration", fullURL);
		modelAndView.addObject("userAdded","Пользователь добавлен");
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
		AccauntRegistration accauntRegistration = (AccauntRegistration) session.createQuery("from AccauntRegistration as accaunt_registration where hash = '"+uuid+"'").uniqueResult();
		System.out.println("accauntRegistration "+accauntRegistration);
		int idAccount = accauntRegistration.getIdAccount();
		Account account = (Account) session.createQuery("from Account as account where id = " + idAccount).uniqueResult();
		System.out.println("account"+account);
		account.setIsActive(true);
		System.out.println("account"+account);
		session.getTransaction().commit(); 
    	session.close();
		
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("ConfirmEmail","Вы подтвердили email");
		modelAndView.setViewName("login");
		return modelAndView;		
	}
	
	

}
