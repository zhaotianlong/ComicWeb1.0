package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.sql.Update;
@Entity(name="commentsub")
public class CommentSub implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subid")
	private int subId;
	
	@ManyToOne
	@JoinColumn(name="fromid",referencedColumnName="accountid")
	private User fromId;
	
	@ManyToOne
	@JoinColumn(name="toid",referencedColumnName="accountid")
	private User toId;
	
	@ManyToOne
	@JoinColumn(name="commentid",referencedColumnName="commentid")
	private Comment commentId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="newdate")
	private Date upDate;
	
	@Column(name="commentdes")
	private String commentDes;

	@Column(name="newstatus")
	private int newstatus;
	
	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public User getFromId() {
		return fromId;
	}

	public void setFromId(User fromId) {
		this.fromId = fromId;
	}

	public User getToId() {
		return toId;
	}

	public void setToId(User toId) {
		this.toId = toId;
	}

	public Comment getCommentId() {
		return commentId;
	}

	public void setCommentId(Comment commentId) {
		this.commentId = commentId;
	}

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}

	public String getCommentDes() {
		return commentDes;
	}

	public void setCommentDes(String commentDes) {
		this.commentDes = commentDes;
	}

	public int getNewstatus() {
		return newstatus;
	}

	public void setNewstatus(int newstatus) {
		this.newstatus = newstatus;
	}

}
