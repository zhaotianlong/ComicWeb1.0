package dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import model.Comment;
import model.vo.CommentModel;
import dao.ICommentDao;
import dao.common.AbstractHibernateDao;

@Repository(value="commentDao")
public class CommentDao extends AbstractHibernateDao<Comment> implements ICommentDao {

	public CommentDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(Comment.class);
	}
	public List<CommentModel> FindComment(String userId,int pre,int max) {
		String str="SELECT comicname,commentid, commentdes, datetime,userid,`comment`.comicid,src FROM `comment`,comic WHERE `comment`.userid = "+userId+" AND comic.comicid = `comment`.comicid ORDER BY `comment`.datetime DESC";
		Query query=getCurrentSession().createSQLQuery(str).addEntity("CommentModel", CommentModel.class);
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	public int GetSum(String userId){
		String str="SELECT count(*) as sumcomment  FROM `comment` WHERE `comment`.userid = "+userId;
		Query query=getCurrentSession().createSQLQuery(str).addScalar("sumcomment",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumcomment");
	}
	
}
