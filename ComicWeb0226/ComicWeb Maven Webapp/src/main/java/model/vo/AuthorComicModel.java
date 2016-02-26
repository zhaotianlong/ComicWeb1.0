package model.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AuthorComicModel {
	@Id
	@Column(name = "comicid")
	private String comicId;
	@Column(name = "comicname")
	private String comicName;
	@Column(name = "comicstatus")
	private int comicStatus;
	@Column(name = "description")
	private String description;
	@Column(name = "src")
	private String src;
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "newupdate")
	@Temporal(TemporalType.DATE)
	private Date newUpdate;
	@Column(name="good")
	private int good;
	@Column(name="bad")
	private int bad;
	@Column(name="charge")
	private int charge;
	@Column(name="initial")
	private String initial;
	@Column(name="type")
	private String type;
	@Column(name="partsum")
	private int partSum;
	@Column(name="commentsum")
	private int commentSum;
	@Column(name="lovesum")
	private int loveSum;
	
	
	
	public int getLoveSum() {
		return loveSum;
	}
	public void setLoveSum(int loveSum) {
		this.loveSum = loveSum;
	}
	public int getCommentSum() {
		return commentSum;
	}
	public void setCommentSum(int commentSum) {
		this.commentSum = commentSum;
	}
	public int getPartSum() {
		return partSum;
	}
	public void setPartSum(int partSum) {
		this.partSum = partSum;
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
	public int getComicStatus() {
		return comicStatus;
	}
	public void setComicStatus(int comicStatus) {
		this.comicStatus = comicStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getNewUpdate() {
		return newUpdate;
	}
	public void setNewUpdate(Date newUpdate) {
		this.newUpdate = newUpdate;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
