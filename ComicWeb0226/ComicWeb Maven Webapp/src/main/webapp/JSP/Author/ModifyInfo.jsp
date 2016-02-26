<%@page import="model.Author"%>
<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<aside id="modifyInfo" class="right" >
			<div class="profile">
				<h1>修改资料</h1>
				<h5><form:errors></form:errors></h5>
				<form:form modelAttribute="AuthorInfoModel" action="modifyinfo/do" cssClass="profileContent" id="modifyform">
					<div>账号:<span><%= ((Author)request.getSession().getAttribute("author")).getAuthorId()%></span></div>
					<div>
						昵称:<input type="text" id="nickName" name="nickName" value="${AuthorInfoModel.realName}" disabled="disabled" />
						<a id="nickNameMdf" onclick="Modify('nickName','nickNameMdf','nickNameCac')">修改</a>
						<a id="nickNameCac" onclick="Cancel('nickName','nickNameMdf','nickNameCac')" hidden="hidden">取消</a>
					</div>
					<div>邮箱:<input type="text" id="mail" name="mail" value="${AuthorInfoModel.mail}" disabled="disabled" />
						<a id="mailMdf" onclick="Modify('mail','mailMdf','mailCac')">修改</a>
						<a id="mailCac" onclick="Cancel('mail','mailMdf','mailCac')" hidden="hidden">取消</a>
					</div>
					<div>性别:<input type="radio" name="gender">
						<c:choose>
							<c:when test="${AuthorInfoModel.gender}==W">
								<span>男</span><input type="radio" name="gender" checked="checked" value="M">
							</c:when>
							<c:otherwise>
								<span>女</span><input type="radio" name="gender"  checked="checked" value="W">
							</c:otherwise>
						</c:choose>
					</div>
					<div>生日:<input type="date" id="born" name="born" value="${AuthorInfoModel.born}" disabled="disabled"/>
						<a id="bornMdf" onclick="Modify('born','bornMdf','bornCac')">修改</a>
						<a id="bornCac" onclick="Cancel('born','bornMdf','bornCac')" hidden="hidden">取消</a>
					</div>
					<div>手机:<input type="text" id="tel" name="tel" value="${AuthorInfoModel.tel}" disabled="disabled"/>
						<a id="telMdf" onclick="Modify('tel','telMdf','telCac')">修改</a>
						<a id="telCac" onclick="Cancel('tel','telMdf','telCac')" hidden="hidden">取消</a>
					</div>
					<div>地址:<input type="text" id="address" name="address" value="${AuthorInfoModel.address}" disabled="disabled"/>
						<a id="addressMdf" onclick="Modify('address','addressMdf','addressCac')">修改</a>
						<a id="addressCac" onclick="Cancel('address','addressMdf','addressCac')" hidden="hidden">取消</a>
					</div>
					<div>身份证:<input type="text" id="idcard" name="idcard" value="${AuthorInfoModel.idcard}" disabled="disabled"/>
						<a id="idcardMdf" onclick="Modify('idcard','idcardMdf','idcardCac')">修改</a>
						<a id="idcardCac" onclick="Cancel('idcard','idcardMdf','idcardCac')" hidden="hidden">取消</a>
					</div>
					<input type="submit" class="button buttonYellowGreen" value="提交">
					<a onclick="Reset();" class="button buttonOrange">重置</a>
				</form:form>
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
					var urlStr="<%=request.getContextPath()%>/author/modifyinfo";
					$.ajax({
						url : urlStr,
						async : false,
						error : function() {
							alert("ajax出错！");
						},
						success : function(data) {
							$("#modifyInfo").html(data);
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