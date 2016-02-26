package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "comic")
public class Comic implements Serializable {

	@Id
	@Column(name = "comicid", nullable = false)
	private String comicId;
	@Column(name = "comicname")
	private String comicName;
	@Column(name = "comicstatus", nullable = false)
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
	
	
	@ManyToOne
	@JoinColumn(name = "authorid", referencedColumnName = "authorid")
	private Author authorId;
	
	
	
	@OneToMany(mappedBy = "comic", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<ComicPart> comicParts;
	
	@OneToMany(mappedBy = "comicId", cascade = CascadeType.ALL)
	private Set<Comment> comments;
	
	@OneToMany(mappedBy = "comicId",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<ComicTab> comicTabs;
	
	
	@OneToMany(mappedBy="comicId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<UserComic> userComics;
	
	@OneToMany(mappedBy = "comicId", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<ComicTrend> ComicTrend;
	
	@OneToMany(mappedBy="userId",cascade=CascadeType.ALL)
	private Set<ComicScore> comicScores;
	
	public Date getNewUpdate() {
		return newUpdate;
	}

	public void setNewUpdate(Date newUpdate) {
		this.newUpdate = newUpdate;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
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


	public Author getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Author authorId) {
		this.authorId = authorId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Set<ComicPart> getComicParts() {
		return comicParts;
	}

	public void setComicParts(Set<ComicPart> comicParts) {
		this.comicParts = comicParts;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<ComicTab> getComicTabs() {
		return comicTabs;
	}

	public void setComicTabs(Set<ComicTab> comicTabs) {
		this.comicTabs = comicTabs;
	}

	public Set<UserComic> getUserComics() {
		return userComics;
	}

	public void setUserComics(Set<UserComic> userComics) {
		this.userComics = userComics;
	}

	public Set<ComicTrend> getComicTrend() {
		return ComicTrend;
	}

	public void setComicTrend(Set<ComicTrend> comicTrend) {
		ComicTrend = comicTrend;
	}

	public Set<ComicScore> getComicScores() {
		return comicScores;
	}

	public void setComicScores(Set<ComicScore> comicScores) {
		this.comicScores = comicScores;
	}
	
	
	
}
