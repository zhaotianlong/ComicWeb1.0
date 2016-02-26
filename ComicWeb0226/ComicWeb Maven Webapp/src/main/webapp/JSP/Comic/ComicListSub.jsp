<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
	<c:forEach var="comic" items="${comicList}">
		<li>
			<div class="subscriptionOne">
			<a href="" class="imgLink"><img src=""></a>
			<a href="" class="fontDes">${comic.comicName}</a>
			<div class="fontDes">更新时间：<span class="fontRed">${comic.partDate}</span></div>
			<div class="fontDes">作者：<span class="fontRed">${comic.authorName}</span></div>
			<div class="fontDes">状态：<span class="fontRed">
				<c:choose>
					<c:when test="${comic.comicstatus==1}">
						已完结
					</c:when>
					<c:otherwise>
						连载中
					</c:otherwise>
				</c:choose>
			</span></div>
			<div class="fontDes">类别：<span class="fontRed"></span></div>
			<div class="">
				<a href="<%=request.getContextPath()%>/comic/comiclist/${comic.comicId}" class=" button buttonOrange">开始阅读</a>
				<a href="" class="button buttonBlue">详细信息</a>
			</div>
		</div>
	</li>
	</c:forEach>
</ul>
<div class="subscriptionFooter">
	<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a> <a
		onclick="PrePage()" class="button buttonWhite">上一页</a> <select
		id="selectPage" onchange="SelectedPage();">
		<c:forEach var="i" begin="1" end="${comic_sum}" step="1">
			<c:choose>
				<c:when test="${i==comic_currentPage}">
					<option value="${i}" selected="selected">${i}</option>
				</c:when>
				<c:otherwise>
					<option value="${i}">${i}</option>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</select> <a onclick="NextPage()" class="button buttonWhite">下一页</a> <a
		onclick="GoOnePage('${comic_sum}')" class="button buttonWhite">尾页</a>
</div>
<script type="text/javascript">
	function SelectedPage() {
	var urlStr="<%=request.getContextPath()%>/comic/comiclistpage";	
	var currentNum = $("#selectPage").val();
		//alert(currentNum);
		$.ajax({
			url : urlStr,
			async : false,
			data:{"status":arr['status'],"charge":arr['charge'],"type":arr['type'],"initial":arr['initial'],"order":arr['order'],"page":currentNum},
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#comicListSub").html(data);
			}
		});
	}
	function GoOnePage(num){
		var urlStr="<%=request.getContextPath()%>/comic/comiclistpage";	
		if(num<1)num=1;
		if(num>${comic_sum})num=${comic_sum};
		var currentNum =num;
			$.ajax({
				url : urlStr,
				async : false,
				data:{"status":arr['status'],"charge":arr['charge'],"type":arr['type'],"initial":arr['initial'],"order":arr['order'],"page":currentNum},
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#comicListSub").html(data);
				}
			});
	}
	function PrePage(){
		var urlStr="<%=request.getContextPath()%>/comic/comiclistpage";	
		var num=$("#selectPage").val();
		if(--num>0){
			var currentNum =num;
			$.ajax({
				url : urlStr,
				async : false,
				data:{"status":arr['status'],"charge":arr['charge'],"type":arr['type'],"initial":arr['initial'],"order":arr['order'],"page":currentNum},
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#comicListSub").html(data);
				}
			});
		}
	}
	function NextPage(){
		var urlStr="<%=request.getContextPath()%>/comic/comiclistpage";	
		var num=$("#selectPage").val();
		if(++num<=${comic_sum}){
			var currentNum =num;
			$.ajax({
				url : urlStr,
				data:{"status":arr['status'],"charge":arr['charge'],"type":arr['type'],"initial":arr['initial'],"order":arr['order'],"page":currentNum},
				async : false,
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#comicListSub").html(data);
				}
			});
		}
	}
	
</script>
