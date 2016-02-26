<%@page import="model.Comic"%>
<%@page import="model.Author"%>
<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/MutifyFile.css">
<aside class="right">
	<div class="subscription" style="width: 100%;">
		<h1>页管理</h1>
		<div class="subscriptionContent">
			<div class="subscriptionTitle">
				<span>章节名:</span><input type="text" id="partName" value="${partName}" disabled="disabled" />
				 <a id="partNameMdf"
					onclick="Modify('partName')">修改</a> <a id="partNameConf"
					onclick="Config('partName')" style="display: none;">确认</a> <a id="partNameCac"
					onclick="Cancel('partName')" style="display: none;">取消</a>
					<a onclick="openPopup('openModal2')" style="float:right;">批量导入</a>
			</div>
			
			<div class="pageManage">
				<ul>
					<c:forEach var="page" items="${pages}">
						<li><img id="${page.id}" src="<%=request.getContextPath()%>/${page.filePath}" width="80" height="80" />
						<div class="div1">
							<div class="div2">修改</div>
							<input type="file" id="${page.pageId}" onchange="GetFilePath('${page.pageId}','${page.id}')" class="inputstyle" />
						</div>
						<a onclick="UpLoadImg('${page.pageId}')">上传</a>
						<a onclick="CancelImg('${page.pageId}')">删除</a>
						<div></div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
			<div class="subscriptionLine" style="width: 100%;"></div>
			<div class="subscriptionFooter">
				<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a> 
				<a onclick="PrePage()" class="button buttonWhite">上一页</a> 
				<select id="selectPage" onchange="SelectedPage();">
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
				</select> 
				<a  onclick="NextPage()" class="button buttonWhite">下一页</a> 
				<a onclick="GoOnePage('${page_sum}')"  class="button buttonWhite">尾页</a>
			</div>
	</div>
</aside>
		<div id="openModal2" class="modalDialog">
	<div style="top:30%;left:40%;">
		<div onclick="closePopup('openModal2')" title="Close" class="close">X</div>
					<div id="containner" style="width: 900px;">
				<div id="uploadready">
					<form id="upload"  method="POST" enctype="multipart/form-data">
						<h2 style="position:relative">
							<input type="button" value="浏览图片" style=" position:absolute; cursor:pointer; left:45px; top:-8px; width:200px">
							<input style="opacity:0; position:absolute; cursor:pointer; left:30px; top:-8px" name="" type="file" multiple="true" onchange="dropHandler(this.files)"   />
						</h2>
						<div id="dropbox">
							<ul id="pageContent">
							</ul>
							<ul id="uploaded"></ul> 
						</div>
						<input type="button" style="display:none" id="upload_btn" value="上 传" />
						<input type="hidden" id="comicPartId" value="${comicPartId}" />
					</form>
					<input type="hidden" id="url" value="<%=request.getContextPath() %>" />
				</div> 
			</div>
			<div id="uploadView"></div>	
	</div>
</div>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/MutifyFile.js"></script>
<script type="text/javascript">	
var arr=0;

function GetFilePath(id) {
	alert("fucks");
	var addr = null;
	var docObj = document.getElementById("file"+id);
	
	var imgObjPreview = document.getElementById("crop_preview"+id);
	if (docObj.files && docObj.files[0]) {
		//火狐下，直接设img属性
		imgObjPreview.style.display = 'block';
		//imgObjPreview.src = docObj.files[0].getAsDataURL();

		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		addr = imgObjPreview.src;
	} else {
		//IE下，使用滤镜
		docObj.select();
		var imgSrc = document.selection.createRange().text;
		//图片异常的捕捉，防止用户修改后缀来伪造图片
		try {
			alert('2');
			localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters
					.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			addr = imgSrc;
		} catch (e) {
			alert("您上传的图片格式不正确，请重新选择!");
			return false;
		}
		imgObjPreview.style.display = 'none';
		document.selection.empty();
	}
}

function GetFilePath(id,previewId) {
	var addr = null;
	var docObj = document.getElementById(id);

	var imgObjPreview = document.getElementById(previewId);
	if (docObj.files && docObj.files[0]) {
		//火狐下，直接设img属性
		imgObjPreview.style.display = 'block';
		//imgObjPreview.src = docObj.files[0].getAsDataURL();

		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		addr = imgObjPreview.src;
	} else {
		//IE下，使用滤镜
		docObj.select();
		var imgSrc = document.selection.createRange().text;
		//图片异常的捕捉，防止用户修改后缀来伪造图片
		try {
			localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters
					.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			addr = imgSrc;
			//alert(imgSrc);
		} catch (e) {
			alert("您上传的图片格式不正确，请重新选择!");
			return false;
		}
		imgObjPreview.style.display = 'none';
		document.selection.empty();
	}
	return addr;
}

	function CancelImg(id){
		var urlStr="<%=request.getContextPath()%>/cms/cancelpageimg";
		var currentNum = $("#selectPage").val();
		$.ajax({
			url : urlStr,
			async : false,
			data:{"pageId":id,"currentNum":currentNum},
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#usrPanelContent").html(data);
			}
		});
	}
	function UpLoadImg(id){
		var file = document.getElementById(id).files[0];
		var xhr=new XMLHttpRequest();
		var oMyForm = new FormData();
		oMyForm.append("pageId", id);
		oMyForm.append("fileImg",file);
			
		 xhr.open("POST","<%=request.getContextPath()%>/cms/modifypageimg", true);
		 xhr.send(oMyForm); 
	}
	
	function SelectedPage() {
		var urlStr="<%=request.getContextPath()%>/cms/modifycomicpage/${comicPartId}";	
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
		var urlStr="<%=request.getContextPath()%>/cms/modifycomicpage/${comicPartId}";	
		if(num<1)num=1;
		if(num>${page_sum})num=${page_sum};
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
		var urlStr="<%=request.getContextPath()%>/cms/modifycomicpage/${comicPartId}";	
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
		var urlStr="<%=request.getContextPath()%>/cms/modifycomicpage/${comicPartId}";	
		var num=$("#selectPage").val();
		if(++num<=${page_sum}){
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
					$("#usrPanelContent").html(data);
				}
			});
		}
	}
	
	var str=new Object();
	function Modify(inputId){
		$("#"+inputId).removeAttr("disabled");
		str[inputId]=$("#"+inputId).val();
		$("#"+inputId+"Mdf").hide();
		$("#"+inputId+"Conf").show();
		$("#"+inputId+"Cac").show();
	}
	function Cancel(inputId){
		$("#"+inputId).attr("disabled","disabled");
		$("#"+inputId).val(str[inputId]);
		$("#"+inputId+"Mdf").show();
		$("#"+inputId+"Cac").hide();
		$("#"+inputId+"Conf").hide();
	}
	function Config(inputId){
		$("#"+inputId).attr("disabled","disabled");
		var param=$("#"+inputId).val();
		
		var urlStr="<%=request.getContextPath()%>/cms/modifycomicpartname";	
		$.ajax({
			url : urlStr,
			async : false,
			data:{"partId":"${comicPartId}","partName":param},
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#usrPanelContent").html(data);
			}
		});
	}
</script>
