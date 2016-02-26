<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/login.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.1.4.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/additional-methods.js"></script>

<script type="text/javascript">
	$().ready(
			function() {
				$("#resistForm").validate({
					rules : {
						account : {
							required : true,
							maxlength : 12,
							minlength : 8,
							chrnum : true
							//remote:{
								//type:"post",
								//url:/<%=path%>/homepage/CheckEuqals
								//data:{account:function(){return $('#account').val();}}
							//}
						},
						password : {
							required : true,
							maxlength : 20,
							minlength : 8,
							chrnum : true
						},
						rePassword : {
							required : true,
							chrnum : true,
							equalTo : "#password"
						},
						mail : {
							required : true,
							email : true
						},
						tel: {
							mobile : true
						}
					},
					messages : {
						account : {
							required : "账户不能为空",
							maxlength : "账户至多为12位",
							minlength : "账户至少为8位",
							remote:"用户名已经存在"
						},
						password : {
							required : "密码不能为空",
							maxlength : "密码至多为20位",
							minlength : "密码至少为8位",
						},
						rePassword : {
							required : "确认密码不能为空",
							equalTo : "两次输入密码不相同"
						},
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
	function AuthorClass(){
		$("#author").attr("class","loginClass");
		$("#visiter").attr("class","loginNotClass");
		$("#visiterForm").hide();
		$("#authorForm").show();
	}
	function VisitorClass(){
		$("#visiter").attr("class","loginClass");
		$("#author").attr("class","loginNotClass");
		$("#authorForm").hide();
		$("#visiterForm").show();
	}
</script>
	<style type="text/css">
		.loginClass{
			background-color: #85C5EB;
			color: #efefef;
		}
		.loginNotClass{
			background-color: #efefef;
			color: #666;

		}
		.loginPanel{
			height: 55px;
		}
		.loginPanel a{
			float: left;
			width: 170px;
			text-align: center;
			padding: 15px;
			font-size: 18px;
			cursor: pointer;
		}
	</style>
<style type="text/css">
.error {
	text-align:center;
	color: #ea5200;
}
</style>
<title>LO米注册</title>
</head>
<body class="register_bg">
	<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>
	<div class="login_div" style="background-color:#85C5EB;">
		<div id="loginClass" class="loginPanel">
			<a id="visiter" class="loginClass" onclick="VisitorClass();">我是游客</a>
			<a id="author" class="loginNotClass" onclick="AuthorClass();">我是作者</a>
		</div>
		<form:form  modelAttribute="ResistModel" action="userdoresist" method="post" id="visiterForm">
			<div class="input_div">
				<input type="text" id="account" name="account"  placeholder="请输入您的账户"/>
				<div class="error"><form:errors path="account"></form:errors></div>
			</div>
			<div class="input_div">
				<input type="password" id="password" name="password" placeholder="请键入您的密码" />
				<div class="error"><form:errors path="password"></form:errors></div>
			</div>
			<div class="input_div">
				<input type="password" name="rePassword" placeholder="请确认您的密码" />
				<div class="error"><form:errors path="rePassword"></form:errors></div>
			</div>
			<div class="input_div">
				<input type="email" name="mail" placeholder="请确认您的邮箱" />
				<div class="error"><form:errors path="mail"></form:errors></div>
			</div>
			<div class="input_div">
				<input type="tel" name="tel" placeholder="请确认您的手机" />
				<div class="error"><form:errors path="tel"></form:errors></div>
			</div>
			<div class="input_gender">
				<div class="widthCenter">
					<span>性别</span> 
					<input type="radio" name="gender" checked="checked" value="M">
					<div class="icon_div mIcon"></div>
					<input type="radio" name="gender" value="W">
					<div class="icon_div fIcon"></div>
				</div>
			</div>
			<div class="input_date">
				<div class="widthCenter">
					<span>出生日期</span> <input type="date" name="born" value="1997/01/01" />
				</div>

			</div>
			<div class="other">
				<div class="other_div">
					<input type="checkbox" /><span>记住登录状态</span><span
						class="otherLine"></span>
				</div>
				<div class="other_div">
					<a href="">老用户登录</a>
				</div>
			</div>
			<input type="submit" value="注册" style="background-color:#B7D5F1;" />
			<div class="thirdPartyLine"></div>
			<div class="thirdParty">
				<a href="" class="thirdParty_bg1"></a> <a href=""
					class="thirdParty_bg2"></a>
			</div>
			</form:form>
			<form:form  modelAttribute="ResistModel" action="authordoresist" method="post" id="authorForm" cssStyle="display:none;">
			<form:errors path="*"></form:errors>
			<div class="input_div">
				<input type="text" id="account" name="account"  placeholder="请输入您的账户"/>
				<div class="error"><form:errors path="account"></form:errors></div>
			</div>
			<div class="input_div">
				<input type="password" id="password" name="password" placeholder="请键入您的密码" />
				<div class="error"><form:errors path="password"></form:errors></div>
			</div>
			<div class="input_div">
				<input type="password" name="rePassword" placeholder="请确认您的密码" />
				<div class="error"><form:errors path="rePassword"></form:errors></div>
			</div>
			<div class="input_div">
				<input type="email" name="mail" placeholder="请确认您的邮箱" />
				<div class="error"><form:errors path="mail"></form:errors></div>
			</div>
			<div class="input_div">
				<input type="tel" name="tel" placeholder="请确认您的手机" />
				<div class="error"><form:errors path="tel"></form:errors></div>
			</div>
			<div class="input_gender">
				<div class="widthCenter">
					<span>性别</span> 
					<input type="radio" name="gender" checked="checked" value="M">
					<div class="icon_div mIcon"></div>
					<input type="radio" name="gender" value="W">
					<div class="icon_div fIcon"></div>
				</div>
			</div>
			<div class="input_date">
				<div class="widthCenter">
					<span>出生日期</span> <input type="date" name="born" value="1997/01/01" />
				</div>

			</div>
			<div class="other">
				<div class="other_div">
					<input type="checkbox" /><span>记住登录状态</span><span
						class="otherLine"></span>
				</div>
				<div class="other_div">
					<a href="">老用户登录</a>
				</div>
			</div>
			<input type="submit" value="注册" style="background-color:#B7D5F1;" />
			<div class="thirdPartyLine"></div>
			<div class="thirdParty">
				<a href="" class="thirdParty_bg1"></a> <a href=""
					class="thirdParty_bg2"></a>
			</div>
			</form:form>
	</div>
</body>
</html>
