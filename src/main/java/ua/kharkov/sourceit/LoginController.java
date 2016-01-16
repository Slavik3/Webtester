package ua.kharkov.sourceit;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.kharkov.sourceit.Webtester.entity.Account;
import ua.kharkov.sourceit.Webtester.entity.Role;
import ua.kharkov.sourceit.Webtester.entity.Test;

@Controller
public class LoginController {
	
	static Logger log = Logger.getLogger(LoginController.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(ModelMap model) {
		log.info("index");
        System.out.println("role--> "+SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        Collection collectionRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Iterator itr = collectionRole.iterator();
        
       // if(collectionRole.size()>1)
        	//send to max role?
        String returnPage = null;
        ModelAndView modelAndView= new ModelAndView();
        while(itr.hasNext()) {
        	Object element = itr.next();
            System.out.print("element "+element + " ");
            if(element.toString().equals("ROLE_ANONYMOUS")){
            	returnPage = "login";
            }
            if(element.toString().equals("ROLE_ADMIN")) {
            	returnPage = "admin";
            }
            if(element.toString().equals("ROLE_STUDENT"))
            	returnPage = "student";
            if(element.toString().equals("ROLE_TUTOR")) 
            	returnPage = "tutor";
        }
        log.info("returnPage " + returnPage);
        return "redirect:"+returnPage;
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        log.info("admin Page...");
        //String name = principal.getName(); 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.info("login--> " + login);
        ModelMap model = new ModelMap();
        model.addAttribute("name", login);
        
        Collection collectionRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Iterator itr = collectionRole.iterator();
        Object role = null;
        while(itr.hasNext()) {
        	role = itr.next();
        }
        model.addAttribute("role", role);
        
        Session session = sessionFactory.openSession();
		List<Account> listAccount = session.createQuery("from Account as account").list();
		log.info("accaunt==> " + listAccount);
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.addObject("listAccaunt", listAccount);
		modelAndView.setViewName("admin/adminPage");
		
		
        return modelAndView;
    }
    
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView studentPage(ModelMap model) {
        log.info("student Page...");
        
        Collection collectionRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Iterator itr = collectionRole.iterator();
        Object role = null;
        while(itr.hasNext()) {
        	role = itr.next();
        }
        model.addAttribute("role", role);   
        
        model.addAttribute("message", "student Page...");
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.info("login--> "+login);
        model.addAttribute("name", login);
		ModelAndView modelAndView = new ModelAndView();		
		
		Session session = sessionFactory.openSession();
		List<Test> listTest = session.createQuery("from Test as test").list();
		
		modelAndView.addObject("listTest", listTest);
		
		modelAndView.setViewName("student/studentPage");
        return modelAndView;
    }
    
    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @RequestMapping(value = "/tutor", method = RequestMethod.GET)
    public ModelAndView newTest(ModelMap model) {
        log.info("new test");
        
        Collection collectionRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Iterator itr = collectionRole.iterator();
        Object role = null;
        while(itr.hasNext()) {
        	role = itr.next();
        }
        model.addAttribute("role", role);   
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.info("login--> "+login);
        model.addAttribute("name", login);
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName("tutor/newTest");
        return modelAndView;
    }
    
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "login_error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
    	log.info("login");
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}

		if (logout != null) { //TODO print in jsp
			model.addObject("msg", "You've been logged out successfully.");
		}
		//if role = admin
		//model.setViewName("adminPage");
		
		model.setViewName("login");
		return model;
	}
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    	log.info("start logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        log.info("finish logout");
        return "login";
    }
    
    @RequestMapping(value = "/registrationPage", method = RequestMethod.POST)
	public ModelAndView  registrationPage(@ModelAttribute Account accaunt) {
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.addObject("user", accaunt);
		modelAndView.setViewName("registrationUser");
		return modelAndView;
	}
    
    
}
