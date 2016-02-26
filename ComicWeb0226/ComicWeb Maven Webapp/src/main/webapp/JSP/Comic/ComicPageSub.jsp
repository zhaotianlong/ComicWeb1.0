<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="subscriptionFooter">
	<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a> <a
		onclick="PrePage()" class="button buttonWhite">上一页</a> <select
		id="selectPage" onchange="SelectedPage();">
		<c:forEach var="i" begin="1" end="${page_sum}" step="1">
			<c:choose>
				<c:when test="${i==page_currentPage}">
					<option value="${i}" selected="selected">${i}</option>
				</c:when>
				<c:otherwise>
					<option value="${i}">${i}</option>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</select> <a onclick="NextPage()" class="button buttonWhite">下一页</a> <a
		onclick="GoOnePage('${page_sum}')" class="button buttonWhite">尾页</a>
</div>
<div class="comicImg">
	<img src="<%=request.getContextPath()%>/${comicPath}" />
</div>
<div class="subscriptionFooter">
	<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a> <a
		onclick="PrePage()" class="button buttonWhite">上一页</a> <select
		id="selectPage1" onchange="SelectedPage1();">
		<c:forEach var="i" begin="1" end="${page_sum}" step="1">
			<c:choose>
				<c:when test="${i==page_currentPage}">
					<option value="${i}" selected="selected">${i}</option>
				</c:when>
				<c:otherwise>
					<option value="${i}">${i}</option>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</select> <a onclick="NextPage()" class="button buttonWhite">下一页</a> <a
		onclick="GoOnePage('${page_sum}')" class="button buttonWhite">尾页</a>
</div>


<script type="text/javascript">

function SelectedPage() {
	var currentNum = $("#selectPage").val();
	var comicId=$("#comicId").val();
	var comicPartId=$("#comicPartId").val();
	var urlStr="<%=request.getContextPath()%>/comic/comiclist/"+comicId+"/"+comicPartId+"/"+currentNum;		
	$.ajax({
			url : urlStr,
			async : false,
			data:{},
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#pageSub").html(data);
			}
		});
	}


function SelectedPage1() {
	var currentNum = $("#selectPage1").val();
	var comicId=$("#comicId").val();
	var comicPartId=$("#comicPartId").val();
	var urlStr="<%=request.getContextPath()%>/comic/comiclist/"+comicId+"/"+comicPartId+"/"+currentNum;		
	$.ajax({
			url : urlStr,
			async : false,
			data:{},
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#pageSub").html(data);
			}
		});
	}
	
function GoOnePage(num){
	if(num<1)num=1;
	if(num>${page_sum})num=${page_sum};
	var currentNum =num;
	var comicId=$("#comicId").val();
	var comicPartId=$("#comicPartId").val();
	var urlStr="<%=request.getContextPath()%>/comic/comiclist/"+comicId+"/"+comicPartId+"/"+currentNum;			
		$.ajax({
			url : urlStr,
			async : false,
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#pageSub").html(data);
			}
		});
}

function PrePage(){
	
	var num=$("#selectPage").val();
	if(--num>0){
		var currentNum =num;
		var comicId=$("#comicId").val();
		var comicPartId=$("#comicPartId").val();
		var urlStr="<%=request.getContextPath()%>/comic/comiclist/"+comicId+"/"+comicPartId+"/"+currentNum;			
		$.ajax({
			url : urlStr,
			async : false,
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#pageSub").html(data);
			}
		});
	}
}
function NextPage(){
		var num = $("#selectPage").val();
		if (++num <= ${page_sum}) {
			var currentNum = num;
			var comicId=$("#comicId").val();
			var comicPartId=$("#comicPartId").val();
			var urlStr="<%=request.getContextPath()%>/comic/comiclist/"+comicId+"/"+comicPartId+"/"+currentNum;			
			$.ajax({
				url : urlStr,
				async : false,
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#pageSub").html(data);
				}
			});
		}

	}
</script>