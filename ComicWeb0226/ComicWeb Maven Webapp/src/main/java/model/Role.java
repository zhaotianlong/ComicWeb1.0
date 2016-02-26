package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="role")
public class Role implements Serializable {
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

	public Set<User> getUserCollection() {
		return userCollection;
	}
	public void setUserCollection(Set<User> userCollection) {
		this.userCollection = userCollection;
	}
	public Set<RoleAuthority> getRoleAuthorities() {
		return roleAuthorities;
	}
	public void setRoleAuthorities(Set<RoleAuthority> roleAuthorities) {
		this.roleAuthorities = roleAuthorities;
	}


	@Id
	@GeneratedValue
	@Column(name="roleid")
	private Integer roleId;
	@Column(name="rolename")
	private String roleName;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="roleId")
	private Set<User> userCollection;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="roleAuthorityId")
	private Set<RoleAuthority> roleAuthorities;
}
