package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import model.Author;
import model.AuthorUser;
import model.Comic;
import model.ComicPage;
import model.ComicPart;
import model.ComicTab;
import model.ComicTrend;
import model.Comment;
import model.CommentSub;
import model.Role;
import model.SpitSlot;
import model.Tab;
import model.User;
import model.UserComic;
import model.vo.AuthorComicModel;
import model.vo.AuthorCreateComicModel;
import model.vo.CmsModifyCommentModel;
import model.vo.CmsModifyCommentbackModel;
import model.vo.CmsModifySpitSlotModel;
import model.vo.ComicTrendModel;
import model.vo.LoveComicModel;
import model.vo.LoveComicerModel;
import model.vo.UserInfoModel;
import model.vo.UserPasswordModel;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import service.IAuthorService;
import service.IManagerService;
import service.IPersoncenterService;
import service.impl.AuthorService;
import service.impl.ManangerService;
import util.SortList;



@Controller
@RequestMapping("/cms")
public class CMSController {
	
	final private int userMax=4;
	final private int loveMax=4;
	final private int comicerMax=4;
	final private int authorMax=4;
	final private int authorComicMax=4;
	final private int comicMax=4;
	final private int authorComicPart=4;
	final private int authorComicPage=4;
	final private int commentMax=4;
	final private int commentSubMax=4;
	final private int spitslotMax=4;
	final private int usercomicMax=4;
	final private int authoruserMax=4;
	final private int comictrendMax=4;
	
	//解决客户端的date 为空的问题
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	
	@Resource(name="managerService")
	private IManagerService managerService;
	
	@Resource(name="authorService")
	private IAuthorService authorService;
	
	@Resource(name = "personcenterService")
	private IPersoncenterService personcenterService;
	
	@RequestMapping("/index")
	public String Index(Model m,HttpSession httpSession){
		httpSession.setAttribute("manager", managerService.GetUser("manager"));
		
		m.addAttribute("userSum",managerService.GetUserSum());
		m.addAttribute("authorSum",managerService.GetAuthorSum());
		m.addAttribute("comicSum",managerService.GetComicSum());
		m.addAttribute("userComicSum",managerService.GetUserComicSum());
		m.addAttribute("authorUserSum",managerService.GetAuthorSum());
		m.addAttribute("commentSum",managerService.GetCommentSum());
		
		return "CMS/Index";
	}
	
	@RequestMapping("/home")
	public String Home(Model m){
		m.addAttribute("userSum",managerService.GetUserSum());
		m.addAttribute("authorSum",managerService.GetAuthorSum());
		m.addAttribute("comicSum",managerService.GetComicSum());
		m.addAttribute("userComicSum",managerService.GetUserComicSum());
		m.addAttribute("authorUserSum",managerService.GetAuthorSum());
		m.addAttribute("commentSum",managerService.GetCommentSum());
		
		return "CMS/Home";
	}
	
	@RequestMapping("/modifyinfo")
	public String ModifyInfo(Model m,HttpSession httpSession) {
		
		User user=managerService.GetUser("manager");
		
		System.out.println(user.getNickName()+"+++++");
		
		UserInfoModel uim=new UserInfoModel();
		uim.setBorn(user.getBornDate());
		uim.setGender(user.getGender());
		uim.setMail(user.getMail());
		uim.setTel(user.getTel());
		uim.setNickName(user.getNickName());
		m.addAttribute("UserInfoModel",uim);
		
		return "CMS/ModifyInfo";
	}
	
