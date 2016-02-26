<%@page import="org.springframework.ui.Model"%>
<%@page import="model.Comic"%>
<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside id="loveComic" class="right">
	<div class="subscription">
		<h1>订阅作品</h1>
		<div class="subscriptionContent">
			<div class="subscriptionTitle">
				<a href="" class="titleLine">全部订阅</a> <a href="">未读作品</a>
			</div>

			<div class="subscriptionComic">
				<c:forEach items="${loveComic}" var="comic">
					<div class="subscriptionOne">
						<a
							href="<%= request.getContextPath() %>/comic/comiclist/${comic.comicId}"
							class="imgLink"><img src="${comic.src}"></a> <a href=""
							class="fontDes">${comic.comicName}</a>
						<div class="fontDes">
							更新内容: <span class="fontRed">第${comic.partNum}话</span>
						</div>
						<div class="fontDes">
							更新时间:<span class="fontRed">${comic.partUpdate }</span>
						</div>
						<div class="">
							<a
								href="<%= request.getContextPath() %>/comic/comiclist/${comic.comicId}"
								class=" button buttonOrange">开始阅读</a>
							<button onclick="CannelLoveComic('${comic.comicId}')"
								class="button buttonBlue">取消订阅</button>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="subscriptionLine"></div>
			<div class="subscriptionFooter">
				<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a> 
				<a onclick="PrePage()" class="button buttonWhite">上一页</a> 
				<select id="selectPage" onchange="SelectedPage();">
					<c:forEach var="i" begin="1" end="${loveComic_sum}" step="1">
						<c:choose>
							<c:when test="${i==loveComic_currentPage}">
								<option value="${i}" selected="selected">${i}</option>
							</c:when>
							<c:otherwise>
								<option value="${i}">${i}</option>
							</c:otherwise>
						</c:choose>

					</c:forEach>
				</select> 
				<a  onclick="NextPage()" class="button buttonWhite">下一页</a> 
				<a onclick="GoOnePage('${loveComic_sum}')"  class="button buttonWhite">尾页</a>
			</div>
		</div>
	</div>
</aside>
<script type="text/javascript">
	function CannelLoveComic(comicId){

		var urlStr="<%=request.getContextPath()%>/personcenter/lovecomic/cannelcomic";
		var currentNum = $("#selectPage").val();
		$.ajax({
			url : urlStr,
			async : false,
			data : {
				"comicId" : comicId,
				"currentNum" : currentNum
			},
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#loveComic").html(data);
			}
		});
	}
	function SelectedPage() {
	var urlStr="<%=request.getContextPath()%>/personcenter/lovecomic";	
	var currentNum = $("#selectPage").val();
		//alert(currentNum);
		urlStr+="/"+currentNum;
		$.ajax({
			url : urlStr,
			async : false,
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#loveComic").html(data);
			}
		});
	}
	function GoOnePage(num){
		var urlStr="<%=request.getContextPath()%>/personcenter/lovecomic";	
		if(num<1)num=1;
		if(num>${loveComic_sum})num=${loveComic_sum};
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
					$("#loveComic").html(data);
				}
			});
	}
	function PrePage(){
		alert("tset");
		var urlStr="<%=request.getContextPath()%>/personcenter/lovecomic";	
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
					$("#loveComic").html(data);
				}
			});
		}
	}
	function NextPage(){
		var urlStr="<%=request.getContextPath()%>/personcenter/lovecomic";	
		alert("tset");
		var num=$("#selectPage").val();
		if(++num<=${loveComic_sum}){
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
					$("#loveComic").html(data);
				}
			});
		}
	}
	
</script>