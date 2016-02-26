<%@page import="model.User"%>
<%@page import="model.vo.ComicTrendModel"%>
<%@ page language="java" import="java.util.List" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	User u=(User)session.getAttribute("user");
	String nickName=u.getNickName();
	String id=u.getAccountId();
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
		var urlStr="<%=path%>/personcenter/"+id;
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
	function TabRemoveClass(){
		$("#userChoose .active").removeClass("active");
	}
</script>
</head>
<body class="personCenter">
	<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>
	<div style="height:40px;"></div>
	<div class="container">
		<aside class="left">
			<div class="userArea">
				<div class="userIcon">
					<img
						src="<%=iconPath%>">
				</div>
				<div class="nickName">昵称:<%=nickName %></div>
				<div class="userId">账户:<%=id %></div>
			</div>
			<div id="userChoose" class="userChoose">
				<ul id="userChoose">
					<li onclick="tab('index');TabRemoveClass();this.className='active'"> <span class="personchooseIcon1"></span> <span>我的个人中心</span>
					</li>
					<li onclick="tab('message');TabRemoveClass();this.className='active'"> <span class="personchooseIcon2"></span> <span>消息中心</span>
					</li>
					<li onclick="tab('lovecomic');TabRemoveClass();this.className='active'"> <span class="personchooseIcon3"></span> <span>订阅作品</span>
					</li>
					<li onclick="tab('lovecomicer');TabRemoveClass();this.className='active'"> <span class="personchooseIcon3"></span> <span>作者关注</span>
					</li>
					 <li onclick="tab('modifyinfo');TabRemoveClass();this.className='active'"><span class="personchooseIcon6"></span> <span>修改资料</span>
					</li>
					 <li onclick="tab('modifypassword');TabRemoveClass();this.className='active'"> <span class="personchooseIcon6"></span> <span>修改密码</span>
					</li>
					<li onclick="tab('modifyicon');TabRemoveClass();this.className='active'"> <span class="personchooseIcon7"></span> <span>修改头像</span>
					</li>
				</ul>
			</div>
		</aside>
		<div id="usrPanelContent">
			<jsp:include page="Index.jsp"></jsp:include>
		</div>
		<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
	</div>

</body>
</html>
