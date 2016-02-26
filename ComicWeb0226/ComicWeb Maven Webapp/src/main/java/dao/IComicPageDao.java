package dao;

import model.ComicPage;
import dao.common.IOperation;

public interface IComicPageDao extends IOperation<ComicPage> {
	/***
	 * 获取章节中最大的id
	 * @param partId string类型  partID
	 * @return
	 */
	public int GetmaxpageId(String partId);
	/***
	 * 获取某章节下漫画页
	 * @param partId 章节id
	 * @return
	 */
	public int GetSum(String partId);
}
