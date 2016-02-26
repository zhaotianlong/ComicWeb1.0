package service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import model.Author;
import model.Comic;
import model.ComicPage;
import model.ComicPart;
import model.ComicTab;
import model.ComicTrend;
import model.Tab;
import model.vo.AuthorComicModel;
import model.vo.AuthorInfoModel;
import model.vo.ComicTrendModel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import dao.IAuthorDao;
import dao.IAuthorUser;
import dao.IComicDao;
import dao.IComicPageDao;
import dao.IComicPartDao;
import dao.IComicTabDao;
import dao.IComicTrendDao;
import dao.IFileDao;
import dao.ITabDao;
import service.IAuthorService;
import util.SortList;

@Transactional
@Service("authorService")
public class AuthorService implements IAuthorService {
	
	@Resource(name="authorDao")
	private IAuthorDao authorDao;
	
	@Resource(name="authorUserDao")
	private IAuthorUser authorUserDao;
	
	@Resource(name="fileDao")
	private IFileDao fileDao;
	
	@Resource(name="comicDao")
	private IComicDao comicDao;
	
	@Resource(name="comicTabDao")
	private IComicTabDao comicTabDao;
	
	@Resource(name="tabDao")
	private ITabDao tabDao;
	
	@Resource(name="comicPartDao")
	private IComicPartDao comicPartDao;
	
	@Resource(name="comicPageDao")
	private IComicPageDao comicPageDao;
	
	@Resource(name = "comicTrendDao")
	private IComicTrendDao comicTrendDao;
	
	public void SaveAuthorInfo(Author a){
		authorDao.Update(a);
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

	public List<AuthorComicModel> GetAuthorComic(String authorId, String orderby, int pre,
			int max) {
		// TODO Auto-generated method stub
		return comicDao.GetAuthorComic(authorId, orderby, pre, max);
	}
	public int GetSumAC(String authorId) {
		return comicDao.GetSumByAC(authorId);
	}
	public Comic GetComic(String comicId) {
		return comicDao.FindOne(comicId);
	}
	
	public Tab GetTab(String tabdid) {
		return tabDao.FindOne(Integer.parseInt(tabdid));
	}
	public void InsertComicTab(ComicTab ct){
		
		Comic c=ct.getComicId();
		for(ComicTab tmp:c.getComicTabs())
		{
			if(tmp.getComicId().getComicId().equals(ct.getComicId().getComicId())&&tmp.getTabId().getTabId()==ct.getTabId().getTabId())
				return ;
		}
		
		comicTabDao.Create(ct);
	}
	public void DeleteComicTab(Comic c,String tabid){
		ComicTab tmp=null;
		for(ComicTab ct:c.getComicTabs())
		{
			if(tabid.equals(""+ct.getTabId().getTabId()))
				tmp=ct;
		}
		if(tmp!=null)
		{
			tmp.getComicId().getComicTabs().remove(tmp);
			tmp.setComicId(null);
			comicTabDao.Delete(tmp);
		}
		
	}
	
	
	public void SaveComicInfo(Comic c){
		comicDao.Update(c);
	}
	
	public void CreateComicPage(ComicPage cp){
		comicPageDao.Create(cp);
	}
	public void CreateComicPart(ComicPart cp){
		comicPartDao.Create(cp);
	}
	public ComicPart GetComicPart(String partId){
		return comicPartDao.FindOne(partId);
	}
	public ComicPage GetComicPage(String pageId){
		return comicPageDao.FindOne(pageId);
	}
	public void SavePage(ComicPage cp){
		comicPageDao.Update(cp);
	}
	public Author GetAuthor(String authorId){
		return authorDao.FindOne(authorId);
	}
	public void CreateComic(Comic c){
		comicDao.Create(c);
	}
	public void CreateComicTab(ComicTab ct){
		comicTabDao.Create(ct);
	}
	public void SaveComicPart(ComicPart cp){
		comicPartDao.Update(cp);
	}
	public int GetSumAU(String authorId){
		return authorUserDao.GetSumByAuthorId(authorId);
	}
	public void DeleteComic(String comicId){
		Comic c=comicDao.FindOne(comicId);
		if(c!=null){
			//System.out.println("fun");
			c.getAuthorId().getComicCollection().remove(c);
			//c.setAuthorId(null);
			comicDao.Delete(c);
		}
		
	}

	public void DeleteComicPage(String pageId) {
		// TODO Auto-generated method stub
		ComicPage cp=comicPageDao.FindOne(pageId);
		cp.getPartId().getComicPages().remove(cp);
		comicPageDao.Delete(cp);
	}
	
	public int GetMaxIdPage(String partId){
		return comicPageDao.GetmaxpageId(partId);
	}
	
	public List<ComicTrendModel> GetComicTrend(String authorId,int pre,int max){
		return comicTrendDao.GetAuthorTrend(authorId, pre, max);
	}
	
	public int GetComicTrendSum(String authorId){
		return comicTrendDao.GetSumAT(authorId);
	}
	public void CreateComicTrend(ComicTrend ct){
		comicTrendDao.Create(ct);
	}
	public int GetPartMaxId(String comicId){
		ComicPart cp=comicPartDao.FindTopOne(comicId);
		if(cp!=null)
			return cp.getId();
		else 
			return 0;
	}
	
}