	@RequestMapping("/modifyinfo/do")
	public String DoModifyInfo(@Valid @ModelAttribute("UserInfoModel") UserInfoModel uim,BindingResult result, Model m, HttpSession httpSession){
		
		User user = (User) httpSession.getAttribute("manager");
		if(result.hasErrors()){
			System.out.println("fuck!");
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		user.setBornDate(uim.getBorn());
		user.setGender(uim.getGender());
		user.setMail(uim.getMail());
		user.setTel(uim.getTel());
		user.setNickName(uim.getNickName());
		m.addAttribute("UserInfoModel",uim);
		managerService.SaveUserInfo(user);
		return "redirect:/cms/index";
		
	}
	
	
	@RequestMapping("/modifypassword")
	public String ModifyPassword(HttpSession httpSession,Model m) {
		
		User user = (User) httpSession.getAttribute("manager");
		UserPasswordModel upm=new UserPasswordModel();
		upm.setPassword(user.getPassword());
		m.addAttribute("UserPasswordModel",upm);
		return "CMS/ModifyPassword";
	}

	@RequestMapping("/modifypassword/do")
	public String DoModifyPassword(@Valid @ModelAttribute("UserPasswordModel") UserPasswordModel upm,BindingResult result, Model m, HttpSession httpSession){
		User user = (User) httpSession.getAttribute("manager");
		if(result.hasErrors()){
			return "redirect:/cms/home";
		}
		
		user.setPassword(upm.getPassword());
		managerService.SaveUserInfo(user);
		return "redirect:/cms/index";
	}
	
	@RequestMapping("/modifyicon")
	public String ModifyIcon() {
		return "CMS/ModifyIcon";
	}
	@RequestMapping(value="/modifyicon/do",method = RequestMethod.POST)
	public String UpdateIcon(@RequestParam("fileId") MultipartFile fileId,@RequestParam("x") int x,@RequestParam("y") int y,@RequestParam("w") int w,@RequestParam("h") int h,
			@RequestParam("selectW") int selectW,@RequestParam("selectH") int selectH,HttpSession session) {
		try{
		User user=(User)session.getAttribute("manager");
		String iconName=user.getAccountId();
		if(x>=0||y>=0||w<=0||h<=0||selectW<=0||selectH<=0)return "redirect:/personcenter/home";
		if (!fileId.isEmpty()) {
			x=0-x;
			y=0-y;
		String path=managerService.CutoutIcon(fileId, x, y, w, h,selectW,selectH,iconName);
		user.setIconPath(path);
		managerService.SaveUserInfo(user);
		}
		return "redirect:/cms/index";
		}
		catch(Exception e){
			System.out.println("error:"+e.getMessage());
			return "redirect:/cms/index";
		}
	}
	
	
	@RequestMapping("/usermanage")
	public String UserManage(Model m){
		
		int sum=managerService.GetUserSum();
		int total=sum;
		sum=sum%userMax==0?sum/userMax:sum/userMax+1;
		if(sum==0)sum=1;
		
		m.addAttribute("userList_total",total);
		m.addAttribute("userList_currentPage",1);
		m.addAttribute("userList_sum",sum);
		m.addAttribute("users",managerService.GetUserList(0, userMax));
		
		return "CMS/UserManage";
	}
	@RequestMapping("/usermanage/{currentNum}")
	public String UserManage(@PathVariable("currentNum") int currentNum,HttpSession httpSession, Model m){
		
		int sum=managerService.GetUserSum();
		int total=sum;
		sum=sum%userMax==0?sum/userMax:sum/userMax+1;
		if(sum==0)sum=1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*userMax;
		
		m.addAttribute("userList_total",total);
		m.addAttribute("userList_currentPage",currentNum);
		m.addAttribute("userList_sum",sum);
		m.addAttribute("users",managerService.GetUserList(pre, userMax));
		
		return "CMS/UserManage";
	}
	/***
	 * 添加新用户
	 * @param u
	 * @param result
	 * @param m
	 * @param httpSession
	 * @return
	 */
	
	@RequestMapping("/adduser")
	public String AddUser(@Valid @ModelAttribute("User") User u,BindingResult result, Model m, HttpSession httpSession){
		
		if(result.hasErrors()){
			System.out.println("fuck!");
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		Role r=managerService.GetRole(1);
		u.setRoleId(r);
		u.setResisterDate(new Date());
		
		managerService.CreateUser(u);
		return "redirect:/cms/index";
		
	}
	
	
	@RequestMapping("/usermanage/modifypanel/{userid}")
	public String  UserModifyPanel(@PathVariable("userid") String userid,HttpSession httpSession, Model m){
		User user=managerService.GetUser(userid);
		httpSession.setAttribute("user", user);
		m.addAttribute("user", user);
		return "CMS/UserManageSub";
	}
	
	
	
	
	@RequestMapping("/modifyuserinfo")
	public String ModifyUserInfo(Model m,HttpSession httpSession) {
		
		User user=(User)httpSession.getAttribute("user");
		m.addAttribute("user", user);
		return "CMS/ModifyUser/ModifyInfoSub";
	}
	
	@RequestMapping("/modifyuserinfo/do")
	public String ModifyUserInfo(@Valid @ModelAttribute("user") User u,BindingResult result, Model m, HttpSession httpSession){
		
		if(result.hasErrors()){
			System.out.println("fuck!");
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		User user=(User)httpSession.getAttribute("user");
		User userOld=managerService.GetUser(user.getAccountId());
		userOld.setAccountId(u.getAccountId());
		userOld.setBornDate(u.getBornDate());
		userOld.setGender(u.getGender());
		userOld.setMail(u.getMail());
		userOld.setNickName(u.getNickName());
		userOld.setPassword(u.getPassword());
		userOld.setResisterDate(u.getResisterDate());
		userOld.setTel(u.getTel());
		
		managerService.SaveUserInfo(userOld);
		return "redirect:/cms/index";
		
	}
	
	
	@RequestMapping("/modifyusericon")
	public String ModifyUserIcon() {
		return "CMS/ModifyUser/ModifyIconSub";
	}
	@RequestMapping(value="/modifyusericon/do",method = RequestMethod.POST)
	public String UpdateUserIcon(@RequestParam("fileId") MultipartFile fileId,@RequestParam("x") int x,@RequestParam("y") int y,@RequestParam("w") int w,@RequestParam("h") int h,
			@RequestParam("selectW") int selectW,@RequestParam("selectH") int selectH,HttpSession session) {
		try{
		User user=(User)session.getAttribute("user");
		String iconName=user.getAccountId();
		if(x>=0||y>=0||w<=0||h<=0||selectW<=0||selectH<=0)return "redirect:/cms/index";
		if (!fileId.isEmpty()) {
			x=0-x;
			y=0-y;
		String path=managerService.CutoutIcon(fileId, x, y, w, h,selectW,selectH,iconName);
		User userOld=managerService.GetUser(user.getAccountId());
		userOld.setIconPath(path);
		managerService.SaveUserInfo(userOld);
		}
		return "redirect:/cms/index";
		}
		catch(Exception e){
			System.out.println("error:"+e.getMessage());
			return "redirect:/cms/index";
		}
	}
	

	@RequestMapping("/lovecomic")
	public String LoveComic(HttpSession httpSession, Model m) {
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]="'"+user.getAccountId()+"'";
		List<LoveComicModel> comics = personcenterService.FindLoveComic(propertyName,valueName,0,loveMax);
		int sum=personcenterService.GetSum(propertyName, valueName);
		int total=sum;
		sum=sum%loveMax==0?sum/loveMax:sum/loveMax+1;
		if(sum==0)sum=1;
		
		m.addAttribute("loveComic_currentPage",1);
		m.addAttribute("loveComic_sum",sum);
		m.addAttribute("loveComic", comics);
		m.addAttribute("loveComic_total", total);
		return "CMS/ModifyUser/ModifyLove";
	}
	
	@RequestMapping("/lovecomic/{currentNum}")
	public String LoveComic(@PathVariable("currentNum") int currentNum,HttpSession httpSession, Model m) {
		
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]="'"+user.getAccountId()+"'";
		
		int sum=personcenterService.GetSum(propertyName, valueName);
		int total=sum;
		sum=sum%loveMax==0?sum/loveMax:sum/loveMax+1;
		if(sum==0)sum=1;
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*loveMax;
		List<LoveComicModel> comics = personcenterService.FindLoveComic(propertyName,valueName,pre,loveMax);
	
		m.addAttribute("loveComic", comics);
		m.addAttribute("loveComic_sum",sum);
		m.addAttribute("loveComic_currentPage",currentNum);
		m.addAttribute("loveComic_total", total);
		
		return "CMS/ModifyUser/ModifyLove";
	}
	
	@RequestMapping("/lovecomic/cannelcomic")
	public String  CannelComic(@RequestParam("comicId") String comicId,@RequestParam("currentNum") int  currentNum,  HttpSession httpSession,Model m){
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId","comicId"};
		String[] valueName=new String[2];
		valueName[0]="'"+user.getAccountId()+"'";
		valueName[1]="'"+comicId+"'";
		personcenterService.CannelLoveComic(propertyName,valueName);
		
		propertyName[1]="1";
		valueName[1]="1";
		int sum=personcenterService.GetSum(propertyName, valueName);
		int total=sum;
		sum=sum%loveMax==0?sum/loveMax:sum/loveMax+1;
		if(sum==0)sum=1;
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*loveMax;;
		//System.out.println("pre:"+pre+" max:"+max);
		List<LoveComicModel> comics = personcenterService.FindLoveComic(propertyName,valueName,pre,loveMax);
		m.addAttribute("loveComic_sum",sum);
		m.addAttribute("loveComic", comics);
		m.addAttribute("loveComic_currentPage",currentNum);
		m.addAttribute("loveComic_total", total);
		return "CMS/ModifyUser/ModifyLove";
	}
	
	/***
	 * 添加订阅作品
	 * @param authorId 作者id
	 * @param currentNum 当前页
	 * @param httpSession seesion
	 * @param m model对象
	 * @return
	 */
	@RequestMapping("/lovecomic/addcomic")
	public String AddLoveComic(@RequestParam("comicId") String comicId,@RequestParam("currentNum") int currentNum,HttpSession httpSession,Model m){
		
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId","comicId"};
		String[] valueName=new String[2];
		valueName[0]="'"+user.getAccountId()+"'";
		valueName[1]="'"+comicId+"'";
		managerService.CreateUserComic(user.getAccountId(),comicId);
		
		propertyName[1]="1";
		valueName[1]="1";
		int sum=personcenterService.GetSum(propertyName, valueName);
		int total=sum;
		sum=sum%loveMax==0?sum/loveMax:sum/loveMax+1;
		if(sum==0)sum=1;
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*loveMax;;
		//System.out.println("pre:"+pre+" max:"+max);
		List<LoveComicModel> comics = personcenterService.FindLoveComic(propertyName,valueName,pre,loveMax);
		m.addAttribute("loveComic_sum",sum);
		m.addAttribute("loveComic", comics);
		m.addAttribute("loveComic_currentPage",currentNum);
		m.addAttribute("loveComic_total", total);
		return "CMS/ModifyUser/ModifyLove";
		
	}
	
	
	/***
	 * 访问关注作者界面
	 * @param httpSession
	 * @param m
	 * @return
	 */
	
	@RequestMapping("/lovecomicer")
	public String LoveComicer(HttpSession httpSession, Model m) {
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]="'"+user.getAccountId()+"'";
		List<LoveComicerModel> authors= personcenterService.FindLoveComicer(propertyName,valueName,0,comicerMax);
		for (LoveComicerModel loveComicerModel : authors) {
			System.out.println("fuck12:"+loveComicerModel.getAuthorId());
		}
		int sum=personcenterService.GetSumByAuhorUser(propertyName, valueName);
		int total=sum;
		sum=sum%comicerMax==0?sum/comicerMax:sum/comicerMax+1;
		if(sum==0)sum=1;
		
		m.addAttribute("loveComicer_currentPage",1);
		m.addAttribute("loveComicer_sum",sum);
		m.addAttribute("loveComicer", authors);
		m.addAttribute("loveComicer_total", total);
		
		return "CMS/ModifyUser/ModifyComicer";
	}

	/***
	 * 删除关注的作者
	 * @param authorId
	 * @param currentNum
	 * @param httpSession
	 * @param m
	 * @return
	 */
	
	@RequestMapping("/lovecomicer/cancel")
	public String CancelAuthor(@RequestParam("authorId") String authorId,@RequestParam("currentNum") int currentNum,HttpSession httpSession,Model m){
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId","authorId"};
		String[] valueName=new String[2];
		valueName[0]="'"+user.getAccountId()+"'";
		valueName[1]="'"+authorId+"'";
		personcenterService.CannelLoveComicer(propertyName,valueName);
		
		propertyName[1]="1";
		valueName[1]="1";
		int sum=personcenterService.GetSumByAuhorUser(propertyName, valueName);
		int total=sum;
		sum=sum%comicerMax==0?sum/comicerMax:sum/comicerMax+1;
		if(sum==0)sum=1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*comicerMax;
		
		List<LoveComicerModel> authors = personcenterService.FindLoveComicer(propertyName,valueName,pre,comicerMax);
		m.addAttribute("loveComicer_sum",sum);
		m.addAttribute("loveComicer", authors);
		m.addAttribute("loveComicer_currentPage",currentNum);
		m.addAttribute("loveComicer_total", total);
		
		return "CMS/ModifyUser/ModifyComicer";
	}
	

	
	
	/***
	 * 添加关注作者在用户修改面板中
	 * @param authorId
	 * @param currentNum
	 * @param httpSession
	 * @param m
	 * @return
	 */
	
	@RequestMapping("/lovecomicer/addcomicer")
	public String AddLoveAuthor(@RequestParam("authorId") String authorId,@RequestParam("currentNum") int currentNum,HttpSession httpSession,Model m){
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId","authorId"};
		String[] valueName=new String[2];
		valueName[0]="'"+user.getAccountId()+"'";
		valueName[1]="'"+authorId+"'";
		managerService.CreateAuthorUser(user.getAccountId(),authorId);
		
		propertyName[1]="1";
		valueName[1]="1";
		int sum=personcenterService.GetSumByAuhorUser(propertyName, valueName);
		int total=sum;
		sum=sum%comicerMax==0?sum/comicerMax:sum/comicerMax+1;
		if(sum==0)sum=1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*comicerMax;
		
		List<LoveComicerModel> authors = personcenterService.FindLoveComicer(propertyName,valueName,pre,comicerMax);
		m.addAttribute("loveComicer_sum",sum);
		m.addAttribute("loveComicer", authors);
		m.addAttribute("loveComicer_currentPage",currentNum);
		m.addAttribute("loveComicer_total", total);
		
		return "CMS/ModifyUser/ModifyComicer";
	}

	
	
	

