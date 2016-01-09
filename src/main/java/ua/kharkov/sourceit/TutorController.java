package ua.kharkov.sourceit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.kharkov.sourceit.Webtester.entity.Account;
import ua.kharkov.sourceit.Webtester.entity.Answer;
import ua.kharkov.sourceit.Webtester.entity.Qwastion;
import ua.kharkov.sourceit.Webtester.entity.Test;

@Controller
public class TutorController {
	
	static Logger log = Logger.getLogger(StudentController.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	int idTest;
	
	@RequestMapping(value = "/addTest", method = RequestMethod.GET)
	public ModelAndView addTest(@ModelAttribute Test test) {
		log.info("start addTest");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        Session session = sessionFactory.openSession(); 
        Account account = (Account) session.createQuery("from Account as account where login = '" + login+"'").uniqueResult();
        int idAccaunt = account.getId();
        test.setIdAccaunt(idAccaunt);
        Date date = new Date();
        test.setCreated(date);
        System.out.println("test "+test);
		session.beginTransaction();
		session.save(test);
		session.getTransaction().commit();
		Test testForAddQwastion = (Test) session.createQuery("from Test as test where name = '" + test.getName()+"'").uniqueResult();
		idTest = testForAddQwastion.getId();
		log.info("idTest "+idTest);
		session.close();
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("tutor/newQwastion");
		log.info("end addTest");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addQwastion", method = RequestMethod.GET)
	public ModelAndView addQwastion(@RequestParam String qwastionName,
			@RequestParam String answer1, @RequestParam String answer2,
			@RequestParam String answer3, @RequestParam String answer4, @RequestParam String correctAnswer) {
		System.out.println("qwastionName " + qwastionName);
		System.out.println(answer1+answer2+answer3+answer4);
		System.out.println("correctAnswer "+correctAnswer);
		ModelAndView modelAndView = new ModelAndView();
		Qwastion qwastion = new Qwastion();
		qwastion.setName(qwastionName);
		Date date = new Date();
		qwastion.setCreated(date);
		System.out.println("idTest==> "+idTest);
		qwastion.setIdTest(idTest);//TODO
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		session.save(qwastion);
		session.getTransaction().commit();
		session.beginTransaction();
		Answer answerO1 = new Answer();
		answerO1.setCreated(date);
		answerO1.setName(answer1);
		Qwastion qwastionFor = (Qwastion) session.createQuery("from Qwastion as qwastion where name = '" + qwastionName+"'").uniqueResult();
		int idQqwastion	= qwastionFor.getId();
		System.out.println("idQqwastion " + idQqwastion);
		answerO1.setIdQwastion(idQqwastion);
		if(correctAnswer.equals("1"))
			answerO1.setCorrect(true);
		session.save(answerO1);
		//session.getTransaction().commit();
		//session.close();
		
		//Session session2 = sessionFactory.openSession();
		//session.beginTransaction();
		Answer answerO2 = new Answer();
		answerO2.setCreated(date);
		answerO2.setName(answer2);
		answerO2.setIdQwastion(idQqwastion);
		if(correctAnswer.equals("2"))
			answerO2.setCorrect(true);
		session.save(answerO2);
		//session.getTransaction().commit();
		//session.close();
		
		//Session session3 = sessionFactory.openSession();
		//session3.beginTransaction();
		Answer answerO3 = new Answer();
		answerO3.setCreated(date);
		answerO3.setName(answer3);
		answerO3.setIdQwastion(idQqwastion);
		if(correctAnswer.equals("3"))
			answerO3.setCorrect(true);
		session.save(answerO3);
		//session3.getTransaction().commit();
		//session3.close();
		
		//Session session4 = sessionFactory.openSession();
		//session4.beginTransaction();
		Answer answerO4 = new Answer();
		answerO4.setCreated(date);
		answerO4.setName(answer4);
		answerO4.setIdQwastion(idQqwastion);
		if(correctAnswer.equals("4"))
			answerO4.setCorrect(true);
		session.save(answerO4);
		session.getTransaction().commit();
		session.close();
		modelAndView.setViewName("tutor/newQwastion");
		return modelAndView;
	}
	
	@RequestMapping(value = "/completeCreationTestAndExit", method = RequestMethod.GET)
	public String completeCreationTestAndExit() {
		return "redirect:/tutor";		
	}
	
	

}
