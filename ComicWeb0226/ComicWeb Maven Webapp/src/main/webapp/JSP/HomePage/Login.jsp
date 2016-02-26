<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/login.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/global.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.1.4.js"></script>
<title>LO米登录</title>
<style type="text/css">
		.loginClass{
			background-color: #5C356C;
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
	<script type="text/javascript">
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
</head>
<body class="login_bg">
	<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>

	<div class="login_div">
		<div class="login_title"></div>
		<div id="loginClass" class="loginPanel">
			<a id="visiter" class="loginClass" onclick="VisitorClass();">我是游客</a>
			<a id="author" class="loginNotClass" onclick="AuthorClass();">我是作者</a>
		</div>
		<form:form id="visiterForm"  modelAttribute="LoginModel" action="userdologin" method="post">
			<label>${error}</label>
			<form:errors path="*"></form:errors>
			<div class="input_div">
				<input type="text" placeholder="请输入您的用户名" name="account" />
				<div class="icon_div userIcon"></div>
			</div>
			<div class="input_div">
				<input type="password" placeholder="请键入您的密码" name="password" />
				<div class="icon_div passwordIcon"></div>
			</div>
			<div class="other">
				<div class="other_div">
					<input type="checkbox" /><span>记住登录状态</span><span
						class="otherLine"></span>
				</div>
				<div class="other_div">
					<a href="">忘记密码？</a><span class="otherLine"></span>
				</div>
				<div class="other_div">
					<a href="">新用户注册</a>
				</div>
			</div>
			<input type="submit" value="登录" />
			<div class="thirdPartyLine"></div>
			<div class="thirdParty">
				<a href="" class="thirdParty_bg1"></a> <a href=""
					class="thirdParty_bg2"></a>
			</div>
		</form:form>
		<form:form id="authorForm"  modelAttribute="LoginModel" action="authordologin" method="post" cssStyle="display:none;">
			<label>${error}</label>
			<form:errors path="*"></form:errors>
			<div class="input_div">
				<input type="text" placeholder="请输入您的用户名" name="account" />
				<div class="icon_div userIcon"></div>
			</div>
			<div class="input_div">
				<input type="password" placeholder="请键入您的密码" name="password" />
				<div class="icon_div passwordIcon"></div>
			</div>
			<div class="other">
				<div class="other_div">
					<input type="checkbox" /><span>记住登录状态</span><span
						class="otherLine"></span>
				</div>
				<div class="other_div">
					<a href="">忘记密码？</a><span class="otherLine"></span>
				</div>
				<div class="other_div">
					<a href="">新用户注册</a>
				</div>
			</div>
			<input type="submit" value="登录" />
			<div class="thirdPartyLine"></div>
			<div class="thirdParty">
				<a href="" class="thirdParty_bg1"></a> <a href=""
					class="thirdParty_bg2"></a>
			</div>
		</form:form>
		
	</div>
</body>
</html>