package dao.impl;

import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import model.ComicScore;
import model.vo.RankByTabModel;
import dao.IComicScoreDao;
import dao.common.AbstractHibernateDao;

@Repository(value="comicScoreDao")
public class ComicScoreDao extends AbstractHibernateDao<ComicScore> implements
		IComicScoreDao {

	public ComicScoreDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(ComicScore.class);
	}
	public int GetSum(){
		String str="SELECT count(*) as sumcs FROM comicscore ";
		try{
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumcs",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumcs");
		}
		catch(Exception ex){
			return 0;
		}
	}
	public int GetSum(String comicId){
		String str="SELECT count(*) as sumcs FROM comicscore  WHERE comicscore.comicid = "+comicId;
		try{
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumcs",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumcs");
		}
		catch(Exception ex){
		return 0;
		}
	
	}
	public Integer GetSumStarLevel(String comicId){
		String str="SELECT Sum(starlevel) as sumlevel FROM comicscore WHERE comicscore.comicid = "+comicId;
		try{
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumlevel",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		if( m.get("sumlevel")==null)return 0;
		return (Integer) m.get("sumlevel");
		}
		catch(Exception ex){
		return 0;
		}
	}
	public boolean CheckComicScore(String comicId,String userId){
		String str="SELECT * FROM comicscore  WHERE comicscore.comicid = "+comicId+" And comicscore.userid="+userId;
		try{
		Query query = getCurrentSession().createSQLQuery(str).addEntity(ComicScore.class);
			if(query.list().size()==1)
				return true;
			return false;
		}
		catch(Exception ex){
		return false;
		}
	}	
}
