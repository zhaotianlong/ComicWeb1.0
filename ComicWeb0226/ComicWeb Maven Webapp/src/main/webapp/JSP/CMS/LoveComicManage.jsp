<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<aside id="loveComicer" class="right">
			<div class="inerestWriter" style="width: 100%;">
				<h1>订阅管理</h1>
				<div class="inerestWriterContetnt">
					<div class="interestwTitle">共<span class="fontRed">${usercomic_total}</span>条 评论     <a onclick="openPopup('openModal2')">新增订阅</a></div>
					<table>
						<tr>
							<th class="td_1"><input type="checkbox" /></th>
							<th class="th_1">订阅id</th>
							<th class="th_2">用户id</th>
							<th class="th_2">用户名</th>
							<th class="th_2">漫画id</th>
							<th class="th_2">漫画名</th>
							<th class="th_2">管理</th>
						</tr>
						<c:forEach var="tmp" items="${usercomics}">
						<tr id="${tmp.id}">
							<th class="td_1"><input type="checkbox" /></th>
							<td class="th_1">${tmp.id}</td>
							<td class="th_2">${tmp.userId.accountId}</td>
							<th class="th_2">${tmp.userId.nickName}</th>
							<td class="th_2">${tmp.comicId.comicId}</td>
							<th class="th_2">${tmp.comicId.comicName}</th>
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
						<c:forEach var="i" begin="1" end="${usercomic_sum}" step="1">
							<c:choose>
								<c:when test="${i==usercomic_currentPage}">
									<option value="${i}" selected="selected">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					 	<a onclick="NextPage()" class="button buttonWhite">下一页</a>
						<a onclick="GoOnePage(${usercomic_sum})" class="button buttonWhite">尾页</a>
					</div>
				</div>
			</div>
			
			<div id="openModal1" class="modalDialog">
	<div>
		<div onclick="closePopup('openModal1')" title="Close" class="close">X</div>
		<div>修改订阅</div>
						<form action="modifyusercomic/do" class="profileContent" id="modifyform">
				<input type="hidden" name="id" id="param1"  />
			<div class="modifyDiv">
				<span>用户ID:</span>	
				<input type="text" id="param2" value=""
					disabled="disabled" /> <a id="param2Mdf"
					onclick="Modify('param2')">修改</a> <a id="param2Conf"
					onclick="Config('param2')" hidden="hidden">确认</a> <a id="param2Cac"
					onclick="Cancel('param2')" hidden="hidden">取消</a> <input
					type="hidden" id="param2Hidden" name="userId"
					value="" />
			</div>
			<div class="modifyDiv">
				<span>漫画ID:</span>
				<input type="text" id="param3" value=""
					disabled="disabled" /> <a id="param3Mdf"
					onclick="Modify('param3')">修改</a> <a id="param3Conf"
					onclick="Config('param3')" hidden="hidden">确认</a> <a id="param3Cac"
					onclick="Cancel('param3')" hidden="hidden">取消</a> <input
					type="hidden" id="param3Hidden" name="comicId"
					value="" />
			</div>
			<div class="modifyDiv" style="text-align: center;">
				<input type="submit" value="提交" class="button" />
			</div>
				</form >
	</div>
</div>
<div id="openModal2" class="modalDialog">
	<div>
		<div onclick="closePopup('openModal2')" title="Close" class="close">X</div>
									<div>新增订阅</div>
				<form action="createspitslot/do" class="profileContent" id="modifyform">
			<div class="modifyDiv">
				<span>用户ID:</span>	
				<input type="text" id="userId" value=""
					disabled="disabled" /> <a id="userIdMdf"
					onclick="Modify('userId')">修改</a> <a id="userIdConf"
					onclick="Config('userId')" hidden="hidden">确认</a> <a id="userIdCac"
					onclick="Cancel('userId')" hidden="hidden">取消</a> <input
					type="hidden" id="userIdHidden" name="userId"
					value="" />
			</div>
			<div class="modifyDiv">
				<span>漫画ID:</span>
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
				</form >
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
		var urlStr="<%=request.getContextPath()%>/cms/usercomicmanage";	
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
		var urlStr="<%=request.getContextPath()%>/cms/usercomicmanage";	
		if(num<1)num=1;
		if(num>${usercomic_sum})num=${usercomic_sum};
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
		var urlStr="<%=request.getContextPath()%>/cms/usercomicmanage";	
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
		var urlStr="<%=request.getContextPath()%>/cms/usercomicmanage";	
		var num=$("#selectPage").val();
		if(++num<=${usercomic_sum}){
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
		var urlStr="<%=request.getContextPath()%>/cms/cancelusercomic";	
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
		
		