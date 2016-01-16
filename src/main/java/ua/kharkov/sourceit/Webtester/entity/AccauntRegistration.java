package ua.kharkov.sourceit.Webtester.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accaunt_registration")
public class AccauntRegistration implements Serializable {
	
	@Id
	private int id;
	private String hash;
	@Column(name="id_account")
	private int idAccount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	
	public AccauntRegistration() {
		super();
	}
	
	public AccauntRegistration(int id, String hash, int idAccount) {
		super();
		this.id = id;
		this.hash = hash;
		this.idAccount = idAccount;
	}
	
	@Override
	public String toString() {
		return "AccauntRegistration [id=" + id + ", hash=" + hash
				+ ", idAccount=" + idAccount + "]";
	}
	
	

	
}
