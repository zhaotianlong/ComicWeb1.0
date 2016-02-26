/**
 * 
 */
package dao.impl;

import java.util.List;


import org.springframework.stereotype.Repository;

import model.User;
import dao.IUserDao;
import dao.common.AbstractHibernateDao;

/**
 * @author Administrator
 *
 */
@Repository("userDao")
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

	public UserDao() {
		super();
		setClazz(User.class);
	}
}
