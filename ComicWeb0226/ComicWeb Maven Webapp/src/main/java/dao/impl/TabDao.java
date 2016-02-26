package dao.impl;

import org.springframework.stereotype.Repository;

import model.Tab;
import dao.ITabDao;
import dao.common.AbstractHibernateDao;

@Repository(value="tabDao")
public class TabDao extends AbstractHibernateDao<Tab> implements ITabDao {

	public TabDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(Tab.class);
	}

	
}
