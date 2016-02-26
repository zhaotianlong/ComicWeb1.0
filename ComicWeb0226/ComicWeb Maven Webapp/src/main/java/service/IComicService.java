package service;

import java.util.List;

import model.Comic;
import model.ComicPage;
import model.ComicPart;
import model.ComicScore;
import model.Comment;
import model.CommentSub;
import model.SpitSlot;
import model.User;
import model.vo.ComicListModel;
import model.vo.RankByTabModel;

public interface IComicService {
	public List<ComicListModel> GetAllComic(String[] property,String[] value,int pre,int maxNum,String tag); 
	public int GetSum(String[] property, String[] value, String tag);
	public List<Comment> GetComicComment(String[] propertyName,String[] valueName,int pre,int maxNum);
	public Comic GetComicInfo(String comicId);
	public int GetCommentSum(String[] property, String[] value);
	public void InsertComment(Comment c);
	public  Comic GetComic(String comicId);
	public 	Comment GetComent(int commentId);
	public User GetUser(String accountId);
	public void InsertCommentBack(CommentSub cs);
	public void UpdateComment(Comment c);
	/***
	 * 获取漫画某章节
	 * @param comicpartId 漫画章节id
	 * @return
	 */
	public ComicPart GetComicPart(String comicpartId);
	public int  GetComicPartSum(String comicName);
	public void InsertSpitSlot(SpitSlot ss);
	public List<RankByTabModel> RankByGood();
	public List<RankByTabModel> RankByLove();
	public List<RankByTabModel> RankByTab(String id,int pre,int max);
	/***
	 * 获取漫画评总分
	 * @param comicId
	 * @return
	 */
	public float GetComicScoreSum(String comicId);
	/***
	 * 检查用户是否曾评分
	 * @param comicId 漫画id
	 * @param userId  用户id
	 * @return
	 */
	public boolean CheckComicScore(String comicId,String userId);
	/***
	 * 获取漫画评论人数
	 * @param comicId 漫画id
	 * @return
	 */
	public int GetComicScorePeopleSum(String comicId);
	/***
	 * 获取漫画评分对象
	 * @param prorty 属性列表
	 * @param value	 值列表
	 * @return
	 */
	public ComicScore GetComicScore(String[] prorty,String[] value);
	/***
	 * 创建漫画评分对象
	 * @param cs 评分对象
	 */
	public void CreateComicScore(ComicScore cs);
	/***
	 * 获取某漫画的订阅人数
	 * @param comicId 漫画id
	 * @return
	 */
	public int GetUserSumUc(String comicId);
	/***
	 * 获取某作者的漫画数
	 * @param authorId 漫画id
	 * @return
	 */
	public int GetAuthorSumByComic(String authorId);
	/***
	 * 获取漫画页对象
	 * @param pageId 页id
	 * @return
	 */
	public ComicPage GetComicPage(String pageId);
	/***
	 * 搜索漫画
	 * @param keyword 关键字
	 * @param pre 起始记录
	 * @param max 最大条数
	 * @return
	 */
	public List<Comic> SearchComic(String keyword,int pre,int max);
	/***
	 * 获取包含关键字的漫画数
	 * @param keyword 关键字
	 * @return
	 */
	public int GetSumBySearch(String keyword);
}
