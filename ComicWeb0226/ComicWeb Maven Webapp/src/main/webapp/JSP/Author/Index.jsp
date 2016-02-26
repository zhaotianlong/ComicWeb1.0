<%@page import="model.Author"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	Author u=(Author)session.getAttribute("author");
	String realName=u.getRealName();
	String id=u.getAuthorId();
	String iconPath=u.getIconPath();
	iconPath=path+"/"+iconPath;
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>LOLO漫画网</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/personCenter.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.4.js"></script>
<script type="text/javascript">
	function tab(id){
		var urlStr="<%=path%>/author/"+id;
		$.ajax({
			url:urlStr,
			async:false,
			error:function(){
				alert("ajax出错！");
			},
			success:function(data){
				$("#usrPanelContent").html(data)
			}
		});
	}
</script>
</head>
<body class="personCenter">
	<div class="pageInfo">
		<h2 class="title">个人信息</h2>
		<p>账户:<%=u.getAuthorId() %></p>
		<p>姓名:<%=u.getRealName() %></p>
		<p>出生日期:<%=u.getBornDate() %></p>
		<p>性别:<%if(u.getGender().equals("W")){ %>
			男
			<%}else{ %>
			女
			<%} %>
		</p>
		<p>邮箱:<%=u.getMail() %></p>
		<p>手机:<%=u.getTel() %></p>
		<p>身份证:<%=u.getIdcard() %></p>
		<p>注册日期:<%=u.getResisterDate() %></p>
		<p>住址:<%=u.getAddress() %></p>
	</div>
	<div style="height:40px;"></div>
	<div class="container">
		<aside class="left">
			<div class="userArea">
				<div class="userIcon">
					<img
						src="<%=iconPath%>">
				</div>
				<div class="nickName">昵称:<%=realName%></div>
				<div class="userId">账户:<%=id %></div>
			</div>
			<div class="userChoose">
				<ul>
					<li><a onclick="tab('home');"> <span class="personchooseIcon1"></span> <span>我的个人中心</span>
					</a></li>
					<li><a onclick="tab('createcomic')"> <span class="personchooseIcon3"></span> <span>新建作品</span>
					</a></li>
					<li><a onclick="tab('comicmanage')"> <span class="personchooseIcon3"></span> <span>作品管理</span>
					</a></li>
					<li><a onclick="tab('modifyinfo')"> <span class="personchooseIcon6"></span> <span>修改资料</span>
					</a></li>
					<li><a onclick="tab('modifypassword')"> <span class="personchooseIcon6"></span> <span>修改密码</span>
					</a></li>
					<li><a onclick="tab('modifyicon')"> <span class="personchooseIcon7"></span> <span>修改头像</span>
					</a></li>
					<li><a href="<%=request.getContextPath()%>/homepage/logout"> <span class="personchooseIcon7"></span> <span>退出</span>
					</a></li>
				</ul>
			</div>
		</aside>
		<div id="usrPanelContent">
			<jsp:include page="Home.jsp"></jsp:include>
		</div>
		<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
	</div>
</body>
</html>
