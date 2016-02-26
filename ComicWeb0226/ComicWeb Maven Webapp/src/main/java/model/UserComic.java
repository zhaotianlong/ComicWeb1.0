package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="usercomic")
public class UserComic implements Serializable{
	@Id
	@GeneratedValue
	@Column(name="id",nullable=false)
	private int id;
	@ManyToOne
	@JoinColumn(name="userid",referencedColumnName="accountid")
	private User userId;
	@ManyToOne
	@JoinColumn(name="comicid",referencedColumnName="comicid")
	private Comic comicId;
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
	public Comic getComicId() {
		return comicId;
	}
	public void setComicId(Comic comicId) {
		this.comicId = comicId;
	}
	
}
