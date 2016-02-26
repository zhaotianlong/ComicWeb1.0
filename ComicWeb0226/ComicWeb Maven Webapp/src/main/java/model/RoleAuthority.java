package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "roleauthority")
public class RoleAuthority {
	public Role getRoleId() {
		return userRoleId;
	}

	public void setRoleId(Role userRoleId) {
		this.userRoleId = userRoleId;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Authority getAuthorityId() {
		return roleAuthorityId;
	}

	public void setAuthorityId(Authority roleAuthorityId) {
		this.roleAuthorityId = roleAuthorityId;
	}
	

	@Id
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "roleid", referencedColumnName = "roleid")
	private Role userRoleId;
	@ManyToOne
	@JoinColumn(name = "authorityid", referencedColumnName = "authorityid")
	private Authority roleAuthorityId;
}
