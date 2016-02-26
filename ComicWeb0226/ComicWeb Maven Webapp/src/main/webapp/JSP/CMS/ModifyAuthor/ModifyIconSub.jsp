<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.Jcrop.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/jquery.Jcrop.css">
	<div class="icon" style="width: 100%;">
		<h1>更换头像</h1>
		<div class="iconContent">
			<div class="fontDes">仅支持JPG、GIF、PNG格
				式，文件小于2M。图片质量越高，生成头像越清晰。动态图片上传后将被转换为静态图片。</div>
			<div id="test" class="iconBigImg">
				<img src="" id="srcImg">
			</div>
			<div class="iconImgGroup">
				<div class="iconPreImg" id="preview_box">
					<img src="" id="crop_preview">
				</div>
				<form class="fileUpLoad" action="<%=request.getContextPath()%>/cms/modifyauthoricon/do" enctype="multipart/form-data" method="post">
					<a><input type="file"  id="fileId" name="fileId" onchange="setImagePreview()"  />设置头像</a>
					<input id="x" name="x" type="hidden" />
					<input id="y" name="y" type="hidden" />
					<input id="w" name="w" type="hidden" />
					<input id="h" name="h" type="hidden" />
					<input id="selectW" name="selectW" type="hidden" />
					<input id="selectH" name="selectH" type="hidden" />
					<input type="submit" class="button buttonBlue" value="上传">
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">

		var jcrop_api = null;
		var tmp=null;
		function GetFilePath() {
			var addr = null;
			var docObj = document.getElementById("fileId");

			var imgObjPreview = document.getElementById("crop_preview");
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
		function setImagePreview() {
			var addr = GetFilePath();
			if(addr!=null){
			if (jcrop_api != null){
				DestroyJcrop();
			}
			$("#srcImg").removeAttr("style")
			$("#crop_preview").attr('src', addr);
			$("#srcImg").attr('src', addr);
			InitJcrop();
			}

		}
		$(document).ready();
		function InitJcrop() {
			//记得放在jQuery(window).load(...)内调用，否则Jcrop无法正确初始化
			$("#srcImg").Jcrop({
				onChange : showPreview,
				onSelect : showPreview,
				aspectRatio: 1 ,
				boxWidth : 600,
				boxHeight:600
			}, function() {
				jcrop_api = this;
			});
			//简单的事件处理程序，响应自onChange,onSelect事件，按照上面的Jcrop调用
			function showPreview(coords) {
				if (parseInt(coords.w) > 0) {
					//计算预览区域图片缩放的比例，通过计算显示区域的宽度(与高度)与剪裁的宽度(与高度)之比得到
					//var size=jcrop_api.getWidgetSize();
					var rx = $("#preview_box").width() / coords.w;
					var ry = $("#preview_box").height() / coords.h;
					//通过比例值控制图片的样式与显示
					$("#crop_preview").css({
						width : Math.round(rx * $("#srcImg").width()) + "px", //预览图片宽度为计算比例值与原图片宽度的乘积
						height : Math.round(rx * $("#srcImg").height()) + "px", //预览图片高度为计算比例值与原图片高度的乘积
						marginLeft : "-" + Math.round(rx * coords.x) + "px",
						marginTop : "-" + Math.round(ry * coords.y) + "px"
					});
						var height=parseInt($("#crop_preview").height());
						var weight=parseInt($("#crop_preview").width());
						var heightPre=parseInt($("#preview_box").height());
						var weightPre=parseInt($("#preview_box").width());
						var x=parseInt($("#crop_preview").css("margin-left"));
						var y=parseInt($("#crop_preview").css("margin-top"));
					    $("#x").val(x);
				        $("#y").val(y);
				        $("#w").val(weight);
				        $("#h").val(height);
				        $("#selectW").val(weightPre);
				        $("#selectH").val(heightPre);
				}

			}
		}
		function DestroyJcrop() {
			jcrop_api.destroy();
		}
	</script>