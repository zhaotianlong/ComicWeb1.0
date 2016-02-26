package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UsersDao_tmp;

public class UserService_tmp {
	  @Autowired
	private UsersDao_tmp usersDao;

	public UsersDao_tmp getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao_tmp userDao) {
		this.usersDao = userDao;
	}
	public int UserCount(){
        return usersDao.getAllUser().size();
    }
	public void AddUser(){
       usersDao.addOneUser();
    }
}
