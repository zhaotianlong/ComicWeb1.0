<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<aside id="loveComicer" class="right">
			<div class="inerestWriter" style="width: 100%;">
				<h1>漫画管理</h1>
				<div class="inerestWriterContetnt">
					<div class="interestwTitle">共<span class="fontRed">${comicList_total}</span>部作品     <a onclick="openPopup('openModal2')">新增漫画</a></div>
					<table>
						<tr>
							<th class="td_1"><input type="checkbox" /></th>
							<th class="th_1">漫画id</th>
							<th class="th_2">漫画名</th>
							<th class="th_2">创建日期</th>
							<th class="th_2">管理</th>
						</tr>
						<c:forEach var="tmp" items="${comics}">
						<tr>
							<td class="td_1"><input type="checkbox" /></td>
							<td class="th_1">${tmp.comicId}</td>
							<td class="th_2">${tmp.comicName}</td>
							<th class="th_2">${tmp.date}</th>
							<th class="th_2">
								<a onclick="ComicModifyPanel('${tmp.comicId}')">修改</a>
								<a>删除</a>
							</th>
						</tr>
						</c:forEach>
					</table>
					<div class="subscriptionFooter">
						<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a>
						<a onclick="PrePage()" class="button buttonWhite">上一页</a> 
						<select id="selectPage" onchange="SelectedPage();">
						<c:forEach var="i" begin="1" end="${comicList_sum}" step="1">
							<c:choose>
								<c:when test="${i==comicList_currentPage}">
									<option value="${i}" selected="selected">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select>
					 	<a onclick="NextPage()" class="button buttonWhite">下一页</a>
						<a onclick="GoOnePage(${comicList_sum})" class="button buttonWhite">尾页</a>
					</div>
				</div>
			</div>
			<div id="openModal2" class="modalDialog">
	<div>
		<div onclick="closePopup('openModal2')" title="Close" class="close">X</div>
						<div>新增漫画</div>
				<form  action="comicmanage/createcomic/do" class="profileContent" id="modifyform" method="post" enctype="multipart/form-data" >
				<div class="modifyDiv">
					<span>漫 画 名:</span>
						 <input type="text" id="comicName"  disabled="disabled" /> 
						 <a id="comicNameMdf" onclick="Modify('comicName')">输入</a> 
						 <a id="comicNameConf" onclick="Config('comicName')" hidden="hidden">确认</a>
						 <a id="comicNameCac" onclick="Cancel('comicName')" hidden="hidden">取消</a>
						 <input type="hidden" id="comicNameHidden" name="comicName" />
				</div>
				<div class="modifyDiv">
					<span>漫画介绍:</span>
					<textarea cols="15" rows="5" id="description" disabled="disabled"></textarea>
					<a id="descriptionConf" onclick="Config('description')" hidden="hidden">确认</a> 
					<a id="descriptionMdf" onclick="Modify('description')">输入</a> 
					<a id="descriptionCac" onclick="Cancel('description')" hidden="hidden">取消</a> 
					<input type="hidden" id="descriptionHidden" name="description" />
				</div>
				<div class="modifyDiv">
					收   费: 
					<span>是</span> <input type="radio" name="charge"  value="1" />
					<span>否</span> <input type="radio" name="charge" checked="checked"  value="0" />
				</div>
				<div class="modifyDiv">
					状     态:
					 <span>连载中</span> <input type="radio" name="comicStatus" checked="checked" value="0" />
					 <span>已完结</span> <input type="radio" name="comicStatus" value="1" />
				</div>
				<div class="authorComicTab modifyDiv">
					标     签:
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
				<div class="modifyDiv">
				首 字 母:
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
				<div class="modifyDiv">
					 <span>作者id:</span>
						 <input type="text" id="authorId"  disabled="disabled"/> 
						 <a id="authorIdMdf" onclick="Modify('authorId')">输入</a> 
						 <a id="authorIdConf" onclick="Config('authorId')" hidden="hidden">确认</a>
						 <a id="authorIdCac" onclick="Cancel('authorId')" hidden="hidden">取消</a>
					     <input type="hidden" id="authorIdHidden" name="authorId"/>
				</div>
				<div class="modifyDiv"><span>漫画封面:</span>
					<img src="about:blank" id="preview" height="100px" weight="7px" alt="请您输入封面" />
				    <a href="javascript:;" class="a-upload"> <input type="file" id="imgUpLoad" name="imgUpLoad" onchange="PreView()" />上传图片</a>
				</div>
				<div class="modifyDiv" style="text-align: center;">
					<input type="submit" class="button " value="提交" />
					<input type="button" onclick="Reset();" class="button " value="重置" />
				</div>
			</form>
	</div>
