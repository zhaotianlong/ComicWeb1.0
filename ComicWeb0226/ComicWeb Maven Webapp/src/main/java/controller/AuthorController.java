package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.FormSubmitEvent.MethodType;
import javax.validation.Valid;

import model.Author;
import model.AuthorUser;
import model.Comic;
import model.ComicPage;
import model.ComicPart;
import model.ComicTab;
import model.ComicTrend;
import model.Tab;
import model.User;
import model.vo.AuthorComicModel;
import model.vo.AuthorCreateComicModel;
import model.vo.AuthorInfoModel;
import model.vo.ComicTrendModel;
import model.vo.LoveComicModel;
import model.vo.ModifyComicModel;
import model.vo.UserInfoModel;
import model.vo.UserPasswordModel;

import org.apache.commons.io.FileUtils;
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

import service.IAuthorService;
import util.SortList;
import auth.RoleAuth;

@Controller
@RoleAuth(name = "user", value = { "author" })
@RequestMapping(value = "/author")
public class AuthorController {

	private final int authorComicMax = 3;
	private final int authorComicPage = 48;
	private final int authorComicPart = 10;
	private final int authorTrendMax=4;

	@Resource(name = "authorService")
	private IAuthorService authorService;

	/*
	 * 首次载入时界面
	 */

	@RequestMapping(value = "/index")
	public String Index(HttpSession httpSession,Model m) {
		Author author=(Author)httpSession.getAttribute("author");
		Author a=authorService.GetAuthor(author.getAuthorId());
		int loverSum=authorService.GetSumAU("'"+a.getAuthorId()+"'");
		int comicSum=authorService.GetSumAC("'"+a.getAuthorId()+"'");
		
		int sum=authorService.GetComicTrendSum("'"+a.getAuthorId()+"'");
		int total=sum;
		sum=sum%authorTrendMax==0?sum/authorTrendMax:sum/authorTrendMax+1;
		if(sum==0)sum=1;
		
		List<ComicTrendModel> comictrends= authorService.GetComicTrend("'"+a.getAuthorId()+"'",0, authorTrendMax);
		m.addAttribute("comictrend_sum", sum);
		m.addAttribute("comictrends",comictrends);
		m.addAttribute("comictrend_currentpage", 1);
		m.addAttribute("comictrend_total", total);
		
		m.addAttribute("author",a);
		m.addAttribute("comicSum",comicSum);
		m.addAttribute("loverSum",loverSum);
		return "Author/Index";
	}

	/*
	 * 个人中心总界面
	 */
	@RequestMapping(value = "/home")
	public String Home(HttpSession httpSession,Model m) {
		Author author=(Author)httpSession.getAttribute("author");
		Author a=authorService.GetAuthor(author.getAuthorId());
		int loverSum=authorService.GetSumAU("'"+a.getAuthorId()+"'");
		int comicSum=authorService.GetSumAC("'"+a.getAuthorId()+"'");
		
		
		int sum=authorService.GetComicTrendSum("'"+a.getAuthorId()+"'");
		int total=sum;
		sum=sum%authorTrendMax==0?sum/authorTrendMax:sum/authorTrendMax+1;
		if(sum==0)sum=1;
		
		List<ComicTrendModel> comictrends= authorService.GetComicTrend("'"+a.getAuthorId()+"'",0, authorTrendMax);
		m.addAttribute("comictrend_sum", sum);
		m.addAttribute("comictrends",comictrends);
		m.addAttribute("comictrend_currentpage", 1);
		m.addAttribute("comictrend_total", total);
		
		
		m.addAttribute("author",a);
		m.addAttribute("comicSum",comicSum);
		m.addAttribute("loverSum",loverSum);
		return "Author/Home";
	}
	/***
	 * 作者动态
	 * @param httpSession session对象
	 * @param m model对象
	 * @return
	 */
	@RequestMapping(value = "/authortrend")
	public String AuthorTrend(HttpSession httpSession,Model m){
		Author author=(Author)httpSession.getAttribute("author");
		Author a=authorService.GetAuthor(author.getAuthorId());
		int sum=authorService.GetComicTrendSum("'"+a.getAuthorId()+"'");
		int total=sum;
		sum=sum%authorTrendMax==0?sum/authorTrendMax:sum/authorTrendMax+1;
		if(sum==0)sum=1;
		
		
		List<ComicTrendModel> comictrends= authorService.GetComicTrend("'"+a.getAuthorId()+"'",0, authorTrendMax);
		m.addAttribute("comictrend_sum", sum);
		m.addAttribute("comictrends",comictrends);
		m.addAttribute("comictrend_currentpage", 1);
		m.addAttribute("comictrend_total", total);
		
		return "Author/AuthorTrend";
	}
	
	
	/***
	 * 作者动态
	 * @param page 页数
	 * @param httpSession session对象
	 * @param m model对象
	 * @return
	 */
	@RequestMapping(value = "/authortrend/{page}")
	public String AuthorTrend(@PathVariable("id")int page,HttpSession httpSession,Model m){
		Author author=(Author)httpSession.getAttribute("author");
		Author a=authorService.GetAuthor(author.getAuthorId());
		int sum=authorService.GetComicTrendSum("'"+a.getAuthorId()+"'");
		int total=sum;
		sum=sum%authorTrendMax==0?sum/authorTrendMax:sum/authorTrendMax+1;
		if(sum==0)sum=1;
		
		page=page<1?1:page;
		page=page>sum?sum:page;
		int pre=(page-1)*authorTrendMax;;
		
		List<ComicTrendModel> comictrends= authorService.GetComicTrend("'"+a.getAuthorId()+"'",pre, authorTrendMax);
		m.addAttribute("comictrend_sum", sum);
		m.addAttribute("comictrends",comictrends);
		m.addAttribute("comictrend_currentpage", page);
		m.addAttribute("comictrend_total", total);
		
		return "Author/AuthorTrend";
	}

