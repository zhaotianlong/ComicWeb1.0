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

@Entity(name="comictrend")
public class ComicTrend implements Serializable {

	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dateline")
	private Date dateLine;
	
	@Column(name="des")
	private String des;
	
	@ManyToOne
	@JoinColumn(name="comicid",referencedColumnName="comicid")
	private Comic comicId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateLine() {
		return dateLine;
	}

	public void setDateLine(Date dateLine) {
		this.dateLine = dateLine;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Comic getComicId() {
		return comicId;
	}

	public void setComicId(Comic comicId) {
		this.comicId = comicId;
	}
	
	
}
