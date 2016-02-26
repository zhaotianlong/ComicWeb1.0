package dao.impl;


import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import model.ComicPage;
import dao.IComicPageDao;
import dao.common.AbstractHibernateDao;

@Repository(value="comicPageDao")
public class ComicPageDao extends AbstractHibernateDao<ComicPage> implements
		IComicPageDao {

	public ComicPageDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(ComicPage.class);
	}

	public int GetmaxpageId(String partId){
		String str="select max(id) as mid from comicpage where partid="+partId;
		Query query=getCurrentSession().createSQLQuery(str).addScalar("mid",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("mid");
	}
	public int GetSum(String partId){
		String str="select count(*) as pagesum from comicpage where partid="+partId;
		Query query=getCurrentSession().createSQLQuery(str).addScalar("pagesum",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("pagesum");
	}
	
}