	/*
	 * 修改个人信息页面
	 */
	@RequestMapping(value = "/modifyinfo")
	public String ModifyInfo(Model m, HttpSession httpSession) {
		Author author = (Author) httpSession.getAttribute("author");
		AuthorInfoModel aim = new AuthorInfoModel();
		aim.setAddress(author.getAddress());
		aim.setBorn(author.getBornDate());
		aim.setGender(author.getGender());
		aim.setMail(author.getMail());
		aim.setRealName(author.getMail());
		aim.setTel(author.getTel());
		aim.setIdcard(author.getIdcard());
		m.addAttribute("AuthorInfoModel", aim);
		return "Author/ModifyInfo";
	}

	@RequestMapping("/modifyinfo/do")
	public String DoModifyInfo(
			@Valid @ModelAttribute("AuthorInfoModel") AuthorInfoModel aim,
			BindingResult result, Model m, HttpSession httpSession) {

		Author author = (Author) httpSession.getAttribute("author");
		author.setBornDate(aim.getBorn());
		author.setGender(aim.getGender());
		author.setMail(aim.getMail());
		author.setTel(aim.getTel());
		author.setRealName(aim.getRealName());
		author.setAddress(aim.getAddress());
		author.setIdcard(aim.getIdcard());
		m.addAttribute("author", author);

		authorService.SaveAuthorInfo(author);
		return "redirect:/author/index";

	}

	@RequestMapping("/modifypassword")
	public String ModifyPassword(HttpSession httpSession, Model m) {

		Author author = (Author) httpSession.getAttribute("author");
		UserPasswordModel upm = new UserPasswordModel();
		upm.setPassword(author.getPassword());
		m.addAttribute("UserPasswordModel", upm);
		return "Author/ModifyPassword";
	}

	@RequestMapping("/modifypassword/do")
	public String DoModifyPassword(
			@Valid @ModelAttribute("UserPasswordModel") UserPasswordModel upm,
			BindingResult result, Model m, HttpSession httpSession) {
		Author author = (Author) httpSession.getAttribute("author");
		if (result.hasErrors()) {
			UserPasswordModel tmp = new UserPasswordModel();
			tmp.setPassword(author.getPassword());
			m.addAttribute("UserPasswordModel", tmp);
			return "redirect:/author/index";
		}
		author.setPassword(upm.getPassword());
		m.addAttribute("author", author);
		authorService.SaveAuthorInfo(author);
		return "redirect:/author/index";
	}

	@RequestMapping("/modifyicon")
	public String ModifyIcon() {
		return "Author/ModifyIcon";
	}

