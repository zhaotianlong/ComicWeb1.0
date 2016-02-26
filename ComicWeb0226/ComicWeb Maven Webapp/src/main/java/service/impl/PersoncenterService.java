package service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import dao.IAuthorDao;
import dao.IAuthorUser;
import dao.IComicDao;
import dao.IComicPartDao;
import dao.IComicTrendDao;
import dao.ICommentDao;
import dao.ICommentSubDao;
import dao.IFileDao;
import dao.IUserComicDao;
import dao.IUserDao;
import model.Author;
import model.AuthorUser;
import model.Comic;
import model.ComicPart;
import model.ComicTrend;
import model.Comment;
import model.CommentSub;
import model.User;
import model.UserComic;
import model.vo.ComicTrendModel;
import model.vo.CommentModel;
import model.vo.CommentSubModdel;
import model.vo.LoveComicModel;
import model.vo.LoveComicerModel;
import service.IPersoncenterService;

@Transactional
@Service("personcenterService")
public class PersoncenterService implements IPersoncenterService {
	private final String strPic = "D:\\tomcat 8.0\\webapps\\ComicWeb\\WebResources\\img\\userIcon";
	@Resource(name = "userComicDao")
	private IUserComicDao userComicDao;

	@Resource(name = "comicPartDao")
	private IComicPartDao comicPartDao;

	@Resource(name = "authorUserDao")
	private IAuthorUser authorUserDao;

	@Resource(name = "comicDao")
	private IComicDao comicDao;

	@Resource(name = "userDao")
	private IUserDao userDao;

	@Resource(name = "fileDao")
	private IFileDao fileDao;

	@Resource(name = "commentDao")
	private ICommentDao commentDao;

	@Resource(name = "commentsubDao")
	private ICommentSubDao commentsubDao;

	@Resource(name = "authorDao")
	private IAuthorDao authorDao;

	@Resource(name = "comicTrendDao")
	private IComicTrendDao comicTrendDao;
	
	public int GetSum(String[] propertyName, String[] valueName) {
		return userComicDao.FindSum(propertyName, valueName);
	}

	public List<LoveComicModel> FindLoveComic(String[] propertyName,
			String[] valueName, int pre, int last) {
		// TODO Auto-generated method stub
		ArrayList<LoveComicModel> arr = new ArrayList<LoveComicModel>();
		List<UserComic> userComicList = userComicDao.FindByPage(propertyName,
				valueName, pre, last);
		for (UserComic uc : userComicList) {
			LoveComicModel tmp = new LoveComicModel();
			tmp.setComicId(uc.getComicId().getComicId());
			tmp.setComicName(uc.getComicId().getComicName());
			tmp.setSrc(uc.getComicId().getSrc());
			ComicPart cp = comicPartDao
					.FindTopOne(uc.getComicId().getComicId());
			if(cp!=null){
			tmp.setPartId(cp.getPartId());
			tmp.setPartNum(cp.getPartNum());
			tmp.setPartUpdate(cp.getPartUpdate());
			}
			arr.add(tmp);
		}
		return arr;
	}

	public void CannelLoveComic(String[] propertyName, String[] valueName) {

		List<UserComic> userComics = userComicDao.FindList(propertyName,
				valueName);
		UserComic uc = userComics.get(0);

		uc.getComicId().getUserComics().remove(uc);
		uc.getUserId().getUserComics().remove(uc);

		userComicDao.Delete(userComics.get(0));
	}

	public void AddLoveComic(String[] propertyName,String[] valueName){
		List<UserComic> userComics=userComicDao.FindList(propertyName, valueName);
		if(userComics==null){
			User user=userDao.FindOne(valueName[0]);
			Author author=authorDao.FindOne(valueName[1]);
			AuthorUser au=new AuthorUser();
			au.setAuthorId(author);
			au.setUserId(user);
			au.setLoveDate(new Date());
			
			authorUserDao.Create(au);
		}
		
	}

	public List<LoveComicerModel> FindLoveComicer(String[] propertyName,
			String[] valueName, int pre, int last) {
		ArrayList<LoveComicerModel> arr = new ArrayList<LoveComicerModel>();
		String[] tmpProper = { "authorId" };
		String[] tmpVal = new String[1];

		List<AuthorUser> authorUserList = authorUserDao.FindByPage(propertyName, valueName, pre, last);
		for (AuthorUser au : authorUserList) {
			LoveComicerModel tmp = new LoveComicerModel();
			tmp.setAuthorId(au.getAuthorId().getAuthorId());
			tmp.setAthuorName(au.getAuthorId().getRealName());
			tmp.setLoveDate(au.getLoveDate());
			tmpVal[0] ="'"+au.getAuthorId().getAuthorId()+"'";
			tmp.setCmicSum(comicDao.FindSum(tmpProper, tmpVal));
			tmp.setFansSum(authorUserDao.FindSum(tmpProper, tmpVal));
			arr.add(tmp);
		}
		return arr;
	}

