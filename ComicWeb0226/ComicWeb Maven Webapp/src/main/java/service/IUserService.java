package service;

import java.io.IOException;

import model.Author;
import model.User;
import model.vo.LoginModel;
import dao.common.IOperation;

public interface IUserService extends IOperation<User> {
	public void CheckLogin(LoginModel lm) throws IOException;
	public void CheckAuthorLogin(LoginModel lm)throws IOException;
	public Author GetAuthor(String authorId);
	public void CreateAuthor(Author a);

}
