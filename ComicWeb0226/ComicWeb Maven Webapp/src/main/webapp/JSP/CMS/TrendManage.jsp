<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<aside id="loveComicer" class="right">
			<div class="inerestWriter" style="width: 100%;">
				<h1>动态管理</h1>
				<div class="inerestWriterContetnt" style="width: 100%;">
					<div class="interestwTitle">共<span class="fontRed">${comictrend_total}</span>条 评论     <a onclick="openPopup('openModal2')">新增动态</a></div>
					<table>
						<tr>
							<th class="td_1"><input type="checkbox" /></th>
							<th class="th_1">动态id</th>
							<th class="th_2">动态内容</th>
							<th class="th_2">漫画id</th>
							<th class="th_2">漫画名称</th>
							<th class="th_2">作者id</th>
							<th class="th_2">作者名</th>
							<th class="th_2">日期</th>
							<th class="th_2">管理</th>
						</tr>
						<c:forEach var="tmp" items="${comictrends}">
						<tr id="${tmp.id}">
							<th class="td_2"><input type="checkbox" /></th>
							<td class="th_1" title="${tmp.id}">${tmp.id}</td>
							<td class="th_1" title="${tmp.des}">${tmp.des}</td>
							<td class="th_2" title="${tmp.comicId.comicId}">${tmp.comicId.comicId}</td>
							<th class="th_2" title="${tmp.comicId.comicName}">${tmp.comicId.comicName}</th>
							<th class="th_2" title="${tmp.comicId.authorId.authorId}">${tmp.comicId.authorId.authorId}</th>
							<th class="th_2" title="${tmp.comicId.authorId.realName}">${tmp.comicId.authorId.realName}</th>
							<td class="th_1" title="${tmp.dateLine}}">${tmp.dateLine}</th>
							<th class="th_2">
								<a onclick="ModifyCommentInfo('${tmp.id}');openPopup('openModal1')">修改</a>
								<a onclick="CancelComment('${tmp.id}')">删除</a>
							</th>
						</tr>
						</c:forEach>
					</table>
					<div class="subscriptionFooter">
						<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a>
						<a onclick="PrePage()" class="button buttonWhite">上一页</a> 
						<select id="selectPage" onchange="SelectedPage();">
						<c:forEach var="i" begin="1" end="${comictrend_sum}" step="1">
							<c:choose>
								<c:when test="${i==comictrend_currentPage}">
									<option value="${i}" selected="selected">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select>
					 	<a onclick="NextPage()" class="button buttonWhite">下一页</a>
						<a onclick="GoOnePage(${comictrend_sum})" class="button buttonWhite">尾页</a>
					</div>
				</div>
			</div>
<div id="openModal1" class="modalDialog">
	<div>
		<div onclick="closePopup('openModal1')" title="Close" class="close">X</div>
		<div>修改动态</div>
		<form:form  modelAttribute="ComicTrendModel" action="modifyspitslot/do" cssClass="profileContent" id="modifyform">
					<input type="hidden" name="id" id="param1"  />
			<div class="modifyDiv">
				<span>动态内容:</span>	
					<textarea cols="40" rows="10" id="param2"
					disabled="disabled"></textarea>
					 <a id="param2Mdf"
					onclick="Modify('param2')">修改</a> <a id="param2Conf"
					onclick="Config('param2')" hidden="hidden">确认</a> <a id="param2Cac"
					onclick="Cancel('param2')" hidden="hidden">取消</a> <input
					type="hidden" id="param2Hidden" name="des"
					value="" />
			</div>
			<div class="modifyDiv">
				<span>漫画ID :  </span>
				<input type="text" id="param3" value=""
					disabled="disabled" /> <a id="param3Mdf"
					onclick="Modify('param3')">修改</a> <a id="param3Conf"
					onclick="Config('param3')" hidden="hidden">确认</a> <a id="param3Cac"
					onclick="Cancel('param3')" hidden="hidden">取消</a> <input
					type="hidden" id="param3Hidden" name="comicId"
					value="" />
			</div>
			<div class="modifyDiv">
				<span>状态日期 :  </span>
				<input type="date" id="param4" value=""
					disabled="disabled" /> <a id="param4Mdf"
					onclick="Modify('param4')">修改</a> <a id="param4Conf"
					onclick="Config('param4')" hidden="hidden">确认</a> <a id="param4Cac"
					onclick="Cancel('param4')" hidden="hidden">取消</a> <input
					type="hidden" id="param4Hidden" name="dateLine"
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
						<div>新增动态</div>
				<form:form  modelAttribute="ComicTrendModel" action="createspitslot/do" cssClass="profileContent" id="modifyform">
			<div class="modifyDiv">
				<span>动态内容:</span>			
					<textarea cols="40" rows="10" id="des"
					disabled="disabled"></textarea>
					<a id="desMdf"
					onclick="Modify('des')">修改</a> <a id="desConf"
					onclick="Config('des')" hidden="hidden">确认</a> <a id="desCac"
					onclick="Cancel('des')" hidden="hidden">取消</a> <input
					type="hidden" id="desHidden" name="des"
					value="" />
			</div>
			<div class="modifyDiv"><span>漫画 ID:  </span>
				<input type="text" id="comicId" value=""
					disabled="disabled" /> <a id="comicIdMdf"
					onclick="Modify('comicId')">修改</a> <a id="comicIdConf"
					onclick="Config('comicId')" hidden="hidden">确认</a> <a id="comicIdCac"
					onclick="Cancel('comicId')" hidden="hidden">取消</a> <input
					type="hidden" id="comicIdHidden" name="comicId"
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
		var urlStr="<%=request.getContextPath()%>/cms/comictrendmanage";	
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
		var urlStr="<%=request.getContextPath()%>/cms/comictrendmanage";	
		if(num<1)num=1;
		if(num>${comictrend_sum})num=${comictrend_sum};
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
		var urlStr="<%=request.getContextPath()%>/cms/comictrendmanage";	
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
		var urlStr="<%=request.getContextPath()%>/cms/comictrendmanage";	
		var num=$("#selectPage").val();
		if(++num<=${comictrend_sum}){
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
		var urlStr="<%=request.getContextPath()%>/cms/cancelcomictrend";	
		var num=$("#selectPage").val();
		$.ajax({
				url : urlStr,
				async : false,
				data:{"id":id,"currentNum":num},
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
		
		