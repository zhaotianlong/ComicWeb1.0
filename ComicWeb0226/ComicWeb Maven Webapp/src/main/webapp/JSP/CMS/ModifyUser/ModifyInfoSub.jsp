<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="profile" style="width: 100%;">
	<h1>修改资料</h1>
	<div class="profileContent">
<form:form modelAttribute="User" action="modifyuserinfo/do" cssClass="profileContent" id="modifyform">
<div>
				账户名:<input type="text" id="accountId" value="${user.accountId}"
					disabled="disabled" /> <a id="accountIdMdf"
					onclick="Modify('accountId')">修改</a> <a id="accountIdConf"
					onclick="Config('accountId')" hidden="hidden">确认</a> <a id="accountIdCac"
					onclick="Cancel('accountId')" hidden="hidden">取消</a> <input
					type="hidden" id="accountIdHidden" name="accountId"
					value="${user.accountId}" />
</div>

<div>
				密码:<input type="text" id="password" value="${user.password}"
					disabled="disabled" /> <a id="passwordMdf"
					onclick="Modify('password')">修改</a> <a id="passwordConf"
					onclick="Config('password')" hidden="hidden">确认</a> <a id="passwordCac"
					onclick="Cancel('password')" hidden="hidden">取消</a> <input
					type="hidden" id="passwordHidden" name="password"
					value="${user.password}" />
</div>
<div>
				出生日期:<input type="date" id="bornDate" value="${user.bornDate}"
					disabled="disabled" /> <a id="bornDateMdf"
					onclick="Modify('bornDate')">修改</a> <a id="bornDateConf"
					onclick="Config('bornDate')" hidden="hidden">确认</a> <a id="bornDateCac"
					onclick="Cancel('bornDate')" hidden="hidden">取消</a> <input
					type="hidden" id="bornDateHidden" name="bornDate"
					value="${user.bornDate}" />
</div>
<div>
				性别:
					<c:choose>
						<c:when test="${user.gender=='M'}">
								<input type="radio" name="gender" value="M" checked="checked" />
								<input type="radio" name="gender" value="W"  />
						</c:when>
						<c:otherwise>
								<input type="radio" name="gender" value="M"  />
								<input type="radio" name="gender" value="W" checked="checked"  />
						</c:otherwise>
					</c:choose>
</div>
<div>
				注册日期:<input type="date" id="resisterDate" value="${user.resisterDate}"
					disabled="disabled" /> <a id="resisterDateMdf"
					onclick="Modify('resisterDate')">修改</a> <a id="resisterDateConf"
					onclick="Config('resisterDate')" hidden="hidden">确认</a> <a id="resisterDateCac"
					onclick="Cancel('resisterDate')" hidden="hidden">取消</a> <input
					type="hidden" id="resisterDateHidden" name="resisterDate"
					value="${user.resisterDate}" />
</div>
<div>
				电话:<input type="text" id="tel" value="${user.tel}"
					disabled="disabled" /> <a id="telMdf"
					onclick="Modify('tel')">修改</a> <a id="telConf"
					onclick="Config('tel')" hidden="hidden">确认</a> <a id="telCac"
					onclick="Cancel('tel')" hidden="hidden">取消</a> <input
					type="hidden" id="telHidden" name="tel"
					value="${user.tel}" />
</div>
<div>
				邮箱:<input type="text" id="mail" value="${user.mail}"
					disabled="disabled" /> <a id="mailMdf"
					onclick="Modify('mail')">修改</a> <a id="mailConf"
					onclick="Config('mail')" hidden="hidden">确认</a> <a id="mailCac"
					onclick="Cancel('mail')" hidden="hidden">取消</a> <input
					type="hidden" id="mailHidden" name="mail"
					value="${user.mail}" />
</div>
<div>
				昵称:<input type="text" id="nickName" value="${user.nickName}"
					disabled="disabled" /> <a id="nickNameMdf"
					onclick="Modify('nickName')">修改</a> <a id="nickNameConf"
					onclick="Config('nickName')" hidden="hidden">确认</a> <a id="nickNameCac"
					onclick="Cancel('nickName')" hidden="hidden">取消</a> <input
					type="hidden" id="nickNameHidden" name="nickName"
					value="${user.nickName}" />
</div>
	<input type="submit" class="button buttonYellowGreen" value="提交">
	<a onclick="Reset();" class="button buttonOrange">重置</a>
</form:form>
</div>
</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/additional-methods.js"></script>
	<script type="text/javascript">
			var str=new Object();
			function Modify(inputId){
				$("#"+inputId).removeAttr("disabled");
				str[inputId]=$("#"+inputId).val();
				$("#"+inputId+"Mdf").hide();
				$("#"+inputId+"Conf").show();
				$("#"+inputId+"Cac").show();
			}
			function Cancel(inputId){
				$("#"+inputId).attr("disabled","disabled");
				$("#"+inputId).val(str[inputId]);
				$("#"+inputId+"Mdf").show();
				$("#"+inputId+"Cac").hide();
				$("#"+inputId+"Conf").hide();
			}
			function Config(inputId){
				$("#"+inputId).attr("disabled","disabled");
				str[inputId]=$("#"+inputId).val();
				$("#"+inputId+"Hidden").val(str[inputId]);
				$("#"+inputId+"Mdf").show();
				$("#"+inputId+"Conf").hide();
				$("#"+inputId+"Cac").hide();
			}
				function Reset(){
					var urlStr="<%=request.getContextPath()%>/cms/modifyuserinfo";
					$.ajax({
						url : urlStr,
						async : false,
						error : function() {
							alert("ajax出错！");
						},
						success : function(data) {
							$("#userSubPanel").html(data);
						}
					});	
				}
				
				$().ready(
						function() {
							$("#modifyform").validate({
								rules : {
									mail : {
										required : true,
										email : true
									},
									tel: {
										mobile : true
									}
								},
								messages : {
									mail : {
										required : "邮箱不能为空",
										email : "邮箱格式有误"
									}
								}
							});
							jQuery.validator.addMethod("chrnum", function(value, element) {
								var chrnum = /^([a-zA-Z0-9]+)$/;
								return this.optional(element) || (chrnum.test(value));
							}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");
							jQuery.validator.addMethod("mobile", function(value, element) {
								var length = value.length;
								var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
								return this.optional(element)
										|| (length == 11 && mobile.test(value));
							}, "手机号码格式错误");
						});
								
			</script>