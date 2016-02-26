<%@page import="model.Comic"%>
<%@page import="model.Author"%>
<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="subscription" style="width: 100%;">
		<h1>章节管理</h1>
	<div class="subscriptionContent">
	<form  name="fileImage" action="<%=request.getContextPath()%>/cms/addcomicpagelist" enctype="multipart/form-data" method="post">
		<input type="hidden" value="${comicId}" name="comicId" />
		<div>
			<span>章节名称:</span><input type="text" value="" name="partName">
		</div>
		<div>
			<a onclick="InsertPage()">添加章节</a>
		</div>
		<div id="imglist">
		</div>
		<input type="submit" value="上传"  />
	</form>
	</div>
</div>
<script type="text/javascript">
	var arr=0;
	function InsertPage(){
			var str="<div id='div"+arr+"'> <input type='file' id='file"+arr+"' name='fileImage' onchange='GetFilePath(\""+arr+"\")' /> "
			str+="<img id='crop_preview"+arr+"' src weight='100' height='70' />"
			str+="<a onclick='DeleteImg(\"div"+arr+"\")'>删除</a> </div>";
			$("#imglist").append(str);
			arr++;
	}
	function DeleteImg(id){
		$("#"+id).remove();
	}
	
	
	function GetFilePath(id) {

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
	
</script>