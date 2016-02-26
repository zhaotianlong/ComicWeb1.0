<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>LOLO漫画网</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/global.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/comic.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.4.js"></script>
	
</head>
<body class="index">
	<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>
	<div style="height:40px;"></div>
	<div class="container">
		<div class="breadTab">
			<a href="">首页</a><span>></span>
			<a href="">少年漫画</a><span>></span>
			<span class="curComic"><a href="<%=request.getContextPath()%>/comic/comiclist/${comicId}">${comicName}</a></span>
		</div>
		<aside class="comic">
			<div class="comicTitle">${comicName}、${comicPart}<input id="comicId" type="hidden" value="${comicId}" /><input id="comicPartId" type="hidden" value="${comicPartId}" /></div>
			<div><a href="<%=request.getContextPath()%>/comic/comiclist/${comicId}/${comicPrePartId}" class="textLeft">上一话:${comicPrePartName}</a><a href="<%=request.getContextPath()%>/comic/comiclist/${comicId}/${comicNextPartId}" class="textRight">下一话:${comicNextPartName}</a></div>
			<div class="comicLine"></div>
			<div id="pageSub">
				<jsp:include page="/JSP/Comic/ComicPageSub.jsp"></jsp:include>
			</div>
			<div class="spitslot">
				<div class="spitslotTitle">吐槽集中营</div>
				<div class="spitslotList">
					<c:forEach var="spitSlot" items="${spitSlots}">
						<div>${spitSlot.spitSlotDes}</div>
					</c:forEach>
					<form action="<%=request.getContextPath()%>/comic/insertspitslot" method="post" onsubmit="return CheckNull()">
						<span>不吐不快：</span>
						<input type="text" id="des" name="des" value=""  />
						<input type="hidden" name="comicId" value="${comicId}" />
						<input type="hidden" name="partId" value="${comicPartId}"  />
						<input type="submit" value="Go"/>
					</form>
				</div>
			</div>
		</aside>
		<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
		function CheckNull(){
			if($("#des").val()==""){
				alert("请输入你的槽！");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>