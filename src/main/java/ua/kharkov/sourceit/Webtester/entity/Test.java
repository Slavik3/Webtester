package ua.kharkov.sourceit.Webtester.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test")
public class Test implements Serializable {
	
	@Id
	private int id;
	
	@Column(name="id_account")
	private int idAccaunt;
	
	private String name;
	
	private String description;
	
	@Column(name="time_for_question")
	private int timeForQuestion;
	
	private Date created;
	
	private Date updated;
	
	private boolean active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAccaunt() {
		return idAccaunt;
	}

	public void setIdAccaunt(int idAccaunt) {
		this.idAccaunt = idAccaunt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTimeForQuestion() {
		return timeForQuestion;
	}

	public void setTimeForQuestion(int timeForQuestion) {
		this.timeForQuestion = timeForQuestion;
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

	public Test() {
		super();
	}

	public Test(int id, int idAccaunt, String name, String description,
			int timeForQuestion, Date created, Date updated, boolean active) {
		super();
		this.id = id;
		this.idAccaunt = idAccaunt;
		this.name = name;
		this.description = description;
		this.timeForQuestion = timeForQuestion;
		this.created = created;
		this.updated = updated;
		this.active = active;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", idAccaunt=" + idAccaunt + ", name=" + name
				+ ", description=" + description + ", timeForQuestion="
				+ timeForQuestion + ", created=" + created + ", updated="
				+ updated + ", active=" + active + "]";
	}	

}
