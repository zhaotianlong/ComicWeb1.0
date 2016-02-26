package model.vo;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class ResistModel {
	@NotEmpty(message = "账户不能为空")
	@Length(max=12,min=8,message="账户最多12位最少8位")
	@Pattern(regexp="^([a-zA-Z0-9]+)$",message="账户只能有数字或字母组成")
	private String account;
	
	@NotEmpty(message = "密码不能为空")
	@Length(max=20,min=8,message="密码最多20位最少8位")
	@Pattern(regexp="^([a-zA-Z0-9]+)$",message="密码能有数字或字母组成")
	private String password;
	
	@NotEmpty(message = "密码不能为空")
	@Pattern(regexp="^([a-zA-Z0-9]+)$",message="密码只能有数字或字母组成")
	@Length(max=20,min=8,message="密码最多20位最少8位")
	private String rePassword;
	
	@NotEmpty(message = "邮箱不能为空")
	@Email(message = "邮箱格式不对")
	private String mail;
	
	private String gender;
	
	@NotEmpty(message = "手机号不能为空")
	@Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1}))+\\d{8})$", message = "手机格式不对")
	private String tel;
	
	private String nickName;
	
	@Past
	private Date born;

	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
