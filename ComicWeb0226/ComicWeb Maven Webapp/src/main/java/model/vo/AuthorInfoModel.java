package model.vo;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class AuthorInfoModel {
	@NotEmpty(message = "邮箱不能为空")
	@Email(message = "邮箱格式不对")
	private String mail;
	
	private String gender;
	
	@NotEmpty(message = "手机号不能为空")
	@Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1}))+\\d{8})$", message = "手机格式不对")
	private String tel;
	
	@Past
	private Date born;

	private String realName;
	
	private String address;
	
	private String idcard;
	
	

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
