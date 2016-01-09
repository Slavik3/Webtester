package ua.kharkov.sourceit.Webtester.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_role")
public class AccauntRole implements Serializable {
	@Id
	private int id;
	
	//@ManyToOne(fetch=FetchType.LAZY)
	@Column(name="id_account")
	private int idAccaunt;
	
	//@ManyToOne(fetch=FetchType.LAZY)
	@Column(name="id_role")
	private int idRole;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAccount() {
		return idAccaunt;
	}

	public void setIdAccount(int idAccount) {
		this.idAccaunt = idAccount;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public AccauntRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccauntRole(int id, int idAccount, int idRole) {
		super();
		this.id = id;
		this.idAccaunt = idAccount;
		this.idRole = idRole;
	}

	@Override
	public String toString() {
		return "AccauntRole [id=" + id + ", idAccount=" + idAccaunt
				+ ", idRole=" + idRole + "]";
	}
	
}
