package service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.IAuthorDao;
import dao.IComicDao;
import dao.IComicPageDao;
import dao.IComicPartDao;
import dao.IComicScoreDao;
import dao.IComicTabDao;
import dao.ICommentDao;
import dao.ICommentSubDao;
import dao.ISpitSlotDao;
import dao.IUserComicDao;
import dao.IUserDao;
import dao.impl.CommentDao;
import model.Comic;
import model.ComicPage;
import model.ComicPart;
import model.ComicScore;
import model.ComicTab;
import model.Comment;
import model.CommentSub;
import model.SpitSlot;
import model.Tab;
import model.User;
import model.vo.ComicListModel;
import model.vo.RankByTabModel;
import service.IComicService;

@Transactional
@Service("comicService")
public class ComicService implements IComicService {

	
	@Resource(name = "comicDao")
	private IComicDao comicDao;

	@Resource(name = "comicTabDao")
	private IComicTabDao comicTabDao;
	
	@Resource(name = "commentDao")
	private ICommentDao commentDao;
	
	@Resource(name = "commentsubDao")
	private ICommentSubDao commentsubDao;
	
	@Resource(name = "userDao")
	private IUserDao userDao;
	
	@Resource(name="spitslotDao")
	private ISpitSlotDao spitslotDao;
	
	@Resource(name="comicScoreDao")
	private IComicScoreDao comicScoreDao;
	
	@Resource(name="comicPartDao")
	private IComicPartDao comicPartDao;
	
	@Resource(name="userComicDao")
	private IUserComicDao userComicDao;
	
	@Resource(name="comicPageDao")
	private IComicPageDao comicPageDao;
	
	public List<ComicListModel> GetAllComic(String[] property, String[] value,
			int pre, int maxNum, String tag) {
		// TODO Auto-generated method stub
		if(property!=null)
		System.out.println("service:"+property[0]);
		comicDao.Where(property, value);
		comicDao.OrderBy(tag);
		return comicDao.FindWithTab(pre, maxNum);
	}
	
	public int GetSum(String[] property, String[] value, String tag){
		comicDao.Where(property, value);
		comicDao.OrderBy(tag);
		return comicDao.GetSum();
	}
	
	public Comic GetComicInfo(String comicId) {
		
		return comicDao.FindOne(comicId);
	}
	
	public List<Comment> GetComicComment(String[] propertyName,String[] valueName,int pre,int maxNum)
	{

		commentDao.Where(propertyName, valueName);
		commentDao.OrderBy("datetime desc");
		return commentDao.Find(pre, maxNum);
	}
	
	public int GetCommentSum(String[] property, String[] value){

		return commentDao.FindSum(property, value);
	}
	
	public void InsertComment(Comment c) {
		commentDao.Create(c);
	}
	
	public void UpdateComment(Comment c) {
		commentDao.Update(c);
	}

	public  Comic GetComic(String comicId) {
		return comicDao.FindOne(comicId);
	}
	public 	Comment GetComent(int commentId){
		return commentDao.FindOne(commentId);
	}
	
	public void InsertCommentBack(CommentSub cs) {
		commentsubDao.Create(cs);
	}
	
	public User GetUser(String accountId) {
		return userDao.FindOne(accountId);
	}
	public ComicPart GetComicPart(String comicpartId){
		return comicPartDao.FindOne(comicpartId);
	}

	public int  GetComicPartSum(String comicId) {
		
		String[] property={"comicId"};
		String[] value=new String[1];
		value[0]=comicId;
		return comicPartDao.FindSum(property, value);
		//return comicDao.FindOne(comicName).getComicParts().size();
	}
	
	public void InsertSpitSlot(SpitSlot ss) {
		spitslotDao.Create(ss);
	}
	
	public List<RankByTabModel> RankByGood() {
		return comicDao.RankByGood(0, 10);
	}
	public List<RankByTabModel> RankByLove() {
		return comicDao.RankByLove(0, 10);
	}
	public List<RankByTabModel> RankByTab(String id,int pre,int max){
		return  comicDao.RankByTab(id, pre, max);
	}
	public float GetComicScoreSum(String comicId){
		return comicScoreDao.GetSumStarLevel(comicId);
	}
	public int GetComicScorePeopleSum(String comicId){
		return comicScoreDao.GetSum(comicId);
	}
	public boolean CheckComicScore(String comicId,String userId){
		return comicScoreDao.CheckComicScore(comicId, userId);
	}
	public ComicScore GetComicScore(String[] prorty,String[] value){
		return comicScoreDao.FindList(prorty, value).get(0);
	}
	public void CreateComicScore(ComicScore cs){
		comicScoreDao.Create(cs);
	}
	public int GetUserSumUc(String comicId){
		return userComicDao.GetSumByComic(comicId);
	}
	public int GetAuthorSumByComic(String authorId){
		return comicDao.GetSumByAC(authorId);
	}
	public ComicPage GetComicPage(String pageId){
		return comicPageDao.FindOne(pageId);
	}
	public List<Comic> SearchComic(String keyword,int pre,int max){
		return comicDao.GetComicByKeyWord(keyword, pre, max);
	}
	public int GetSumBySearch(String keyword){
		return comicDao.GetSumByKeyWord(keyword);
	}
}
