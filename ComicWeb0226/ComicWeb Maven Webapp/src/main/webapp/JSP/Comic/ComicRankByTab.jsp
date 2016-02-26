<%@page import="model.vo.RankByTabModel"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOLO漫画网</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/rankList.css">
</script>
</head>
<body>
	<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>
	<div style="height:40px;"></div>
	<div class="container">
		<jsp:include page="/JSP/Layout/navgate.jsp"></jsp:include>
		<div class="navBarAnnote">
			<span class="navBarAnnoteFirst">热门点击:</span> <a href="#">野良神</a> <a
				href="#">一人之下</a> <a href="#">凌舞云姬</a> <a href="#">血魔人</a> <a
				href="#">最终幻想</a> <a href="#">风色幻想</a>
		</div>

		<aside class="left rankLeft">
			<div class="comicListTitle">
				<h1>排行榜</h1>
			</div>
			<ul>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist">人气排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/love">订阅排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/4">科幻排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/8">魔法排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/3">战斗排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/1">冒险排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/6">悬疑排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/2">搞笑排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/5">爱情排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/7">竞技排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/9">历史排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/10">战争排行</a></li>
				<li><a href="<%=request.getContextPath()%>/comic/ranklist/tab/11">仙侠排行</a></li>
			</ul>
		</aside>
		<aside class="right rankRight">
			<div class="comicListTitle">
				<h1>人气排行榜</h1>
			</div>
			<ul id="comicRankSub">
					<%
						List<RankByTabModel> rankByTabModels=(List<RankByTabModel> )request.getAttribute("rankByTabModels");
						for(int i=0;i<rankByTabModels.size();i++)
						{
					%>
					<li>
						<div class="rank_bg1 divInline"><%=i+1%></div>
						<div class="divInline">
							<div class="divInline">
								<img src="<%=request.getContextPath()%>/<%=rankByTabModels.get(i).getSrc()%>" height="120" width="80" />
							</div>
							<div class="rankOne">
								<div class="rankOneFont"><%=rankByTabModels.get(i).getComicName() %></div>
								<ul class="rankOneList">
									<li>作者:<%=rankByTabModels.get(i).getAuthorName() %></li>
									<li>状态:
										<%if(rankByTabModels.get(i).getStatus()==1){%>
										已完结
										<%}else{ %>
										连载中
										<%} %>
									</li>
									<li>题材:<%=rankByTabModels.get(i).getTabs() %></li>
									<li>点赞数:<%=rankByTabModels.get(i).getGood()%></li>
								</ul>
								<p>简介：<%=rankByTabModels.get(i).getDes()%></p>
							</div>
						</div>
						<div class="divInline">
							<div class="gapLine"></div>
						</div>
						<div class="divInline">
							<a class="rankA" href="<%=request.getContextPath()%>/comic/comiclist/<%=rankByTabModels.get(i).getComicId()%>">
								<div class="rank_bg2"></div>
								<div>开始阅读</div>
							</a>
						</div>
					</li>
				<%} %>
			</ul>
		</aside>
		<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
	</script>

</body>
</html>