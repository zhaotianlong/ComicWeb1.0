package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.sound.midi.SysexMessage;

import model.Author;
import model.Comic;
import model.ComicPage;
import model.ComicPart;
import model.ComicScore;
import model.ComicTab;
import model.Comment;
import model.CommentSub;
import model.SpitSlot;
import model.User;
import model.vo.RankByTabModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.xmlrpc.base.Array;

import service.IComicService;
import service.impl.ComicService;
import util.SortList;

@Controller
@RequestMapping("/comic")
public class ComicController {

	private final int max = 3;
	private final int maxComment = 3;
	private final int maxComic = 1;
	private final int maxRank=10;
	private final int comicsearchMax=3;

	@Resource(name = "comicService")
	private IComicService comicService;

	@RequestMapping("/comiclist")
	public String ComicList(Model m) {

		m.addAttribute("comicList",
				comicService.GetAllComic(null, null, 0, max, "newupdate"));
		int sum = comicService.GetSum(null, null, "newupdate");
		sum = sum % max == 0 ? sum / max : sum / max + 1;
		m.addAttribute("comic_sum", sum);
		m.addAttribute("comic_currentPage", 1);
		return "Comic/ComicList";
	}

	@RequestMapping("/comiclistparam")
	public String ComicListFind(@RequestParam("status") String status,
			@RequestParam("charge") String charge,
			@RequestParam("type") String type,
			@RequestParam("initial") String initial,
			@RequestParam("order") String order, Model m) {
		String property[] = new String[4];
		String value[] = new String[4];
		property[0] = "comicstatus";
		property[1] = "charge";
		property[3] = "tabid";
		property[2] = "initial";
		value[0] = status;
		value[1] = charge;
		value[3] = type;
		value[2] = initial;

		for (int i = 0; i < property.length; i++)
			if (value[i] == "") {
				property[i] = "1";
				value[i] = "1";
			}
		if (initial != "")
			value[2] = "'" + value[2] + "'";

		m.addAttribute("comicList",
				comicService.GetAllComic(property, value, 0, max, order));
		int sum = comicService.GetSum(property, value, order);
		sum = sum % max == 0 ? sum / max : sum / max + 1;
		m.addAttribute("comic_sum", sum);
		m.addAttribute("comic_currentPage", 1);
		return "Comic/ComicListSub";
	}

	@RequestMapping("/comiclistpage")
	public String ComicListPage(@RequestParam("status") String status,
			@RequestParam("charge") String charge,
			@RequestParam("type") String type,
			@RequestParam("initial") String initial,
			@RequestParam("order") String order,
			@RequestParam("page") int page, Model m) {
		String property[] = new String[4];
		String value[] = new String[4];
		property[0] = "comicstatus";
		property[1] = "charge";
		property[3] = "tabid";
		property[2] = "initial";
		value[0] = status;
		value[1] = charge;
		value[3] = type;
		value[2] = initial;

		for (int i = 0; i < property.length; i++)
			if (value[i] == "") {
				property[i] = "1";
				value[i] = "1";
			}
		if (initial != "")
			value[2] = "'" + value[2] + "'";

		int sum = comicService.GetSum(property, value, order);
		sum = sum % max == 0 ? sum / max : sum / max + 1;
		page = page <= 0 ? 1 : page;
		page = page > sum ? sum : page;
		m.addAttribute("comicList", comicService.GetAllComic(property, value,
				(page - 1) * max, max, order));
		m.addAttribute("comic_sum", sum);
		m.addAttribute("comic_currentPage", page);
		return "Comic/ComicListSub";
	}

