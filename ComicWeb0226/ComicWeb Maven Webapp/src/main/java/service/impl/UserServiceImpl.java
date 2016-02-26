package service.impl;

import java.io.IOException;

import javax.annotation.Resource;
import javax.swing.text.View;

import org.springframework.stereotype.Service;

import model.Author;
import model.User;
import model.vo.LoginModel;
import service.IUserService;
import service.common.AbstractService;
import dao.IAuthorDao;
import dao.IUserComicDao;
import dao.IUserDao;
import dao.common.IOperation;

@Service("userService")
public class UserServiceImpl extends AbstractService<User> implements IUserService {

	@Resource(name="userDao")
	private IUserDao dao;
	
	@Resource(name="userComicDao")
	private IUserComicDao userComicDao;	
	
	@Resource(name="authorDao")
	private IAuthorDao authorDao;
	
	public  UserServiceImpl() {
		super();
	}
	@Override
	protected IOperation<User> getDao() {
		// TODO Auto-generated method stub
		//System.out.println("dao"+dao+"");
		return this.dao;
	}
	
	public void CheckLogin(LoginModel lm) throws IOException {
			User user=dao.FindOne(lm.getAccount());
			if(user==null) throw new IOException("账户不存在");
			if(user.getPassword().equals(lm.getPassword())==false)throw new IOException("输入密码有误");
	}
	
	public Author GetAuthor(String authorId){
		return authorDao.FindOne(authorId);
	}
	public void CheckAuthorLogin(LoginModel lm) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("lm:"+lm.getAccount());
		Author author=authorDao.FindOne(lm.getAccount());
		if(author==null) throw new IOException("账户不存在");
		if(author.getPassword().equals(lm.getPassword())==false)throw new IOException("输入密码有误");
	}
	public void CreateAuthor(Author a){
		authorDao.Create(a);
	}
	
}
