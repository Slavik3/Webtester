package ua.kharkov.sourceit;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BcryptHashing {
	
	static Logger log = Logger.getLogger(BcryptHashing.class.getName());
	
	public static String BcryptHash(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		log.info("hashedPassword " + hashedPassword);
		return hashedPassword;
	}

}