	@RequestMapping(value = "/comiclist/{name}")
	public String ComicInfo(@PathVariable("name") String name, Model m,HttpSession httpSession) {
		String propertyName[] = { "comicId" };
		String valueName[] = new String[1];
		valueName[0] ="'"+ name+"'";//??????????

		Comic c = comicService.GetComicInfo(name);

		List<Comment> comments = comicService.GetComicComment(propertyName,
				valueName, 0, maxComment);
		Author a = c.getAuthorId();

		Set<ComicPart> comicParts = c.getComicParts();
		int comentSum = comicService.GetCommentSum(propertyName, valueName);
		comentSum = comentSum % maxComment == 0 ? comentSum / maxComment
				: comentSum / maxComment + 1;
		String tabs = "";
		String comicTop3 = "";
		for (ComicTab ct : c.getComicTabs()) {
			tabs += "|" + ct.getTabId().getTabName();
		}

		Iterator<Comic> iterator = a.getComicCollection().iterator();
		int count = 0;
		while (iterator.hasNext()) {
			if (count == 3)
				break;
			Comic cTmp = iterator.next();
			comicTop3 += cTmp.getComicName() + " ";
			count++;
		}

		// int comicPartsSum=1;
		int comicPartsSum = comicParts.size();
		int comicPartsPage = comicPartsSum % maxComic == 0 ? comicPartsSum
				/ maxComic : comicPartsSum / maxComic + 1;
		ComicPart[] tmp = new ComicPart[comicParts.size()];
		ComicPart[] cps = (ComicPart[]) comicParts.toArray(tmp);
		
		
		
		User user=(User)httpSession.getAttribute("user");
		if(user==null){
			m.addAttribute("iscomicscore",0);
			m.addAttribute("userstar",0);
		}
		else
			{	
				if(comicService.CheckComicScore("'"+name+"'", "'"+user.getAccountId()+"'")){
				m.addAttribute("iscomicscore",1);
				String[] property={"comicId","userId"};
				String[] value=new String[2];
				value[0]="'"+name+"'";
				value[1]="'"+user.getAccountId()+"'";
				m.addAttribute("userstar",comicService.GetComicScore(property,value).getStarlevel());
				}
				else
				{
					m.addAttribute("iscomicscore",0);
					m.addAttribute("userstar",0);
				}
			}
		float sumScore=comicService.GetComicScoreSum("'"+name+"'");
		int sumPeople=comicService.GetComicScorePeopleSum("'"+name+"'");
		
		if(sumPeople==0)
			m.addAttribute("comicscore_avg", 0);
		else
			m.addAttribute("comicscore_avg", sumScore/sumPeople);
		m.addAttribute("comicscorepeople_sum",sumPeople);
		m.addAttribute("comicTop3", comicTop3);
		m.addAttribute("comicSum", comicService.GetAuthorSumByComic("'"+a.getAuthorId()+"'"));
		m.addAttribute("loveSum", comicService.GetUserSumUc("'"+name+"'"));
		m.addAttribute("author", a);
		m.addAttribute("comic", c);
		m.addAttribute("comicParts", cps);
		m.addAttribute("comicPartsSum", comicPartsSum);
		m.addAttribute("comicPartsPage", comicPartsPage);
		m.addAttribute("comments", comments);
		m.addAttribute("tabs", tabs);
		m.addAttribute("comment_sum", comentSum);
		m.addAttribute("comment_currentPage", 1);
		m.addAttribute("maxComic", maxComic);
		return "Comic/ComicInfo";
	}

	@RequestMapping(value = "/commentpage")
	public String ComicComent(@RequestParam("comicId") String comicId,
			@RequestParam("page") int page, Model m) {
		String propertyName[] = { "comicId" };
		String valueName[] = new String[1];
		valueName[0] = comicId;

		List<Comment> comments = comicService.GetComicComment(propertyName,
				valueName, (page - 1) * maxComment, maxComment);
		int comentSum = comicService.GetCommentSum(propertyName, valueName);
		comentSum = comentSum % maxComment == 0 ? comentSum / maxComment
				: comentSum / maxComment + 1;
		page = page <= 0 ? 1 : page;
		page = page > comentSum ? comentSum : page;

		m.addAttribute("comments", comments);
		m.addAttribute("comment_sum", comentSum);
		m.addAttribute("comment_currentPage", page);
		return "Comic/ComicComment";
	}

	@RequestMapping(value = "/insertcomment", method = RequestMethod.POST)
	public String InsertComent(@RequestParam("comicId") String comicId,
			@RequestParam("comment") String comment, HttpSession httpSession) {

		System.out.println("comment:" + comment);
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {

			Comment c = new Comment();
			c.setComicId(comicService.GetComic(comicId));
			c.setCommentDes(comment);
			c.setDateTime(new Date());
			c.setUserId(user);
			comicService.InsertComment(c);
		}
		return "redirect:/comic/comiclist/" + comicId;
	}

