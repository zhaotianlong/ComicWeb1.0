package dao;

import java.util.List;

import model.ComicTab;
import dao.common.IOperation;

public interface IComicTabDao extends IOperation<ComicTab> {
	public String WhereNew(String[] property, String[] value);
	List<ComicTab> GetComicTab(String str,int pre,int max);
}
