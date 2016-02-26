package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import model.Author;
import model.User;
import model.vo.AuthorComicModel;
import model.vo.LoveComicModel;
import model.vo.LoveComicerModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import service.IAuthorService;
import service.IPersoncenterService;

@Controller
@RequestMapping(value="/info")
public class InfoController {
	final private int lcmPageNum=3;
	final private int commentMax=3;
	private final int authorComicMax = 3;
	@Resource(name = "personcenterService")
	private IPersoncenterService personcenterService;
	
	@Resource(name = "authorService")
	private IAuthorService authorService;
	/***
	 * 用户信息面板首页
	 * @param userid 用户id
	 * @param m
	 * @return
	 */
	@RequestMapping("/userinfo/{userid}")
	public String UserInfoIndex(@PathVariable("userid") String userid,Model m){
		User user=personcenterService.FindUser(userid);
		m.addAttribute("tagUser", user);
		m.addAttribute("userid",userid);
		return "Info/UserInfo";
	}
	/***
	 * 用户信息面板
	 * @param userid
	 * @param m
	 * @return
	 */
	@RequestMapping("/userinfo/info/{userid}")
	public String UserInfo(@PathVariable("userid") String userid,Model m){
		User user=personcenterService.FindUser(userid);
		m.addAttribute("tagUser", user);
		m.addAttribute("userid",userid);
		return "Info/UserInfoSub/UserInfo";
	}
	
	/***
	 * 用户订阅漫画面板
	 * @param userid
	 * @param m
	 * @return
	 */
	@RequestMapping("/userinfo/lovecomic/{userid}")
	public String UserLoveComic(@PathVariable("userid") String userid,Model m){
		User user=personcenterService.FindUser(userid);
		
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]=user.getAccountId();
		List<LoveComicModel> comics = personcenterService.FindLoveComic(propertyName,valueName,0,lcmPageNum);
		int sum=personcenterService.GetSum(propertyName, valueName);
		sum=sum%lcmPageNum==0?sum/lcmPageNum:sum/lcmPageNum+1;
		if(sum==0)sum=1;
		
