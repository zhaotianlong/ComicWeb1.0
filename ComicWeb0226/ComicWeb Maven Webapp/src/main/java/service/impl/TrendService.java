package service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.IAuthorUser;
import dao.IUserComicDao;
import service.ITrendService;
@Transactional
@Service("trendService")
public class TrendService implements ITrendService {

	@Resource(name = "userComicDao")
	private IUserComicDao userComicDao;
	
	@Resource(name = "authorUserDao")
	private IAuthorUser authorUserDao;
	
	
}
