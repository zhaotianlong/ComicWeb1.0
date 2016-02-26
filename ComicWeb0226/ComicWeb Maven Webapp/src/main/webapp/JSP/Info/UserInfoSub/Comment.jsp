<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<div class="messageCenter">
		<h1>Ta的评论</h1>
		<!--收件箱/发件箱-->
		<div id="messageSub">
			<div class="commentsDiv">
	<div class="comments">
		<c:forEach var="comment" items="${comments}">
		<div class="comment">
			<a class="comment-img">
				<img src="<%=request.getContextPath()%>/${comment.comicIconSrc}" alt="" width="50" height="50">
				<div>确认查阅</div>
			</a>
			<div class="comment-body">
				<div class="text">
					<p>${comment.des}</p>
				</div>
				<p class="attribution">在 <a href="#non">${comment.comicName}</a>时间:${comment.newDate} </p>
			</div>
		</div>
		</c:forEach>
	</div>
</div>
	
</div>
<div class="subscriptionFooter">
	<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a> <a
		onclick="PrePage()" class="button buttonWhite">上一页</a> <select
		id="selectPage" onchange="SelectedPage();">
		<c:forEach var="i" begin="1" end="${comments_sum}" step="1">
			<c:choose>
				<c:when test="${i==comments_currentpage}">
					<option value="${i}" selected="selected">${i}</option>
				</c:when>
				<c:otherwise>
					<option value="${i}">${i}</option>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</select> 
	<a onclick="NextPage()" class="button buttonWhite">下一页</a> <a
		onclick="GoOnePage(${comments_sum})" class="button buttonWhite">尾页</a>
</div>
<script type="text/javascript">
function SelectedPage() { 
	var urlStr="<%=request.getContextPath()%>/info/userinfo/comment/${userid}";	
		var currentNum = $("#selectPage").val();
			urlStr+="/"+currentNum;
			$.ajax({
				url : urlStr,
				async : false,
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#messageSub").html(data);
				}
			});
		}
function GoOnePage(num){
	var urlStr="<%=request.getContextPath()%>/info/userinfo/comment/${userid}";	
	if(num<1)num=1;
	if(num>${comments_sum})num=${comments_sum};
	var currentNum =num;
		urlStr+="/"+currentNum;
		$.ajax({
			url : urlStr,
			async : false,
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#messageSub").html(data);
			}
		});
}
function PrePage(){
	var urlStr="<%=request.getContextPath()%>/info/userinfo/comment/${userid}";	
	var num=$("#selectPage").val();
	if(--num>0){
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
				$("#messageSub").html(data);
			}
		});
	}
}
function NextPage(){
	var urlStr="<%=request.getContextPath()%>/info/userinfo/comment/${userid}";		
	var num=$("#selectPage").val();
	if(++num<=${comments_sum}){
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
				$("#messageSub").html(data);
			}
		});
	}
	
}		
</script>
			
		</div>
	</div>
