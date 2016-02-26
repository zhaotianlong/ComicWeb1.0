package dao.impl;


import org.springframework.stereotype.Repository;

import model.Role;
import dao.IRoleDao;
import dao.common.AbstractHibernateDao;
@Repository("roleDao")
public class RoleDao extends AbstractHibernateDao<Role> implements IRoleDao {

	public RoleDao() {
		super();
		setClazz(Role.class);
		// TODO Auto-generated constructor stub
	}

}
