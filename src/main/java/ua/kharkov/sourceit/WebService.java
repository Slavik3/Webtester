package ua.kharkov.sourceit;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.kharkov.sourceit.Webtester.entity.Account;

@Controller
@RestController
public class WebService {
	
	static Logger log = Logger.getLogger(WebService.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET,headers="Accept=application/json")
	public List<Account> getCountries()	{
		Session session = sessionFactory.openSession();
		List<Account> listAccount = session.createQuery("from Account as account").list();
		log.debug("listAccount " + listAccount);
		return listAccount;
	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public Account getCountryById(@PathVariable int id)
	{
		List<Account> listOfCountries = new ArrayList<Account>();
		Session session = sessionFactory.openSession();
		List<Account> listAccount = session.createQuery("from Account as account").list();
		log.debug("listAccount " + listAccount);
		return listAccount.get(id);
	}
	
	

}