	@ResponseBody
	@RequestMapping(value = "/insertcommentback")
	public String InsertComentBack(@RequestParam("comment") String comment,
			@RequestParam("commentId") int commentId,
			@RequestParam("toId") String toId, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			CommentSub cs = new CommentSub();
			Comment c = comicService.GetComent(commentId);
			c.setDateTime(new Date());
			cs.setCommentId(c);
			cs.setCommentDes(comment);
			cs.setToId(comicService.GetUser(toId));
			cs.setUpDate(new Date());
			cs.setFromId(user);
			comicService.InsertCommentBack(cs);
			comicService.UpdateComment(c);
			return "";
		}
		return null;
	}
	
	/***
	 * 访问创建漫画评分
	 * @param comicId 漫画id
	 * @param score 得分
	 * @param httpSession session对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/comicscore")
	public String DoComicScore(@RequestParam("comicId") String comicId,@RequestParam("score")int score,HttpSession httpSession){
		User user=(User)httpSession.getAttribute("user");
		
		if(!comicService.CheckComicScore("'"+comicId+"'", "'"+user.getAccountId()+"'"))
		{
			ComicScore cs=new ComicScore();
			cs.setComicId(comicService.GetComic(comicId));
			cs.setNewDate(new Date());
			cs.setStarlevel(score);
			cs.setUserId(comicService.GetUser(user.getAccountId()));
			comicService.CreateComicScore(cs);
		}
		return "ok";
	}
	

	@RequestMapping(value = "/comiclist/{name}/{id}")
	public String ComicInfoSub(@PathVariable("name") String name,
			@PathVariable("id") String id, Model m) {	
		int partSum=comicService.GetComicPartSum("'"+name+"'");
		
		ComicPart cp = comicService.GetComicPart(id);
		Set<ComicPage> cps = cp.getComicPages();
		
		List<ComicPage> comicPages = new ArrayList<ComicPage>(cps);
		SortList<ComicPage> sortPage = new SortList<ComicPage>();
		sortPage.Sort(comicPages, "getId", "");
		if(comicPages!=null)
			m.addAttribute("comicPath", comicPages.get(0).getFilePath());
		else
			m.addAttribute("comicPath", "");
		
		Comic c=comicService.GetComic(name);
		List<ComicPart> comicParts = new ArrayList<ComicPart>(c.getComicParts());
		SortList<ComicPart> sortPart = new SortList<ComicPart>();
		sortPart.Sort(comicParts, "getId", "");
		String prePartId=cp.getPartId();
		String nextPartId=cp.getPartId();
		int j=-1;
		for (int i = 0; comicParts!=null&&i < comicParts.size(); i++,j++) {
			if(comicParts.get(i).getPartId().equals(id))
				break;
		}
		if(j!=-1)prePartId=comicParts.get(j).getPartId();
		
		int pageSum=comicPages.size();

		m.addAttribute("comicPrePartId",prePartId);
		m.addAttribute("comicPrePartName",comicService.GetComicPart(prePartId).getPartName());
		
		if(j+2<comicParts.size())nextPartId=comicParts.get(j+2).getPartId();
		m.addAttribute("comicNextPartId",nextPartId);
		m.addAttribute("comicNextPartName",comicService.GetComicPart(nextPartId).getPartName());
		
		Set<SpitSlot> spitSlots=cp.getSpitSlots();
		m.addAttribute("page_sum", pageSum);
		m.addAttribute("page_currentPage", 1);
		m.addAttribute("spitSlots",spitSlots);
		m.addAttribute("comicName", cp.getComic().getComicName());
		m.addAttribute("comicPart", cp.getPartName());
		m.addAttribute("comicPartId", cp.getPartId());
		m.addAttribute("comicId", cp.getComic().getComicId());
		return "Comic/ComicContent";
	}

	
	@RequestMapping(value = "/comiclist/{name}/{id}/{page}")
	public String ComicInfoSub(@PathVariable("name") String name,
			@PathVariable("id") String id, @PathVariable("page") int page,
			Model m) {

		
		ComicPart cp = comicService.GetComicPart(id);
		Set<ComicPage> cps = cp.getComicPages();
		List<ComicPage> comicPages = new ArrayList<ComicPage>(cps);
		SortList<ComicPage> sortPage = new SortList<ComicPage>();
		sortPage.Sort(comicPages, "getId", "");
		
		int pageSum=comicPages.size();
		if(pageSum==0)pageSum=1;
		page=page<1?1:page;
		page=page>pageSum?pageSum:page;
		
		
		if(comicPages!=null)
			m.addAttribute("comicPath",comicPages.get(page-1).getFilePath());
		else
			m.addAttribute("comicPath", "");
		
		Comic c=comicService.GetComic(name);
		List<ComicPart> comicParts = new ArrayList<ComicPart>(c.getComicParts());
		SortList<ComicPart> sortPart = new SortList<ComicPart>();
		sortPart.Sort(comicParts, "getId", "");
		String prePartId=cp.getPartId();
		String nextPartId=cp.getPartId();
		int j=-1;
		for (int i = 0; comicParts!=null&&i < comicParts.size(); i++,j++) {
			if(comicParts.get(i).getPartId().equals(id))
				break;
		}
		if(j!=-1)prePartId=comicParts.get(j).getPartId();

		m.addAttribute("comicPrePartId",prePartId);
		m.addAttribute("comicPrePartName",comicService.GetComicPart(prePartId).getPartName());
		if(j+2<comicParts.size())nextPartId=comicParts.get(j+2).getPartId();
		m.addAttribute("comicNextPartId",nextPartId);
		m.addAttribute("comicNextPartName",comicService.GetComicPart(nextPartId).getPartName());
		m.addAttribute("page_sum", pageSum);
		m.addAttribute("page_currentPage", page);
		return "Comic/ComicPageSub";
	}
	
	
	
	@RequestMapping(value = "/insertspitslot")
	public String ComicInsertSpitSlot(@RequestParam("comicId") String comicId,@RequestParam("partId") String partId,@RequestParam("des") String des){
		if(!des.equals("")){
		ComicPart cp = comicService.GetComicPart(partId);
		SpitSlot ss=new SpitSlot();
		ss.setPartId(cp);
		ss.setSpitSlotDes(des);
		comicService.InsertSpitSlot(ss);
		}
		return "redirect:/comic/comiclist/" + comicId+"/"+partId;
	}
	
	
	
	@RequestMapping(value = "/ranklist")
	public String ComicRank(Model m) {
		m.addAttribute("rankByTabModels", comicService.RankByGood());
		return "Comic/ComicRank";
	}
	
	@RequestMapping(value = "/ranklist/love")
	public String ComicRankByLove(Model m) {
		m.addAttribute("rankByTabModels", comicService.RankByLove());
		return "Comic/ComicRankSub";
	}
	@RequestMapping(value = "/ranklist/tab/{id}")
	public String ComicRankBytag(@PathVariable("id") String idS,Model m) {
		
		List<RankByTabModel> rankByTabModels=comicService.RankByTab(idS, 0, maxRank);
		m.addAttribute("rankByTabModels", rankByTabModels);
		return "Comic/ComicRankSub";
	}
	/***
	 * 访问漫画搜索
	 * @param keyword 关键词
	 * @param m model对象
	 * @return
	 */
	@RequestMapping(value="/search")
	public String ComicSearch(@RequestParam("keyword")String keyword,Model m){
		int sum=comicService.GetSumBySearch(keyword);
		int total=sum;
		sum=sum%comicsearchMax==0?sum/comicsearchMax:sum/comicsearchMax+1;
		if(sum==0)sum=1;
		List<Comic> comics=comicService.SearchComic(keyword, 0, comicsearchMax);
		
		m.addAttribute("search_total",total);
		m.addAttribute("search_sum",sum);
		m.addAttribute("search_currentpage", 1);
		m.addAttribute("comics",comics);
		m.addAttribute("keyword",keyword);
		return "Comic/ComicSearch";
	}
	/***
	 * 访问漫画搜索某页
	 * @param keywords 关键字
	 * @param page 页数
	 * @param m model对象
	 * @return
	 */
	@RequestMapping(value="/search/{keyword}/{page}")
	public String ComicSearch(@PathVariable("keyword")String keywords,@PathVariable("page")Integer page,Model m){
		int sum=comicService.GetSumBySearch(keywords);
		int total=sum;
		sum=sum%comicsearchMax==0?sum/comicsearchMax:sum/comicsearchMax+1;
		if(sum==0)sum=1;
		
		page=page<1?1:page;
		page=page>sum?sum:page;
		int pre=(page-1)*comicsearchMax;;
		
		List<Comic> comics=comicService.SearchComic(keywords, pre, comicsearchMax);
		
		m.addAttribute("search_total",total);
		m.addAttribute("search_sum",sum);
		m.addAttribute("search_currentpage", page);
		m.addAttribute("comics",comics);
		m.addAttribute("keyword",keywords);
		
		return "Comic/ComicSearchSub";
	}
	
	
}
