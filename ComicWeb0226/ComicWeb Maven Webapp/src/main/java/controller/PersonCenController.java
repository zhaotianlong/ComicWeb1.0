package controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.swing.UIManager;
import javax.validation.Valid;

import net.sf.ehcache.search.aggregator.Sum;

import org.apache.commons.io.FileUtils;

import model.Comic;
import model.CommentSub;
import model.User;
import model.UserComic;
import model.vo.ComicTrendModel;
import model.vo.LoginModel;
import model.vo.LoveComicModel;
import model.vo.LoveComicerModel;
import model.vo.UserInfoModel;
import model.vo.UserPasswordModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import service.IComicService;
import service.IPersoncenterService;
import service.IUserService;
import auth.RoleAuth;

;

@Controller
@RequestMapping("/personcenter")
@RoleAuth(name = "user", value = { "author", "user" })
public class PersonCenController {
	final private int lcmPageNum=3;
	final private int commentMax=3;
	final private int comictrendMax=4;
	final private int authortrendMax=4;
	@Resource(name = "personcenterService")
	private IPersoncenterService personcenterService;
	@RoleAuth(name = "user", value = { "author", "user" })
	@RequestMapping("/home")
	public String Index(HttpSession httpSession,Model m) {
		
		User user=(User) httpSession.getAttribute("user");
		int sum=personcenterService.GetAuthorTrendSum("'"+user.getAccountId()+"'");
		int total=sum;
		sum=sum%authortrendMax==0?sum/authortrendMax:sum/authortrendMax+1;
		if(sum==0)sum=1;
		
		List<ComicTrendModel> comictrends= personcenterService.GetAuthorTrend("'"+user.getAccountId()+"'",0, authortrendMax);
		m.addAttribute("comictrend_sum", sum);
		m.addAttribute("comictrends",comictrends);
		m.addAttribute("comictrend_currentpage", 1);
		m.addAttribute("comictrend_total", total);
		m.addAttribute("comment_sum",personcenterService.GetCommentSum("'"+user.getAccountId()+"'"));
		m.addAttribute("commentfrom_sum", personcenterService.GetCommentSubSum("'"+user.getAccountId()+"'"));
		m.addAttribute("commentto_sum", personcenterService.GetCommentSubToSum("'"+user.getAccountId()+"'"));
		m.addAttribute("commentsubNew_sum",personcenterService.GetCommentSubNewSum("'"+user.getAccountId()+"'"));
		return "PersonCenter/PersonCenter";
	}

