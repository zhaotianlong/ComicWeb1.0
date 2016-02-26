<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="messageBox">
<!-- 
	<div>
		<a href="" class="messageButton"> 全选 </a> <a href=""
			class="messageButton"> 删除 </a>
	</div>
	<table>
		<tr>
			<th class="td_1"><input type="checkbox" /></th>
			<th class="th_1">漫画名</th>
			<th class="th_2">评论内容</th>
			<th class="th_3">日期</th>
			<th class="th_3" id="lo">操作</th>
		</tr>
		<c:forEach var="comment" items="${comments}">
			<tr>
				<td class="td_1"><input type="checkbox" /></td>
				<td class="th_1">${comment.comicName}</td>
				<td class="th_2">${comment.des}</td>
				<td class="th_3">${comment.newDate}</td>
				<td class="th_3">
				<a href="<%=request.getContextPath()%>/comic/comiclist/${comment.comicId}">查看</a><a onclick="CannelCommentBack('${comment.commentId}')">撤回</a></td>
			</tr>
		</c:forEach>
	</table>
 -->
<div class="commentsDiv">
	<section class="comments">
		<c:forEach var="comment" items="${comments}">
		<article class="comment">
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
		</article>
		</c:forEach>
	</section>
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
	var urlStr="<%=request.getContextPath()%>/personcenter/message/comment";	
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
	var urlStr="<%=request.getContextPath()%>/personcenter/message/comment";	
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
	var urlStr="<%=request.getContextPath()%>/personcenter/message/comment";	
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
	var urlStr="<%=request.getContextPath()%>/personcenter/message/comment";	
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
function CannelCommentBack(id){
	var urlStr="<%=request.getContextPath()%>/personcenter/message/comment/cancel";
	urlStr+="/"+id;
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

		
</script>
