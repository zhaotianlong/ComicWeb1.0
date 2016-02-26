package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * 申请记录
 * @param id 自增
 * @param fromid 申请人id
 * @param toid 接受人id
 * @param status 状态
 * @param date 日期
 * 
 * */
@Entity(name="goodfriendrecord")
public class GoodFriendRecord {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="fromId")
	private String fromId;
	@Column(name="toId")
	private String toId;
	@Column(name="status")
	private int status;
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