	@RequestMapping(value = "/modifyicon/do", method = RequestMethod.POST)
	public String UpdateIcon(@RequestParam("fileId") MultipartFile fileId,
			@RequestParam("x") int x, @RequestParam("y") int y,
			@RequestParam("w") int w, @RequestParam("h") int h,
			@RequestParam("selectW") int selectW,
			@RequestParam("selectH") int selectH, HttpSession session, Model m) {
		try {
			Author author = (Author) session.getAttribute("author");
			String iconName = author.getAuthorId();
			if (x >= 0 || y >= 0 || w <= 0 || h <= 0 || selectW <= 0
					|| selectH <= 0)
				return "redirect:/author/index";
			if (!fileId.isEmpty()) {
				x = 0 - x;
				y = 0 - y;
				String path = authorService.CutoutIcon(fileId, x, y, w, h,
						selectW, selectH, iconName);
				author.setIconPath(path);
				authorService.SaveAuthorInfo(author);
				m.addAttribute("author", author);
			}
			return "redirect:/author/index";
		} catch (Exception e) {
			System.out.println("error:" + e.getMessage());
			return "redirect:/author/index";
		}
	}

	@RequestMapping("/comicmanage")
	public String ComicManage(HttpSession httpSession, Model m) {

		Author author = (Author) httpSession.getAttribute("author");

		List<AuthorComicModel> comics = authorService.GetAuthorComic("'"+author.getAuthorId()+"'", "date", 0, authorComicMax);
		int sum = authorService.GetSumAC("'"+author.getAuthorId()+"'");
		int total = sum;
		sum = sum % authorComicMax == 0 ? sum / authorComicMax : sum
				/ authorComicMax + 1;
		if (sum == 0)
			sum = 1;

		m.addAttribute("comicmage_currentPage", 1);
		m.addAttribute("comicmage_sum", sum);
		m.addAttribute("comics", comics);
		m.addAttribute("comictotal", total);

		return "Author/ComicManage";
	}

	@RequestMapping("/comicmanage/{currentNum}")
	public String ComicManage(@PathVariable("currentNum") int currentNum,
			HttpSession httpSession, Model m) {

		Author author = (Author) httpSession.getAttribute("author");

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

		m.addAttribute("comicmage_currentPage", currentNum);
		m.addAttribute("comicmage_sum", sum);
		m.addAttribute("comics", comics);
		m.addAttribute("comictotal", total);

		return "Author/ComicManage";
	}

