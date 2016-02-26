<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="commentList">
	<c:forEach var="comment" items="${comments}">
		<div class="commentListOne">
			<img src="<%=request.getContextPath()%>/${comment.userId.iconPath}" />
			<div class="commentListOne_top">
				<span class="textLeft fontBlue name">${comment.userId.nickName}</span>
				<span class="textRight fontGray"></span>
			</div>
			<div class="commentListOne_top">${comment.commentDes}</div>
			<div class="commentListOne_top">
				<span class="textLeft time">${comment.dateTime}</span>
				<a onclick="CommentBackDisplay('${comment.id}','${comment.userId.accountId}')" class="textRight fontGray">
					<span class="iconSmall1"></span>
					<span>回复</span>
				</a>
			</div>
		</div>
		<c:forEach var="commentSub" items="${comment.commentSubs}">
			<div class="commentListOne commentListTwo ">
				<img src="<%=request.getContextPath()%>/${commentSub.fromId.iconPath}" />
				<div class="commentListOne_top">
					<span class="textRight fontBlue name">to:cvbnm,${commentSub.toId.nickName}</span>
				</div>
				<div class="commentListOne_top">${commentSub.commentDes}</div>
				<div class="commentListOne_top">
					<span class="textLeft time">${commentSub.upDate}</span>
					 <a  onclick="CommentBackDisplay('${comment.id}','${comment.userId.accountId}')" class="textRight fontGray">
					 	<span class="iconSmall1"></span>
					 	<span>回复</span>
					</a>
				</div>
			</div>
		</c:forEach>
		<div id="${comment.id}" class="commentListOne commentListTwo" style="display:none">
				<img src="<%=request.getContextPath()%>/${comment.userId.iconPath}"/>
						<div class="commentListOne_top"><span class="textLeft fontBlue name">LOLO</span><span class="textRight fontGray">278楼</span></div>
						<div class="commentListOne_top">
							<input type="text" value="" class="inputDis"/>
						</div>
						<div class="commentListOne_top">
							<span class="textLeft time"><%=new java.util.Date()%></span>
							<a onclick="CommentBack('${comment.id}')" class="textRight fontGray"><span>确认</span></a>
							<a onclick="CommentBackHide('${comment.id}')" class="textRight fontGray"><span>取消</span></a>
					 </div>
		</div>
	</c:forEach>
</div>
<div class="subscriptionFooter">
	<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a> <a
		onclick="PrePage()" class="button buttonWhite">上一页</a> <select
		id="selectPage" onchange="SelectedPage();">
		<c:forEach var="i" begin="1" end="${comment_sum}" step="1">
			<c:choose>
				<c:when test="${i==comment_currentPage}">
					<option value="${i}" selected="selected">${i}</option>
				</c:when>
				<c:otherwise>
					<option value="${i}">${i}</option>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</select> <a onclick="NextPage()" class="button buttonWhite">下一页</a> <a
		onclick="GoOnePage(${comment_sum})" class="button buttonWhite">尾页</a>
</div>
<script type="text/javascript">
//回复只能点击一次
var toId;
function CommentBackDisplay(id,id2){
		toId=id2;
		$("#"+id).show();
}
function CommentBackHide(id){
		$("#"+id+"  input").val('');
		toid
		$("#"+id).hide();
}
function CommentBack(comentId){
	var urlStr="<%=request.getContextPath()%>/comic/insertcommentback";
	//id="ld"不能被页面识别
	var comment=$("#"+comentId+"  input").val();
	$.ajax({
		url : urlStr,
		async : false,
		data:{"comment":comment,"commentId":comentId,"toId":toId},
		error : function() {
			alert("ajax出错！");
		},
		success : function(data) {
			window.location.href=window.location.href;
		}
	});
}


function SelectedPage() {
	var urlStr="<%=request.getContextPath()%>/comic/commentpage";	
	var currentNum = $("#selectPage").val();
	var id=$("#comicId").val();
		$.ajax({
			url : urlStr,
			async : false,
			data:{"comicId":id,"page":currentNum},
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#commicComment").html(data);
			}
		});
	}
function GoOnePage(num){
	var urlStr="<%=request.getContextPath()%>/comic/commentpage";	
	var id=$("#comicId").val();
	if(num<1)num=1;
	if(num>${comment_sum})num=${comment_sum};
	var currentNum =num;
		$.ajax({
			url : urlStr,
			async : false,
			data:{"comicId":id,"page":currentNum},
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#commicComment").html(data);
			}
		});
}

function PrePage(){
	var urlStr="<%=request.getContextPath()%>/comic/commentpage";	
	var id=$("#comicId").val();
	var num=$("#selectPage").val();
	if(--num>0){
		var currentNum =num;
		$.ajax({
			url : urlStr,
			async : false,
			data:{"comicId":id,"page":currentNum},
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#commicComment").html(data);
			}
		});
	}
}
function NextPage(){
	var urlStr="<%=request.getContextPath()%>/comic/commentpage";
	var id=$("#comicId").val();
		var num = $("#selectPage").val();
		if (++num <= ${comment_sum}) {
			var currentNum = num;
			$.ajax({
				url : urlStr,
				async : false,
				data:{"comicId":id,"page":currentNum},
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#commicComment").html(data);
				}
			});
		}

	}
</script>
