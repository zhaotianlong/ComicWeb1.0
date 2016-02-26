package dao;

import java.util.List;

import model.Comic;
import model.vo.AuthorComicModel;
import model.vo.ComicListModel;
import model.vo.RankByTabModel;
import dao.common.IOperation;

public interface IComicDao extends IOperation<Comic> {
	public List<ComicListModel> FindWithTab( int pre, int max);
	public int GetSum();
	public List<RankByTabModel> RankByTab(String tabId,int pre,int max);
	public List<RankByTabModel> RankByGood(int pre,int max);
	public List<RankByTabModel> RankByLove(int pre,int max);
	public List<AuthorComicModel> GetAuthorComic(String authorId,String orderby,int pre,int max);
	public int GetSumByAC(String authorId);
	/***
	 * 获取漫画 通过关键字
	 * @param keyword 关键字
	 * @param pre 起始记录
	 * @param max 最大条数
	 * @return
	 */
	public List<Comic> GetComicByKeyWord(String keyword,int pre,int max);
	/***
	 * 获取包含关键字的漫画数
	 * @param keyword 关键字
	 * @return
	 */
	public int GetSumByKeyWord(String keyword);
}
