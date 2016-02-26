package dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import model.User;
import model.UserComic;
import dao.IUserComicDao;
import dao.common.AbstractHibernateDao;

@Repository("userComicDao")
public class UserComicDao extends AbstractHibernateDao<UserComic> implements
		IUserComicDao {
	public UserComicDao() {
		super();
		setClazz(UserComic.class);
	}
	public UserComic GetUserComic(String comicId,String userId){
		String str="SELECT* FROM usercomic WHERE usercomic.userid = "+comicId+" AND usercomic.comicid ="+userId;
		Query query=getCurrentSession().createSQLQuery(str).addEntity("UserComic",UserComic.class);
		return (UserComic)query.list().get(0);
	}
	public int GetSumByComic(String comicId){
		String str="SELECT count(*) as sumuc FROM usercomic WHERE usercomic.comicid="+comicId;
		try{
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumuc",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if(query.list().get(0)==null)return 0;
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumuc");
		}
		catch(Exception ex){
			return 0;
		}
	}
	
}
