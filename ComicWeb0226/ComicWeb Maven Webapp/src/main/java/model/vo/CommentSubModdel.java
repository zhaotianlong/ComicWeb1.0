package model.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CommentSubModdel {
	
	@Id
	@Column(name="subid")
	private String subid;
	
	@Column(name="commentdes")
	private String des;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="newdate")
	private Date newDate;
	
	@Column(name="userid")
	private String userId;
	
	
	@Column(name="comicid")
	private String comicId;
	
	@Column(name="comicname")
	private String comicName;
	
	@Column(name="commentdest")
	private String comment;

	@Column(name="nickname")
	private String userName;
	
	@Column(name="src")
	private String comicIconSrc;

	@Column(name="iconPath")
	private String iconPath;
	
	@Column(name="newstatus")
	private int newstatus;
	
	public String getSubid() {
		return subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Date getNewDate() {
		return newDate;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getComicId() {
		return comicId;
	}

	public void setComicId(String comicId) {
		this.comicId = comicId;
	}

	public String getComicName() {
		return comicName;
	}

	public void setComicName(String comicName) {
		this.comicName = comicName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComicIconSrc() {
		return comicIconSrc;
	}

	public void setComicIconSrc(String comicIconSrc) {
		this.comicIconSrc = comicIconSrc;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public int getNewstatus() {
		return newstatus;
	}

	public void setNewstatus(int newstatus) {
		this.newstatus = newstatus;
	}

}
