package ua.kharkov.sourceit;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.kharkov.sourceit.Webtester.entity.Account;

@Controller
public class AdminController {
	
	static Logger log = Logger.getLogger(AdminController.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@RequestMapping(value = "/editUserPage/{idAccaunt}")
	public ModelAndView  editUserPage(@PathVariable("idAccaunt") int idAccaunt) {
		log.debug("idAccaunt " + idAccaunt);
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("admin/editUser");
		Session session = sessionFactory.openSession(); 
		Account accountForEdit = (Account) session.createQuery("from Account as account where id = " + idAccaunt).uniqueResult();
		 modelAndView.addObject("user", accountForEdit);
		System.out.println("ok");
		log.debug("end editUserPage");
		return modelAndView;
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView  editUser(@RequestParam Integer idAccaunt, @RequestParam String login, @RequestParam String email) {
		ModelAndView modelAndView = new ModelAndView();
		log.debug("start editUser");
		log.debug("email " + email);
    	Session session = sessionFactory.openSession();  
    	session.beginTransaction();  
    	  
    	Account accaunt = (Account)session.get(Account.class, idAccaunt);  
    	accaunt.setLogin(login);
    	accaunt.setEmail(email);
    	session.getTransaction().commit(); 
    	
    	List<Account> listAccount = session.createQuery("from Account as account").list();
		modelAndView.addObject("listAccaunt", listAccount);
		modelAndView.setViewName("admin/adminPage");
    	
    	sessionFactory.close();
    	log.debug("Updated Successfully");
		return modelAndView;
	}
	
	@RequestMapping(value = "/remove/{idAccaunt}")
	public String  deleteUser(@PathVariable("idAccaunt") int idAccaunt) {
		log.debug("start deleteUser");
		log.debug("idAccaunt " + idAccaunt);
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		Query query = session.createQuery("delete Account where id = "+idAccaunt);
    	query.executeUpdate();
    	session.getTransaction().commit();
    	session.close();
    	log.debug("end deleteUser");
		return "redirect:/admin";
	}
	
	@RequestMapping(value = "/isActive/{idAccaunt}")
	public String  isActiveUser(@PathVariable("idAccaunt") int idAccaunt) {
		log.info("start active/deActive Account");
		log.info("idAccaunt " + idAccaunt);
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		Account account = (Account)session.get(Account.class, idAccaunt);
		boolean isActive = account.getIsActive();
		if(isActive == true) {
			account.setIsActive(false);
		}
		if(isActive == false) {
			account.setIsActive(true);
		}
		session.getTransaction().commit(); 
    	session.close();
    	log.info("end active/deActive Accaunt");
		return "redirect:/admin";
	}
	
	
	
}
