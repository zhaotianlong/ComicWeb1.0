package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="comicscore")
public class ComicScore implements Serializable {

	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;
	
	@Column(name="starlevel")
	private int starlevel;
	
	@ManyToOne
	@JoinColumn(name = "comicid", referencedColumnName = "comicid")
	private Comic comicId;
	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "accountid")
	private User userId;
	
	@Column(name = "newdate")
	@Temporal(TemporalType.DATE)
	private Date newDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStarlevel() {
		return starlevel;
	}

	public void setStarlevel(int starlevel) {
		this.starlevel = starlevel;
	}

	public Comic getComicId() {
		return comicId;
	}

	public void setComicId(Comic comicId) {
		this.comicId = comicId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Date getNewDate() {
		return newDate;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
	
	
	
}
