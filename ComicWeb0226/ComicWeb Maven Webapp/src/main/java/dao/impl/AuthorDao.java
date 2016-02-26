package dao.impl;

import org.springframework.stereotype.Repository;

import model.Author;
import dao.IAuthorDao;
import dao.common.AbstractHibernateDao;

@Repository(value="authorDao")
public class AuthorDao extends AbstractHibernateDao<Author> implements
		IAuthorDao {

	public AuthorDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(Author.class);
	}
}
