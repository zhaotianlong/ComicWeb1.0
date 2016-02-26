package model.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="comiclistModel")
public class ComicListModel {
	
	@Id
	@Column(name="comicid")
	private String comicId;
	@Column(name="comicname")
	private String comicName;
	@Column(name="newupdate")
	private Date   partDate;
	
	@Column(name="realname")
	private String authorName;
	
	@Column(name="good")
	private int good;

	@Column(name="comicstatus")
	private int comicstatus;
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
	public Date getPartDate() {
		return partDate;
	}
	public void setPartDate(Date partDate) {
		this.partDate = partDate;
	}

	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getComicstatus() {
		return comicstatus;
	}
	public void setComicstatus(int comicstatus) {
		this.comicstatus = comicstatus;
	}

}
