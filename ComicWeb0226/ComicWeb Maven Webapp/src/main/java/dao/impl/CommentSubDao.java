package dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import model.Comment;
import model.CommentSub;
import model.vo.CommentModel;
import model.vo.CommentSubModdel;
import dao.ICommentSubDao;
import dao.common.AbstractHibernateDao;


@Repository(value="commentsubDao")

public class CommentSubDao extends AbstractHibernateDao<CommentSub> implements
		ICommentSubDao {

	public CommentSubDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(CommentSub.class);
	}
	public List<CommentSubModdel> FindComment(String userId,int pre,int max) {
		String str="SELECT  commentsub.newstatus,nickname,`comment`.commentdes as commentdest,commentsub.toid as userid,`comment`.comicid, commentsub.newdate,commentsub.commentdes,commentsub.subid, comicname,iconPath,src FROM `user`,commentsub,`comment`,comic WHERE `user`.accountid=commentsub.toid and commentsub.fromid = "+userId+" AND `comment`.commentid = commentsub.commentid AND comic.comicid=`comment`.comicid ORDER BY commentsub.newdate DESC";
		Query query=getCurrentSession().createSQLQuery(str).addEntity("CommentSubModdel", CommentSubModdel.class);
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	public int GetSum(String userId){
		String str="SELECT count(*) as sumcomment FROM commentsub WHERE commentsub.fromid = "+userId+"";
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumcomment",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumcomment");
	}
	
	public List<CommentSubModdel> FindCommentTo(String userId,int pre,int max) {
		String str="SELECT  commentsub.newstatus,nickname,`comment`.commentdes as commentdest,commentsub.fromid as userid,`comment`.comicid, commentsub.newdate,commentsub.commentdes,commentsub.subid, comicname,iconPath,src FROM commentsub,`comment`,`user`,comic WHERE `user`.accountid = commentsub.fromid and  commentsub.toid = "+userId+" AND `comment`.commentid = commentsub.commentid AND comic.comicid=`comment`.comicid ORDER BY commentsub.newdate DESC";
		Query query=getCurrentSession().createSQLQuery(str).addEntity("CommentSubModdel", CommentSubModdel.class);
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	public int GetSumTo(String userId){
		String str="SELECT count(*) as sumcomment FROM commentsub WHERE commentsub.toid = "+userId+"";
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumcomment",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumcomment");
	}
	
	public int GetNewSum(String userId){
		String str="SELECT count(*) as sumcomment FROM commentsub WHERE commentsub.toid = "+userId+" and newstatus=0";
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumcomment",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumcomment");
	}
	
	
	
}
