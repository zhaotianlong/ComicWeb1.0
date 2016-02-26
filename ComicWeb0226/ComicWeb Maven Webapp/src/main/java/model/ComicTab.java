package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity(name="comictab")
public class ComicTab  implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id",nullable=false)
	private int id;
	@ManyToOne
	@JoinColumn(name="comicid",referencedColumnName="comicid")
	private Comic comicId;
	
	@ManyToOne
	@JoinColumn(name="tabid",referencedColumnName="tabid")
	private Tab tabId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Comic getComicId() {
		return comicId;
	}
	public void setComicId(Comic comicId) {
		this.comicId = comicId;
	}
	public Tab getTabId() {
		return tabId;
	}
	public void setTabId(Tab tabId) {
		this.tabId = tabId;
	}
	
}
