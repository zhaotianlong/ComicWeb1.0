package dao;

import java.util.List;

import model.ComicTab;
import model.Comment;
import model.vo.CommentModel;
import model.vo.CommentSubModdel;
import dao.common.IOperation;

public interface ICommentDao extends IOperation<Comment> {

	public List<CommentModel> FindComment(String userId,int pre,int max);
	public int GetSum(String userId);
}