</div>	
			<script type="text/javascript">
			function openPopup(id) {
				$('#'+id).css({'display': 'block'});
				setTimeout(function() {
					$('#'+id).css({'opacity': '1', 'pointer-events': 'auto'});	
				}, 100);
			}
			function closePopup(id) {
				$('#'+id).css({'opacity': '0', 'pointer-events': 'none'});
				setTimeout(function() {
					$('#'+id).css({'display': 'none'});	
				}, 100);
			};	
	function SelectedPage() {
		var urlStr="<%=request.getContextPath()%>/cms/comicmanage";	
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
		var urlStr="<%=request.getContextPath()%>/cms/comicmanage";	
		if(num<1)num=1;
		if(num>${comicList_sum})num=${comicList_sum};
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
	
	function PrePage(){
		var urlStr="<%=request.getContextPath()%>/cms/comicmanage";	
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
					$("#usrPanelContent").html(data);
				}
			});
		}
	}
	function NextPage(){
		var urlStr="<%=request.getContextPath()%>/cms/comicmanage";	
		var num=$("#selectPage").val();
		if(++num<=${comicList_sum}){
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
	
	function ComicModifyPanel(id){
		var urlStr="<%=request.getContextPath()%>/cms/comicmanage/modifypanel";
		urlStr+="/"+id;
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
	
	function ClickTab(id,tabid){
		if($("#"+id).hasClass("active"))
			{
				$("#"+id).removeClass("active");
				$("#tab"+tabid).remove();
				
			}
		else
			{
				var str="<input type='hidden' name='tab' id='tab"+tabid+"'   value='"+tabid+"' />";
				$("#"+id).addClass("active");
				$("#type").append(str);
			}
	}
	
	var str=new Object();
	function Modify(inputId){
		//有问题！！！！！！！
		alert("okModify "+$("#"+inputId).val());
		$("#"+inputId).removeAttr("disabled");
		str[inputId]=$("#"+inputId).val();
		$("#"+inputId+"Mdf").hide();
		$("#"+inputId+"Conf").show();
		$("#"+inputId+"Cac").show();
	}
	
	function Cancel(inputId){
		alert("okCancel "+inputId);
		$("#"+inputId).attr("disabled","disabled");
		$("#"+inputId).val(str[inputId]);
		$("#"+inputId+"Mdf").show();
		$("#"+inputId+"Cac").hide();
		$("#"+inputId+"Conf").hide();
	}
	
	function Config(inputId){
		alert("okConfig "+inputId);
		$("#"+inputId).attr("disabled","disabled");
		str[inputId]=$("#"+inputId).val();
		$("#"+inputId+"Hidden").val(str[inputId]);
		$("#"+inputId+"Mdf").show();
		$("#"+inputId+"Conf").hide();
		$("#"+inputId+"Cac").hide();
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
			imgObjPreview.src = window.URL
					.createObjectURL(docObj.files[0]);
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

	function PreView() {
		var addr = GetFilePath();
		$("#preview").attr("src", addr);
	}
	
</script>	
</aside>
		
		
