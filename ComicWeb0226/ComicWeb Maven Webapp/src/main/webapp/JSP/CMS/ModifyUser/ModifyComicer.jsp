<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<div class="inerestWriter" style="width: 100%;">
				<h1>他的关注管理</h1>
				<div class="inerestWriterContetnt">
					<div class="interestwTitle">共<span class="fontRed">${loveComicer_total}</span>名 作者     <a onclick="openPopup('openModal2')">添加关注</a></div>
					<table>
						<tr>
							<th class="td_1"><input type="checkbox" /></th>
							<th class="th_1">作者id</th>
							<th class="th_2">作者名</th>
							<th class="th_2">管理</th>
						</tr>
						<c:forEach var="tmp" items="${loveComicer}">
						<tr>
							<td class="td_1"><input type="checkbox" /></td>
							<td class="th_1">${tmp.authorId}</td>
							<td class="th_2">${tmp.athuorName}</td>
							<th class="th_2">
								<a onclick="CannelLoveComic('${tmp.authorId}')">取消关注</a>
							</th>
						</tr>
						</c:forEach>
					</table>
					<div class="subscriptionFooter">
						<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a>
						<a onclick="PrePage()" class="button buttonWhite">上一页</a> 
						<select id="selectPage" onchange="SelectedPage();">
						<c:forEach var="i" begin="1" end="${loveComicer_sum}" step="1">
							<c:choose>
								<c:when test="${i==loveComicer_currentPage}">
									<option value="${i}" selected="selected">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select>
					 	<a onclick="NextPage()" class="button buttonWhite">下一页</a>
						<a onclick="GoOnePage(${loveComicer_sum})" class="button buttonWhite">尾页</a>
					</div>
				</div>
			</div>
			<div id="openModal2" class="modalDialog">
	<div>
		<div onclick="closePopup('openModal2')" title="Close" class="close">X</div>
									<div>新增关注</div>
				<div class="modifyDiv">
				<span>作者ID:</span>
				<input type="text" id="loveComicerId"  />
				</div>
				<div class="modifyDiv" style="text-align: center;">
				<input type="button" onclick="AddLoveComicer()" value="提交" class="button" />
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
			function AddLoveComicer(){
				var authorId=$("#loveComicerId").val();
				var urlStr="<%=request.getContextPath()%>/cms/lovecomicer/addcomicer";
				var currentNum = $("#selectPage").val();
				$.ajax({
					url : urlStr,
					async : false,
					data : {
						"authorId" : authorId,
						"currentNum" : currentNum
					},
					error : function() {
						alert("ajax出错！");
					},
					success : function(data) {
						$("#userSubPanel").html(data);
					}
				});
				
				$("#model").hide();
			}
			
			
			function CannelLoveComic(authorId){
				var urlStr="<%=request.getContextPath()%>/cms/lovecomicer/cancel";
				var currentNum = $("#selectPage").val();
				$.ajax({
					url : urlStr,
					async : false,
					data : {
						"authorId" : authorId,
						"currentNum" : currentNum
					},
					error : function() {
						alert("ajax出错！");
					},
					success : function(data) {
						$("#userSubPanel").html(data);
					}
				});
			}
	
	function SelectedPage() {
		var urlStr="<%=request.getContextPath()%>/cms/lovecomicer";	
		var currentNum = $("#selectPage").val();
			urlStr+="/"+currentNum;
			$.ajax({
				url : urlStr,
				async : false,
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#userSubPanel").html(data);
				}
			});
		}
	function GoOnePage(num){
		var urlStr="<%=request.getContextPath()%>/cms/lovecomicer";	
		if(num<1)num=1;
		if(num>${loveComicer_sum})num=${loveComicer_sum};
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
					$("#userSubPanel").html(data);
				}
			});
	}
	
	function PrePage(){
		var urlStr="<%=request.getContextPath()%>/cms/lovecomicer";	
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
					$("#userSubPanel").html(data);
				}
			});
		}
	}
	function NextPage(){
		var urlStr="<%=request.getContextPath()%>/cms/lovecomicer";	
		var num=$("#selectPage").val();
		if(++num<=${loveComicer_sum}){
			var currentNum =num;
			urlStr+="/"+currentNum;
			$.ajax({
				url : urlStr,
				async : false,
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#userSubPanel").html(data);
				}
			});
		}
		
	}	
</script>	
		
		