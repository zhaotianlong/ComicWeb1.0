package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import model.ComicPart;
import model.vo.ComicListModel;
import dao.IComicPartDao;
import dao.common.AbstractHibernateDao;
@Repository("comicPartDao")
public class ComicPartDao extends AbstractHibernateDao<ComicPart> implements IComicPartDao {

	public ComicPartDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(ComicPart.class);
	}

	public ComicPart FindTopOne(String comicId) {
		try{
		String str="SELECT * FROM comicpart WHERE comicpart.comicid ="+comicId+" ORDER BY comicpart.id DESC";
		
		Query query = getCurrentSession().createSQLQuery(hqlExe).addEntity(
				ComicPart.class);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (ComicPart) query.list().get(0);
		}
		catch(Exception ex){
			return null;
		}
	}
}
