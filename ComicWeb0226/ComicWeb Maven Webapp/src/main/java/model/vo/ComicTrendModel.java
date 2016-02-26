package model.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ComicTrendModel {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="comicid")
	private String comicId;
	@Column(name="comicname")
	private String comicName;
	@Column(name="comicsrc")
	private String comicSrc;
	@Column(name="comicdes")
	private String des;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dateline")
	private Date dateLine;
	@Column(name="authorid")
	private String authorId;
	@Column(name="authorname")
	private String authorName;
	@Column(name="authoricon")
	private String authorIcon;
	
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
	public String getComicSrc() {
		return comicSrc;
	}
	public void setComicSrc(String comicSrc) {
		this.comicSrc = comicSrc;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Date getDateLine() {
		return dateLine;
	}
	public void setDateLine(Date dateLine) {
		this.dateLine = dateLine;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorIcon() {
		return authorIcon;
	}
	public void setAuthorIcon(String authorIcon) {
		this.authorIcon = authorIcon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
