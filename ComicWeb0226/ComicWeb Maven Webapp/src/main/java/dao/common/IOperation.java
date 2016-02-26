package dao.common;

import java.io.Serializable;
import java.util.List;

public interface IOperation<T extends Serializable> {
	T FindOne(final Object id);
	List<T> FindAll();
	void Create(final T model);
	T Update(final T model);
	void Delete(final T model);
	public List<T> FindList(String conditionName,String str);
	public List<T> FindWithCondition(String str);
	public List<T> FindList(String[] propertyName,String[] valueName);
	public List<T> FindByPage(String[] propertyName,String[] valueName,int pre,int maxNum);
	public int FindSum(String[] propertyName,String[] valueName);
	public void Where(String[] propertyName,String[] valueName);
	public void OrderBy(String tag);
	public List<T> Find();
	public List<T> Find(int pre,int maxNum);
}
