package service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import model.Author;
import model.AuthorUser;
import model.Comic;
import model.ComicTrend;
import model.Comment;
import model.CommentSub;
import model.Role;
import model.SpitSlot;
import model.User;
import model.UserComic;
import model.vo.LoveComicModel;


public interface IManagerService {

	public int GetUserSum();
	public int GetAuthorSum();
	public int GetComicSum();
	public int GetCommentSum();
	public int GetAuthorUserSum();
	public int GetUserComicSum();
	/***
	 * 获取动态总数
	 * @return
	 */
	public int GetComicTrendSum();

	public User GetUser(String userId);
	public void SaveUserInfo(User u);
	
	public String CutoutIcon(MultipartFile file,int x,int y,int w,int h,int selectW,int selectH,String  iconName)throws IOException;
	
	public List<User> GetUserList(int pre,int max);
	public void CreateUser(User u);
	
	/***
	 * 获取权限对象
	 * @param roleId 权限id int类型
	 * @return
	 */
	public Role GetRole(Integer roleId);
	
	/***
	 * 获取关注的记录
	 * @param propertyName 属性名（userId，authorId）
	 * @param valueName  值 （userId，authorId）
	 * @return
	 */
	public AuthorUser GetAuthorUser(String[] propertyName,String[] valueName);
	
	/***
	 * 创建关注记录
	 * @param propertyName 属性名（userId，authorId）
	 * @param valueName 值 （userId，authorId）
	 */
	public void CreateAuthorUser(String userId,String authorId);
	/***
	 * 获取用户订阅记录
	 * @param propertyName 属性名（userId，ComicId）
	 * @param valueName 值 （userId，ComicId）
	 * @return
	 */
	public UserComic GetUserComic(String[] propertyName,String[] valueName);
	
	
	/***
	 * 获取作者列表
	 * @param pre 开始条数
	 * @param max 最大多少条
	 * @return
	 */
	public List<Author> GetAuthorList(int pre,int max);
	
	/***
	 * 创建作者对象 
	 * @param a 类型Author 作者对象
	 */
	public void CreateAuthor(Author a);
	/***
	 * 获取作者对象
	 * @param authorId String 作者id
	 * @return
	 */
	public Author GetAuthor(String authorId);
	/***
	 * 更新作者对象信息
	 * @param a author对象 
	 */
	public void SaveAuthorInfo(Author a);
	/***
	 * 获取漫画列表
	 * @param pre 类型int 查询开始条数
	 * @param max 类型int 查询最大条数
	 * @return
	 */
	public List<Comic> GetComicList(int pre,int max);
	/***
	 * 获取评论列表
	 * @param pre 类型int 查询开始条数
	 * @param max 类型int 查询最大条数
	 * @return
	 */
	public List<Comment> GetCommentList(int pre,int max);
	/***
	 * 获取评论对象
	 * @param commentId Integer类型 评论id
	 * @return
	 */
	public Comment GetComment(Integer commentId);
	/***
	 * 更新评论信息
	 * @param c comment对象 评论对象
	 */
	public void SaveCommentInfo(Comment c);
	/***
	 * 创建评论对象
	 * @param c comment类型 评论对象
	 */
	public void CreateComment(Comment c);
	/***
	 * 删除评论对象
	 * @param c
	 */
	public void DeleteComment(Comment c);
	/***
	 * 获取回复对象
	 * @param commentSubId int类型 回复id
	 * @return
	 */
	public CommentSub GetCommentSub(Integer commentSubId);
	/***
	 * 保存回复对象
	 * @param cs CommentSub类型 回复对象
	 */
	public void SaveCommentSubInfo(CommentSub cs);
	/***
	 * 创建回复对象
	 * @param cs CommentSub类型 回复对象
	 */
	public void CreateCommentSub(CommentSub cs);
	/***
	 * 删除回复对象
	 * @param cs CommentSub类型 回复对象
	 */
	public void DeleteCommentSub(CommentSub cs);
	/***
	 * 获取吐槽列表
	 * @param pre int类型   起始记录
	 * @param max int类型  最大条数
	 * @return
	 */
	public List<SpitSlot> GetSpitSlotList(int pre,int max);
	
