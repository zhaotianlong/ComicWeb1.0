package dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import model.Comic;
import model.vo.AuthorComicModel;
import model.vo.ComicListModel;
import model.vo.RankByTabModel;
import dao.IComicDao;
import dao.common.AbstractHibernateDao;

@Repository("comicDao")
public class ComicDao extends AbstractHibernateDao<Comic> implements IComicDao {
	public ComicDao() {
		super();
		setClazz(Comic.class);
	}

	@Override
	public void Where(String[] property, String[] value) {
		String str = "";
		hqlExe = "";

		if (property != null && value != null) {
			if (property.length != value.length)
				return;
			//property[i] =“c”+ property[i] 报错
			for (int i = 0; i < property.length; i++) {
				if (i == 0)
					str += " where " + property[0] + "=" + value[0];
				else
					str += " and " + property[i] + "=" + value[i];
			}
			hqlExe += str + " and ct.comicid=c.comicid ";
			hqlExe += " and c.authorid=a.authorid";
		} else {
			hqlExe += " where ct.comicid=c.comicid and c.authorid=a.authorid";
		}
		System.out.println("test:" + str);

	}

	public List<ComicListModel> FindWithTab(int pre, int max) {

		String str = "select distinct  c.comicid,c.comicname,c.comicstatus,c.newupdate,c.good,a.realname from comic as c,comictab as ct,author as a ";
		hqlExe = str + hqlExe;
		Query query = getCurrentSession().createSQLQuery(hqlExe).addEntity(
				ComicListModel.class);
		query.setFirstResult(pre);
		query.setMaxResults(max);

		return query.list();
	}

	public int GetSum() {
		String str = "select distinct  c.comicid,c.comicname,c.comicstatus,c.newupdate,c.good,a.realname from comic as c,comictab as ct,author as a ";
		hqlExe = str + hqlExe;
		Query query = getCurrentSession().createSQLQuery(hqlExe).addEntity(
				ComicListModel.class);
		return query.list().size();
	}
	public List<RankByTabModel> RankByTab(String tabId,int pre,int max) {
		String str="SELECT c.comicid, c.comicname, c.comicstatus,c.description,c.src,c.good,author.realname, "
				+"( SELECT group_concat(tabname) FROM comic t,tab ,comictab WHERE c.comicid=t.comicid AND t.comicid = comictab.comicid AND tab.tabid = comictab.tabid) AS type "
				+"FROM comic c, comictab ,author ,tab "
				+"WHERE c.comicid = comictab.comicid AND comictab.tabid = "+tabId+" AND c.authorid = author.authorid AND comictab.tabid = tab.tabid "
				+"ORDER BY c.good DESC";
		Query query = getCurrentSession().createSQLQuery(str).addEntity(
				RankByTabModel.class);
		
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	public List<RankByTabModel> RankByGood(int pre,int max) {
		String str="SELECT c.comicid, c.comicname, c.comicstatus,c.description,c.src,c.good,author.realname, "
				+"( SELECT group_concat(tabname) FROM comic t,tab ,comictab WHERE c.comicid=t.comicid AND t.comicid = comictab.comicid AND tab.tabid = comictab.tabid) AS type "
				+"FROM comic c,author "
				+"WHERE c.authorid = author.authorid "
				+"ORDER BY c.good DESC";
		Query query = getCurrentSession().createSQLQuery(str).addEntity(
				RankByTabModel.class);
		
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	public List<RankByTabModel> RankByLove(int pre,int max) {
		String str="SELECT c.comicid, c.comicname, c.comicstatus,c.description,c.src,c.good,author.realname, "
				+"( SELECT group_concat(tabname) FROM comic t,tab ,comictab WHERE c.comicid=t.comicid AND t.comicid = comictab.comicid AND tab.tabid = comictab.tabid) AS type, "
				+"(SELECT Count(*) FROM usercomic AS uc WHERE uc.comicid=c.comicid) AS lovesum  "
				+"FROM comic c,author "
				+"WHERE c.authorid = author.authorid "
				+"ORDER BY lovesum DESC";
		Query query = getCurrentSession().createSQLQuery(str).addEntity(
				RankByTabModel.class);
		
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	
	public List<AuthorComicModel> GetAuthorComic(String authorId,String orderby,int pre,int max) {
		String str="select comic.*,"
				+ "( SELECT group_concat(tabname) FROM comic t,tab ,comictab WHERE comic.comicid=t.comicid AND t.comicid = comictab.comicid AND tab.tabid = comictab.tabid) AS type,"
				+"(select count(*) from comicpart as cp where cp.comicid=comic.comicid ) AS partsum,"
				+"(select count(*) from usercomic as uc where uc.comicid=comic.comicid) as lovesum,"
				+"((select count(*) from comment where comment.comicid=comic.comicid)+(select count(*) from comment,commentsub where comment.comicid=comic.comicid and comment.commentid=commentsub.commentid)) as commentsum"
				+ " from comic where authorid="+authorId+" order by "+orderby+" desc";
		Query query = getCurrentSession().createSQLQuery(str).addEntity(AuthorComicModel.class);
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}

	public int GetSumByAC(String authorId) {
		String str="select count(*) as sumac from comic where authorid="+authorId+"";
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumac",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumac");
	}
	
	public List<Comic> GetComicByKeyWord(String keyword,int pre,int max){
		String str="select *  from comic where comicname like '%"+keyword+"%'";
		
		Query query = getCurrentSession().createSQLQuery(str).addEntity(
				Comic.class);
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	public int GetSumByKeyWord(String keyword){
		String str="select count(*) as sumkey from comic where comicname like '%"+keyword+"%'";
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumkey",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumkey");
	}
}
