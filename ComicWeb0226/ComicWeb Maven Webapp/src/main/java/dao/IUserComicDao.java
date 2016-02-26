package dao;

import model.UserComic;
import dao.common.IOperation;

public interface IUserComicDao extends IOperation<UserComic> {
	/***
	 * 获取订阅对象
	 * @param comicId string类型  漫画id
	 * @param userId string类型  用户id
	 * @return
	 */
	public UserComic GetUserComic(String comicId,String userId);
	/***
	 * 获取某用户的订阅总数
	 * @param comicId 漫画id
	 * @return
	 */
	public int GetSumByComic(String comicId);
	
}
