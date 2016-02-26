<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta charset="UTF-8">
 <base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/global.css">
<title>LO米登录</title>
</head>
<body class="login_bg">
	<header class="top">
	<div class="top_container">
		<div class="top_grid">
			<a class="grid_wrap">
				<div class="mobileImg">
					<img src="icon/web16.png">
					
				</div>
				<div class="webFont">LOLO网</div>
				<div></div>
			</a> <a class="grid_wrap">
				<div class="mobileImg">
					<img src="icon/shopingcart16.png">
				</div>
				<div class="shopingcartFont">周边</div>
				<div class="mobile3"></div>
			</a> <a class="grid_wrap">
				<div class="mobileImg">
					<img src="icon/share16.png">
				</div>
				<div class="shareFont">分享到</div>
				<div class="mobile3"></div>
			</a>
		</div>
		<div class="top_grid">
			<a href="" class="grid_wrap regist">注册</a> <a href=""
				class="grid_wrap login">登录</a>
		</div>
	</div>
	</header>
	<div class="login_div">
		<div class="login_title"></div>
		<form>
			<div class="input_div">
				<input type="text" placeholder="请输入您的用户名" />
				<div class="icon_div userIcon"></div>
			</div>
			<div class="input_div">
				<input type="password" placeholder="请键入您的密码" />
				<div class="icon_div passwordIcon"></div>
			</div>
			<div class="other">
				<div class="other_div">
					<input type="checkbox" /><span>记住登录状态</span><span class="otherLine"></span>
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
		</form>
	</div>
</body>
</html>
