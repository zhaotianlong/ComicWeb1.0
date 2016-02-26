package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity(name="comment")
public class Comment implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="commentid",nullable=false)
	private int id;
	
	@Column(name="commentdes")
	private String commentDes;
	
	@Column(name="datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	@ManyToOne
	@JoinColumn(name="comicid",referencedColumnName="comicid")
	private Comic comicId;
	
	@ManyToOne
	@JoinColumn(name="userid",referencedColumnName="accountid")
	private User userId;

	
	@OneToMany(mappedBy="commentId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<CommentSub> commentSubs;
	

	public String getCommentDes() {
		return commentDes;
	}
	public void setCommentDes(String commentDes) {
		this.commentDes = commentDes;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Comic getComicId() {
		return comicId;
	}
	public void setComicId(Comic comicId) {
		this.comicId = comicId;
	}

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

	public Set<CommentSub> getCommentSubs() {
		return commentSubs;
	}

	public void setCommentSubs(Set<CommentSub> commentSubs) {
		this.commentSubs = commentSubs;
	}

	
	
	
}
