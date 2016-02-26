package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.mapkey.auMapKey;
import model.mapkey.comicpartMapKey;

@Entity(name="comicpart")
public class ComicPart  implements Serializable{

	@Id
	@Column(name="partid",nullable=false)
	private String partId;
	
	@Column(name="id",nullable=false)
	private int id;
	
	@Column(name="partnum",nullable=false)
	private int partNum;
	
	@Column(name="partname")
	private String partName;
	
	@Column(name="newdate")
	@Temporal(TemporalType.DATE)
	private Date partUpdate;
	
	@ManyToOne
	@JoinColumn(name="comicid",referencedColumnName="comicid")
	private Comic comic;
	
	@OneToMany(mappedBy="partId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<ComicPage> comicPages;

	@OneToMany(mappedBy="partId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<SpitSlot> spitSlots;
		
	
	
	public Set<SpitSlot> getSpitSlots() {
		return spitSlots;
	}
	public void setSpitSlots(Set<SpitSlot> spitSlots) {
		this.spitSlots = spitSlots;
	}
	public Set<ComicPage> getComicPages() {
		return comicPages;
	}
	public void setComicPages(Set<ComicPage> comicPages) {
		this.comicPages = comicPages;
	}
	public int getPartNum() {
		return partNum;
	}
	public void setPartNum(int partNum) {
		this.partNum = partNum;
	}
	public Date getPartUpdate() {
		return partUpdate;
	}
	public void setPartUpdate(Date partUpdate) {
		this.partUpdate = partUpdate;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public Comic getComic() {
		return comic;
	}
	public void setComic(Comic comic) {
		this.comic = comic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	
}
