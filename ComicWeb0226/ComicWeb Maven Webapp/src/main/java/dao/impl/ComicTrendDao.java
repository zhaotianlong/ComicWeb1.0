package dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import model.ComicPage;
import model.ComicTrend;
import model.vo.ComicTrendModel;
import model.vo.CommentModel;
import dao.IComicTrendDao;
import dao.common.AbstractHibernateDao;

@Repository("comicTrendDao")
public class ComicTrendDao extends AbstractHibernateDao<ComicTrend> implements
		IComicTrendDao {

	public ComicTrendDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(ComicTrend.class);
	}
	
	public int GetSum(){
		String str="select count(*) As sumtrend  FROM comictrend";
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumtrend",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumtrend");
	}

	public List<ComicTrendModel> GetLoveAuthorTrend(String userId, int pre, int max) {
		// TODO Auto-generated method stub
		String str="SELECT comictrend.id As id,authoruser.authorid AS authorid,author.realname AS authorname,author.iconPath AS authoricon,comic.comicid AS comicid,comic.comicname AS comicname,comic.src AS comicsrc,comictrend.dateline AS dateline,comictrend.des AS comicdes "
				+" FROM author ,authoruser ,comic ,comictrend"
				+" WHERE authoruser.userid ="+userId+" AND author.authorid = authoruser.authorid AND comic.authorid = author.authorid AND comictrend.comicid = comic.comicid"
				+" ORDER BY dateline DESC";
		Query query=getCurrentSession().createSQLQuery(str).addEntity("ComicTrendModel", ComicTrendModel.class);
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	public int GetSumLAT(String userId){
		String str=" SELECT count(*) As sumtrend "
				+" FROM author ,authoruser ,comic ,comictrend"
				+" WHERE authoruser.userid ="+userId+" AND author.authorid = authoruser.authorid AND comic.authorid = author.authorid AND comictrend.comicid = comic.comicid";
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumtrend",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumtrend");
	}
	
	public List<ComicTrendModel> GetLoveComicTrend(String userId, int pre,int max) {
		String str="SELECT  comictrend.id As id,comic.comicid AS comicid,comic.comicname AS comicname,comic.src AS comicsrc,comictrend.dateline AS dateline,comictrend.des AS comicdes,author.authorid As authorid,author.realname As authorname,author.iconPath As authoricon "
				+" FROM author ,usercomic ,comic ,comictrend"
				+" WHERE comictrend.comicid = comic.comicid AND usercomic.userid = "+userId+"AND comic.comicid = usercomic.comicid AND author.authorid = comic.authorid"
				+" ORDER BY dateline DESC";
		Query query=getCurrentSession().createSQLQuery(str).addEntity("ComicTrendModel", ComicTrendModel.class);
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	public int GetSumLCT(String userId){
		String str=" SELECT count(*) As sumtrend  "
				+" FROM author ,usercomic ,comic ,comictrend"
				+" WHERE comictrend.comicid = comic.comicid AND usercomic.userid = "+userId+"AND comic.comicid = usercomic.comicid AND author.authorid = comic.authorid";
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumtrend",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumtrend");
	}
	public List<ComicTrendModel> GetAuthorTrend(String authorId,int pre,int max){
		String str=" SELECT	comictrend.id As id,comictrend.dateline AS dateline,comictrend.des AS comicdes,author.authorid  As authorid,author.realname  As authorname,author.iconPath As authoricon,comic.comicid AS comicid,comic.comicname AS comicname,comic.src AS comicsrc"
				+" FROM comictrend ,author ,comic"
				+" WHERE author.authorid = "+authorId+" AND comic.authorid = author.authorid AND comictrend.comicid = comic.comicid";
		Query query=getCurrentSession().createSQLQuery(str).addEntity("ComicTrendModel", ComicTrendModel.class);
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	public int GetSumAT(String authorId){
		String str=" SELECT count(*) As sumtrend  "
				+" FROM comictrend ,author ,comic"
				+" WHERE author.authorid = "+authorId+" AND comic.authorid = author.authorid AND comictrend.comicid = comic.comicid";
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumtrend",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumtrend");
	}
}
