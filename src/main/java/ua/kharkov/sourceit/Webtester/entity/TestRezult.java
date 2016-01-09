package ua.kharkov.sourceit.Webtester.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test_rezult")
public class TestRezult implements Serializable {
	@Id
	private int id;
	
	@Column(name="id_account")
	private int idAccount;
	
	@Column(name="id_test")
	private int idTest;
	
	@Column(name="correct_count")
	private int correctCount;
	
	@Column(name="oll_count")
	private int ollCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public int getCorrectCount() {
		return correctCount;
	}

	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
	}

	public int getOllCount() {
		return ollCount;
	}

	public void setOllCount(int ollCount) {
		this.ollCount = ollCount;
	}

	public TestRezult() {
		super();
	}

	public TestRezult(int id, int idAccount, int idTest, int correctCount,
			int ollCount) {
		super();
		this.id = id;
		this.idAccount = idAccount;
		this.idTest = idTest;
		this.correctCount = correctCount;
		this.ollCount = ollCount;
	}

	@Override
	public String toString() {
		return "TestRezult [id=" + id + ", idAccount=" + idAccount
				+ ", idTest=" + idTest + ", correctCount=" + correctCount
				+ ", ollCount=" + ollCount + "]";
	}
	

}
