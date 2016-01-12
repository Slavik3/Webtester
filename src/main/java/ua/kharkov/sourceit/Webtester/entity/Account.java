package ua.kharkov.sourceit.Webtester.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}
	
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
