package ua.kharkov.sourceit.Webtester.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qwastion")
public class Question implements Serializable {
	
	@Id
	private int id;
	@Column(name="id_test")
	private int idTest;
	private String name;
	private Date created;
	private Date updated;
	private boolean active;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdTest() {
		return idTest;
	}
	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Question() {
		super();
	}
	
	public Question(int id, int idTest, String name, Date created,
			Date updated, boolean active) {
		super();
		this.id = id;
		this.idTest = idTest;
		this.name = name;
		this.created = created;
		this.updated = updated;
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "Qwastion [id=" + id + ", idTest=" + idTest + ", name=" + name
				+ ", created=" + created + ", updated=" + updated + ", active="
				+ active + "]";
	}
	
	

}
