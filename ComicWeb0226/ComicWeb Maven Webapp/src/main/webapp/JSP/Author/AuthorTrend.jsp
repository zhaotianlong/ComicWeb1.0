<%@page import="java.util.List"%>
<%@page import="model.vo.ComicTrendModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/timelineAu.css">
<div class="timeLineNew">
		<section id="cd-timeline" class="cd-container">
			<c:forEach var="i" begin="0" end="${comictrends.size()-1}" step="1" >
				<c:choose>
					<c:when test="${i%2==0}">
						<div class="cd-timeline-block">
							<div class="cd-timeline-img cd-movie">
								<img src="<%=request.getContextPath() %>/img/author/cd-icon-movie.svg" alt="Movie">
							</div>
							<div class="cd-timeline-content">
								<img  class="comicImg" src="<%=request.getContextPath() %>/${comictrends.get(i).getAuthorIcon()}">
								<span class="cd-date">${comictrends.get(i).getDateLine()}  ${comictrends.get(i).getDes()}</span>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="cd-timeline-block">
							<div class="cd-timeline-img cd-picture">
								<img src="<%=request.getContextPath() %>/img/author/cd-icon-picture.svg" alt="Picture">
							</div>
							<div class="cd-timeline-content">
								 <span class="cd-date">${comictrends.get(i).getDateLine()}  ${comictrends.get(i).getDes()}</span>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<div class="cd-timeline-block">
				<div class="cd-timeline-img cd-movie">
					<img src="<%=request.getContextPath() %>/img/author/cd-icon-location.svg" alt="Location">
				</div>
				<div class="authortrendMore" onclick="NextPage()">查看更多动态</div>
			</div>
		</section>
	</div>
<script type="text/javascript">
function NextPage(){
	var urlStr="<%=request.getContextPath()%>/author/authortrend/${comictrend_currentpage}";
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