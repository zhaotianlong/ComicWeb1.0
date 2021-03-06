<%@page import="model.Comic"%>
<%@page import="model.Author"%>
<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
<script type="text/javascript">
var arr = new Array();
$("#modifyInfo").ready(function() {
	var str = "${comicTab}";
	arr = str.split("_");
	for (var i = 0; i < arr.length; i++)
		$("#li" + arr[i]).addClass("active");
	$("#character").val("${comic.initial}");
});
var arr = new Array();
$("#modifyInfo").ready(function() {
	var str = "${comicTab}";
	arr = str.split("_");
	for (var i = 0; i < arr.length; i++)
		$("#li" + arr[i]).addClass("active");
	$("#character").val("${comic.initial}");
});

function ClickTab(id,tabid){
	if($("#"+id).hasClass("active"))
		{
			var urlStr="<%=request.getContextPath()%>/cms/canceltab";
			$("#"+id).removeClass("active");
			$.ajax({
				url : urlStr,
				async : false,
				data:{"comicid":"${comic.comicId}","tabid":tabid},
				error : function() {
					alert("ajax出错！");
				},
				success : function() {
				}
			});
		}
	else
		{
			var urlStr="<%=request.getContextPath()%>/cms/inserttab";
			$("#"+id).addClass("active");
			$.ajax({
				url : urlStr,
				async : false,
				data:{"comicid":"${comic.comicId}","tabid":tabid},
				error : function() {
					alert("ajax出错！");
				},
				success : function() {
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
	str[inputId]=$("#"+inputId).val();
	$("#"+inputId+"Hidden").val(str[inputId]);
	$("#"+inputId+"Mdf").show();
	$("#"+inputId+"Conf").hide();
	$("#"+inputId+"Cac").hide();
}

function Reset(){
	var urlStr="<%=request.getContextPath()%>/cms/modifyinfo";
$.ajax({
	url : urlStr,
	async : false,
	error : function() {
		alert("ajax出错！");
	},
	success : function(data) {
		$("#modifyInfo").html(data);
	}
});
}

function GetFilePath() {
	var addr = null;
	var docObj = document.getElementById("imgUpLoad");

	var imgObjPreview = document.getElementById("preview");
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

function PreView(){
	var addr=GetFilePath();
	$("#preview").attr("src",addr);
}

function ImgUpload(){
	$.ajaxFileUpload({
		url:'<%=request.getContextPath()%>/cms/modifycomicicon',
		secureuri:false,
		fileElementId:'imgUpLoad',
		dataType: 'json', 
		data:{"comicId":"${comic.comicId}"},
	    success: function (data, status)            //相当于java中try语句块的用法
            {   
			 	alert('上传成功');
            },
            error: function (data, status, e)            //相当于java中catch语句块的用法
            {
            	
			 	//alert('上传失败'); 此处有问题
            }  
		
		
	});
}



</script>
<aside id="modifyInfo" class="right">
	<div class="profile" style="width: 100%;">
		<h1>修改资料</h1>
			<div class="profileContent">
		<form:form modelAttribute="Comic" action="modifycomicinfo/do" cssClass="profileContent" id="modifyform">
			<input type="hidden" name="comicId" value="${comic.comicId}" />
			<div>
				漫画名:<input type="text" id="comicName" value="${comic.comicName}"
					disabled="disabled" /> <a id="comicNameMdf"
					onclick="Modify('comicName')">修改</a> <a id="comicNameConf"
					onclick="Config('comicName')" hidden="hidden">确认</a> <a id="comicNameCac"
					onclick="Cancel('comicName')" hidden="hidden">取消</a> <input
					type="hidden" id="comicNameHidden" name="comicName"
					value="${comic.comicName}" />
			</div>
			<div>
				漫画介绍:
				<textarea cols="15" rows="5" id="description" name="description"
					disabled="disabled">${comic.description}</textarea>
					<a id="descriptionConf"
					onclick="Config('description')" hidden="hidden">确认</a>
				<a id="descriptionMdf" onclick="Modify('description')">修改</a> <a
					id="descriptionCac" onclick="Cancel('description')" hidden="hidden">取消</a>
				<input type="hidden" id="descriptionHidden" name="description"
					value="${comic.description}" />
			</div>
			<div>
				收费:
				<c:choose>
					<c:when test="${comic.charge==1}">
						<span>是</span>
						<input type="radio" name="charge" checked="checked" value="1">
						<span>否</span>
						<input type="radio" name="charge" value="0">
					</c:when>
					<c:otherwise>
						<span>是</span>
						<input type="radio" name="charge" value="1">
						<span>否</span>
						<input type="radio" name="charge" checked="checked" value="0">
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				状态:
				<c:choose>
					<c:when test="${comic.comicStatus==0}">
						<span>连载中</span>
						<input type="radio" name="comicStatus" checked="checked" value="0">
						<span>已完结</span>
						<input type="radio" name="comicStatus" value="1">
					</c:when>
					<c:otherwise>
						<span>连载中</span>
						<input type="radio" name="comicStatus" value="0">
						<span>已完结</span>
						<input type="radio" name="comicStatus" checked="checked" value="1">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="authorComicTab">
				<ul id="type" class="test">
					<li><a id="li1" onclick="ClickTab('li1','1')">冒险</a></li>
					<li><a id="li2" onclick="ClickTab('li2','2')">搞笑</a></li>
					<li><a id="li3" onclick="ClickTab('li3','3')">战斗</a></li>
					<li><a id="li4" onclick="ClickTab('li4','4')">科幻</a></li>
					<li><a id="li5" onclick="ClickTab('li5','5')">爱情</a></li>
					<li><a id="li6" onclick="ClickTab('li6','6')">侦探</a></li>
					<li><a id="li7" onclick="ClickTab('li7','7')">竞技</a></li>
					<li><a id="li8" onclick="ClickTab('li8','8')">魔法</a></li>
					<li><a id="li9" onclick="ClickTab('li9','9')">历史</a></li>
					<li><a id="li10" onclick="ClickTab('li10','10')">战争</a></li>
					<li><a id="li11" onclick="ClickTab('li11','11')">仙侠</a></li>

				</ul>
			</div>
			<div>
				<select id="character" name="initial">
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
						<option value="E">E</option>
						<option value="F">F</option>
						<option value="G">G</option>
						<option value="H">H</option>
						<option value="I">I</option>
						<option value="J">J</option>
						<option value="K">K</option>
						<option value="L">L</option>
						<option value="M">M</option>
						<option value="N">N</option>
						<option value="O">O</option>
						<option value="P">P</option>
						<option value="Q">Q</option>
						<option value="R">R</option>
						<option value="S">S</option>
						<option value="T">T</option>
						<option value="U">U</option>
						<option value="V">V</option>
						<option value="W">W</option>
						<option value="X">X</option>
						<option value="Y">Y</option>
						<option value="Y">Y</option>
						<option value="Z">Z</option>
				</select>
			</div>
			<div>
				<img src="<%=request.getContextPath()%>/${comic.src}" id="preview" height="100px" weight="7px"> 
				<input type="file" id="imgUpLoad" name="imgUpLoad" onchange="PreView()" />
				<a onclick="ImgUpload()">上传</a>
			</div>
			
			<input type="submit" class="button buttonYellowGreen" value="提交" />
			<a onclick="Reset();" class="button buttonOrange">重置</a>
		</form:form>
		</div>
	</div>
</aside>