<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<aside id="loveComicer" class="right">
			<div class="inerestWriter" style="width: 100%;">
				<h1>吐槽管理</h1>
				<div class="inerestWriterContetnt" style="width: 100%;">
					<div class="interestwTitle">共<span class="fontRed">${spitslot_total}</span>条 评论     <a onclick="openPopup('openModal2')">新增吐槽</a></div>
					<table>
						<tr>
							<th class="td_1"><input type="checkbox" /></th>
							<th class="th_1">吐槽id</th>
							<th class="th_2">吐槽内容</th>
							<th class="th_2">漫画章节id</th>
							<th class="th_2">漫画章节名称</th>
							<th class="th_2">漫画id</th>
							<th class="th_2">漫画名</th>
							<th class="th_2">管理</th>
						</tr>
						<c:forEach var="tmp" items="${spitslots}">
						<tr id="${tmp.spitslotId}">
							<th class="td_1"><input type="checkbox" /></th>
							<td class="th_1" title="${tmp.spitslotId}">${tmp.spitslotId}</td>
							<td class="th_2" title="${tmp.spitSlotDes}">${tmp.spitSlotDes}</td>
							<td class="th_2" title="${tmp.partId.partId}">${tmp.partId.partId}</td>
							<th class="th_2" title="${tmp.partId.partName}">${tmp.partId.partName}</th>
							<th class="th_2" title="${tmp.partId.comic.comicId}">${tmp.partId.comic.comicId}</th>
							<th class="th_2" title="${tmp.partId.comic.comicName}">${tmp.partId.comic.comicName}</th>
							<th class="th_2">
								<a onclick="ModifyCommentInfo('${tmp.spitslotId}');openPopup('openModal1')">修改</a>
								<a onclick="CancelComment('${tmp.spitslotId}')">删除</a>
							</th>
						</tr>
						</c:forEach>
					</table>
					<div class="subscriptionFooter">
						<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a>
						<a onclick="PrePage()" class="button buttonWhite">上一页</a> 
						<select id="selectPage" onchange="SelectedPage();">
						<c:forEach var="i" begin="1" end="${spitslot_sum}" step="1">
							<c:choose>
								<c:when test="${i==spitslot_currentPage}">
									<option value="${i}" selected="selected">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select>
					 	<a onclick="NextPage()" class="button buttonWhite">下一页</a>
						<a onclick="GoOnePage(${spitslot_sum})" class="button buttonWhite">尾页</a>
					</div>
				</div>
			</div>
<div id="openModal1" class="modalDialog">
	<div>
		<div onclick="closePopup('openModal1')" title="Close" class="close">X</div>
		<div>修改吐槽</div>
		<form:form  modelAttribute="CmsModifyCommentModel" action="modifyspitslot/do" cssClass="profileContent" id="modifyform">
					<input type="hidden" name="spitslotId" id="param1"  />
			<div class="modifyDiv">
				<span>吐槽内容:</span>	
					<textarea cols="40" rows="10" id="param2"
					disabled="disabled"></textarea>
					 <a id="param2Mdf"
					onclick="Modify('param2')">修改</a> <a id="param2Conf"
					onclick="Config('param2')" hidden="hidden">确认</a> <a id="param2Cac"
					onclick="Cancel('param2')" hidden="hidden">取消</a> <input
					type="hidden" id="param2Hidden" name="spitSlotDes"
					value="" />
			</div>
			<div class="modifyDiv">
				<span>章节ID :  </span>
				<input type="text" id="param3" value=""
					disabled="disabled" /> <a id="param3Mdf"
					onclick="Modify('param3')">修改</a> <a id="param3Conf"
					onclick="Config('param3')" hidden="hidden">确认</a> <a id="param3Cac"
					onclick="Cancel('param3')" hidden="hidden">取消</a> <input
					type="hidden" id="param3Hidden" name="partId"
					value="" />
			</div>
				<div class="modifyDiv" style="text-align: center;">
				<input type="submit" value="提交" class="button" />
			</div>
			</form:form >
	</div>
</div>
<div id="openModal2" class="modalDialog">
	<div>
		<div onclick="closePopup('openModal2')" title="Close" class="close">X</div>
						<div>新增吐槽</div>
				<form:form  modelAttribute="CmsModifyCommentModel" action="createspitslot/do" cssClass="profileContent" id="modifyform">
			<div class="modifyDiv">
				<span>吐槽内容:</span>			
					<textarea cols="40" rows="10" id="spitSlotDes"
					disabled="disabled"></textarea>
					<a id="spitSlotDesMdf"
					onclick="Modify('spitSlotDes')">修改</a> <a id="spitSlotDesConf"
					onclick="Config('spitSlotDes')" hidden="hidden">确认</a> <a id="spitSlotDesCac"
					onclick="Cancel('spitSlotDes')" hidden="hidden">取消</a> <input
					type="hidden" id="spitSlotDesHidden" name="spitSlotDes"
					value="" />
			</div>
			<div class="modifyDiv"><span>章节 ID:  </span>
				<input type="text" id="partId" value=""
					disabled="disabled" /> <a id="partIdMdf"
					onclick="Modify('partId')">修改</a> <a id="partIdConf"
					onclick="Config('partId')" hidden="hidden">确认</a> <a id="partIdCac"
					onclick="Cancel('partId')" hidden="hidden">取消</a> <input
					type="hidden" id="partIdHidden" name="partId"
					value="" />
			</div>
			<div class="modifyDiv" style="text-align: center;">
				<input type="submit" value="提交" class="button" />
			</div>
				</form:form >
	</div>
</div>				
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
		var urlStr="<%=request.getContextPath()%>/cms/spitslotmanage";	
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
		var urlStr="<%=request.getContextPath()%>/cms/spitslotmanage";	
		if(num<1)num=1;
		if(num>${spitslot_sum})num=${spitslot_sum};
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
		var urlStr="<%=request.getContextPath()%>/cms/spitslotmanage";	
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
		var urlStr="<%=request.getContextPath()%>/cms/spitslotmanage";	
		var num=$("#selectPage").val();
		if(++num<=${spitslot_sum}){
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
	
	function ModifyCommentInfo(id){
		var count=1;
		$("#"+id+" >td").each(function() {
			$("#param"+count).val($(this).html());
			$("#param"+count+"Hidden").val($(this).html());
			count++;
		})
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
	
	function CancelComment(id){
		var urlStr="<%=request.getContextPath()%>/cms/cancelspitslot";	
		var num=$("#selectPage").val();
		$.ajax({
				url : urlStr,
				async : false,
				data:{"spitslotId":id,"currentNum":num},
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#usrPanelContent").html(data);
				}
			});
	}
	
</script>	
			
		</aside>
		
		