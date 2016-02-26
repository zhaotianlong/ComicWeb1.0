<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<aside id="loveComicer" class="right">
			<div class="inerestWriter">
				<h1>关注的作者</h1>
				<div class="inerestWriterContetnt">
					<div class="interestwTitle">关注作者：<span class="fontRed">18</span>名</div>
					<table>
						<tr>
							<th class="td_1"><input type="checkbox" /></th>
							<th class="th_1">作者名字</th>
							<th class="th_2">作品数</th>
							<th class="th_2">粉丝数</th>
							<th class="th_2">关注时间</th>
							<th class="th_2">管理</th>
						</tr>
						<c:forEach var="tmp" items="${loveComicer}">
						<tr>
							<td class="td_1"><input type="checkbox" /></td>
							<td class="th_1">${tmp.athuorName}</td>
							<td class="th_2">${tmp.cmicSum}</td>
							<th class="th_2">${tmp.fansSum}</th>
							<th class="th_2">${tmp.loveDate}</th>
							<th class="th_2">
								<a onclick="CannelLoveComicer('${tmp.authorId}')">取消关注</a><a>查看</a>
							</th>
						</tr>
						</c:forEach>
						<tr>
							<td class="td_1"><input type="checkbox" /></td>
							<td class="th_1">ssss</td>
							<td class="th_2">ssss</td>
							<th class="th_2">ssss</th>
							<th class="th_2">ssss</th>
							<th class="th_2">ssss</th>
						</tr>
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
			<script type="text/javascript">
	function SelectedPage() {
		var urlStr="<%=request.getContextPath()%>/personcenter/lovecomicer";	
		var currentNum = $("#selectPage").val();
			//alert(currentNum);
			urlStr+="/"+currentNum;
			$.ajax({
				url : urlStr,
				async : false,
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#infoSub").html(data);
				}
			});
		}
	function GoOnePage(num){
		var urlStr="<%=request.getContextPath()%>/personcenter/lovecomicer";	
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
					$("#infoSub").html(data);
				}
			});
	}
	
	function PrePage(){
		var urlStr="<%=request.getContextPath()%>/personcenter/lovecomicer";	
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
					$("#infoSub").html(data);
				}
			});
		}
	}
	function NextPage(){
		var urlStr="<%=request.getContextPath()%>/personcenter/lovecomicer";	
		var num=$("#selectPage").val();
		if(++num<=${loveComicer_sum}){
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
					$("#infoSub").html(data);
				}
			});
		}
		
	}
	function CannelLoveComicer(authorId){
		var urlStr="<%=request.getContextPath()%>/personcenter/lovecomicer/cancel";
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
				$("#infoSub").html(data);
			}
		});
	}
	
	
</script>	
			
		</aside>
		
		