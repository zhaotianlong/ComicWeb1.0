package dao;

import java.util.List;

import model.Comment;
import model.CommentSub;
import model.vo.CommentSubModdel;
import dao.common.IOperation;

public interface ICommentSubDao extends IOperation<CommentSub> {
	public List<CommentSubModdel> FindComment(String userId,int pre,int max);
	public int GetSum(String userId);
	public List<CommentSubModdel> FindCommentTo(String userId,int pre,int max);
	public int GetSumTo(String userId);
	/***
	 * 获取未读取评论
	 * @param userId 用户id
	 * @return 条数
	 */
	public int GetNewSum(String userId);
}
