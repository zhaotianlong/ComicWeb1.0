<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="profile" style="width: 100%;">
	<h1>修改资料</h1>
	<div class="profileContent">
<form:form modelAttribute="Author" action="modifyauthorinfo/do" cssClass="profileContent" id="modifyform">
<div>
				账户名:<input type="text" id="authorId" value="${author.authorId}"
					disabled="disabled" /> <a id="authorIdMdf"
					onclick="Modify('authorId')">修改</a> <a id="authorIdConf"
					onclick="Config('authorId')" hidden="hidden">确认</a> <a id="authorIdCac"
					onclick="Cancel('authorId')" hidden="hidden">取消</a> <input
					type="hidden" id="authorIdHidden" name="authorId"
					value="${author.authorId}" />
</div>

<div>
				密码:<input type="text" id="password" value="${author.password}"
					disabled="disabled" /> <a id="passwordMdf"
					onclick="Modify('password')">修改</a> <a id="passwordConf"
					onclick="Config('password')" hidden="hidden">确认</a> <a id="passwordCac"
					onclick="Cancel('password')" hidden="hidden">取消</a> <input
					type="hidden" id="passwordHidden" name="password"
					value="${author.password}" />
</div>
<div>
				出生日期:<input type="date" id="bornDate" value="${author.bornDate}"
					disabled="disabled" /> <a id="bornDateMdf"
					onclick="Modify('bornDate')">修改</a> <a id="bornDateConf"
					onclick="Config('bornDate')" hidden="hidden">确认</a> <a id="bornDateCac"
					onclick="Cancel('bornDate')" hidden="hidden">取消</a> <input
					type="hidden" id="bornDateHidden" name="bornDate"
					value="${author.bornDate}" />
</div>
<div>
				性别:
					<c:choose>
						<c:when test="${author.gender=='M'}">
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
				注册日期:<input type="date" id="resisterDate" value="${author.resisterDate}"
					disabled="disabled" /> <a id="resisterDateMdf"
					onclick="Modify('resisterDate')">修改</a> <a id="resisterDateConf"
					onclick="Config('resisterDate')" hidden="hidden">确认</a> <a id="resisterDateCac"
					onclick="Cancel('resisterDate')" hidden="hidden">取消</a> <input
					type="hidden" id="resisterDateHidden" name="resisterDate"
					value="${author.resisterDate}" />
</div>
<div>
				电话:<input type="text" id="tel" value="${author.tel}"
					disabled="disabled" /> <a id="telMdf"
					onclick="Modify('tel')">修改</a> <a id="telConf"
					onclick="Config('tel')" hidden="hidden">确认</a> <a id="telCac"
					onclick="Cancel('tel')" hidden="hidden">取消</a> <input
					type="hidden" id="telHidden" name="tel"
					value="${author.tel}" />
</div>
<div>
				邮箱:<input type="text" id="mail" value="${author.mail}"
					disabled="disabled" /> <a id="mailMdf"
					onclick="Modify('mail')">修改</a> <a id="mailConf"
					onclick="Config('mail')" hidden="hidden">确认</a> <a id="mailCac"
					onclick="Cancel('mail')" hidden="hidden">取消</a> <input
					type="hidden" id="mailHidden" name="mail"
					value="${author.mail}" />
</div>
<div>
				名字:<input type="text" id="realName" value="${author.realName}"
					disabled="disabled" /> <a id="realNameMdf"
					onclick="Modify('realName')">修改</a> <a id="realNameConf"
					onclick="Config('realName')" hidden="hidden">确认</a> <a id="realNameCac"
					onclick="Cancel('realName')" hidden="hidden">取消</a> <input
					type="hidden" id="realNameHidden" name="realName"
					value="${author.realName}" />
</div>
<div>
				地址:<input type="text" id="address" value="${author.address}"
					disabled="disabled" /> <a id="addressMdf"
					onclick="Modify('address')">修改</a> <a id="addressConf"
					onclick="Config('address')" hidden="hidden">确认</a> <a id="addressCac"
					onclick="Cancel('address')" hidden="hidden">取消</a> <input
					type="hidden" id="addressHidden" name="address"
					value="${author.address}" />
</div>
<div>
				身份证:<input type="text" id="idcard" value="${author.idcard}"
					disabled="disabled" /> <a id="idcardMdf"
					onclick="Modify('idcard')">修改</a> <a id="idcardConf"
					onclick="Config('idcard')" hidden="hidden">确认</a> <a id="idcardCac"
					onclick="Cancel('idcard')" hidden="hidden">取消</a> <input
					type="hidden" id="idcardHidden" name="idcard"
					value="${author.idcard}" />
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
					var urlStr="<%=request.getContextPath()%>/cms/modifyauthorinfo";
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