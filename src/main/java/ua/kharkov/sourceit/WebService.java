package ua.kharkov.sourceit;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ua.kharkov.sourceit.Webtester.entity.Account;

@Path("/webservice") 
public class WebService {
	
	static Logger log = Logger.getLogger(WebService.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@GET
    @Path("/hello")
    @Produces("application/json")
    public List<Account> hello(){
		Session session = sessionFactory.openSession();
		List<Account> listAccount = session.createQuery("from Account as account").list();
		System.out.println("listAccount "+listAccount);
		//Collections.unmodifie
        return new ArrayList<Account>(listAccount);   
    }
	
	

}
