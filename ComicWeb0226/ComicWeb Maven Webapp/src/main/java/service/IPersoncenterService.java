package service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import model.Comic;
import model.ComicTrend;
import model.Comment;
import model.CommentSub;
import model.User;
import model.UserComic;
import model.vo.ComicTrendModel;
import model.vo.CommentModel;
import model.vo.CommentSubModdel;
import model.vo.LoveComicModel;
import model.vo.LoveComicerModel;

public interface IPersoncenterService{
	public List<LoveComicModel> FindLoveComic(String[] propertyName,String[] valueName,int pre,int last);
	public void CannelLoveComic(String[] propertyName,String[] valueName);
	public void AddLoveComic(String[] propertyName,String[] valueName);
	public int GetSum(String[] propertyName,String[] valueName);
	
	List<LoveComicerModel> FindLoveComicer(String[] propertyName,String[] valueName,int pre,int last);
	public int GetSumByAuhorUser(String[] propertyName, String[] valueName);
	public void CannelLoveComicer(String[] propertyName, String[] valueName);
	
	public User FindUser(String userId);
	public void SaveUserInfo(User user);
	public String CutoutIcon(MultipartFile file,int x,int y,int w,int h,int selectW,int selectH,String  iconName)throws IOException;
	public List<CommentModel> FindComments(String userId,int pre,int max);//此处有问题
	public List<CommentSubModdel> FindCommentSubs(String userId,int pre,int max);
	public List<CommentSubModdel> FindCommentSubTos(String userId,int pre,int max);
	
	public  int GetCommentSum(String userId) ;
	public  int GetCommentSubSum(String userId) ;
	/***
	 * 获取新回复条数
	 * @param userId 用户id
	 * @return
	 */
	public int GetCommentSubNewSum(String userId);
	public  int GetCommentSubToSum(String userId) ;
	public void CancelCommentBack(int id);
	public void CancelComment(int id);
	
	/***
	 * 获取漫画动态
	 * @param userId string类型 用户id
	 * @return
	 */
	public List<ComicTrendModel> GetComicTrend(String userId,int pre,int max);
	/***
	 * 获取作者动态
	 * @param userId string类型 用户id
	 * @return
	 */
	public List<ComicTrendModel> GetAuthorTrend(String userId,int pre,int max);
	
	/***
	 * 获取订阅动态总数
	 * @param userId
	 * @return
	 */
	public int GetComicTrendSum(String userId);
	/***
	 * 获取关注动态总数
	 * @param userId
	 * @return
	 */
	public int GetAuthorTrendSum(String userId);
	/***
	 * 更新回复状态
	 * @param subid 回复id
	 * @param status 状态
	 */
	public void UpdateCommentSubStatus(int subid,int status);
}