	public int GetSumByAuhorUser(String[] propertyName, String[] valueName) {
		return authorUserDao.FindSum(propertyName, valueName);
	}

	public void CannelLoveComicer(String[] propertyName, String[] valueName) {
		List<AuthorUser> authorUsers = authorUserDao.FindList(propertyName,
				valueName);
		authorUserDao.Delete(authorUsers.get(0));
	}

	public User FindUser(String userId) {
		return userDao.FindOne(userId);
	}

	public void SaveUserInfo(User user) {
		userDao.Update(user);
	}

	public String fileUpLoad(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String CutoutIcon(MultipartFile file, int x, int y, int w, int h,
			int selectW, int selectH, String iconName) throws IOException {
		// TODO Auto-generated method stub
		String strTest = file.getOriginalFilename();
		System.out.println(strTest);
		String format = null;
		if (file.getSize() > 2000000)
			throw new IOException("图片超过2M！");
		if (strTest.endsWith(".jpg"))
			format = ".jpg";
		else if (strTest.endsWith(".png"))
			format = ".png";
		else if (strTest.endsWith(".gif"))
			format = ".gif";
		else
			throw new IOException("图片格式有误！");

		InputStream is = file.getInputStream();
		File f = new File(
				"D:\\tomcat 8.0\\webapps\\ComicWeb\\WebResources\\img\\userIcon\\"
						+ iconName + format);
		FileOutputStream fos = new FileOutputStream(f);
		fileDao.ResizeImage(is, fos, "jpg", w, h);
		FileInputStream fis = new FileInputStream(f);
		fileDao.CutoutImg(fis, f, "jpg", selectW, selectH, x, y);
		fis.close();
		return "/img/userIcon/" + iconName + format;
	}

	public List<CommentModel> FindComments(String userId, int pre, int max) {
		return commentDao.FindComment(userId, pre, max);
	}

	public List<CommentSubModdel> FindCommentSubs(String userId, int pre,
			int max) {
		return commentsubDao.FindComment(userId, pre, max);
	}

	public int GetCommentSum(String userId) {
		return commentDao.GetSum(userId);
	}

	public int GetCommentSubSum(String userId) {
		// TODO Auto-generated method stub
		return commentsubDao.GetSum(userId);
	}
	public int GetCommentSubNewSum(String userId){
		return commentsubDao.GetNewSum(userId);
	}

	public List<CommentSubModdel> FindCommentSubTos(String userId, int pre,
			int max) {
		// TODO Auto-generated method stub
		return commentsubDao.FindCommentTo(userId, pre, max);
	}

	public int GetCommentSubToSum(String userId) {
		// TODO Auto-generated method stub
		return commentsubDao.GetSumTo(userId);
	}

	public void CancelCommentBack(int id) {
		// TODO Auto-generated method stub
		CommentSub cs = commentsubDao.FindOne(id);
		cs.getCommentId().getCommentSubs().remove(cs);
		cs.setCommentId(null);
		commentsubDao.Delete(cs);

	}

	public void CancelComment(int id) {
		// TODO Auto-generated method stub
		Comment c = commentDao.FindOne(id);
		commentDao.Delete(c);
	}

	public List<ComicTrendModel> GetComicTrend(String userId,int pre,int max) {
		// TODO Auto-generated method stub
		return comicTrendDao.GetLoveComicTrend(userId, pre, max);
	}
	public int GetComicTrendSum(String userId){
		return comicTrendDao.GetSumLCT(userId);
	}
	
	public List<ComicTrendModel> GetAuthorTrend(String userId,int pre,int max) {
		// TODO Auto-generated method stub
		return comicTrendDao.GetLoveAuthorTrend(userId, pre, max);
	}
	public int GetAuthorTrendSum(String userId){
		return comicTrendDao.GetSumLAT(userId);
	}
	public void UpdateCommentSubStatus(int subid,int status){
		CommentSub cs=commentsubDao.FindOne(subid);
		cs.setNewstatus(status);
		commentsubDao.Update(cs);
	}
	
	
	

}
