<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.vo.RankByTabModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	List<RankByTabModel> rankByTabModels = (List<RankByTabModel>) request
			.getAttribute("rankByTabModels");
	for (int i = 0; i < rankByTabModels.size(); i++) {
%>
<li>
	<div class="rank_bg1 divInline"><%=i + 1%></div>
	<div class="divInline">
		<div class="divInline">
			<img
				src="<%=request.getContextPath()%>/<%=rankByTabModels.get(i).getSrc()%>"
				height="120" width="80" />
		</div>
		<div class="rankOne">
			<div class="rankOneFont"><%=rankByTabModels.get(i).getComicName()%></div>
			<ul class="rankOneList">
				<li>作者:<%=rankByTabModels.get(i).getAuthorName()%></li>
				<li>状态: <%
					if (rankByTabModels.get(i).getStatus() == 1) {
				%> 已完结 <%
					} else {
				%>
					连载中 <%
					}
				%>
				</li>
				<li>题材:<%=rankByTabModels.get(i).getTabs()%></li>
				<li>点赞数:<%=rankByTabModels.get(i).getGood()%></li>
			</ul>
			<p>
				简介：<%=rankByTabModels.get(i).getDes()%></p>
		</div>
	</div>
	<div class="divInline">
		<div class="gapLine"></div>
	</div>
	<div class="divInline">
		<a class="rankA"
			href="<%=request.getContextPath()%>/comic/comiclist/<%=rankByTabModels.get(i).getComicId()%>">
			<div class="rank_bg2"></div>
			<div>开始阅读</div>
		</a>
	</div>
</li>
<%
	}
%>