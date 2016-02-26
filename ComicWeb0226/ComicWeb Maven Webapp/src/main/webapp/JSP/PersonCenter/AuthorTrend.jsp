<%@page import="java.util.List"%>
<%@page import="model.vo.ComicTrendModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/timeline.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/grid.css">

<div class="dcondition">
					<h1>作者动态</h1>
				</div>
				<div class="page1">
					<div class="main">
						<div class="content">
							<div class="container_12">
									
								<c:if test="${comictrends!=null}">
								<div class="grid_6">
									<c:forEach var="i" begin="0" end="${comictrend_total-1}" step="1">
		  							<c:choose>
											<c:when test="${i==0}">
												<div class="p1_box leftTimeLine cl1>">
										<div class="type">
											<img alt="" src="<%=request.getContextPath()%>/${comictrends.get(i).getAuthorIcon()}">
										</div>
										<img src="<%=request.getContextPath()%>/${comictrends.get(i).getComicSrc()}" alt="">
										<a href="<%=request.getContextPath()%>/comic/comiclist/${comictrends.get(i).getComicId()}" class="bot"><div class="des"><strong>${comictrends.get(i).getDes()}</strong><br />
										<date>${comictrends.get(i).getDateLine()}</date>
										</div><span>
										<div>作者</div>
										<br />
										<div>${comictrends.get(i).getAuthorName()}</div>
										</span></a>
									</div>
											</c:when>
											<c:when test="${i==2}">
												<div class="p1_box leftTimeLine cl3>">
										<div class="type">
											<img alt="" src="<%=request.getContextPath()%>/${comictrends.get(i).getAuthorIcon()}">
										</div>
										<img src="<%=request.getContextPath()%>/${comictrends.get(i).getComicSrc()}" alt="">
										<a href="<%=request.getContextPath()%>/comic/comiclist/${comictrends.get(i).getComicId()}" class="bot"><div class="des"><strong>${comictrends.get(i).getDes()}</strong><br />
										<date>${comictrends.get(i).getDateLine()}</date>
										</div><span>
										<div>作者</div>
										<br />
										<div>${comictrends.get(i).getAuthorName()}</div>
										</span></a>
									</div>
											</c:when>
										</c:choose>
									</c:forEach>
								</div>
								<div class="grid_6">
								<c:forEach var="i" begin="0" end="${comictrend_total-1}" step="1">
									<c:choose>
										<c:when test="${i==1}">
											<div class="p1_box rightTimeLine cl2>">
										<div class="type">
											<img alt="" src="<%=request.getContextPath()%>/${comictrends.get(i).getAuthorIcon()}">
										</div>
										<img src="<%=request.getContextPath()%>/${comictrends.get(i).getComicSrc()}" alt="">
										<a href="<%=request.getContextPath()%>/comic/comiclist/${comictrends.get(i).getComicId()}" class="bot"><div class="des"><strong>${comictrends.get(i).getDes()}</strong><br />
										<date>${comictrends.get(i).getDateLine()}</date>
										</div><span>
										<div>作者</div>
										<br />
										<div>${comictrends.get(i).getAuthorName()}</div>
										</span></a>
									</div>
										</c:when>
										<c:when test="${i==3}">
										<div class="p1_box rightTimeLine cl4>">
										<div class="type">
											<img alt="" src="<%=request.getContextPath()%>/${comictrends.get(i).getAuthorIcon()}">
										</div>
										<img src="<%=request.getContextPath()%>/${comictrends.get(i).getComicSrc()}" alt="">
										<a href="<%=request.getContextPath()%>/comic/comiclist/${comictrends.get(i).getComicId()}" class="bot"><div class="des"><strong>${comictrends.get(i).getDes()}</strong><br />
										<date>${comictrends.get(i).getDateLine()}</date>
										</div><span>
										<div>作者</div>
										<br />
										<div>${comictrends.get(i).getAuthorName()}</div>
										</span></a>
									</div>
										</c:when>
									</c:choose>
									</c:forEach>
									</c:if>
								</div>
								<div class="clear"></div>
								<div class="grid_12">
									<a onclick="NextPage()" class="round">查看更多动态</a>
								</div>
							</div>
						</div>
					</div>
				</div>
<script type="text/javascript">
function NextPage(){
	var urlStr="<%=request.getContextPath()%>/personcenter/comicTrend/${comictrend_currentpage}";
	var num=${comictrend_currentpage};
	if(++num<=${comictrend_sum}){
		$.ajax({
			url : urlStr,
			async : false,
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#comicTrend").html(data);
			}
		});
	}
	
}
</script>
