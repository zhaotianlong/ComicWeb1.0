<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOLO漫画网</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/global.css">
</head>
<body class="index">
	<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>
	<div style="height:40px;"></div>
	<div class="container">
		<aside class="error404"></aside>
		<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
	</div>

</body>
</html>