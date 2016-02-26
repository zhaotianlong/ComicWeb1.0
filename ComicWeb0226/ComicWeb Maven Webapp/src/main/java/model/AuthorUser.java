package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.mapkey.auMapKey;

//@IdClass(auMapKey.class)
@Entity(name="authoruser")
public class AuthorUser implements Serializable {

	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userid",referencedColumnName="accountid")
	private User userId;
	@ManyToOne
	@JoinColumn(name="authorid",referencedColumnName="authorid")
	private Author authorId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="lovedate")
	private Date loveDate;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	public Author getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Author authorId) {
		this.authorId = authorId;
	}
	public Date getLoveDate() {
		return loveDate;
	}
	public void setLoveDate(Date loveDate) {
		this.loveDate = loveDate;
	}
	
}
