package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.jws.WebParam.Mode;
import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpSession;

import model.Author;
import model.Authority;
import model.Comic;
import model.RoleAuthority;
import model.User;
import model.UserComic;
import model.vo.LoginModel;
import model.vo.ResistModel;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import service.IPersoncenterService;
import service.IRoleService;
import service.IUserService;

@Controller
@RequestMapping("/homepage")
public class HomePageController {

	@Resource(name = "userService")
	private IUserService userService;
	@Resource(name = "roleService")
	private IRoleService roleService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("/index")
	public String Home() {
		return "HomePage/Index";
	}

	@RequestMapping("/login")
	public String Login(Model m, HttpSession session) {
		if (session.getAttribute("user") != null)
			return "HomePage/Index";
		m.addAttribute("LoginModel", new LoginModel());
		return "HomePage/Login";
	}

	@RequestMapping("/userdologin")
	public String UserDoLogin(@Valid @ModelAttribute("LoginModel") LoginModel lm,
			BindingResult result, Model m, HttpSession session) {
		try {
			if (result.hasErrors())
				return "HomePage/Login";
			userService.CheckLogin(lm);
			User user = userService.FindOne(lm.getAccount());
			session.setAttribute("user", user);
			//session.setAttribute("author", userService.GetAuthor("oaini"));
			return "redirect:/homepage/index";
		} catch (IOException ex) {
			m.addAttribute("error", ex.getMessage());
			return "HomePage/Login";
		}
	}
	@RequestMapping("/authordologin")
	public String AuthorDoLogin(@Valid @ModelAttribute("LoginModel") LoginModel lm,
			BindingResult result, Model m, HttpSession session) {
		try {
			if (result.hasErrors())
				return "HomePage/Login";
			userService.CheckAuthorLogin(lm);;
			session.setAttribute("author", userService.GetAuthor(lm.getAccount()));
			
			return "redirect:/author/index";
		} catch (IOException ex) {
			m.addAttribute("error", ex.getMessage());
			return "HomePage/Login";
		}
	}
	
	@RequestMapping("/resist")
	public String Resist(Model m, HttpSession session) {
		if (session.getAttribute("user") != null)
			return "HomePage/Index";
		m.addAttribute("ResistModel", new ResistModel());
		return "HomePage/Resist";
	}

	@RequestMapping("/userdoresist")
	public String UserDoResist(
			@Valid @ModelAttribute("ResistModel") ResistModel resistModel,
			BindingResult result, Model m, HttpSession session) {
		try {

			if (result.hasErrors()) {
				if (resistModel.getPassword().equals(
						resistModel.getRePassword()) == false)
					result.addError(new ObjectError("equals", "两次密码输入不相同"));
				return "HomePage/Resist";
			}

			if (userService.FindOne(resistModel.getAccount()) != null)
				throw new IOException("登录过程有误，请重新输入");

			User user = GetRmToUser(resistModel);
			userService.Create(user);
			System.out.println(resistModel);
			session.setAttribute("user", user);
			return "HomePage/Index";
		} catch (Exception ex) {
			// TODO: handle exception
			m.addAttribute("error", ex.getMessage());
			return "HomePage/Resist";
		}
	}

	@RequestMapping("/authordoresist")
	public String AuthorDoResist(
			@Valid @ModelAttribute("ResistModel") ResistModel resistModel,
			BindingResult result, Model m, HttpSession session) {
		try {

			if (result.hasErrors()) {
				if (resistModel.getPassword().equals(
						resistModel.getRePassword()) == false)
					result.addError(new ObjectError("equals", "两次密码输入不相同"));
				return "HomePage/Resist";
			}

			if (userService.GetAuthor(resistModel.getAccount()) != null)
				throw new IOException("登录过程有误，请重新输入");

			Author author = GetRmToAuthor(resistModel);
			userService.CreateAuthor(author);
			session.setAttribute("author", author);
			return "redirect:/author/index";
		} catch (Exception ex) {
			// TODO: handle exception
			m.addAttribute("error", ex.getMessage());
			return "HomePage/Resist";
		}
	}
	
	@RequestMapping("/logout")
	public String Logout(HttpSession httpSession){
		httpSession.invalidate();
		return "redirect:/homepage/index";
	}
	
	
	public User GetRmToUser(ResistModel resistModel) {
		User user = new User();
		user.setAccountId(resistModel.getAccount());
		user.setBornDate(resistModel.getBorn());
		user.setGender(resistModel.getGender());
		user.setMoney(0);
		user.setPassword(resistModel.getPassword());
		user.setMail(resistModel.getMail());
		user.setRoleId(roleService.FindOne(1));
		return user;
	}
	public Author GetRmToAuthor(ResistModel resistModel) {
		Author user = new Author();
		user.setAuthorId(resistModel.getAccount());
		user.setBornDate(resistModel.getBorn());
		user.setGender(resistModel.getGender());
		user.setMoney(0);
		user.setPassword(resistModel.getPassword());
		user.setMail(resistModel.getMail());
		user.setRoleId(roleService.FindOne(2));
		return user;
	}
	

	@ResponseBody
	@RequestMapping(value = "/CheckEuqals")
	public boolean CheckEuqals(@RequestParam String account) {
		System.out.print("-----"
				+ (userService.FindOne(account) == null ? true : false));
		return userService.FindOne(account) == null ? true : false;
	}

}
