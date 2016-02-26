package model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.mail.imap.protocol.Status;

@Entity
public class RankByTabModel {
	
	@Id
	@Column(name="comicid")
	private String comicId;
	@Column(name="comicname")
	private String comicName;
	@Column(name="comicstatus")
	private int status;
	@Column(name="src")
	private String src;
	@Column(name="good")
	private int good;
	@Column(name="realname")
	private String authorName;
	@Column(name="type")
	private String tabs;
	
	@Column(name="description")
	private String des;
	
	
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getTabs() {
		return tabs;
	}
	public void setTabs(String tabs) {
		this.tabs = tabs;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	
	
}
