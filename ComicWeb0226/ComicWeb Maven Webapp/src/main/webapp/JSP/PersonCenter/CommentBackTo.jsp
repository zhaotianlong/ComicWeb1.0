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
			<th class="th_2">主贴内容</th>
			<th class="th_2">回复内容</th>
			<th class="th_2">回复人</th>
			<th class="th_3">日期</th>
			<th class="th_3" id="lo">操作</th>
		</tr>
		<c:forEach var="comment" items="${comments}">
			<tr>
				<td class="td_1"><input type="checkbox" /></td>
				<td class="th_1">${comment.comicName}</td>
				<td class="th_2">${comment.des}</td>
				<td class="th_2">${comment.comment}</td>
				<td class="th_2">${comment.userName}</td>
				<td class="th_3">${comment.newDate}</td>
				<td class="th_3">
				<a href="<%=request.getContextPath()%>/comic/comiclist/${comment.comicId}">查看</a></td>
			</tr>
		</c:forEach>
	</table>
	-->
	<div class="commentsDiv">
	<section class="comments">
		<c:forEach var="comment" items="${comments}">
		<article class="comment">
			<a class="comment-img">
				<img src="<%=request.getContextPath()%>/${comment.iconPath}" alt="" width="50" height="50" />
				<c:choose>
					<c:when test="${comment.newstatus==0}">
						<div onclick="ReadOver('${comment.subid}')">查阅</div>
					</c:when>
					<c:otherwise>
						<div><img class="readOver" src="<%=request.getContextPath()%>/img/personCenter/ok.png">已查阅</div>
					</c:otherwise>
				</c:choose>
			</a>
			<div class="comment-body">
				<div class="text">
					<p>${comment.des}</p>
					<small>主贴“${comment.comment}”</small>
					<c:if test="${comment.newstatus==0}">
							<img  src="<%=request.getContextPath()%>/img/personCenter/new.png" class="new">
					</c:if>
				</div>
				<p class="attribution">在 <a href="#non">${comment.comicName}中 ${comment.userName}回复了你 </a>时间:${comment.newDate} </p>
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
	var urlStr="<%=request.getContextPath()%>/personcenter/message/commentbackto";	
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
	var urlStr="<%=request.getContextPath()%>/personcenter/message/commentbackto";	
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
	var urlStr="<%=request.getContextPath()%>/personcenter/message/commentbackto";	
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
	var urlStr="<%=request.getContextPath()%>/personcenter/message/commentbackto";	
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
function ReadOver(id){
	var urlStr="<%=request.getContextPath()%>/personcenter/message/commentbackto/updatestatus/"+id;
	$.ajax({
		url : urlStr,
		async : false,
		error : function() {
			alert("ajax出错！");
		},
		success : function(data) {
			alert("设置查阅完毕！");
		}
	});
}		
</script>
