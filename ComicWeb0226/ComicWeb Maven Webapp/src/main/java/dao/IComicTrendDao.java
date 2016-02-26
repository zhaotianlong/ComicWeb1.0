package dao;

import java.util.List;

import model.ComicTrend;
import model.vo.ComicTrendModel;
import dao.common.IOperation;

public interface IComicTrendDao extends IOperation<ComicTrend> {
	
	/***
	 * 获取动态总数
	 * @return
	 */
	public int GetSum();
	 /***
		 * 获取关注动态
		 * @param userId 用户id
		 * @return 动态列表
		 */
	 List<ComicTrendModel> GetLoveAuthorTrend(String userId,int pre,int max);
	 /***
	  * 获取关注动态
	  * @param userId
	  * @return
	  */
	 public int GetSumLAT(String userId);
	 /***
	  * 获取订阅动态
	  * @param userId 用户id
	  * @param pre 记录的开始
	  * @param max 最大条数
	  * @return
	  */
	 public List<ComicTrendModel> GetLoveComicTrend(String userId, int pre, int max);
	 /***
	  * 获取订阅动态数
	  * @param userId
	  * @return
	  */
	 public int GetSumLCT(String userId);
	 /***
	  * 获取作者动态
	  * @param authorId 作者id
	  * @param pre 记录开始
	  * @param max 最大条数
	  * @return
	  */
	 public List<ComicTrendModel> GetAuthorTrend(String authorId,int pre,int max);
	 /***
	  * 获取作者动态条数
	  * @param authorId 作者id
	  * @return
	  */
	 public int GetSumAT(String authorId);
	 
}
