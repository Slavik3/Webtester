package ua.kharkov.sourceit.Webtester.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="answer")
public class Answer implements Serializable {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int id;
	
	@Column(name="id_qwastion")
	private int idQwastion;
	private String name;
	private boolean correct;
	private Date created;
	private Date updated;
	private boolean active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdQwastion() {
		return idQwastion;
	}
	public void setIdQwastion(int idQwastion) {
		this.idQwastion = idQwastion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
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
	
	public Answer() {
		super();
	}
	
	public Answer(int id, int idQwastion, String name, boolean correct,
			Date created, Date updated, boolean active) {
		super();
		this.id = id;
		this.idQwastion = idQwastion;
		this.name = name;
		this.correct = correct;
		this.created = created;
		this.updated = updated;
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "Answer [id=" + id + ", idQwastion=" + idQwastion + ", name="
				+ name + ", correct=" + correct + ", created=" + created
				+ ", updated=" + updated + ", active=" + active + "]";
	}
	
	

}
