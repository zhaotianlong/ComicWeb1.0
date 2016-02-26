<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<div class="inerestWriter" style="width: 100%;">
				<h1>他的订阅管理</h1>
				<div class="inerestWriterContetnt">
					<div class="interestwTitle">共<span class="fontRed">${loveComic_total}</span>部  漫画      <a onclick="openPopup('openModal2')">添加订阅</a></div>
					<table>
						<tr>
							<th class="td_1"><input type="checkbox" /></th>
							<th class="th_1">漫画id</th>
							<th class="th_2">漫画名</th>
							<th class="th_2">管理</th>
						</tr>
						<c:forEach var="tmp" items="${loveComic}">
						<tr>
							<td class="td_1"><input type="checkbox" /></td>
							<td class="th_1">${tmp.comicId}</td>
							<td class="th_2">${tmp.comicName}</td>
							<th class="th_2">
								<a onclick="CannelLoveComic('${tmp.comicId}')">取消关注</a>
							</th>
						</tr>
						</c:forEach>
					</table>
					<div class="subscriptionFooter">
						<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a>
						<a onclick="PrePage()" class="button buttonWhite">上一页</a> 
						<select id="selectPage" onchange="SelectedPage();">
						<c:forEach var="i" begin="1" end="${loveComic_sum}" step="1">
							<c:choose>
								<c:when test="${i==loveComic_currentPage}">
									<option value="${i}" selected="selected">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select>
					 	<a onclick="NextPage()" class="button buttonWhite">下一页</a>
						<a onclick="GoOnePage(${loveComic_sum})" class="button buttonWhite">尾页</a>
					</div>
				</div>
			</div>
					<div id="openModal2" class="modalDialog">
	<div>
		<div onclick="closePopup('openModal2')" title="Close" class="close">X</div>
									<div>新增订阅</div>
				<div class="modifyDiv">
				<span>漫画ID:</span>
				<input type="text" id="loveComicId"  />
				</div>
				<div class="modifyDiv" style="text-align: center;">
				<input type="button" onclick="AddLoveComic()" value="提交" class="button" />
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
			function AddLoveComic(){
				var comicId=$("#loveComicId").val();
				var urlStr="<%=request.getContextPath()%>/cms/lovecomic/addcomic";
				var currentNum = $("#selectPage").val();
				$.ajax({
					url : urlStr,
					async : false,
					data : {
						"comicId" : comicId,
						"currentNum" : currentNum
					},
					error : function() {
						alert("ajax出错！");
					},
					success : function(data) {
						$("#userSubPanel").html(data);
					}
				});
				
				$("#loveComic").hide();
			}
			
			function CannelLoveComic(comicId){
				var urlStr="<%=request.getContextPath()%>/cms/lovecomic/cannelcomic";
				var currentNum = $("#selectPage").val();
				$.ajax({
					url : urlStr,
					async : false,
					data : {
						"comicId" : comicId,
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
		var urlStr="<%=request.getContextPath()%>/cms/lovecomic";	
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
		var urlStr="<%=request.getContextPath()%>/cms/lovecomic";	
		if(num<1)num=1;
		if(num>${loveComic_sum})num=${loveComic_sum};
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
		var urlStr="<%=request.getContextPath()%>/cms/lovecomic";	
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
		var urlStr="<%=request.getContextPath()%>/cms/lovecomic";	
		var num=$("#selectPage").val();
		if(++num<=${loveComic_sum}){
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
			
		
		