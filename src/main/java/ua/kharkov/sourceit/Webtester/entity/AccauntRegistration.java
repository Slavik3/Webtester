package ua.kharkov.sourceit.Webtester.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accaunt_registration")
public class AccauntRegistration implements Serializable {
	@Column(name="id")
	@Id
	private int idAccount;
	private String hash;
	
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public AccauntRegistration() {
		super();
	}
	
	public AccauntRegistration(int idAccount, String hash) {
		super();
		this.idAccount = idAccount;
		this.hash = hash;
	}
	
	@Override
	public String toString() {
		return "AccauntRegistration [idAccount=" + idAccount + ", hash=" + hash
				+ "]";
	}
	
}