	@RequestMapping("/modifycomic/{comicId}")
	public String ModifyComic(@PathVariable("comicId") String comicId, Model m) {
		String str = "";
		try {
		Comic comic = authorService.GetComic(comicId);
		m.addAttribute("comic", comic);

		for (ComicTab ct : comic.getComicTabs())
			str += ct.getTabId().getTabId() + "_";
		} catch (Exception e) {
			// TODO: handle exception
		}
		m.addAttribute("comicTab", str);
		return "Author/ModifyComic";
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

	@RequestMapping("/modifycomicinfo")
	public String ModifyComicInfo(@Valid @ModelAttribute("Comic") Comic c,
			BindingResult result, Model m, HttpSession httpSession) {

		Comic comic = authorService.GetComic(c.getComicId());
		if (result.hasErrors()) {
			return "redirect:/author/index";
		}
		comic.setComicName(c.getComicName());
		comic.setDescription(c.getDescription());
		comic.setCharge(c.getCharge());
		comic.setComicStatus(c.getComicStatus());
		comic.setInitial(c.getInitial());
		authorService.SaveComicInfo(comic);
		return "redirect:/author/index";
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
		//Set<ComicPart> cps=new HashSet<ComicPart>();
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
		return "Author/ModifyComicPart";
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
		return "Author/ModifyComicPart";
	}

	@RequestMapping(value = "/addcomicpage/{comicId}")
	public String AddComicPage(@PathVariable("comicId") String comicId, Model m) {
		Comic c = authorService.GetComic(comicId);
		Set<ComicPart> cps = c.getComicParts();
		m.addAttribute("comicPageNew", cps.size());
		m.addAttribute("comicId", comicId);
		return "Author/AddComicPage";
	}

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
			int partNum = authorService.GetPartMaxId(comicId)+1;
			
			
			String s=UUID.randomUUID().toString();
			String partId=s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
			while(authorService.GetComicPart(partId)!=null){
				String tmp=UUID.randomUUID().toString();
				partId=tmp.substring(0,8)+tmp.substring(9,13)+tmp.substring(14,18)+tmp.substring(19,23)+tmp.substring(24);
			}
			
			cp.setComic(c);
			cp.setPartId(partId);
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
					//page.setPageId(cp.getPartId() + "_" + i);
					String s1=UUID.randomUUID().toString();
					String pageId=s1.substring(0,8)+s1.substring(9,13)+s1.substring(14,18)+s1.substring(19,23)+s1.substring(24);
					while(authorService.GetComicPage(pageId)!=null){
						String tmp=UUID.randomUUID().toString();
						pageId=tmp.substring(0,8)+tmp.substring(9,13)+tmp.substring(14,18)+tmp.substring(19,23)+tmp.substring(24);
					}
					page.setPageId(pageId);
					page.setId(i);
					page.setFilePath("img/comic/" + c.getComicId()+ "/"
							+ originalFilename);
					authorService.CreateComicPage(page);
				}
			}
			ComicTrend ct=new ComicTrend();
			ct.setComicId(c);
			ct.setDateLine(new Date());
			ct.setDes(c.getAuthorId().getRealName()+"更新漫画 "+c.getComicName()+" 第"+cp.getId()+"话");
			authorService.CreateComicTrend(ct);
			authorService.CreateComicPart(cp);
			return "redirect:/author/index";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

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
		return "Author/ModifyComicPage";
	}

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
		for (int i = pre; i < pages.size() && count< authorComicPage; i++,count++)
			pagesNew.add(pages.get(i));

		m.addAttribute("partName", cp.getPartName());
		m.addAttribute("page_currentPage", currentNum);
		m.addAttribute("page_sum", sum);
		m.addAttribute("pages", pagesNew);
		m.addAttribute("page_total", total);
		m.addAttribute("comicPartId", comicPartId);
		return "Author/ModifyComicPage";
	}

	@RequestMapping("/modifycomicpartname")
	public String ModifyComicPartName(@RequestParam("partId") String partId,@RequestParam("partName") String partName){
		ComicPart cp=authorService.GetComicPart(partId);
		cp.setPartName(partName);
		authorService.SaveComicPart(cp);
		return "redirect:/author/modifycomicpage/"+partId;
	}
	
	
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
				return "redirect:/author/modifycomicpage/"+cp.getPartId().getPartId();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	@RequestMapping("/createcomic")
	public String CreateComic(Model m){
		m.addAttribute("AuthorCreateComicModel", new AuthorCreateComicModel());
		return "Author/CreateComic";
	}
	
	@RequestMapping(value="/createcomic/do", method=RequestMethod.POST)
	public String CreateComic(@Valid @ModelAttribute("AuthorCreateComicModel") AuthorCreateComicModel accm,
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
		ComicTrend ct=new ComicTrend();
		ct.setComicId(c);
		ct.setDateLine(new Date());
		ct.setDes(c.getAuthorId().getRealName()+"上传了新漫画： "+c.getComicName());
		authorService.CreateComicTrend(ct);
		
		return "redirect:/author/index";
	}
	
	@ResponseBody
	@RequestMapping(value="/mutifyfile/upload",method=RequestMethod.POST)
	public String MutifyImgUpload(@RequestParam("fileImage") MultipartFile fileImage,
			@RequestParam("partId") String partId, HttpSession httpSession){
		try {
			ComicPart cp=authorService.GetComicPart(partId);
			Comic c = cp.getComic();
			String realPath = httpSession.getServletContext().getRealPath(
					"/WebResources/img/comic/" + c.getComicId());
			
			String s=UUID.randomUUID().toString();
			String pageId=s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
			
			int max=0;
			for(ComicPage tmpPage :cp.getComicPages())
				if(tmpPage.getId()>max)max=tmpPage.getId();
					max++;
			
			if (!fileImage.isEmpty()) {
				String originalFilename = fileImage.getOriginalFilename();
				if (fileImage.getSize() > 2000000)
					return null;
				FileUtils.copyInputStreamToFile(fileImage.getInputStream(),
						new File(realPath, originalFilename));
			ComicPage page = new ComicPage();
			page.setPartId(cp);
			page.setPageId(pageId);
			page.setId(max);
			page.setFilePath("img/comic/" + c.getComicId()+ "/"
					+ originalFilename);
			authorService.CreateComicPage(page);
			}
			ComicTrend ct=new ComicTrend();
			ct.setComicId(c);
			ct.setDateLine(new Date());
			ct.setDes(c.getAuthorId().getRealName()+"更新了漫画： "+c.getComicName()+" 第"+cp.getId()+"话");
			authorService.CreateComicTrend(ct);
			return "redirect:/author/index";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