	@RequestMapping("/lovecomicer/{currentNum}")
	public String LoveComicer(@PathVariable("currentNum") int currentNum,HttpSession httpSession, Model m) {
		
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]="'"+user.getAccountId()+"'";
		
		int sum=personcenterService.GetSumByAuhorUser(propertyName, valueName);
		int total=sum;
		sum=sum%comicerMax==0?sum/comicerMax:sum/comicerMax+1;
		if(sum==0)sum=1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*comicerMax;
		List<LoveComicerModel> authors = personcenterService.FindLoveComicer(propertyName,valueName,pre,comicerMax);
	
		m.addAttribute("loveComicer", authors);
		m.addAttribute("loveComicer_sum",sum);
		m.addAttribute("loveComicer_currentPage",currentNum);
		m.addAttribute("loveComicer_total", total);
		return "CMS/ModifyUser/ModifyComicer";
	}
	
	/***
	 * 作者管理面版
	 * @param m SpringMVC model模型
	 * @return
	 */
	
	@RequestMapping("/authormanage")
	public String AuthorManage(Model m){
		
		int sum=managerService.GetAuthorSum();
		int total=sum;
		sum=sum%authorMax==0?sum/authorMax:sum/authorMax+1;
		if(sum==0)sum=1;
		
		m.addAttribute("authorList_total",total);
		m.addAttribute("authorList_currentPage",1);
		m.addAttribute("authorList_sum",sum);
		m.addAttribute("authors",managerService.GetAuthorList(0, authorMax));
		
		return "CMS/AuthorManage";
	}
	
	/***
	 * 作者管理面版
	 * @param currentNum 当前页数
	 * @param httpSession session对象
	 * @param m SpringMVC model模型
	 * @return
	 */
	@RequestMapping("/authormanage/{currentNum}")
	public String AuthorManage(@PathVariable("currentNum") int currentNum,HttpSession httpSession, Model m){
		
		int sum=managerService.GetAuthorSum();
		int total=sum;
		sum=sum%authorMax==0?sum/authorMax:sum/authorMax+1;
		if(sum==0)sum=1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*authorMax;
		
		m.addAttribute("authorList_total",total);
		m.addAttribute("authorList_currentPage",currentNum);
		m.addAttribute("authorList_sum",sum);
		m.addAttribute("authors",managerService.GetAuthorList(pre, userMax));
		
		return "CMS/AuthorManage";
	}
	/***
	 * 添加作者对象
	 * @param a 作者对象 
	 * @param result 错误对象
	 * @param m SpringMVC model对象
	 * @param httpSession session对象
	 * @return
	 */
	@RequestMapping("/addauthor")
	public String AddUser(@Valid @ModelAttribute("Author") Author a,BindingResult result, Model m, HttpSession httpSession){
		
		if(result.hasErrors()){
			System.out.println("fuck!");
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		Role r=managerService.GetRole(2);
		a.setRoleId(r);
		a.setResisterDate(new Date());
		managerService.CreateAuthor(a);
		return "redirect:/cms/index";
		
	}
	/***
	 * 作者信息修改面板
	 * @param authorId String 作者id
	 * @param httpSession session对象
	 * @param m SpringMVC model对象
	 * @return
	 */
	@RequestMapping("/authormanage/modifypanel/{authorId}")
	public String  AuthorModifyPanel(@PathVariable("authorId") String authorId,HttpSession httpSession, Model m){
		Author author=managerService.GetAuthor(authorId);
		httpSession.setAttribute("author", author);
		m.addAttribute("author", author);
		return "CMS/AuthorManageSub";
	}
	/***
	 * 作者信息修改面板
	 * @param m SpringMVC model对象
	 * @param httpSession session对象
	 * @return
	 */
	@RequestMapping("/modifyauthorinfo")
	public String ModifyAuthorInfo(Model m,HttpSession httpSession) {
		
		Author author=(Author)httpSession.getAttribute("author");
		m.addAttribute("author", author);
		return "CMS/ModifyAuthor/ModifyInfoSub";
	}
	
	/***
	 * 作者信息修改
	 * @param a author模型
	 * @param result 错误对象
	 * @param m SpringMVC model对象 
	 * @param httpSession session对象
	 * @return
	 */
	@RequestMapping("/modifyauthorinfo/do")
	public String ModifyUserInfo(@Valid @ModelAttribute("Author") Author a,BindingResult result, Model m, HttpSession httpSession){
		
		if(result.hasErrors()){
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		Author author=(Author)httpSession.getAttribute("author");
		Author authorOld=managerService.GetAuthor(author.getAuthorId());
		authorOld.setAddress(a.getAddress());
		authorOld.setBornDate(a.getBornDate());
		authorOld.setGender(a.getGender());
		authorOld.setIdcard(a.getIdcard());
		authorOld.setMail(a.getMail());
		authorOld.setPassword(a.getPassword());
		authorOld.setRealName(a.getRealName());
		authorOld.setResisterDate(new Date());
		authorOld.setTel(a.getTel());
		
		managerService.SaveAuthorInfo(authorOld);
		return "redirect:/cms/index";
		
	}
	
	/***
	 * 修改作者头像
	 * @return
	 */
	@RequestMapping("/modifyauthoricon")
	public String ModifyAuthorIcon() {
		return "CMS/ModifyAuthor/ModifyIconSub";
	}
	/***
	 * 修改作者头像
	 * @param fileId MultipartFile对象
	 * @param x 坐标x
	 * @param y 坐标y
	 * @param w 宽度
	 * @param h 高度
	 * @param selectW 选框的宽度
	 * @param selectH 选框的高度
	 * @param session session对象
	 * @return
	 */
	@RequestMapping(value="/modifyauthoricon/do",method = RequestMethod.POST)
	public String UpdateAuthorIcon(@RequestParam("fileId") MultipartFile fileId,@RequestParam("x") int x,@RequestParam("y") int y,@RequestParam("w") int w,@RequestParam("h") int h,
			@RequestParam("selectW") int selectW,@RequestParam("selectH") int selectH,HttpSession session) {
		try{
		Author author=(Author)session.getAttribute("author");
		String iconName=author.getAuthorId();
		if(x>=0||y>=0||w<=0||h<=0||selectW<=0||selectH<=0)return "redirect:/cms/index";
		if (!fileId.isEmpty()) {
			x=0-x;
			y=0-y;
		String path=managerService.CutoutIcon(fileId, x, y, w, h,selectW,selectH,iconName);
		Author authorOld=managerService.GetAuthor(author.getAuthorId());
		authorOld.setIconPath(path);
		managerService.SaveAuthorInfo(authorOld);
		}
		return "redirect:/cms/index";
		}
		catch(Exception e){
			System.out.println("error:"+e.getMessage());
			return "redirect:/cms/index";
		}
	}
	/***
	 * 修改作者作品
	 * @param httpSession sessin对象
	 * @param m SpringMVC model对象
	 * @return
	 */
	@RequestMapping("/modifyauthorcomic")
	public String ModifyAuthorComic(HttpSession httpSession, Model m) {

		Author author = (Author) httpSession.getAttribute("author");

		List<AuthorComicModel> comics = authorService.GetAuthorComic(
				author.getAuthorId(), "date", 0, authorComicMax);
		int sum = authorService.GetSumAC(author.getAuthorId());
		int total = sum;
		sum = sum % authorComicMax == 0 ? sum / authorComicMax : sum
				/ authorComicMax + 1;
		if (sum == 0)
			sum = 1;

		m.addAttribute("comicmage_currentPage", 1);
		m.addAttribute("comicmage_sum", sum);
		m.addAttribute("comics", comics);
		m.addAttribute("comic_total", total);

		return "CMS/ModifyAuthor/ModifyAuthorComic";
	}
	/***
	 * 修改作品翻页
	 * @param currentNum 当前页数 int 
	 * @param httpSession session对象
	 * @param m SpringMVC Model对象
	 * @return
	 */
	@RequestMapping("/modifyauthorcomic/{currentNum}")
	public String ModifyAuthorComic(@PathVariable("currentNum") int currentNum,
			HttpSession httpSession, Model m) {

		Author author = (Author) httpSession.getAttribute("author");

		int sum = authorService.GetSumAC(author.getAuthorId());
		int total = sum;
		sum = sum % authorComicMax == 0 ? sum / authorComicMax : sum
				/ authorComicMax + 1;
		if (sum == 0)
			sum = 1;

		currentNum = currentNum < 1 ? 1 : currentNum;
		currentNum = currentNum > sum ? sum : currentNum;
		int pre = (currentNum - 1) * authorComicMax;
		List<AuthorComicModel> comics = authorService.GetAuthorComic(
				author.getAuthorId(), "date", pre, authorComicMax);

		m.addAttribute("comicmage_currentPage", currentNum);
		m.addAttribute("comicmage_sum", sum);
		m.addAttribute("comics", comics);
		m.addAttribute("comic_total", total);

		return "CMS/ModifyAuthor/ModifyAuthorComic";
	}
	/***
	 * 创建漫画对象	
	 * @param accm AuthorCreateComicModel对象  
	 * @param result 错误对象
	 * @param httpSession session对象
	 * @return
	 */
	@RequestMapping(value="/createcomic/do", method=RequestMethod.POST)
	public String CreateAuthorComic(@Valid @ModelAttribute("AuthorCreateComicModel") AuthorCreateComicModel accm,
			BindingResult result, HttpSession httpSession){
		Author a=(Author)httpSession.getAttribute("author");
		
		Comic c=new Comic();
		String s=UUID.randomUUID().toString();
		String comicId=s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);

		
		c.setComicId(comicId);
		c.setCharge(accm.getCharge());
		c.setComicStatus(accm.getComicStatus());
		c.setAuthorId(authorService.GetAuthor(a.getAuthorId()));
		c.setComicName(accm.getComicName());
		c.setInitial(accm.getInitial());
		c.setDate(new Date());
		c.setDescription(accm.getDescription());
		System.out.println(accm.getImgUpLoad());
		if (result.hasErrors()) {
			return "redirect:/author/index";
		}
		try {
			String realPath = httpSession.getServletContext().getRealPath(
					"/WebResources/img/comicIcon");	
			
			if (!accm.getImgUpLoad().isEmpty()) {
				String originalFilename = accm.getImgUpLoad().getOriginalFilename();
				if (accm.getImgUpLoad().getSize() > 2000000)
					c.setSrc("");
				FileUtils.copyInputStreamToFile(accm.getImgUpLoad().getInputStream(),new File(realPath, originalFilename));
				
				c.setSrc("img/comicIcon/"+originalFilename);
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			c.setSrc("");
		}
		
		authorService.CreateComic(c);
		
		for(int i=0;i<accm.getTab().length;i++){
			ComicTab ct=new ComicTab();
			Tab t=authorService.GetTab(accm.getTab()[i]+"");
			ct.setTabId(t);
			ct.setComicId(c);
			authorService.CreateComicTab(ct);
		}
		
		return "redirect:/cms/index";
	}
	//此处级联删除问题未解决
	@RequestMapping("/modifyauthorcomic/cancel")
	public String CancelAuthorComic(String comicId){
		authorService.DeleteComic(comicId);
		return "";
	}
	
	/***
	 * 获取漫画列表
	 * @param m SpringMVC model对象
	 * @return
	 */
	@RequestMapping("/comicmanage")
	public String ComicManage(Model m){
		int sum=managerService.GetComicSum();
		int total=sum;
		sum=sum%comicMax==0?sum/comicMax:sum/comicMax+1;
		if(sum==0)sum=1;
		
		m.addAttribute("comicList_total",total);
		m.addAttribute("comicList_currentPage",1);
		m.addAttribute("comicList_sum",sum);
		m.addAttribute("comics",managerService.GetComicList(0, comicMax));
		
		return "CMS/ComicManage";
	}
	/***
	 * 访问漫画列表
	 * @param currentNum int类 型 当前页数
	 * @param httpSession session对象
	 * @param m spring mvc对象
	 * @return
	 */
	@RequestMapping("/comicmanage/{currentNum}")
	public String ComicManage(@PathVariable("currentNum") int currentNum,HttpSession httpSession, Model m){
		
		int sum=managerService.GetComicSum();
		int total=sum;
		sum=sum%comicMax==0?sum/comicMax:sum/comicMax+1;
		if(sum==0)sum=1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*comicMax;
		
		m.addAttribute("comicList_total",total);
		m.addAttribute("comicList_currentPage",currentNum);
		m.addAttribute("comicList_sum",sum);
		m.addAttribute("comics",managerService.GetComicList(pre, comicMax));
		
		return "CMS/ComicManage";
	}
	/***
	 * 创建漫画对象	
	 * @param accm AuthorCreateComicModel对象  
	 * @param result 错误对象
	 * @param httpSession session对象
	 * @return
	 */
	@RequestMapping(value="/comicmanage/createcomic/do", method=RequestMethod.POST)
	public String CreateComic(@Valid @ModelAttribute("AuthorCreateComicModel") AuthorCreateComicModel accm,
			BindingResult result, HttpSession httpSession){

		Comic c=new Comic();
		String s=UUID.randomUUID().toString();
		String comicId=s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);

		
		c.setComicId(comicId);
		c.setCharge(accm.getCharge());
		c.setComicStatus(accm.getComicStatus());
		c.setComicName(accm.getComicName());
		c.setInitial(accm.getInitial());
		c.setDate(new Date());
		c.setAuthorId(managerService.GetAuthor(accm.getAuthorId()));
		c.setDescription(accm.getDescription());
		System.out.println(accm.getImgUpLoad());
		if (result.hasErrors()) {
			return "redirect:/author/index";
		}
		try {
			String realPath = httpSession.getServletContext().getRealPath(
					"/WebResources/img/comicIcon");	
			
			if (!accm.getImgUpLoad().isEmpty()) {
				String originalFilename = accm.getImgUpLoad().getOriginalFilename();
				if (accm.getImgUpLoad().getSize() > 2000000)
					c.setSrc("");
				FileUtils.copyInputStreamToFile(accm.getImgUpLoad().getInputStream(),new File(realPath, originalFilename));
				
				c.setSrc("img/comicIcon/"+originalFilename);
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			c.setSrc("");
		}
		
		authorService.CreateComic(c);
		
		for(int i=0;i<accm.getTab().length;i++){
			ComicTab ct=new ComicTab();
			Tab t=authorService.GetTab(accm.getTab()[i]+"");
			ct.setTabId(t);
			ct.setComicId(c);
			authorService.CreateComicTab(ct);
		}
		
		return "redirect:/cms/index";
	}
	
	
	/***
	 * 访问漫画修改面板
	 * @param comicid string类型 comicId
	 * @param httpSession session对象
	 * @param m springmvc对象
	 * @return
	 */
	@RequestMapping("/comicmanage/modifypanel/{comicid}")
	public String  ComicModifyPanel(@PathVariable("comicid") String comicid,HttpSession httpSession, Model m){
		Comic c=authorService.GetComic(comicid);
		httpSession.setAttribute("comic", c);
		m.addAttribute("comic", c);
		return "CMS/ComicManageSub";
	}
	/***
	 * 访问漫画信息修改页面
	 * @param httpSession session对象
	 * @param m springmvc model对象
	 * @return
	 */
	@RequestMapping("/modifycomicinfo")
	public String ModifyComicInfo(HttpSession httpSession, Model m){
		Comic c=(Comic)httpSession.getAttribute("comic");
		Comic comic=authorService.GetComic(c.getComicId());
		String str = "";
		for (ComicTab ct : comic.getComicTabs())
			str += ct.getTabId().getTabId() + "_";
		m.addAttribute("comicTab", str);
		m.addAttribute("comic", comic);
		return "CMS/ModifyComic/ModifyComicInfo";
	}
	
	
	@RequestMapping("/modifycomicinfo/do")
	public String ModifyUserInfo(@Valid @ModelAttribute("Comic") Comic c,BindingResult result, Model m, HttpSession httpSession){
		
		if(result.hasErrors()){
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		Comic comic=(Comic)httpSession.getAttribute("comic");
		Comic comicOld=authorService.GetComic(comic.getComicId());
		comicOld.setComicName(c.getComicName());
		comicOld.setCharge(c.getCharge());
		comicOld.setDate(new Date());
		comicOld.setDescription(c.getDescription());
		comicOld.setInitial(c.getInitial());
		comicOld.setComicStatus(c.getComicStatus());
		authorService.SaveComicInfo(comicOld);
		
		return "redirect:/cms/index";
		
	}
	
	@ResponseBody
	@RequestMapping("/canceltab")
	public void CancelTab(@RequestParam("comicid") String comicid,
			@RequestParam("tabid") String tabid) {
		Comic comic = authorService.GetComic(comicid);
		authorService.DeleteComicTab(comic, tabid);
	}

	@ResponseBody
	@RequestMapping("/inserttab")
	public void InsertTab(@RequestParam("comicid") String comicid,
			@RequestParam("tabid") String tabid) {

		Tab t = authorService.GetTab(tabid);
		Comic c = authorService.GetComic(comicid);
		ComicTab ct = new ComicTab();

		ct.setComicId(c);
		ct.setTabId(t);
		authorService.InsertComicTab(ct);
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifycomicicon")
	public String ModifyComicIcon(
			@RequestParam("imgUpLoad") MultipartFile imgUpLoad,
			@RequestParam("comicId") String comicId, HttpSession httpSession) {
		try {
			String realPath = httpSession.getServletContext().getRealPath(
					"/WebResources/img/comicIcon");
			Comic c = authorService.GetComic(comicId);
			String oldFilePath = httpSession.getServletContext().getRealPath(
					"/WebResources/" + c.getSrc());
			File f = new File(oldFilePath);
			if (f.exists())
				f.delete();

			if (!imgUpLoad.isEmpty()) {
				String originalFilename = imgUpLoad.getOriginalFilename();
				if (imgUpLoad.getSize() > 2000000)
					return null;
				FileUtils.copyInputStreamToFile(imgUpLoad.getInputStream(),
						new File(realPath, originalFilename));
				c.setSrc("img/comicIcon/" + originalFilename);
				authorService.SaveComicInfo(c);
				return "ok";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return null;
	}
	
	@RequestMapping(value = "/modifycomicpart/{comicId}")
	public String ModifyComicPart(@PathVariable("comicId") String comicId,
			Model m) {
		Comic c = authorService.GetComic(comicId);
		Set<ComicPart> cps = c.getComicParts();
		List<ComicPart> comicParts = new ArrayList<ComicPart>(cps);
		SortList<ComicPart> sortList = new SortList<ComicPart>();
		List<ComicPart> comicPartsNew = new ArrayList<ComicPart>();
		sortList.Sort(comicParts, "getId", "");
		for (int i = 0; i < cps.size() && i < authorComicPart; i++)
			comicPartsNew.add(comicParts.get(i));

		int sum = cps.size();
		int total = sum;
		sum = sum % authorComicPart == 0 ? sum / authorComicPart : sum
				/ authorComicPart + 1;
		if (sum == 0)
			sum = 1;

		m.addAttribute("part_currentPage", 1);
		m.addAttribute("part_sum", sum);
		m.addAttribute("comicparts", comicPartsNew);
		m.addAttribute("part_total", total);
		m.addAttribute("comicId", comicId);
		return "CMS/ModifyComic/ModifyChapter";
	}

	@RequestMapping(value = "/modifycomicpart/{comicId}/{currentNum}")
	public String ModifyComicPart(@PathVariable("comicId") String comicId,
			@PathVariable("currentNum") int currentNum, Model m) {
		Comic c = authorService.GetComic(comicId);
		Set<ComicPart> cps = c.getComicParts();
		List<ComicPart> comicParts = new ArrayList<ComicPart>(cps);
		SortList<ComicPart> sortList = new SortList<ComicPart>();
		sortList.Sort(comicParts, "getId", "");
		int sum = cps.size();
		int total = sum;
		sum = sum % authorComicPart == 0 ? sum / authorComicPart : sum
				/ authorComicPart + 1;
		if (sum == 0)
			sum = 1;

		currentNum = currentNum < 1 ? 1 : currentNum;
		currentNum = currentNum > sum ? sum : currentNum;
		int pre = (currentNum - 1) * authorComicPart;

		List<ComicPart> comicPartsNew = new ArrayList<ComicPart>();
		for (int i = pre; i < cps.size() && i < authorComicPart; i++)
			comicPartsNew.add(comicParts.get(i));

		m.addAttribute("part_currentPage", currentNum);
		m.addAttribute("part_sum", sum);
		m.addAttribute("comicparts", comicPartsNew);
		m.addAttribute("part_total", total);
		m.addAttribute("comicId", comicId);
		return "CMS/ModifyComic/ModifyChapter";
	}
	/***
	 * 添加一章
	 * @param comicId
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/addcomicpart/{comicId}")
	public String AddComicPage(@PathVariable("comicId") String comicId, Model m) {
		Comic c = authorService.GetComic(comicId);
		Set<ComicPart> cps = c.getComicParts();
		m.addAttribute("comicPageNew", cps.size());
		m.addAttribute("comicId", comicId);
		return "CMS/ModifyComic/AddComicPart";
	}
	/***
	 * 添加一张的每一话
	 * @param fileImage
	 * @param comicId
	 * @param partName
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/addcomicpagelist", method = RequestMethod.POST)
	public String AddComicPage(
			@RequestParam("fileImage") MultipartFile[] fileImage,
			@RequestParam("comicId") String comicId,
			@RequestParam("partName") String partName, HttpSession httpSession) {
		try {
			Comic c = authorService.GetComic(comicId);
			String realPath = httpSession.getServletContext().getRealPath(
					"/WebResources/img/comic/" + c.getComicId());
			ComicPart cp = new ComicPart();
			String author = c.getAuthorId().getAuthorId();
			int partNum = authorService.GetPartMaxId(comicId);
			String s=UUID.randomUUID().toString();
			String partId=s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
			while(authorService.GetComicPart(partId)!=null){
				String tmp=UUID.randomUUID().toString();
				partId=tmp.substring(0,8)+tmp.substring(9,13)+tmp.substring(14,18)+tmp.substring(19,23)+tmp.substring(24);
			}
			
			cp.setPartId(partId);
			cp.setComic(c);
			//cp.setPartId(author + "_" + comicId + "_" + partNum);
			cp.setId(partNum);
			cp.setPartName(partName);
			//cp.setPartNum(fileImage.length);
			cp.setPartUpdate(new Date());

			for (int i = 0; i < fileImage.length; i++) {
				if (!fileImage[i].isEmpty()) {
					String originalFilename = fileImage[i]
							.getOriginalFilename();
					FileUtils.copyInputStreamToFile(fileImage[i]
							.getInputStream(), new File(realPath,
							originalFilename));
					ComicPage page = new ComicPage();
					page.setPartId(cp);
					
					String s1=UUID.randomUUID().toString();
					String pageId=s1.substring(0,8)+s1.substring(9,13)+s1.substring(14,18)+s1.substring(19,23)+s1.substring(24);
					while(authorService.GetComicPage(pageId)!=null){
						String tmp=UUID.randomUUID().toString();
						pageId=tmp.substring(0,8)+tmp.substring(9,13)+tmp.substring(14,18)+tmp.substring(19,23)+tmp.substring(24);
					}
					page.setPageId(pageId);
					//page.setPageId(cp.getPartId() + "_" + i);
					page.setId(i);
					page.setFilePath("img/comic/" + c.getComicId()+ "/"
							+ originalFilename);
					authorService.CreateComicPage(page);
				}
			}
			authorService.CreateComicPart(cp);
			return "redirect:/cms/index";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/cancelcomicpageall")
	public String CancelAllPage(@RequestParam("partId")String partId,@RequestParam("currentNum") int currentNum){
		
		ComicPart cp=authorService.GetComicPart(partId);
		Set<ComicPage> pages=cp.getComicPages();
		for (ComicPage comicPage : pages) {
			authorService.DeleteComicPage(comicPage.getPageId());
		}
		return "redirect:/cms/modifycomicpart/"+cp.getComic().getComicId()+"/"+currentNum;
	}
	
	/***
	 * 访问某章节中页列表
	 * @param comicPartId
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/modifycomicpage/{comicPartId}")
	public String ModifyComicPage(
			@PathVariable("comicPartId") String comicPartId, Model m) {
		ComicPart cp = authorService.GetComicPart(comicPartId);
		List<ComicPage> pages = new ArrayList<ComicPage>(cp.getComicPages());
		SortList<ComicPage> sortList = new SortList<ComicPage>();
		sortList.Sort(pages, "getId", "");
		List<ComicPage> pagesNew = new ArrayList<ComicPage>();
		for (int i = 0; i < pages.size() && i < authorComicPage; i++)
			pagesNew.add(pages.get(i));

		int sum = pages.size();
		int total = sum;
		sum = sum % authorComicPage == 0 ? sum / authorComicPage : sum
				/ authorComicPage + 1;
		if (sum == 0)
			sum = 1;
		m.addAttribute("partName", cp.getPartName());
		m.addAttribute("page_currentPage", 1);
		m.addAttribute("page_sum", sum);
		m.addAttribute("pages", pagesNew);
		m.addAttribute("page_total", total);
		m.addAttribute("comicPartId", comicPartId);
		return "CMS/ModifyComic/ModifyComicPage";
	}
	
	/***
	 * 访问某章节中页列表
	 * @param comicPartId
	 * @param currentNum
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/modifycomicpage/{comicPartId}/{currentNum}")
	public String ModifyComicPage(
			@PathVariable("comicPartId") String comicPartId,
			@PathVariable("currentNum") int currentNum, Model m) {
		ComicPart cp = authorService.GetComicPart(comicPartId);
		List<ComicPage> pages = new ArrayList<ComicPage>(cp.getComicPages());
		SortList<ComicPage> sortList = new SortList<ComicPage>();
		sortList.Sort(pages, "getId", "");
		int sum = pages.size();
		int total = sum;
		sum = sum % authorComicPage == 0 ? sum / authorComicPage : sum
				/ authorComicPage + 1;
		if (sum == 0)
			sum = 1;

		currentNum = currentNum < 1 ? 1 : currentNum;
		currentNum = currentNum > sum ? sum : currentNum;
		int pre = (currentNum - 1) * authorComicMax;
		List<ComicPage> pagesNew = new ArrayList<ComicPage>();
		int count=0;
		for (int i = pre; i < pages.size() && count < authorComicPage; i++,count++)
			pagesNew.add(pages.get(i));

		m.addAttribute("partName", cp.getPartName());
		m.addAttribute("page_currentPage", currentNum);
		m.addAttribute("page_sum", sum);
		m.addAttribute("pages", pagesNew);
		m.addAttribute("page_total", total);
		m.addAttribute("comicPartId", comicPartId);
		return "CMS/ModifyComic/ModifyComicPage";
	}

	/***
	 * 修改章节名
	 * @param partId
	 * @param partName
	 * @return
	 */
	@RequestMapping("/modifycomicpartname")
	public String ModifyComicPartName(@RequestParam("partId") String partId,@RequestParam("partName") String partName){
		ComicPart cp=authorService.GetComicPart(partId);
		cp.setPartName(partName);
		authorService.SaveComicPart(cp);
		return "redirect:/cms/modifycomicpage/"+partId;
	}
	
	/***
	 * 修改章节中某叶的图片
	 * @param fileImg
	 * @param pageId
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/modifypageimg",method=RequestMethod.POST)
	public String ModifyComicPageImg(
			@RequestParam("fileImg") MultipartFile fileImg,
			@RequestParam("pageId") String pageId, HttpSession httpSession) {

		try {
			ComicPage cp=authorService.GetComicPage(pageId);
			String realPath = httpSession.getServletContext().getRealPath(
					"/WebResources/img/comic/"+cp.getPartId().getComic().getComicId());	
			String oldFilePath = httpSession.getServletContext().getRealPath(
					"/WebResources/" + cp.getFilePath());
			
			File f = new File(oldFilePath);
			if (f.exists())
				f.delete();

			if (!fileImg.isEmpty()) {
				String originalFilename = fileImg.getOriginalFilename();
				if (fileImg.getSize() > 2000000)
					return null;
				FileUtils.copyInputStreamToFile(fileImg.getInputStream(),
						new File(realPath, originalFilename));
				cp.setFilePath("img/comic/"+cp.getPartId().getComic().getComicId()+"/"+originalFilename);
				authorService.SavePage(cp);
				return "redirect:/cms/index";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	
	/***
	 * 访问删除漫画页的界面
	 * @param pageId
	 * @param currentNum
	 * @return
	 */
	@RequestMapping("/cancelpageimg")
	public String CancelComicImg(@RequestParam("pageId") String pageId,@RequestParam("currentNum") int currentNum){
		String partId=authorService.GetComicPage(pageId).getPartId().getPartId();
		authorService.DeleteComicPage(pageId);
		return "redirect:/cms/modifycomicpage/"+partId+"/"+currentNum;
	}
	
	/***
	 * 访问章节中添加漫画页
	 * @param fileImage
	 * @param comicId
	 * @param partName
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/addpageimg")
	public String AddPageImg(
			@RequestParam("fileImage") MultipartFile[] fileImage,
			@RequestParam("partId") String partId, HttpSession httpSession) {
		try {
			ComicPart cp = authorService.GetComicPart(partId);
			String realPath = httpSession.getServletContext().getRealPath(
					"/WebResources/img/comic/" + cp.getComic().getComicId());

			int maxId=authorService.GetMaxIdPage("'"+partId+"'")+1;
			for (int i = 0; i < fileImage.length; i++) {
				if (!fileImage[i].isEmpty()) {
					String originalFilename = fileImage[i]
							.getOriginalFilename();
					FileUtils.copyInputStreamToFile(fileImage[i]
							.getInputStream(), new File(realPath,
							originalFilename));
					ComicPage page = new ComicPage();
					page.setPartId(cp);
					//int id=i+maxId;
					//page.setPageId(cp.getPartId() + "_" + id);
					String s1=UUID.randomUUID().toString();
					String pageId=s1.substring(0,8)+s1.substring(9,13)+s1.substring(14,18)+s1.substring(19,23)+s1.substring(24);
					while(authorService.GetComicPage(pageId)!=null){
						String tmp=UUID.randomUUID().toString();
						pageId=tmp.substring(0,8)+tmp.substring(9,13)+tmp.substring(14,18)+tmp.substring(19,23)+tmp.substring(24);
					}
					page.setPageId(pageId);
					page.setId(i+maxId);
					page.setFilePath("img/comic/" + cp.getComic().getComicId()+ "/"
							+ originalFilename);
					authorService.CreateComicPage(page);
				}
			}
			//System.out.println("i am ok! "+authorService.GetMaxIdPage("'"+partId+"'"));
			return "redirect:/cms/index";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * 访问评论管理界面
	 * @param m SpringMvc Model类型
	 * @return
	 */
	@RequestMapping("/commentmanage")
	public String CommentManane(Model m){
		List<Comment> comments=managerService.GetCommentList(0, commentMax);
		int sum = managerService.GetCommentSum();
		int total = sum;
		sum = sum % commentMax == 0 ? sum / commentMax : sum
				/ commentMax + 1;
		if (sum == 0)
			sum = 1;
		
		m.addAttribute("comment_currentPage", 1);
		m.addAttribute("comment_sum", sum);
		m.addAttribute("comments", comments);
		m.addAttribute("comment_total", total);
		return "CMS/CommentManage";
	}
	/***
	 * 访问评论管理界面 按页数
	 * @param currentNum int类型 当前页数
	 * @param m SpringMvc Model类型
	 * @return
	 */
	@RequestMapping("/commentmanage/{currentNum}")
	public String CommentManane(@PathVariable("currentNum") int currentNum,Model m){

		int sum = managerService.GetCommentSum();
		int total=sum;
		sum=sum%commentMax==0?sum/commentMax:sum/commentMax+1;
		if(sum==0)sum=1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*comicMax;
		
		List<Comment> comments=managerService.GetCommentList(pre, commentMax);
		
		m.addAttribute("comment_currentPage", currentNum);
		m.addAttribute("comment_sum", sum);
		m.addAttribute("comments", comments);
		m.addAttribute("comment_total", total);
		return "CMS/CommentManage";
	}
	/***
	 * 修改评论
	 * @param cmcm CmsModifyCommentModel对象 
	 * @param result 错误对象
	 * @param m springmvc对象
	 * @return cms首页
	 */
	@RequestMapping("/modifycomment/do")
	public String ModifyComment(@Valid @ModelAttribute("CmsModifyCommentModel") CmsModifyCommentModel cmcm,BindingResult result, Model m){
		
		if(result.hasErrors()){
			System.out.println("fuck!");
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		Comment c=managerService.GetComment(cmcm.getId());
		User u=personcenterService.FindUser(cmcm.getAccountId());
		Comic comic=authorService.GetComic(cmcm.getComicId());
		c.setCommentDes(cmcm.getCommentDes());
		c.setComicId(comic);
		c.setUserId(u);
		managerService.SaveCommentInfo(c);
		 return "redirect:/cms/index";
	}
	
	/***
	 * 创建评论
	 * @param cmcm CmsModifyCommentModel对象 
	 * @param result 错误对象
	 * @param m springmvc对象
	 * @return
	 */
	@RequestMapping("/createcomment/do")
	public String CreateComment(@Valid @ModelAttribute("CmsModifyCommentModel") CmsModifyCommentModel cmcm,BindingResult result, Model m){
		
		if(result.hasErrors()){
			System.out.println("fuck!");
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		Comment c=new Comment();
		User u=personcenterService.FindUser(cmcm.getAccountId());
		Comic comic=authorService.GetComic(cmcm.getComicId());
		c.setCommentDes(cmcm.getCommentDes());
		c.setComicId(comic);
		c.setUserId(u);
		managerService.CreateComment(c);
		 return "redirect:/cms/index";
	}
	/***
	 * 删除评论
	 * @param commentId int对象 评论id
	 * @param currentNum 当前评论管理所处的页数
	 * @return
	 */
	@RequestMapping("/cancelcomment")
	public String CancelComment(@RequestParam("commentId") int commentId,@RequestParam("currentNum") int currentNum){
		Comment comment=managerService.GetComment(commentId);
		managerService.DeleteComment(comment);
		return "redirect:/cms/commentmanage/"+currentNum;
	}
	/***
	 * 修改回复页面
	 * @param commentId int类型 评论id
	 * @param m springmvc对象
	 * @return
	 */
	@RequestMapping("/modifycommentback/{commentId}")
	public String ModifyCommentBack(@PathVariable("commentId") int  commentId,Model m){
		Comment comment=managerService.GetComment(commentId);
		List<CommentSub> commentSubs = new ArrayList<CommentSub>(comment.getCommentSubs());		
		int sum = commentSubs.size();
		int total = sum;
		sum = sum % commentSubMax == 0 ? sum / commentSubMax : sum
				/ commentSubMax + 1;
		if (sum == 0)
			sum = 1;
		
		List<CommentSub> commentsNew = new ArrayList<CommentSub>();
		int count=0;
		for (int i = 0; i < total && count < commentSubMax; i++,count++)
			commentsNew.add(commentSubs.get(i));

		m.addAttribute("commentback_currentPage", 1);
		m.addAttribute("commentback_sum", sum);
		m.addAttribute("commentbacks", commentsNew);
		m.addAttribute("commentback_total", total);
		m.addAttribute("commentId", commentId);
		return "CMS/ModifyComment/ModifyCommentBack";
	}
	/***
	 * 访问回复页面
	 * @param commentId int类型 评论id 
	 * @param currentNum int类型 当前页数
	 * @param m
	 * @return
	 */
	@RequestMapping("/modifycommentback/{commentId}/{currentNum}")
	public String ModifyCommentBack(@PathVariable("commentId") int  commentId,@PathVariable("currentNum") int  currentNum,Model m){
		Comment comment=managerService.GetComment(commentId);
		List<CommentSub> commentSubs = new ArrayList<CommentSub>(comment.getCommentSubs());		
		int sum = commentSubs.size();
		int total = sum;
		sum = sum % commentSubMax == 0 ? sum / commentSubMax : sum
				/ commentSubMax + 1;
		if (sum == 0)
			sum = 1;
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*comicMax;
		
		List<CommentSub> commentsNew = new ArrayList<CommentSub>();
		int count=0;
		for (int i = pre; i < total && count < commentSubMax; i++,count++)
			commentsNew.add(commentSubs.get(i));

		m.addAttribute("commentback_currentPage", currentNum);
		m.addAttribute("commentback_sum", sum);
		m.addAttribute("commentbacks", commentsNew);
		m.addAttribute("commentback_total", total);
		m.addAttribute("commentId", commentId);
		return "CMS/ModifyComment/ModifyCommentBack";
	}
	
	/***
	 * 修改回复
	 * @param cmcm CmsModifyCommentModel对象 
	 * @param result 错误对象
	 * @param m springmvc对象
	 * @return cms首页
	 */
	@RequestMapping("/modifycommentback/do")
	public String ModifyCommentBack(@Valid @ModelAttribute("CmsModifyCommentbackModel") CmsModifyCommentbackModel cmcm,BindingResult result, Model m){
		
		if(result.hasErrors()){
			System.out.println("fuck!");
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		CommentSub cs=managerService.GetCommentSub(cmcm.getSubId());
		User fromId=personcenterService.FindUser(cmcm.getFromId());
		User toId=personcenterService.FindUser(cmcm.getToId());
		Comment commentId=managerService.GetComment(cmcm.getCommentId());
		cs.setCommentDes(cmcm.getCommentDes());
		cs.setCommentId(commentId);
		cs.setFromId(fromId);
		cs.setToId(toId);
		managerService.SaveCommentSubInfo(cs);
		 return "redirect:/cms/index";
	}
	
	/***
	 * 创建评论
	 * @param cmcm CmsModifyCommentModel对象 
	 * @param result 错误对象
	 * @param m springmvc对象
	 * @return
	 */
	@RequestMapping("/createcommentback/do")
	public String CreateCommentBack(@Valid @ModelAttribute("CmsModifyCommentbackModel") CmsModifyCommentbackModel cmcm,BindingResult result, Model m){
		
		if(result.hasErrors()){
			System.out.println("fuck!");
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
				
		CommentSub cs=new CommentSub();
		User fromId=personcenterService.FindUser(cmcm.getFromId());
		User toId=personcenterService.FindUser(cmcm.getToId());
		Comment commentId=managerService.GetComment(cmcm.getCommentId());
		cs.setCommentDes(cmcm.getCommentDes());
		cs.setCommentId(commentId);
		cs.setFromId(fromId);
		cs.setToId(toId);
		managerService.CreateCommentSub(cs);
		 return "redirect:/cms/index";
	}
	/***
	 * 删除回复
	 * @param commentId int类型 评论id
	 * @param subId int类型 回复id
	 * @param currentNum 回复所在的页数
	 * @return
	 */
	@RequestMapping("/cancelcommentback")
	public String CancelCommentBack(@RequestParam("commentId") int commentId,@RequestParam("subId") int subId,@RequestParam("currentNum") int currentNum){
		CommentSub cs=managerService.GetCommentSub(subId);
		managerService.DeleteCommentSub(cs);
		return "redirect:/cms/modifycommentback/"+commentId+"/"+currentNum;
	}
	
	
	/***
	 * 访问订阅列表
	 * @param m springmvc model对象
	 * @return
	 */
	@RequestMapping("/usercomicmanage")
	public String UserComicManage(Model m){
		List<UserComic> userComics=managerService.GetUserComicList(0, usercomicMax);
		
		int sum = managerService.GetUserComicSum();
		int total = sum;
		sum = sum % usercomicMax == 0 ? sum / usercomicMax : sum
				/ usercomicMax + 1;
		if (sum == 0)
			sum = 1;
		
		m.addAttribute("usercomic_currentPage", 1);
		m.addAttribute("usercomic_sum", sum);
		m.addAttribute("usercomics", userComics);
		m.addAttribute("usercomic_total", total);
		return "CMS/LoveComicManage";
	}
	/***
	 * 访问关注列表
	 * @param currentNum int类型 当前页
	 * @param m springmvc model对象
	 * @return
	 */
	@RequestMapping("/usercomicmanage/{currentNum}")
	public String UserComicManage(@PathVariable("currentNum") int currentNum,Model m){

		int sum = managerService.GetUserComicSum();
		int total = sum;
		sum = sum % usercomicMax == 0 ? sum / usercomicMax : sum/ usercomicMax + 1;
		if (sum == 0)sum = 1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*usercomicMax;
		
		List<UserComic> userComics=managerService.GetUserComicList(pre, usercomicMax);
		
		m.addAttribute("usercomic_currentPage", currentNum);
		m.addAttribute("usercomic_sum", sum);
		m.addAttribute("usercomics", userComics);
		m.addAttribute("usercomic_total", total);
		return "CMS/LoveComicManage";
	}
	/***
	 * 修改关注对象
	 * @param cmcm CmsModifySpitSlotModel对象 
	 * @param result 错误对象
	 * @param m springmvc对象
	 * @return cms首页
	 */
	@RequestMapping("/modifyusercomic/do")
	public String ModifyUserComic(@RequestParam("id") Integer id,@RequestParam("userId") String userId,@RequestParam("comicId") String comicId, Model m){
		
		
		UserComic uc=managerService.GetUserComic(id);
		User u=personcenterService.FindUser(userId);
		Comic c=authorService.GetComic(comicId);
		uc.setComicId(c);
		uc.setUserId(u);
		managerService.SaveUserComicInfo(uc);
		return "redirect:/cms/index";
	}
	
	/***
	 * 创建订阅
	 * @param cmcm CmsModifySpitSlotModel对象 
	 * @param result 错误对象
	 * @param m springmvc对象
	 * @return
	 */
	@RequestMapping("/createusercomic/do")
	public String CreateUserComic(@RequestParam("userId") String userId,@RequestParam("comicId") String comicId, Model m){
		UserComic uc=new UserComic();
		User u=personcenterService.FindUser(userId);
		Comic c=authorService.GetComic(comicId);
		uc.setComicId(c);
		uc.setUserId(u);
		managerService.CreateUserComic(uc);
		return "redirect:/cms/index";
	}
	/***
	 * 删除订阅
	 * @param commentId int对象 评论id
	 * @param currentNum 当前评论管理所处的页数
	 * @return
	 */
	@RequestMapping("/cancelusercomic")
	public String CancelUserComic(@RequestParam("id") int id,@RequestParam("currentNum") int currentNum){
		UserComic uc=managerService.GetUserComic(id);
		managerService.DeleteUserComic(uc);
		return "redirect:/cms/usercomicmanage/"+currentNum;
	}
	
	
	
	/***
	 * 访问吐槽列表
	 * @param m springmvc model对象
	 * @return
	 */
	@RequestMapping("/spitslotmanage")
	public String SpitSlotManage(Model m){
		List<SpitSlot> spitslots=managerService.GetSpitSlotList(0, spitslotMax);
		
		int sum = managerService.GetSpitSlotSum();
		int total = sum;
		sum = sum % spitslotMax == 0 ? sum / spitslotMax : sum
				/ spitslotMax + 1;
		if (sum == 0)
			sum = 1;
		
		m.addAttribute("spitslot_currentPage", 1);
		m.addAttribute("spitslot_sum", sum);
		m.addAttribute("spitslots", spitslots);
		m.addAttribute("spitslot_total", total);
		return "CMS/SpitSlotManage";
	}
	/***
	 * 访问吐槽列表
	 * @param currentNum int类型 当前页
	 * @param m springmvc model对象
	 * @return
	 */
	@RequestMapping("/spitslotmanage/{currentNum}")
	public String SpitSlotManage(@PathVariable("currentNum") int currentNum,Model m){

		int sum = managerService.GetSpitSlotSum();
		int total=sum;
		sum=sum%spitslotMax==0?sum/spitslotMax:sum/spitslotMax+1;
		if(sum==0)sum=1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*spitslotMax;
		
		List<SpitSlot> spitslots=managerService.GetSpitSlotList(pre, spitslotMax);
		
		m.addAttribute("spitslot_currentPage", currentNum);
		m.addAttribute("spitslot_sum", sum);
		m.addAttribute("spitslots", spitslots);
		m.addAttribute("spitslot_total", total);
		return "CMS/SpitSlotManage";
	}
	/***
	 * 修改吐槽
	 * @param cmcm CmsModifySpitSlotModel对象 
	 * @param result 错误对象
	 * @param m springmvc对象
	 * @return cms首页
	 */
	@RequestMapping("/modifyspitslot/do")
	public String ModifySpitSlot(@Valid @ModelAttribute("CmsModifySpitSlotModel") CmsModifySpitSlotModel cmssm,BindingResult result, Model m){
		
		if(result.hasErrors()){
			System.out.println("fuck!");
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		SpitSlot ss=managerService.GetSpitSlot(cmssm.getSpitslotId());
		ComicPart cp=authorService.GetComicPart(cmssm.getPartId());	
		ss.setSpitSlotDes(cmssm.getSpitSlotDes());
		ss.setPartId(cp);
		managerService.SaveSpitSlotInfo(ss);
		return "redirect:/cms/index";
	}
	
	/***
	 * 创建吐槽
	 * @param cmcm CmsModifySpitSlotModel对象 
	 * @param result 错误对象
	 * @param m springmvc对象
	 * @return
	 */
	@RequestMapping("/createspitslot/do")
	public String CreateSpitSlot(@Valid @ModelAttribute("CmsModifySpitSlotModel") CmsModifySpitSlotModel cmssm,BindingResult result, Model m){
		
		if(result.hasErrors()){
			System.out.println("fuck!");
			 List<ObjectError> ls=result.getAllErrors();  
		        for (int i = 0; i < ls.size(); i++) {  
		            System.out.println("error:"+ls.get(i));  
		        }
		        return "redirect:/cms/index";
		}
		
		SpitSlot ss=new SpitSlot();
		ComicPart cp=authorService.GetComicPart(cmssm.getPartId());	
		ss.setPartId(cp);
		ss.setSpitSlotDes(cmssm.getSpitSlotDes());
		managerService.CreateSpitSlot(ss);
		return "redirect:/cms/index";
	}
	/***
	 * 删除吐槽
	 * @param commentId int对象 评论id
	 * @param currentNum 当前评论管理所处的页数
	 * @return
	 */
	@RequestMapping("/cancelspitslot")
	public String CancelSpitSlot(@RequestParam("spitslotId") int spitslotId,@RequestParam("currentNum") int currentNum){
		SpitSlot ss=managerService.GetSpitSlot(spitslotId);
		managerService.DeleteSpitSlot(ss);
		return "redirect:/cms/spitslotmanage/"+currentNum;
	}
	
	
	
	
	/***
	 * 访问关注列表
	 * @param m springmvc model对象
	 * @return
	 */
	@RequestMapping("/userauthormanage")
	public String UserAuthorManage(Model m){
		List<AuthorUser> authorUsers=managerService.GetAuthorUserList(0, authoruserMax);
		
		int sum = managerService.GetAuthorUserSum();
		int total = sum;
		sum = sum % authoruserMax == 0 ? sum / authoruserMax : sum
				/ authoruserMax + 1;
		if (sum == 0)
			sum = 1;
		
		m.addAttribute("userauthor_currentPage", 1);
		m.addAttribute("userauthor_sum", sum);
		m.addAttribute("userauthors", authorUsers);
		m.addAttribute("userauthor_total", total);
		return "CMS/AuthorUserManage";
	}
	/***
	 * 访问关注列表
	 * @param currentNum int类型 当前页
	 * @param m springmvc model对象
	 * @return
	 */
	@RequestMapping("/userauthormanage/{currentNum}")
	public String UserAuthorManage(@PathVariable("currentNum") int currentNum,Model m){

		int sum = managerService.GetAuthorUserSum();
		int total = sum;
		sum = sum % usercomicMax == 0 ? sum / authoruserMax : sum/ authoruserMax + 1;
		if (sum == 0)sum = 1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*authoruserMax;
		
		List<AuthorUser> authorUsers=managerService.GetAuthorUserList(pre, authoruserMax);
		
		m.addAttribute("userauthor_currentPage",currentNum);
		m.addAttribute("userauthor_sum", sum);
		m.addAttribute("userauthors", authorUsers);
		m.addAttribute("userauthor_total", total);
		return "CMS/AuthorUserManage";
	}
	/***
	 * 修改关注对象
	 * @param id int类型 关注id 
	  * @param userId string类型 用户id 
	 * @param authorId string类型  作者id 
	 * @return cms首页
	 */
	@RequestMapping("/modifyuserauthor/do")
	public String ModifyUserAuthor(@RequestParam("id") Integer id,@RequestParam("userId") String userId,@RequestParam("authorId") String authorId, Model m){
		
		
		AuthorUser au=managerService.GetAuthorUser(id);
		User u=personcenterService.FindUser(userId);
		Author a=authorService.GetAuthor(authorId);
		au.setAuthorId(a);
		au.setUserId(u);
		managerService.SaveUserAuthorInfo(au);;
		return "redirect:/cms/index";
	}
	
	/***
	 * 创建关注
	 * @param userId string类型 用户id 
	 * @param authorId string类型  作者id 
	 * @return
	 */
	@RequestMapping("/createuserauthor/do")
	public String CreateUserAuthor(@RequestParam("userId") String userId,@RequestParam("authorId") String authorId, Model m){
		AuthorUser au=new AuthorUser();
		User u=personcenterService.FindUser(userId);
		Author a=authorService.GetAuthor(authorId);
		au.setAuthorId(a);
		au.setUserId(u);
		managerService.CreateUserAuthor(au);
		return "redirect:/cms/index";
	}
	/***
	 * 删除关注
	 * @param id int对象 关注id
	 * @param currentNum 当前评论管理所处的页数
	 * @return
	 */
	@RequestMapping("/canceluserauthor")
	public String CancelUserAuthor(@RequestParam("id") int id,@RequestParam("currentNum") int currentNum){
		AuthorUser au=managerService.GetAuthorUser(id);
		managerService.DeleteAuthorUser(au);
		return "redirect:/cms/userauthormanage/"+currentNum;
	}
	/***
	 * 访问动态面板
	 * @param m model对象
	 * @return
	 */
	@RequestMapping("/comictrendmanage")
	public String ComictrendManage(Model m){
		List<ComicTrend> comictrends=managerService.GetComicTrendList(0, comictrendMax);
		
		int sum = managerService.GetComicTrendSum();
		int total = sum;
		sum = sum % comictrendMax == 0 ? sum / comictrendMax : sum
				/ comictrendMax + 1;
		if (sum == 0)
			sum = 1;
		
		m.addAttribute("comictrend_currentPage", 1);
		m.addAttribute("comictrend_sum", sum);
		m.addAttribute("comictrends", comictrends);
		m.addAttribute("comictrend_total", total);
		return "CMS/TrendManage";
	}
	/***
	 * 访问动态面板
	 * @param currentNum 页数
	 * @param m model对象
	 * @return
	 */
	@RequestMapping("/comictrendmanage/{currentNum}")
	public String ComictrendManage(@PathVariable("currentNum")int currentNum,Model m){
		
		int sum = managerService.GetComicTrendSum();
		int total = sum;
		sum = sum % comictrendMax == 0 ? sum / comictrendMax : sum
				/ comictrendMax + 1;
		if (sum == 0)
			sum = 1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*comictrendMax;
		
		List<ComicTrend> comictrends=managerService.GetComicTrendList(pre, comictrendMax);
		m.addAttribute("comictrend_currentPage",currentNum);
		m.addAttribute("comictrend_sum", sum);
		m.addAttribute("comictrends", comictrends);
		m.addAttribute("comictrend_total", total);
		return "CMS/TrendManage";
	}
	/***
	 * 更新动态
	 * @param ctm动态对象
	 * @param result 错误对象
	 * @param m model对象
	 * @return
	 */
	@RequestMapping("/modifycomictrend/do")
	public String ModifyComicTrend(@Valid @ModelAttribute("ComicTrendModel") ComicTrendModel ctm,BindingResult result, Model m){
		
		
		ComicTrend ct=managerService.GetComicTrend(ctm.getId());
		ct.setComicId(authorService.GetComic(ctm.getComicId()));
		ct.setDateLine(ctm.getDateLine());
		ct.setDes(ctm.getDes());
		managerService.SaveComictrend(ct);
		
		return "redirect:/cms/index";
	}
	/***
	 * 创建动态
	 * @param ctm 动态对象
	 * @param result 错误对象
	 * @param m model对象
	 * @return
	 */
	@RequestMapping("/createcomictrend/do")
	public String CreateComicTrend(@Valid @ModelAttribute("ComicTrendModel") ComicTrendModel ctm,BindingResult result, Model m){
		ComicTrend ct=new ComicTrend();
		ct.setComicId(authorService.GetComic(ctm.getComicId()));
		ct.setDateLine(ctm.getDateLine());
		ct.setDes(ctm.getDes());
		managerService.CreateComicTrend(ct);
		return "redirect:/cms/index";
	}
	/***
	 * 删除动态对象
	 * @param id 动态对象id
	 * @param currentNum 当前页数
	 * @return
	 */
	@RequestMapping("/cancelcomictrend")
	public String CancelComicTrend(@RequestParam("id") int id,@RequestParam("currentNum") int currentNum){
		ComicTrend ct=managerService.GetComicTrend(id);
		managerService.DeleteComictrend(ct);
		return "redirect:/cms/comictrendmanage/"+currentNum;
	}
}
