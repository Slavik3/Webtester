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
	
	@RequestMapping(value = "/editUserPage/{idAccount}")
	public ModelAndView  editUserPage(@PathVariable("idAccount") int idAccount) {
		log.debug("idAccaunt " + idAccount);
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("admin/editUser");
		Session session = sessionFactory.openSession(); 
		Account accountForEdit = (Account) session.createQuery("from Account as account where id = " + idAccount).uniqueResult();
		modelAndView.addObject("user", accountForEdit);
		log.debug("end editUserPage");
		return modelAndView;
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView  editUser(@RequestParam Integer idAccount, @RequestParam String login, @RequestParam String email) {
		ModelAndView modelAndView = new ModelAndView();
		log.debug("start editUser");
		log.debug("email " + email);
    	Session session = sessionFactory.openSession();  
    	session.beginTransaction();  
    	  
    	Account account = (Account)session.get(Account.class, idAccount);  
    	account.setLogin(login);
    	account.setEmail(email);
    	session.getTransaction().commit(); 
    	
    	List<Account> listAccount = session.createQuery("from Account as account").list();
		modelAndView.addObject("listAccaunt", listAccount);
		modelAndView.setViewName("admin/adminPage");
    	
    	sessionFactory.close();
    	log.debug("Updated Successfully");
		return modelAndView;
	}
	
	@RequestMapping(value = "/remove/{idAccount}")
	public String  deleteUser(@PathVariable("idAccount") int idAccount) {
		log.debug("start deleteUser");
		log.debug("idAccount " + idAccount);
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		Query query = session.createQuery("delete Account where id = "+idAccount);
    	query.executeUpdate();
    	session.getTransaction().commit();
    	session.close();
    	log.debug("end deleteUser");
		return "redirect:/admin";
	}
	
	@RequestMapping(value = "/isActive/{idAccount}")
	public String  isActiveUser(@PathVariable("idAccount") int idAccount) {
		log.info("start active/deActive Account");
		log.info("idAccount " + idAccount);
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		Account account = (Account)session.get(Account.class, idAccount);
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
