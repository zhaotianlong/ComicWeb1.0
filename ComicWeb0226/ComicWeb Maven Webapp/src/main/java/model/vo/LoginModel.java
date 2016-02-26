package model.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginModel {

	@NotEmpty(message="账户不能为空")
	@Length(max=12,min=8,message="账户最多12位最少8位")
	private String account;
	
	@NotEmpty(message="密码不能为空")
	@Length(max=20,min=8,message="密码最多20位最少8位")
	private String password;
	
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
}
