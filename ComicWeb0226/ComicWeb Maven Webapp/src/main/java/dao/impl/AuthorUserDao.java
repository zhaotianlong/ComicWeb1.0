package dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import model.AuthorUser;
import dao.IAuthorUser;
import dao.common.AbstractHibernateDao;
@Repository("authorUserDao")
public class AuthorUserDao extends AbstractHibernateDao<AuthorUser> implements
		IAuthorUser {

	public AuthorUserDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(AuthorUser.class);
	}
	
	public int GetSumByAuthorId(String authorId){
		String str="select count(*) as sumau from authoruser where authorid="+authorId;
		
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumau",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumau");
	}
	
}
