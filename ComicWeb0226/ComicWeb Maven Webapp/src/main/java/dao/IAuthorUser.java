package dao;

import model.AuthorUser;
import dao.common.IOperation;

public interface IAuthorUser extends IOperation<AuthorUser> {
	public int GetSumByAuthorId(String authorId);
}
