package model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="authority")
public class Authority {
	public Integer getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	


	public Set<RoleAuthority> getRoleAuthorities() {
		return roleAuthorities;
	}
	public void setRoleAuthorities(Set<RoleAuthority> roleAuthorities) {
		this.roleAuthorities = roleAuthorities;
	}



	@Id
	@GeneratedValue
	@Column(name="authorityid",nullable=false)
	private Integer authorityId;
	@Column(name="name")
	private String name;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="roleAuthorityId")
	Set<RoleAuthority> roleAuthorities;
}
