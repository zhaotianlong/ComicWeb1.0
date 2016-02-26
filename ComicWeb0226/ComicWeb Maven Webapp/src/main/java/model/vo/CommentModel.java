package model.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CommentModel {
	@Id
	@Column(name="commentid")
	private int commentId;
	
	@Column(name="commentdes")
	private String des;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datetime")
	private Date newDate;
	
	@Column(name="userid")
	private String userId;
	
	@Column(name="comicid")
	private String comicId;
	
	@Column(name="comicname")
	private String comicName;
	
	@Column(name="src")
	private String comicIconSrc;
	

	public String getComicName() {
		return comicName;
	}
	public void setComicName(String comicName) {
		this.comicName = comicName;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public String getComicIconSrc() {
		return comicIconSrc;
	}
	public void setComicIconSrc(String comicIconSrc) {
		this.comicIconSrc = comicIconSrc;
	}
	
	
}
