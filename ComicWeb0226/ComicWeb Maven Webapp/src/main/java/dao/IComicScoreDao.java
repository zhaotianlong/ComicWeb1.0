package dao;

import model.ComicScore;
import dao.common.IOperation;

public interface IComicScoreDao extends IOperation<ComicScore> {
	/***
	 * 获取漫画评分记录总条数
	 * @return
	 */
	public int GetSum();
	/***
	 * 获取漫画评论人数
	 * @param comicId 漫画id
	 * @return
	 */
	public int GetSum(String comicId);
	/***
	 * 获取漫画的总分
	 * @param comicId 漫画id
	 * @return
	 */
	public Integer GetSumStarLevel(String comicId);
	/***
	 * 判断用户是否曾评论漫画 异常
	 * @param comicId 漫画id
	 * @param userId  用户id
	 * @return
	 */
	public boolean CheckComicScore(String comicId,String userId);
}
