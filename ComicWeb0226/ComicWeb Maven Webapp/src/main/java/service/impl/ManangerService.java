package service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import model.Author;
import model.AuthorUser;
import model.Comic;
import model.ComicTrend;
import model.Comment;
import model.CommentSub;
import model.Role;
import model.SpitSlot;
import model.User;
import model.UserComic;
import model.vo.LoveComicModel;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.IAuthorDao;
import dao.IAuthorUser;
import dao.IComicDao;
import dao.IComicPageDao;
import dao.IComicPartDao;
import dao.IComicTabDao;
import dao.IComicTrendDao;
import dao.ICommentDao;
import dao.ICommentSubDao;
import dao.IFileDao;
import dao.IRoleDao;
import dao.ISpitSlotDao;
import dao.ITabDao;
import dao.IUserComicDao;
import dao.IUserDao;
import service.IManagerService;


@Transactional
@Service("managerService")
public class ManangerService implements IManagerService {
	
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
	
	@Resource(name="authorDao")
	private IAuthorDao authorDao;
	
	@Resource(name="authorUserDao")
	private IAuthorUser authorUserDao;
	
	@Resource(name="fileDao")
	private IFileDao fileDao;
	
	@Resource(name="tabDao")
	private ITabDao tabDao;
	
	@Resource(name="comicPartDao")
	private IComicPartDao comicPartDao;
	
	@Resource(name="comicPageDao")
	private IComicPageDao comicPageDao;
	
	@Resource(name="userComicDao")
	private IUserComicDao userComicDao;
	
	@Resource(name="roleDao")
	private IRoleDao roleDao;
	
	@Resource(name = "comicTrendDao")
	private IComicTrendDao comicTrendDao;
	
	public int GetUserSum(){
		return userDao.FindAll().size();
	}
	public int GetAuthorSum(){
		return authorDao.FindAll().size();
	}
	public int GetComicSum(){
		return comicDao.FindAll().size();
	}
	public int GetCommentSum(){
		return commentDao.FindAll().size();
	}
	public int GetAuthorUserSum(){
		return authorUserDao.FindAll().size();
	}
	public int GetUserComicSum(){
		return userComicDao.FindAll().size();
	}
	public int GetComicTrendSum(){
		return comicTrendDao.GetSum();
	}
	
	public void SaveUserInfo(User u){	
		
		System.out.println("in dao:"+u.getNickName());
		userDao.Update(u);
	}
	
	public User GetUser(String userId){
		return userDao.FindOne(userId);
	}
	
	public String CutoutIcon(MultipartFile file,int x, int y, int w, int h,int selectW,int selectH,String iconName) throws IOException {
		// TODO Auto-generated method stub
		String strTest=file.getOriginalFilename();
		System.out.println(strTest);
		String format=null;
		if(file.getSize()>2000000)throw new IOException("图片超过2M！");
		if(strTest.endsWith(".jpg"))
				format=".jpg";
		else if(strTest.endsWith(".png")) 
			format=".png";
		else if(strTest.endsWith(".gif"))
			format=".gif";
		else
			throw new IOException("图片格式有误！");
		
		InputStream is=file.getInputStream();
		File f=new File("D:\\tomcat 8.0\\webapps\\ComicWeb\\WebResources\\img\\userIcon\\"+iconName+format);
		FileOutputStream fos=new FileOutputStream(f);
		fileDao.ResizeImage(is,fos,"jpg", w, h);
		FileInputStream fis=new FileInputStream(f);
		fileDao.CutoutImg(fis, f, "jpg", selectW, selectH, x, y);
		fis.close();
		return "/img/userIcon/"+iconName+format;
		}
	
		public List<User> GetUserList(int pre,int max){
			return userDao.Find(pre, max);
		}
		
		public Role GetRole(Integer roleId){
			return roleDao.FindOne(roleId);
		}
		
		public void CreateUser(User u){
			userDao.Create(u);
		}
		//代码有问题！！！
		public AuthorUser GetAuthorUser(String[] propertyName,String[] valueName){
			List<AuthorUser> authorUsers = authorUserDao.FindList(propertyName,valueName);
			if(authorUsers.size()==0)return null;
			
			AuthorUser ac = authorUsers.get(0);
			return ac;
		}

		public void CreateAuthorUser(String userId,String authorId){
			String[] propertyName={"userId","authorId"};
			String[] valueName=new String[2];
			valueName[0]="'"+userId+"'";
			valueName[1]="'"+authorId+"'";
			if(GetAuthorUser(propertyName, valueName)==null){
				AuthorUser ac=new AuthorUser();
				ac.setUserId(userDao.FindOne(userId));
				ac.setAuthorId(authorDao.FindOne(authorId));
				ac.setLoveDate(new Date());
				authorUserDao.Create(ac);
			}
		}
		//代码有问题！！！
		public UserComic GetUserComic(String[] propertyName,String[] valueName){
			List<UserComic> userComics = userComicDao.FindList(propertyName,valueName);
			if(userComics.size()==0)return null;
			UserComic uc = userComics.get(0);
			return uc;
		}
		
