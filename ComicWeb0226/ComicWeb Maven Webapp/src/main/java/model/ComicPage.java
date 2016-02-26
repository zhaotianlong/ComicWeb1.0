package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity(name="comicpage")
public class ComicPage implements Serializable {
	
	@Id
	@Column(name="pageid")
	private String pageId;
	
	@Column(name="id")
	private int id;
	
	@Column(name="filepath")
	private String filePath;
	
	@ManyToOne
	@JoinColumn(name="partid",referencedColumnName="partid")
	private ComicPart partId;
	
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public ComicPart getPartId() {
		return partId;
	}
	public void setPartId(ComicPart partId) {
		this.partId = partId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