		m.addAttribute("userid",userid);
		m.addAttribute("loveComic_currentPage",1);
		m.addAttribute("loveComic_sum",sum);
		m.addAttribute("loveComic", comics);
		return "Info/UserInfoSub/LoveComic";
	}
	/***
	 * 用户订阅漫画某页
	 * @param userid
	 * @param currentNum
	 * @param httpSession
	 * @param m
	 * @return
	 */
	@RequestMapping("/userinfo/lovecomic/{userid}/{page}")
	public String UserLoveComic(@PathVariable("userid") String userid,@PathVariable("currentNum") int currentNum,HttpSession httpSession, Model m){
		User user=personcenterService.FindUser(userid);
		
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]=user.getAccountId();
		
		int sum=personcenterService.GetSum(propertyName, valueName);
		sum=sum%lcmPageNum==0?sum/lcmPageNum:sum/lcmPageNum+1;
		if(sum==0)sum=1;
		currentNum=currentNum<1?1:currentNum;
		currentNum=currentNum>sum?sum:currentNum;
		int pre=(currentNum-1)*lcmPageNum;
		List<LoveComicModel> comics = personcenterService.FindLoveComic(propertyName,valueName,pre,lcmPageNum);
		
		m.addAttribute("userid",userid);
		m.addAttribute("loveComic", comics);
		m.addAttribute("loveComic_sum",sum);
		m.addAttribute("loveComic_currentPage",currentNum);
		
		return "Info/UserInfoSub/LoveComic";
	}
	/***
	 * 关注作者面板
	 * @param userid
	 * @param m
	 * @return
	 */
	@RequestMapping("/userinfo/lovecomicer/{userid}")
	public String UserLoveComicer(@PathVariable("userid") String userid,Model m) {
		User user=personcenterService.FindUser(userid);
		String[] propertyName={"userId"};
		String[] valueName=new String[1];
		valueName[0]=user.getAccountId();
		List<LoveComicerModel> authors= personcenterService.FindLoveComicer(propertyName,valueName,0,lcmPageNum);
		
		int sum=personcenterService.GetSumByAuhorUser(propertyName, valueName);
		sum=sum%lcmPageNum==0?sum/lcmPageNum:sum/lcmPageNum+1;
		if(sum==0)sum=1;
		
		m.addAttribute("userid",userid);
		m.addAttribute("loveComicer_currentPage",1);
		m.addAttribute("loveComicer_sum",sum);
		m.addAttribute("loveComicer", authors);
		
		return "Info/UserInfoSub/LoveComicer";
	}
	/***
	 * 关注作者面板某页
	 * @param userid 用户id
	 * @param currentNum 页数
	 * @param httpSession session对象
	 * @param m
	 * @return
	 */
	@RequestMapping("/userinfo/lovecomicer/{userid}/{currentNum}")
	public String LoveComicer(@PathVariable("userid") String userid,@PathVariable("currentNum") int currentNum,HttpSession httpSession, Model m) {
		
		User user=personcenterService.FindUser(userid);
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
		
		m.addAttribute("userid",userid);
		m.addAttribute("loveComicer", authors);
		m.addAttribute("loveComicer_sum",sum);
		m.addAttribute("loveComicer_currentPage",currentNum);
		
		return "Info/UserInfoSub/LoveComicer";
	}
	/***
	 * 用户评论面板
	 * @param userid 用户id
	 * @param httpSession session对象
	 * @param m model对象
	 * @return
	 */
	@RequestMapping("/userinfo/comment/{userid}")
	public String UserComment(@PathVariable("userid") String userid,HttpSession httpSession, Model m) {
		User user=personcenterService.FindUser(userid);
		
		int sum=personcenterService.GetCommentSum("'"+user.getAccountId()+"'");
		sum=sum%commentMax==0?sum/commentMax:sum/commentMax+1;
		if(sum==0)sum=1;
		
		m.addAttribute("userid",userid);
		m.addAttribute("comments_sum", sum);
		m.addAttribute("comments", personcenterService.FindComments("'"+user.getAccountId()+"'", 0, commentMax));
		m.addAttribute("comments_currentpage", 1);
		
		return "Info/UserInfoSub/Comment";
	}
	/***
	 * 用户评论面板某页
	 * @param userid 用户id
	 * @param page 页数
	 * @param httpSession session对象
	 * @param m model对象
	 * @return
	 */
	@RequestMapping("/userinfo/comment/{userid}/{page}")
	public String UserCommentSub(@PathVariable("userid") String userid,@PathVariable("page") int page,HttpSession httpSession, Model m) {
		User user=personcenterService.FindUser(userid);
		
		int sum=personcenterService.GetCommentSum("'"+user.getAccountId()+"'");
		sum=sum%commentMax==0?sum/commentMax:sum/commentMax+1;
		if(sum==0)sum=1;
		
		
		page=page<1?1:page;
		page=page>sum?sum:page;
		int pre=(page-1)*commentMax;;
		
		m.addAttribute("userid",userid);
		m.addAttribute("comments_sum", sum);
		m.addAttribute("comments", personcenterService.FindComments("'"+user.getAccountId()+"'",pre, commentMax));
		m.addAttribute("comments_currentpage", page);
		return "Info/UserInfoSub/Comment";
	}
	
	/***
	 * 作者面板首页
	 * @param authorid
	 * @param m
	 * @return
	 */
	@RequestMapping("/authorinfo/{authorid}")
	public String AuthorInfoIndex(@PathVariable("authorid") String authorid,Model m){
		Author author=authorService.GetAuthor(authorid);
		m.addAttribute("tagAuthor", author);
		m.addAttribute("authorid",authorid);
		return "Info/AuthorInfo";
	}
	/***
	 * 作者信息面板
	 * @param userid
	 * @param m
	 * @return
	 */
	@RequestMapping("/authorinfo/info/{authorid}")
	public String AuthorInfo(@PathVariable("authorid") String authorid,Model m){
		Author author=authorService.GetAuthor(authorid);
		m.addAttribute("tagAuthor", author);
		m.addAttribute("authorid",authorid);
		return "Info/AuthorInfoSub/AuthorInfo";
	}
	/***
	 * 作者漫画面板
	 * @param httpSession
	 * @param m
	 * @return
	 */
	@RequestMapping("/authorinfo/info/comic/{authorid}")
	public String AuthorComic(@PathVariable("authorid") String authorid,Model m) {
		Author author=authorService.GetAuthor(authorid);

		List<AuthorComicModel> comics = authorService.GetAuthorComic("'"+author.getAuthorId()+"'", "date", 0, authorComicMax);
		int sum = authorService.GetSumAC("'"+author.getAuthorId()+"'");
		int total = sum;
		sum = sum % authorComicMax == 0 ? sum / authorComicMax : sum
				/ authorComicMax + 1;
		if (sum == 0)
			sum = 1;
		m.addAttribute("authorid",authorid);
		m.addAttribute("comicmage_currentPage", 1);
		m.addAttribute("comicmage_sum", sum);
		m.addAttribute("comics", comics);
		m.addAttribute("comictotal", total);

		return "Author/AuthorInfoSub/Comic";
	}

	@RequestMapping("/authorinfo/info/comic/{authorid}/{currentNum}")
	public String AuthorComic(@PathVariable("authorid") String authorid,@PathVariable("currentNum") int currentNum,HttpSession httpSession, Model m) {

		Author author=authorService.GetAuthor(authorid);

		int sum = authorService.GetSumAC("'"+author.getAuthorId()+"'");
		int total = sum;
		sum = sum % authorComicMax == 0 ? sum / authorComicMax : sum
				/ authorComicMax + 1;
		if (sum == 0)
			sum = 1;

		currentNum = currentNum < 1 ? 1 : currentNum;
		currentNum = currentNum > sum ? sum : currentNum;
		int pre = (currentNum - 1) * authorComicMax;
		List<AuthorComicModel> comics = authorService.GetAuthorComic("'"+
				author.getAuthorId()+"'", "date", pre, authorComicMax);
		m.addAttribute("authorid",authorid);
		m.addAttribute("comicmage_currentPage", currentNum);
		m.addAttribute("comicmage_sum", sum);
		m.addAttribute("comics", comics);
		m.addAttribute("comictotal", total);

		return "Author/AuthorInfoSub/Comic";
	}
	
	
}
