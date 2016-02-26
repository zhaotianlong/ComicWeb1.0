<%@page import="model.User"%>
<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<aside id="modifyInfo" class="right" >
			<div class="profile" style="width: 100%;">
				<h1>修改资料</h1>
				<div class="profileContent">
				<form:form modelAttribute="UserInfoModel" action="modifyinfo/do" cssClass="profileContent" id="modifyform">
					<div><form:errors path="*"></form:errors></div>
					<div>账号:<span><%= ((User)request.getSession().getAttribute("manager")).getAccountId()%></span></div>
					<div>
						昵称:<input type="text" id="nickName"  value="${UserInfoModel.nickName}" disabled="disabled" />
						<a id="nickNameMdf" onclick="Modify('nickName')">修改</a>
						<a id="nickNameConf" onclick="Config('nickName')" hidden="hidden">确认</a>
						<a id="nickNameCac" onclick="Cancel('nickName')" hidden="hidden">取消</a>
						<input type="hidden" id="nickNameHidden" name="nickName" value="${UserInfoModel.nickName}" />
					</div>
					<div>邮箱:<input type="text" id="mail" value="${UserInfoModel.mail}" disabled="disabled" />
						<a id="mailMdf" onclick="Modify('mail')">修改</a>
						<a id="mailConf" onclick="Config('mail')" hidden="hidden">确认</a>
						<a id="mailCac" onclick="Cancel('mail')" hidden="hidden">取消</a>
						<input type="hidden" id="mailHidden" name="mail" value="${UserInfoModel.mail}" />
					</div>
					<div>生日:<input type="date" id="born"  value="${UserInfoModel.born}" disabled="disabled"/>
						<a id="bornMdf" onclick="Modify('born')">修改</a>
						<a id="bornConf" onclick="Config('born')" hidden="hidden">确认</a>
						<a id="bornCac" onclick="Cancel('born')" hidden="hidden">取消</a>
						<input type="hidden" id="bornHidden" name="born" value="${UserInfoModel.born}" />
					</div>
					<div>手机:<input type="text" id="tel" name="tel" value="${UserInfoModel.tel}" disabled="disabled"/>
						<a id="telMdf" onclick="Modify('tel')">修改</a>
						<a id="telConf" onclick="Config('tel')" hidden="hidden">确认</a>
						<a id="telCac" onclick="Cancel('tel')" hidden="hidden">取消</a>
						<input type="hidden" id="telHidden" name="tel" value="${UserInfoModel.tel}" />
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
				alert("id:"+inputId+"  "+"value:"+str[inputId]);
				$("#"+inputId+"Hidden").val(str[inputId]);
				$("#"+inputId+"Mdf").show();
				$("#"+inputId+"Conf").hide();
				$("#"+inputId+"Cac").hide();
			}
				function Reset(){
					var urlStr="<%=request.getContextPath()%>/cms/modifyinfo";
					$.ajax({
						url : urlStr,
						async : false,
						error : function() {
							alert("ajax出错！");
						},
						success : function(data) {
							$("#usrPanelContent").html(data);
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
			
	</aside>