package model.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserPasswordModel {

	@NotEmpty(message = "密码不能为空")
	@Length(max=20,min=8,message="密码最多20位最少8位")
	@Pattern(regexp="^([a-zA-Z0-9]+)$",message="密码能有数字或字母组成")
	private String password;
	
	@NotEmpty(message = "密码不能为空")
	@Pattern(regexp="^([a-zA-Z0-9]+)$",message="密码只能有数字或字母组成")
	@Length(max=20,min=8,message="密码最多20位最少8位")
	private String rePassword;

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
	
	
	

}