	@RequestMapping("/index")
	public String Home(HttpSession httpSession,Model m) {	
		User user=(User) httpSession.getAttribute("user");
		int sum=personcenterService.GetAuthorTrendSum("'"+user.getAccountId()+"'");
		int total=sum;
		sum=sum%authortrendMax==0?sum/authortrendMax:sum/authortrendMax+1;
		if(sum==0)sum=1;
		
		List<ComicTrendModel> comictrends= personcenterService.GetAuthorTrend("'"+user.getAccountId()+"'",0, authortrendMax);
		m.addAttribute("comictrend_sum", sum);
		m.addAttribute("comictrends",comictrends);
		m.addAttribute("comictrend_currentpage", 1);
		m.addAttribute("comictrend_total", total);
		m.addAttribute("comment_sum",personcenterService.GetCommentSum("'"+user.getAccountId()+"'"));
		m.addAttribute("commentfrom_sum", personcenterService.GetCommentSubSum("'"+user.getAccountId()+"'"));
		m.addAttribute("commentto_sum", personcenterService.GetCommentSubToSum("'"+user.getAccountId()+"'"));
		m.addAttribute("commentsubNew_sum",personcenterService.GetCommentSubNewSum("'"+user.getAccountId()+"'"));		
		return "PersonCenter/Index";
	}
	@RequestMapping("/comicTrend")
	public String ComicTrend(HttpSession httpSession,Model m) {
		User user=(User) httpSession.getAttribute("user");
		int sum=personcenterService.GetAuthorTrendSum("'"+user.getAccountId()+"'");
		int total=sum;
		sum=sum%authortrendMax==0?sum/authortrendMax:sum/authortrendMax+1;
		if(sum==0)sum=1;
		
		List<ComicTrendModel> comictrends= personcenterService.GetAuthorTrend("'"+user.getAccountId()+"'",0, authortrendMax);
		m.addAttribute("comictrend_sum", sum);
		m.addAttribute("comictrends",comictrends);
		m.addAttribute("comictrend_currentpage", 1);
		m.addAttribute("comictrend_total", total);
		return "PersonCenter/AuthorTrend";
	}
	@RequestMapping("/comicTrend/{page}")
	public String ComicTrend(@PathVariable("page") int page,HttpSession httpSession,Model m) {
		User user=(User) httpSession.getAttribute("user");
		int sum=personcenterService.GetAuthorTrendSum("'"+user.getAccountId()+"'");
		int total=sum;
		sum=sum%authortrendMax==0?sum/authortrendMax:sum/authortrendMax+1;
		if(sum==0)sum=1;
		
		page=page<1?1:page;
		page=page>sum?sum:page;
		int pre=(page-1)*authortrendMax;;
		
		List<ComicTrendModel> comictrends= personcenterService.GetAuthorTrend("'"+user.getAccountId()+"'",pre, authortrendMax);
		m.addAttribute("comictrend_sum", sum);
		m.addAttribute("comictrends",comictrends);
		m.addAttribute("comictrend_currentpage", page);
		m.addAttribute("comictrend_total", total);
		return "PersonCenter/AuthorTrend";
	}
	
	
	/***
	 * 回复与评论面板
	 * @param m
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/message")
	public String Message(Model m,HttpSession httpSession) {
		User user=(User) httpSession.getAttribute("user");
		
		int sum=personcenterService.GetCommentSum(user.getAccountId());
		int total=sum;
		sum=sum%commentMax==0?sum/commentMax:sum/commentMax+1;
		if(sum==0)sum=1;

		m.addAttribute("comments_sum", sum);
		m.addAttribute("comments_total", total);
		m.addAttribute("comments", personcenterService.FindComments(user.getAccountId(), 0, commentMax));
		m.addAttribute("comments_currentpage", 1);
		m.addAttribute("commentfrom_sum", personcenterService.GetCommentSubSum("'"+user.getAccountId()+"'"));
		m.addAttribute("commentto_sum", personcenterService.GetCommentSubToSum("'"+user.getAccountId()+"'"));
		m.addAttribute("commentsubNew_sum",personcenterService.GetCommentSubNewSum("'"+user.getAccountId()+"'"));
		return "PersonCenter/Message";
	}
	
	@RequestMapping("/message/comment")
	public String MessageComment(Model m,HttpSession httpSession) {
		User user=(User) httpSession.getAttribute("user");
		
		int sum=personcenterService.GetCommentSum("'"+user.getAccountId()+"'");
		sum=sum%commentMax==0?sum/commentMax:sum/commentMax+1;
		if(sum==0)sum=1;
		
		m.addAttribute("comments_sum", sum);
		m.addAttribute("comments", personcenterService.FindComments("'"+user.getAccountId()+"'", 0, commentMax));
		m.addAttribute("comments_currentpage", 1);
		
		return "PersonCenter/CommentSub";
	}
	
	@RequestMapping("/message/comment/{page}")
	public String MessageCommentSub(@PathVariable("page")int page,Model m,HttpSession httpSession) {
		User user=(User) httpSession.getAttribute("user");
		int sum=personcenterService.GetCommentSum("'"+user.getAccountId()+"'");
		sum=sum%commentMax==0?sum/commentMax:sum/commentMax+1;
		if(sum==0)sum=1;
		
		
		page=page<1?1:page;
		page=page>sum?sum:page;
		int pre=(page-1)*commentMax;;
		
		m.addAttribute("comments_sum", sum);
		m.addAttribute("comments", personcenterService.FindComments("'"+user.getAccountId()+"'",pre, commentMax));
		m.addAttribute("comments_currentpage", page);
		return "PersonCenter/CommentSub";
	}
	@RequestMapping("/message/comment/cancel/{subid}")
	public String MessageCommentCancel(@PathVariable("subid")String subid,Model m,HttpSession httpSession) {
		
		personcenterService.CancelComment(Integer.parseInt(subid));
		return "redirect:/personcenter/message/comment";
	}
	
	
	
	@RequestMapping("/message/commentbackfrom")
	public String MessageCommentBackFrom(Model m,HttpSession httpSession) {
		User user=(User) httpSession.getAttribute("user");
		
		int sum=personcenterService.GetCommentSubSum("'"+user.getAccountId()+"'");
		sum=sum%commentMax==0?sum/commentMax:sum/commentMax+1;
		if(sum==0)sum=1;
		
		m.addAttribute("comments_sum", sum);
		m.addAttribute("comments", personcenterService.FindCommentSubs("'"+user.getAccountId()+"'", 0, commentMax));
		m.addAttribute("comments_currentpage", 1);
		
		return "PersonCenter/CommentBackFrom";
	}
	
	@RequestMapping("/message/commentbackfrom/{page}")
	public String MessageCommentBackFromSub(@PathVariable("page")int page,Model m,HttpSession httpSession) {
		User user=(User) httpSession.getAttribute("user");
		int sum=personcenterService.GetCommentSubSum("'"+user.getAccountId()+"'");
		sum=sum%commentMax==0?sum/commentMax:sum/commentMax+1;
		if(sum==0)sum=1;
		
		
		page=page<1?1:page;
		page=page>sum?sum:page;
		int pre=(page-1)*commentMax;;
		
		m.addAttribute("comments_sum", sum);
		m.addAttribute("comments", personcenterService.FindCommentSubs("'"+user.getAccountId()+"'",pre, commentMax));
		m.addAttribute("comments_currentpage", page);
		return "PersonCenter/CommentBackFrom";
	}
	
	@RequestMapping("/message/commentbackfrom/cancel/{subid}")
	public String MessageCommentBackFromSub(@PathVariable("subid")String subid,Model m,HttpSession httpSession) {
		
		personcenterService.CancelCommentBack(Integer.parseInt(subid));
		return "redirect:/personcenter/message/commentbackfrom";
	}
	
	
	
	
	
	@RequestMapping("/message/commentbackto")
	public String MessageCommentBackTo(Model m,HttpSession httpSession) {
		User user=(User) httpSession.getAttribute("user");
		
		int sum=personcenterService.GetCommentSubToSum("'"+user.getAccountId()+"'");
		sum=sum%commentMax==0?sum/commentMax:sum/commentMax+1;
		if(sum==0)sum=1;
		
		m.addAttribute("comments_sum", sum);
		m.addAttribute("comments", personcenterService.FindCommentSubTos("'"+user.getAccountId()+"'", 0, commentMax));
		m.addAttribute("comments_currentpage", 1);
		
		return "PersonCenter/CommentBackTo";
	}
	
	@RequestMapping("/message/commentbackto/{page}")
	public String MessageCommentBackToSub(@PathVariable("page")int page,Model m,HttpSession httpSession) {
		User user=(User) httpSession.getAttribute("user");
		int sum=personcenterService.GetCommentSubToSum("'"+user.getAccountId()+"'");
		sum=sum%commentMax==0?sum/commentMax:sum/commentMax+1;
		if(sum==0)sum=1;
		
		
		page=page<1?1:page;
		page=page>sum?sum:page;
		int pre=(page-1)*commentMax;;
		
		m.addAttribute("comments_sum", sum);
		m.addAttribute("comments", personcenterService.FindCommentSubTos("'"+user.getAccountId()+"'",pre, commentMax));
		m.addAttribute("comments_currentpage", page);
		return "PersonCenter/CommentBackTo";
	}
	/***
	 * 更新我接收到的回复的状态
	 * @param id 回复id
	 * @param httpSession session对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/message/commentbackto/updatestatus/{id}")
	public String  UpdateStatus(@PathVariable("id")int id,HttpSession httpSession){
	personcenterService.UpdateCommentSubStatus(id,1);	
		return "ok";
	}
	
	
	
	
	
	
	@RequestMapping("/lovecomic")
	public String LoveComic(HttpSession httpSession, Model m) {
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]=user.getAccountId();
		List<LoveComicModel> comics = personcenterService.FindLoveComic(propertyName,valueName,0,lcmPageNum);
		int sum=personcenterService.GetSum(propertyName, valueName);
		sum=sum%lcmPageNum==0?sum/lcmPageNum:sum/lcmPageNum+1;
		if(sum==0)sum=1;
		
		m.addAttribute("loveComic_currentPage",1);
		m.addAttribute("loveComic_sum",sum);
		m.addAttribute("loveComic", comics);
		return "PersonCenter/LoveComic";
	}
	
	@RequestMapping("/lovecomic/{currentNum}")
	public String LoveComic(@PathVariable("currentNum") int currentNum,HttpSession httpSession, Model m) {
		
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]=user.getAccountId();
		
		int sum=personcenterService.GetSum(propertyName, valueName);
		sum=sum%lcmPageNum==0?sum/lcmPageNum:sum/lcmPageNum+1;
		if(sum==0)sum=1;
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*lcmPageNum;
		//System.out.println("pre:"+pre+" max:"+max);
		List<LoveComicModel> comics = personcenterService.FindLoveComic(propertyName,valueName,pre,lcmPageNum);
	
		m.addAttribute("loveComic", comics);
		m.addAttribute("loveComic_sum",sum);
		m.addAttribute("loveComic_currentPage",currentNum);
		
		return "PersonCenter/LoveComic";
	}
	
	@RequestMapping("/lovecomic/cannelcomic")
	public String  CannelComic(@RequestParam("comicId") String comicId,@RequestParam("currentNum") int  currentNum,  HttpSession httpSession,Model m){
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId","comicId"};
		String[] valueName=new String[2];
		valueName[0]=user.getAccountId();
		valueName[1]=comicId;
		personcenterService.CannelLoveComic(propertyName,valueName);
		
		propertyName[1]="1";
		valueName[1]="1";
		int sum=personcenterService.GetSum(propertyName, valueName);
		sum=sum%lcmPageNum==0?sum/lcmPageNum:sum/lcmPageNum+1;
		if(sum==0)sum=1;
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*lcmPageNum;;
		//System.out.println("pre:"+pre+" max:"+max);
		List<LoveComicModel> comics = personcenterService.FindLoveComic(propertyName,valueName,pre,lcmPageNum);
		m.addAttribute("loveComic_sum",sum);
		m.addAttribute("loveComic", comics);
		m.addAttribute("loveComic_currentPage",currentNum);
		return "PersonCenter/LoveComic"; 
	}
	
	@RequestMapping("/lovecomicer")
	public String LoveComicer(HttpSession httpSession, Model m) {
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]=user.getAccountId();
		List<LoveComicerModel> authors= personcenterService.FindLoveComicer(propertyName,valueName,0,lcmPageNum);
		
		int sum=personcenterService.GetSumByAuhorUser(propertyName, valueName);
		sum=sum%lcmPageNum==0?sum/lcmPageNum:sum/lcmPageNum+1;
		if(sum==0)sum=1;
		
		m.addAttribute("loveComicer_currentPage",1);
		m.addAttribute("loveComicer_sum",sum);
		m.addAttribute("loveComicer", authors);
		
		return "PersonCenter/LoveComicer";
	}

	@RequestMapping("/lovecomicer/cancel")
	public String CancelAuthor(@RequestParam("authorId") String authorId,@RequestParam("currentNum") int currentNum,HttpSession httpSession,Model m){
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId","authorId"};
		String[] valueName=new String[2];
		valueName[0]=user.getAccountId();
		valueName[1]=authorId;
		personcenterService.CannelLoveComicer(propertyName,valueName);
		
		propertyName[1]="1";
		valueName[1]="1";
		int sum=personcenterService.GetSumByAuhorUser(propertyName, valueName);
		sum=sum%lcmPageNum==0?sum/lcmPageNum:sum/lcmPageNum+1;
		if(sum==0)sum=1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*lcmPageNum;
		
		List<LoveComicerModel> authors = personcenterService.FindLoveComicer(propertyName,valueName,pre,lcmPageNum);
		m.addAttribute("loveComicer_sum",sum);
		m.addAttribute("loveComicer", authors);
		m.addAttribute("loveComicer_currentPage",currentNum);
		
		return"PersonCenter/LoveComicer";
	}

	@RequestMapping("/lovecomicer/{currentNum}")
	public String LoveComicer(@PathVariable("currentNum") int currentNum,HttpSession httpSession, Model m) {
		
		User user = (User) httpSession.getAttribute("user");
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]=user.getAccountId();
		
		int sum=personcenterService.GetSumByAuhorUser(propertyName, valueName);
		sum=sum%lcmPageNum==0?sum/lcmPageNum:sum/lcmPageNum+1;
		if(sum==0)sum=1;
		
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*lcmPageNum;
		List<LoveComicerModel> authors = personcenterService.FindLoveComicer(propertyName,valueName,pre,lcmPageNum);
	
		m.addAttribute("loveComicer", authors);
		m.addAttribute("loveComicer_sum",sum);
		m.addAttribute("loveComicer_currentPage",currentNum);
		
		return "PersonCenter/LoveComicer";
	}
	
	
	@RequestMapping("/modifypassword")
	public String ModifyPassword(HttpSession httpSession,Model m) {
		
		User user = (User) httpSession.getAttribute("user");
		UserPasswordModel upm=new UserPasswordModel();
		upm.setPassword(user.getPassword());
		m.addAttribute("UserPasswordModel",upm);
		return "PersonCenter/ModifyPassword";
	}

	@RequestMapping("/modifypassword/do")
	public String DoModifyPassword(@Valid @ModelAttribute("UserPasswordModel") UserPasswordModel upm,BindingResult result, Model m, HttpSession httpSession){
		User user = (User) httpSession.getAttribute("user");
		if(result.hasErrors()){
			UserPasswordModel tmp=new UserPasswordModel();
			tmp.setPassword(user.getPassword());
			m.addAttribute("UserPasswordModel",tmp);
			return "redirect:/personcenter/home";
		}
		//System.out.println(upm.getPassword());
		user.setPassword(upm.getPassword());
		personcenterService.SaveUserInfo(user);
		return "redirect:/personcenter/home";
	}
	
	
	@RequestMapping("/modifyinfo")
	public String ModifyInfo(Model m,HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		UserInfoModel uim=new UserInfoModel();
		uim.setBorn(user.getBornDate());
		uim.setGender(user.getGender());
		uim.setMail(user.getMail());
		uim.setTel(user.getTel());
		uim.setNickName(user.getNickName());
		m.addAttribute("UserInfoModel",uim);
		return "PersonCenter/ModifyInfo";
	}
	
	 
	@RequestMapping("/modifyinfo/do")
	public String DoModifyInfo(@Valid @ModelAttribute("UserInfoModel") UserInfoModel uim,BindingResult result, Model m, HttpSession httpSession){
		
		User user = (User) httpSession.getAttribute("user");
		user.setBornDate(uim.getBorn());
		user.setGender(uim.getGender());
		user.setMail(uim.getMail());
		user.setTel(uim.getTel());
		user.setNickName(uim.getNickName());
		m.addAttribute("UserInfoModel",uim);
		personcenterService.SaveUserInfo(user);
		return "redirect:/personcenter/home";
		
	}
	
	@RequestMapping("/modifyicon")
	public String ModifyIcon() {
		return "PersonCenter/ModifyIcon";
	}
	@RequestMapping(value="/modifyicon/do",method = RequestMethod.POST)
	public String UpdateIcon(@RequestParam("fileId") MultipartFile fileId,@RequestParam("x") int x,@RequestParam("y") int y,@RequestParam("w") int w,@RequestParam("h") int h,
			@RequestParam("selectW") int selectW,@RequestParam("selectH") int selectH,HttpSession session) {
		try{
		User user=(User)session.getAttribute("user");
		String iconName=user.getAccountId();
		if(x>=0||y>=0||w<=0||h<=0||selectW<=0||selectH<=0)return "redirect:/personcenter/home";
		if (!fileId.isEmpty()) {
			x=0-x;
			y=0-y;
		String path=personcenterService.CutoutIcon(fileId, x, y, w, h,selectW,selectH,iconName);
		user.setIconPath(path);
		personcenterService.SaveUserInfo(user);
		}
		return "redirect:/personcenter/home";
		}
		catch(Exception e){
			System.out.println("error:"+e.getMessage());
			return "redirect:/personcenter/home";
		}
	}

	
}
