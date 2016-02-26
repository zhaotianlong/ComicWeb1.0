package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "user")
public class User implements Serializable{

	@Id
	@Column(name = "accountid", nullable = false)
	private String accountId;
	@Column(name = "password")
	private String password;
	@Column(name = "born")
	@Temporal(TemporalType.DATE)
	private Date bornDate;
	@Column(name = "gender")
	private String gender;

	@ManyToOne
	@JoinColumn(name = "roleid", referencedColumnName = "roleid")
	private Role roleId;
	
	@Column(name = "money")
	private Integer money;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "resisterdate")
	private Date resisterDate;
	
	@Column(name="nickname")
	private String nickName;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="iconpath")
	private String iconPath;
	
	
	@OneToMany(mappedBy="userId",cascade=CascadeType.ALL)
	private Set<Comment> comments;
	
	@OneToMany(mappedBy="userId",cascade=CascadeType.ALL)
	private Set<UserComic> userComics;
	
	@OneToMany(mappedBy="userId",cascade=CascadeType.ALL)
	private Set<AuthorUser> authorUsers;
	
	@OneToMany(mappedBy="fromId",cascade=CascadeType.ALL)
	private Set<CommentSub> fromId;
	
	@OneToMany(mappedBy="toId",cascade=CascadeType.ALL)
	private Set<CommentSub> toId;
	
	@OneToMany(mappedBy="userId",cascade=CascadeType.ALL)
	private Set<ComicScore> comicScores;
	
	
	public String getIconPath() {
		return iconPath;
	}
	
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String account) {
		this.accountId = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Date getResisterDate() {
		return resisterDate;
	}

	public void setResisterDate(Date resisterDate) {
		this.resisterDate = resisterDate;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}
	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}


	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<UserComic> getUserComics() {
		return userComics;
	}

	public void setUserComics(Set<UserComic> userComics) {
		this.userComics = userComics;
	}

	public Set<AuthorUser> getAuthorUsers() {
		return authorUsers;
	}

	public void setAuthorUsers(Set<AuthorUser> authorUsers) {
		this.authorUsers = authorUsers;
	}

	public Set<CommentSub> getFromId() {
		return fromId;
	}

	public void setFromId(Set<CommentSub> fromId) {
		this.fromId = fromId;
	}

	public Set<CommentSub> getToId() {
		return toId;
	}

	public void setToId(Set<CommentSub> toId) {
		this.toId = toId;
	}

	public Set<ComicScore> getComicScores() {
		return comicScores;
	}

	public void setComicScores(Set<ComicScore> comicScores) {
		this.comicScores = comicScores;
	}
	
	
	
}