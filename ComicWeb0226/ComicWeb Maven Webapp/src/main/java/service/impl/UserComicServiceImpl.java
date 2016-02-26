package service.impl;

import java.util.List;

import javax.annotation.Resource;

import dao.IRoleDao;
import dao.IUserComicDao;
import dao.common.IOperation;
import model.Role;
import model.UserComic;
import service.IUserComicService;
import service.common.AbstractService;

public class UserComicServiceImpl extends AbstractService<UserComic> implements
		IUserComicService {
	@Resource(name = "userComicDao")
	private IUserComicDao userComicDao;

	@Override
	protected IOperation<UserComic> getDao() {
		// TODO Auto-generated method stub
		return this.userComicDao;
	}

}
