<%@page import="org.springframework.ui.Model"%>
<%@page import="model.Comic"%>
<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <article class='dos fontawesome-wrench'>
	<div class="subscription">
		<h1>查看作品</h1>
		<div class="subscriptionContent">
			<div class="subscriptionTitle">
				Ta的作品:<span>${comictotal}</span> 部 
			</div>
			<div class="subscriptionComic">
				<c:forEach items="${comics}" var="comic">
					<div class="authorComic">
						<div class="comicIcon">
							<img src="<%=request.getContextPath()%>/${comic.src}" />
						</div>
						<div class="comicInfo">
							<div class="title">
								${comic.comicName}
							</div>
							<div class="content">
								<span>类别:${comic.type}</span>
								<span>状态:
									<c:choose>
										<c:when test="${comic.comicStatus==0}">
											连载中
										</c:when>
										<c:otherwise>
											已完结
										</c:otherwise>
									</c:choose>
								</span>
								<span>章节:${comic.partSum}</span>
								<span>创建时间:${comic.date}</span>
								<span>更新时间:${comic.newUpdate}</span>
								<span>订阅:${comic.loveSum}</span>
								<span>评论:${comic.commentSum}</span>
								<span>点赞:${comic.good}</span>
							</div>
						</div>
						<div class="comicOperation">
							<a onclick="ModifyComic('${comic.comicId}')"  class="buttonBlue">开始阅读</a>
							<a  onclick="ModifyComicPart('${comic.comicId}')"  class="buttonRed">查看详情</a>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="subscriptionLine"></div>
			<div class="subscriptionFooter">
				<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a> 
				<a onclick="PrePage()" class="button buttonWhite">上一页</a> 
				<select id="selectPage" onchange="SelectedPage();">
					<c:forEach var="i" begin="1" end="${comicmage_sum}" step="1">
						<c:choose>
							<c:when test="${i==comicmage_currentPage}">
								<option value="${i}" selected="selected">${i}</option>
							</c:when>
							<c:otherwise>
								<option value="${i}">${i}</option>
							</c:otherwise>
						</c:choose>

					</c:forEach>
				</select> 
				<a  onclick="NextPage()" class="button buttonWhite">下一页</a> 
				<a onclick="GoOnePage('${comicmage_sum}')"  class="button buttonWhite">尾页</a>
			</div>
		</div>
	</div>
<script type="text/javascript">
	function SelectedPage() {
		var urlStr="<%=request.getContextPath()%>/info/authorinfo/comic/${authorid}";	
		var currentNum = $("#selectPage").val();
		urlStr+="/"+currentNum;
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
	function GoOnePage(num){
		var urlStr="<%=request.getContextPath()%>/info/authorinfo/comic/${authorid}";	
		if(num<1)num=1;
		if(num>${comicmage_sum})num=${comicmage_sum};
		var currentNum =num;
			urlStr+="/"+currentNum;
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
	function PrePage(){
		var urlStr="<%=request.getContextPath()%>/info/authorinfo/comic/${authorid}";	
		var num=$("#selectPage").val();
		if(--num>0){
			var currentNum =num;
			urlStr+="/"+currentNum;
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
	}
	function NextPage(){
		var urlStr="<%=request.getContextPath()%>/info/authorinfo/comic/${authorid}";	
		var num=$("#selectPage").val();
		if(++num<=${comicmage_sum}){
			var currentNum =num;
			//alert(currentNum);
			urlStr+="/"+currentNum;
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
	}
	
</script>
</article>