<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/font.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/infopanel.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/infopanel.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/global.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/personCenter.css">
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.1.4.js"></script>
  </head>
  
  <body>
  			<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>
			<section>
				<input type="radio" id="profile" value="1" name="tractor" checked='checked' />    
				<input type="radio" id="settings" value="2" name="tractor" />      
				<input type="radio" id="posts" value="3" name="tractor" />
				<input type="radio" id="books" value="4" name="tractor" />

				<nav>   
					<label for="profile" class='fontawesome-camera-retro' onclick="tab('/authorInfo/info/${userid}')"></label>
					<label for="settings" class='fontawesome-film'  onclick="tab('/authorInfo/comic/${userid}')"></label>
				</nav>
				<div id="infoSub" style=" position: initial; ">
					<jsp:include page="/JSP/Info/UserInfoSub/UserInfo.jsp"></jsp:include>
				</div>
			</section>
				<script type="text/javascript">
				function tab(url) {
					var urlStr="<%=request.getContextPath()%>/info/"+url;	
						$.ajax({
							url : urlStr,
							async : false,
							error : function() {
								alert("ajax出错！");
							},
							success : function(data) {
								$("#infoSub").html(data);
							}
						});
					}
				</script>
				<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
			</body>
</html>
