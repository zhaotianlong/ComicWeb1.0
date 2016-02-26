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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity(name="author")
public class Author implements Serializable {
	

	@Id
	@Column(name = "authorid", nullable = false)
	private String authorId;
	
	@Column(name = "password", nullable = false)
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
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name="realname")
	private String realName;
	
	@Column(name="idcard")
	private String idcard;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="address")
	private String address;
	
	@Column(name="iconpath")
	private String iconPath;
	
	//无法使用
	@OneToMany(mappedBy="comicId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Comic> comicCollection;

	@OneToMany(mappedBy="authorId",cascade=CascadeType.ALL)
	private Set<AuthorUser> authorUsers;
	
	
	
	
	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Comic> getComicCollection() {
		return comicCollection;
	}

	public void setComicCollection(Set<Comic> comicCollection) {
		this.comicCollection = comicCollection;
	}

	public Set<AuthorUser> getAuthorUsers() {
		return authorUsers;
	}

	public void setAuthorUsers(Set<AuthorUser> authorUsers) {
		this.authorUsers = authorUsers;
	}

	

	
	
	
	
}