		public void CreateUserComic(String userId,String comicId){

			String[] propertyName={"userId","comicId"};
			String[] valueName=new String[2];
			valueName[0]="'"+userId+"'";
			valueName[1]="'"+comicId+"'";
			if(GetUserComic(propertyName, valueName)==null){
				UserComic uc=new UserComic();
				uc.setUserId(userDao.FindOne(userId));
				uc.setComicId(comicDao.FindOne(comicId));
				userComicDao.Create(uc);
			}
		}
		public List<Author> GetAuthorList(int pre,int max){
			return authorDao.Find(pre, max);
		}
		public void CreateAuthor(Author a){
			authorDao.Create(a);
		}
		public Author GetAuthor(String authorId){
			return authorDao.FindOne(authorId);
		}
		public void SaveAuthorInfo(Author a){
			authorDao.Update(a);
		}
		public List<Comic> GetComicList(int pre,int max){
			return comicDao.Find(pre, max);
		}
		public List<Comment> GetCommentList(int pre,int max){
			return commentDao.Find(pre, max);
		}
		public Comment GetComment(Integer commentId){
			return commentDao.FindOne(commentId);
		}
		public void SaveCommentInfo(Comment c){
			commentDao.Update(c);
		}
		public void CreateComment(Comment c){
			commentDao.Create(c);
		}
		public void DeleteComment(Comment c){
			//c.getUserId().getComments().remove(c);
			//c.getComicId().getComments().remove(c);
			commentDao.Delete(c);
		}
		
		public CommentSub GetCommentSub(Integer commentSubId){
			return commentsubDao.FindOne(commentSubId);
		}
		public void SaveCommentSubInfo(CommentSub cs){
			commentsubDao.Update(cs);
		}
		public void CreateCommentSub(CommentSub cs){
			commentsubDao.Create(cs);
		}
		public void DeleteCommentSub(CommentSub cs){
			//c.getUserId().getComments().remove(c);
			//c.getComicId().getComments().remove(c);
			commentsubDao.Delete(cs);
		}
		public List<SpitSlot> GetSpitSlotList(int pre,int max){
			return spitslotDao.Find(pre, max);
		} 
		public int GetSpitSlotSum(){
			return spitslotDao.FindAll().size();
		}
		public SpitSlot GetSpitSlot(Integer spitslotId) {
			// TODO Auto-generated method stub
			return spitslotDao.FindOne(spitslotId);
		}
		public void DeleteSpitSlot(SpitSlot ss) {
			// TODO Auto-generated method stub
			  spitslotDao.Delete(ss);
		}
		public void SaveSpitSlotInfo(SpitSlot ss) {
			// TODO Auto-generated method stub
			spitslotDao.Update(ss);
		}
		public void CreateSpitSlot(SpitSlot ss) {
			// TODO Auto-generated method stub
			ss.getPartId().getSpitSlots().remove(ss);
			spitslotDao.Create(ss);
		}
		public UserComic GetUserComic(String comicId, String userId) {
			// TODO Auto-generated method stub
			return userComicDao.GetUserComic(comicId, userId);
		}
		public void DeleteUserComic(UserComic uc) {
			// TODO Auto-generated method stub
			uc.getComicId().getUserComics().remove(uc);
			uc.getUserId().getUserComics().remove(uc);
			userComicDao.Delete(uc);
		}

		public void SaveUserComicInfo(UserComic uc) {
			// TODO Auto-generated method stub
			userComicDao.Update(uc);
		}
		public List<UserComic> GetUserComicList(int pre,int maxNum) {
			// TODO Auto-generated method stub
			return userComicDao.Find(pre, maxNum);
		}
		public UserComic GetUserComic(Integer id) {
			// TODO Auto-generated method stub
			return userComicDao.FindOne(id);
		}
		public void CreateUserComic(UserComic uc){
			userComicDao.Create(uc);
		}
		public void CreateUserAuthor(AuthorUser au) {
			// TODO Auto-generated method stub
			authorUserDao.Create(au);
		}
		public void SaveUserAuthorInfo(AuthorUser au) {
			// TODO Auto-generated method stub
			authorUserDao.Update(au);
		}
		public AuthorUser GetAuthorUser(Integer id) {
			// TODO Auto-generated method stub
			return authorUserDao.FindOne(id);
		}
		public void DeleteAuthorUser(AuthorUser au) {
			// TODO Auto-generated method stub
			authorUserDao.Delete(au);
		}
		public List<AuthorUser> GetAuthorUserList(int pre,int max){
			return authorUserDao.Find(pre, max);
		}
		public List<ComicTrend> GetComicTrendList(int pre,int max){
			return comicTrendDao.Find(pre, max);
		}
		public void SaveComictrend(ComicTrend ct){
			comicTrendDao.Update(ct);
		}
		public void DeleteComictrend(ComicTrend ct){
			comicTrendDao.Delete(ct);
		}
		public void CreateComicTrend(ComicTrend ct){
			comicTrendDao.Create(ct);
		}
		public ComicTrend GetComicTrend(Integer id){
			return comicTrendDao.FindOne(id);
		}
} 