	/***
	 * 获取吐槽的数量
	 * @return
	 */
	public int GetSpitSlotSum();
	/***
	 * 获取吐槽对象
	 * @param spitslotId int类型  吐槽id
	 * @return
	 */
	public SpitSlot GetSpitSlot(Integer spitslotId);
	/***
	 * 删除吐槽对象
	 * @param spitslotId int类型 吐槽id
	 */
	public void DeleteSpitSlot(SpitSlot ss);
	/***
	 * 保存吐槽对象
	 * @param ss SpitSlot对象
	 */
	public void SaveSpitSlotInfo(SpitSlot ss);
	/***
	 * 创建吐槽对象
	 * @param ss SpitSlot对象
	 */
	public void CreateSpitSlot(SpitSlot ss);
	/***
	 * 获取订阅对象
	 * @param comicId string类型  漫画id
	 * @param userId  string类型  用户id
	 * @return
	 */
	public UserComic GetUserComic(String comicId,String userId);
	/***
	 * 删除订阅对象
	 * @param comicId string类型  漫画id
	 * @param userId  string类型  用户id
	 * 
	 */
	public void DeleteUserComic(UserComic uc);
	/***
	 * 创建订阅对象
	 * @param comicId string类型  漫画id
	 * @param userId  string类型  用户id
	 * 
	 */
	public void CreateUserComic(String comicId,String userId);
	/***
	 * 保存订阅对象
	 * @param comicId string类型  漫画id
	 * @param userId  string类型  用户id
	 * 
	 */
	public void SaveUserComicInfo(UserComic uc);
	/***
	 * 获取订阅列表
	 * @return
	 */
	public List<UserComic> GetUserComicList(int pre,int maxNum);
	/***
	 * 获取订阅对象
	 * @param id int类型  订阅id
	 * @return
	 */
	public UserComic GetUserComic(Integer id);
	
	/***
	 * 创建订阅对象
	 * @param uc UserComic 订阅对象
	 */
	public void CreateUserComic(UserComic uc);
	/***
	 * 创建关注对象
	 * @param au AuthorUser对象 
	 */
	public void CreateUserAuthor(AuthorUser au);
	/***
	 * 保存关注对象
	 * @param au AuthorUser对象
	 */
	public void SaveUserAuthorInfo(AuthorUser au);
	/***
	 * 获取关注对象
	 * @param id 关注对象id
	 * @return
	 */
	public AuthorUser GetAuthorUser(Integer id);
	/***
	 * 删除关注对象
	 * @param au AuthorUser对象
	 */
	public void DeleteAuthorUser(AuthorUser au);
	/***
	 * 获取关注对象列表
	 * @param pre int类型   起始记录
	 * @param max int类型  最大条数
	 * @return
	 */
	public List<AuthorUser> GetAuthorUserList(int pre,int max);
	/***
	 * 获取动态列表
	 * @param pre 起始记录
	 * @param max 最大条数
	 * @return
	 */
	public List<ComicTrend> GetComicTrendList(int pre,int max);
	/***
	 * 保存动态对象
	 * @param ct 动态对象
	 */
	public void SaveComictrend(ComicTrend ct);
	/***
	 * 删除动态对象
	 * @param ct 动态对象
	 */
	public void DeleteComictrend(ComicTrend ct);
	/***
	 * 创建动态对象
	 * @param ct 动态对象
	 */
	public void CreateComicTrend(ComicTrend ct);
	/***
	 * 获取动态对象
	 * @param id 动态对象id
	 * @return
	 */
	public ComicTrend GetComicTrend(Integer id);
}
