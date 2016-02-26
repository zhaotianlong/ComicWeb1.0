package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity(name="tab")
public class Tab implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="tabid")
	private int tabId;
	
	@Column(name="tabname")
	private String tabName;
	
	@OneToMany(mappedBy="tabId",cascade=CascadeType.ALL)
	private Set<ComicTab> comicTabs;
	
	public int getTabId() {
		return tabId;
	}
	public void setTabId(int tabId) {
		this.tabId = tabId;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public Set<ComicTab> getComicTabs() {
		return comicTabs;
	}
	public void setComicTabs(Set<ComicTab> comicTabs) {
		this.comicTabs = comicTabs;
	}

	
}
