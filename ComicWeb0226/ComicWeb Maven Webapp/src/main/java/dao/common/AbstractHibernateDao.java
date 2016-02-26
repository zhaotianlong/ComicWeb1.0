package dao.common;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends Serializable> implements
		IOperation<T> {
	private Class<T> clazz;
	private  String hql;
	protected String hqlExe;
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	protected final Session getCurrentSession() {
		// System.out.println("------:"+sessionFactory.getCurrentSession());
		return sessionFactory.getCurrentSession();
	}

	protected final void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
		hql="from "+clazz.getName();
		hqlExe=hql;
	}

	public T FindOne(Object id) {
		// TODO Auto-generated method stub
		// System.out.println("operation:" + id + ""
		// + getCurrentSession().getClass());
		System.out.println(id);
		return (T) getCurrentSession().get(clazz, (Serializable) id);
	}

	public List<T> FindAll() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	public List<T> FindList(String conditionName, String str) {
		// TODO Auto-generated method stub
		// System.out.println("+++++++");
		return getCurrentSession().createQuery(
				"from " + clazz.getName() + " where " + conditionName + "="
						+ str).list();
	}

	public void Create(T model) {
		// TODO Auto-generated method stub
		// getCurrentSession().saveOrUpdate(model);
		getCurrentSession().save(model);
	}

	public T Update(T model) {
		// TODO Auto-generated method stub
		getCurrentSession().update(model);
		return model;
	}

	public void Delete(T model) {
		// TODO Auto-generated method stub
		getCurrentSession().delete(model);
	}

	public List<T> FindWithCondition(String str) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery(str).list();
	}

	public List<T> FindList(String[] propertyName, String[] valueName) {
		// TODO Auto-generated method stub
		if (propertyName.length != valueName.length)
			return null;
		String str = "from " + clazz.getName() + " where";
		for (int i = 0; i < propertyName.length; i++) {
			if (i == 0)
				str += " " + propertyName[0] + "=" + valueName[0];
			else
				str += " and " + propertyName[i] + "=" + valueName[i];
		}

		return getCurrentSession().createQuery(str).list();
	}

	public List<T> FindByPage(String[] propertyName, String[] valueName,int pre, int maxNum) {
		// TODO Auto-generated method stub
		String str="from " + clazz.getName();
		if(propertyName!=null&&valueName!=null){
		if(propertyName.length!=valueName.length)
			return null;
		for(int i=0;i<propertyName.length;i++){
			if(i==0)
				str+=" where "+propertyName[0]+"="+valueName[0];
			else
				str+=" and "+propertyName[i]+"="+valueName[i];
			}
		}
		System.out.println("fuck:"+str);
		Query query=getCurrentSession().createQuery(str);
		query.setFirstResult(pre);
		query.setMaxResults(maxNum);
		return query.list();
	}

	public int FindSum(String[] propertyName, String[] valueName) {
		// TODO Auto-generated method stub
		
		String str="select count(*) from " + clazz.getName();
		if(propertyName!=null&&valueName!=null){
			if(propertyName.length!=valueName.length)return 0;
			System.out.println("test:"+propertyName[0]+" "+propertyName.length+"   "+valueName[0]+" "+valueName.length);
			for(int i=0;i<propertyName.length;i++){
				if(i==0)
					str+=" where "+propertyName[0]+"="+valueName[0];
				else
					str+=" and "+propertyName[i]+"="+valueName[i];
				}
			}
			System.out.println(str);
		 Query query=getCurrentSession().createQuery(str);
		 int count = ((Long) query.iterate().next()).intValue();
		return count;
	}

	
	
	public void Where(String[] propertyName, String[] valueName) {
		// TODO Auto-generated method stub
		if(propertyName!=null&&valueName!=null){
			if(propertyName.length!=valueName.length)return ;
			System.out.println("test:"+propertyName[0]+" "+propertyName.length+"   "+valueName[0]+" "+valueName.length);
			for(int i=0;i<propertyName.length;i++){
				if(i==0)
					hqlExe+=" where "+propertyName[0]+"="+valueName[0];
				else
					hqlExe+=" and "+propertyName[i]+"="+valueName[i];
				}
			}
	}

	public void OrderBy(String tag) {
		// TODO Auto-generated method stub
		hqlExe+=" order by " +tag;
	}

	public List<T> Find() {
		// TODO Auto-generated method stub
		Query q= getCurrentSession().createQuery(hqlExe);
		//System.out.println(hqlExe);
		hqlExe=hql;
		return q.list();
	}

	public List<T> Find(int pre, int maxNum) {
		// TODO Auto-generated method stub
		Query query= getCurrentSession().createQuery(hqlExe);
		query.setFirstResult(pre);
		query.setMaxResults(maxNum);
		//System.out.println(hqlExe);
		hqlExe=hql;
		return query.list();
	}

}
