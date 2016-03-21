package ua.kharkov.sourceit;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.kharkov.sourceit.Webtester.entity.Account;
import ua.kharkov.sourceit.Webtester.entity.Answer;
import ua.kharkov.sourceit.Webtester.entity.Question;
import ua.kharkov.sourceit.Webtester.entity.TestRezult;


@Controller
public class StudentController {
	
	static Logger log = Logger.getLogger(StudentController.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView viewSettings() {
		log.info("start viewSettings");
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
        log.info("login==> "+login);
        Session session = sessionFactory.openSession(); 
        
        Account accaunt = (Account) session.createQuery("from Account as account where login = "+"'"+login+"'").uniqueResult();
        System.out.println("accaunt "+accaunt);
        modelAndView.addObject("accaunt", accaunt);
        
		modelAndView.setViewName("student/about");
    	log.info("end viewSettings");
		return modelAndView;
	}
	
	int countQuestion=0;
	int id;
	int idAccount;
	
	@RequestMapping(value = "/passTheTest/{id}", method = RequestMethod.GET)
	public ModelAndView passTheTest(@PathVariable("id") int idTest) {
		log.info("start passTheTest");
		log.info("idTest " + idTest);
		ModelAndView modelAndView = new ModelAndView();
		Session session = sessionFactory.openSession();  
    	session.beginTransaction();  
		List<Question> listQuestion = session.createQuery("from Qwastion as qwastion where id_test="+"'"+idTest+"'").list();
		
		Question questionObj = listQuestion.get(countQuestion);
		
		id = idTest;
		countQuestion++;
		int listQuestionSize = listQuestion.size();
		
		modelAndView.addObject("qwastion", questionObj.getName());
		List<Answer> listAnswer = session.createQuery("from Answer as answer where 	id_qwastion="+"'"+questionObj.getId()+"'").list();
		modelAndView.addObject("listAnswer", listAnswer);
		
		if(countQuestion < listQuestionSize) {
			modelAndView.addObject("next", "Next");
		}
		if(countQuestion == listQuestionSize) {
			modelAndView.addObject("end", "End");
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    Account accaunt = (Account) session.createQuery("from Account as account where login = "+"'"+login+"'").uniqueResult();
	    session.close();
	    ModelMap model = new ModelMap();
		model.addAttribute("name", accaunt.getLogin());
		
		idAccount = accaunt.getId();
		
		modelAndView.setViewName("student/question");
		
		modelAndView.addObject("countQuestion", countQuestion);
		
		log.info("end passTheTest");
		return modelAndView;
	}
	
	int correctCount = 0;
	
	@RequestMapping(value = "/nextTestQuestion", method = RequestMethod.GET)
	public ModelAndView nextTestQwastion(@RequestParam String correctAnswer) {
		log.info("start nextTestQuestion");
		log.info("correctAnswer " + correctAnswer);
		ModelAndView modelAndView = new ModelAndView();
		
		Session session = sessionFactory.openSession(); 
		List<Question> listQuestion = session.createQuery("from Question as question where id_test="+"'"+id+"'").list();
		Question questionObj = listQuestion.get(countQuestion);
		System.out.println("question "+questionObj);
		countQuestion++;
		int listQwastionSize = listQuestion.size();
		
		modelAndView.addObject("qwastion", "qqq");//qwastionObj.getName()
		
		List<Answer> listAnswer = session.createQuery("from Answer as answer where 	id_question="+"'"+questionObj.getId()+"'").list();
		modelAndView.addObject("listAnswer", listAnswer);
		
		if(countQuestion<listQwastionSize) {
			modelAndView.addObject("next", "Next");
		}
		if(countQuestion==listQwastionSize) {
			modelAndView.addObject("end", "End");
		}
		
		
		
		TestRezult testResult = new TestRezult();
		testResult.setIdAccount(idAccount);
		testResult.setIdTest(id);
		testResult.setCorrectCount(correctCount);//TODO count correctCount
		System.out.println("testResult " + testResult);
		session.save(testResult);		
		//session.getTransaction().commit();
		
		modelAndView.setViewName("student/question");
		log.info("end nextTestQuestion");
		return modelAndView;
	}
	
	
}
