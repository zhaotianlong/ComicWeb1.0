package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import model.ComicPart;
import model.ComicTab;
import model.vo.ComicListModel;
import dao.IComicTabDao;
import dao.common.AbstractHibernateDao;

@Repository("comicTabDao")
public class ComicTabDao extends AbstractHibernateDao<ComicTab> implements IComicTabDao {

	public ComicTabDao() {
		super();
		// TODO Auto-generated constructor stub
		setClazz(ComicTab.class);
	}
	public String WhereNew(String[] property, String[] value) {
		String str = "select * from comictab ";

		if (property != null && value != null) {
			if (property.length != value.length)
				return "";
			//property[i] =“c”+ property[i] 报错
			for (int i = 0; i < property.length; i++) {
				if (i == 0)
					str += " where " + property[0] + "=" + value[0];
				else
					str += " and " + property[i] + "=" + value[i];
			}
		}
		System.out.println("test:" + str);
		return  str;

	}
	public List<ComicTab> GetComicTab(String str,int pre,int max){
		System.out.println("test:" + hqlExe);
		Query query = getCurrentSession().createSQLQuery(str).addEntity(
				ComicTab.class);
		query.setFirstResult(pre);
		query.setMaxResults(max);
		return query.list();
	}
	
}
