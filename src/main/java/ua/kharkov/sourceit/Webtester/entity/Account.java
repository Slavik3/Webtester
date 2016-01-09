package ua.kharkov.sourceit.Webtester.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "account")
@Entity
@Table(name="account")
public class Account implements Serializable {
	@Column(name="id")
	@Id
	private int id;
	
	private String login;	
	
	private String password;
	
	private String email;
	
	private String fio;
	
	private int isActive;

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}
	@XmlElement
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Account() {
		super();
	}

	public Account(int id, String login, String password, String email,
			String fio, int isActive) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
		this.fio = fio;
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Accaunt [id=" + id + ", login=" + login
				+ ", password=" + password + ", email=" + email + ", fio="
				+ fio + ", isActive=" + isActive + "]";
	}

	
}
