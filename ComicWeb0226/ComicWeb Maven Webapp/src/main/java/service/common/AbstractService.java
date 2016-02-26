package service.common;

import java.io.Serializable;
import java.util.List;

import javax.transaction.TransactionScoped;

import org.springframework.transaction.annotation.Transactional;

import dao.common.IOperation;
@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperation<T> {

	protected  abstract IOperation<T> getDao();

	public T FindOne(Object id) {
		// TODO Auto-generated method stub
		return getDao().FindOne(id);
	}


	public List<T> FindAll() {
		// TODO Auto-generated method stub
		return getDao().FindAll();
	}

	public void Create(T model) {
		// TODO Auto-generated method stub
		getDao().Create(model);
	}

	public T Update(T model) {
		// TODO Auto-generated method stub
		return getDao().Update(model);
	}

	public void Delete(T model) {
		// TODO Auto-generated method stub
		getDao().Delete(model);
		
	}

	public List<T> FindList(String conditionName, String str) {
		// TODO Auto-generated method stub
		return getDao().FindList(conditionName, str);
	}

	public List<T> FindWithCondition(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> FindList(String[] propertyName, String[] valueName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> FindByPage(String[] propertyName, String[] valueName,
			int pre, int last) {
		// TODO Auto-generated method stub
		return null;
	}

	public int FindSum(String[] propertyName, String[] valueName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void Where(String[] propertyName, String[] valueName) {
		// TODO Auto-generated method stub
		
	}

	public void OrderBy(String tag) {
		// TODO Auto-generated method stub
		
	}

	public List<T> Find() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> Find(int pre, int maxNum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
