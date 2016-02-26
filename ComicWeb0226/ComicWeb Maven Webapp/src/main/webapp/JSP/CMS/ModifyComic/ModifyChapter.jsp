<%@page import="model.Comic"%>
<%@page import="model.Author"%>
<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside id="loveComic" class="right">
	<div class="subscription" style="width: 100%;">
		<h1>章节管理</h1>
		<div class="subscriptionContent">
			<div class="subscriptionTitle">
				<span>共${part_total}话</span>
				<a class="textRight" onclick="AddComicPart('${comicId}')">新增</a>
			</div>
			
			<table>
				<tr>
					<th class="td_1"><input type="checkbox" /></th>
					<th class="th_1">序列</th>
					<th class="th_3" id="lo">操作</th>
				</tr>
				<c:forEach var="comicpart" items="${comicparts}">
					<tr>
						<td class="td_1"><input type="checkbox" /></td>
						<td class="th_1">第${comicpart.id}话</td>
						<td class="th_3"><a onclick="ModifyComicPage('${comicpart.partId}')">修改</a><a onclick="CancelAllPage('${comicpart.partId}')">清空a</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="subscriptionLine"></div>
		<div class="subscriptionFooter">
			<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a> <a
				onclick="PrePage()" class="button buttonWhite">上一页</a> <select
				id="selectPage" onchange="SelectedPage();">
				<c:forEach var="i" begin="1" end="${part_sum}" step="1">
					<c:choose>
						<c:when test="${i==part_currentPage}">
							<option value="${i}" selected="selected">${i}</option>
						</c:when>
						<c:otherwise>
							<option value="${i}">${i}</option>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</select> <a onclick="NextPage()" class="button buttonWhite">下一页</a> <a
				onclick="GoOnePage('${part_sum}')" class="button buttonWhite">尾页</a>
		</div>
	</div>
<script type="text/javascript">
function CancelAllPage(id){
	var urlStr="<%=request.getContextPath()%>/cms/cancelcomicpageall";
	var currentNum = $("#selectPage").val();
	$.ajax({
		url : urlStr,
		async : false,
		data:{"partId":id,"currentNum":currentNum},
		error : function() {
			alert("ajax出错！");
		},
		success : function(data) {
			$("#usrPanelContent").html(data);
		}
	});
}

function AddComicPart(id){
	
	var urlStr="<%=request.getContextPath()%>/cms/addcomicpart";
		urlStr += "/" + id;
		$.ajax({
			url : urlStr,
			async : false,
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#usrPanelContent").html(data);
			}
		});
	}
function ModifyComicPage(id){
	var urlStr="<%=request.getContextPath()%>/cms/modifycomicpage";
			urlStr += "/" + id;
			$.ajax({
				url : urlStr,
				async : false,
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#usrPanelContent").html(data);
				}
			});
}
function SelectedPage() {
	var urlStr="<%=request.getContextPath()%>/cms/modifycomicpart/${comicId}";	
	var currentNum = $("#selectPage").val();
	urlStr+="/"+currentNum;
	$.ajax({
		url : urlStr,
		async : false,
		error : function() {
			alert("ajax出错！");
		},
		success : function(data) {
			$("#usrPanelContent").html(data);
		}
	});
}
function GoOnePage(num){
	var urlStr="<%=request.getContextPath()%>/cms/modifycomicpart/${comicId}";	
	if(num<1)num=1;
	if(num>${part_sum})num=${part_sum};
	var currentNum =num;
		urlStr+="/"+currentNum;
		$.ajax({
			url : urlStr,
			async : false,
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#usrPanelContent").html(data);
			}
		});
}
function PrePage(){
	var urlStr="<%=request.getContextPath()%>/cms/modifycomicpart/${comicId}";	
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
				$("#usrPanelContent").html(data);
			}
		});
	}
}
function NextPage(){
	var urlStr="<%=request.getContextPath()%>/cms/modifycomicpart/${comicId}";	
	var num=$("#selectPage").val();
	if(++num<=${part_sum}){
		var currentNum =num;
		urlStr+="/"+currentNum;
		$.ajax({
			url : urlStr,
			async : false,
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#usrPanelContent").html(data);
			}
		});
	}
}

</script>
</aside>
