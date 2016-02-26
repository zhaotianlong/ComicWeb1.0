<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<aside id="loveComicer" class="right">
			<div class="inerestWriter" style="width: 100%;">
				<h1>用户管理</h1>
				<div class="inerestWriterContetnt">
					<div class="interestwTitle">共<span class="fontRed">${userList_total}</span>名 用户     <a onclick="openPopup('openModal2')">新增用户</a></div>
					<table>
						<tr>
							<th class="td_1"><input type="checkbox" /></th>
							<th class="th_1">用户id</th>
							<th class="th_2">用户名</th>
							<th class="th_2">注册日期</th>
							<th class="th_2">管理</th>
						</tr>
						<c:forEach var="tmp" items="${users}">
						<tr>
							<td class="td_1"><input type="checkbox" /></td>
							<td class="th_1">${tmp.accountId}</td>
							<td class="th_2">${tmp.nickName}</td>
							<th class="th_2">${tmp.resisterDate}</th>
							<th class="th_2">
								<a onclick="UserModifyPanel('${tmp.accountId}')">修改</a>
								<a>删除</a>
							</th>
						</tr>
						</c:forEach>
					</table>
					<div class="subscriptionFooter">
						<a onclick="GoOnePage('1')" class="button buttonWhite">首页</a>
						<a onclick="PrePage()" class="button buttonWhite">上一页</a> 
						<select id="selectPage" onchange="SelectedPage();">
						<c:forEach var="i" begin="1" end="${userList_sum}" step="1">
							<c:choose>
								<c:when test="${i==userList_currentPage}">
									<option value="${i}" selected="selected">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</select>
					 	<a onclick="NextPage()" class="button buttonWhite">下一页</a>
						<a onclick="GoOnePage(${userList_sum})" class="button buttonWhite">尾页</a>
					</div>
				</div>
			</div>
			<div id="openModal2" class="modalDialog">
	<div>
		<div onclick="closePopup('openModal2')" title="Close" class="close">X</div>
						<div>新增用户</div>
				<form:form modelAttribute="User" action="adduser" cssClass="profileContent" id="resistForm">
				<div class="modifyDiv">
				<span>账户:</span>	
				<input type="text" id="account" name="accountId"  placeholder="请输入您的账户"/>
				</div>
				<div class="modifyDiv">
				<span>昵称:</span>	
				<input type="text" id="nickName" name="nickName"  placeholder="请输入您的昵称"/>
				</div>
				<div class="modifyDiv">
				<span>密码:</span>	
					<input type="password" id="password" name="password" placeholder="请键入您的密码" />
				</div>
				<div class="modifyDiv">
				<span>邮箱:</span>
				<input type="email" name="mail" placeholder="请确认您的邮箱" />
				</div>
				<div class="modifyDiv">
				<span>手机:</span>
				<input type="tel" name="tel" placeholder="请确认您的手机" />
				</div>
				<div class="modifyDiv">
					<span>性别 :</span> 
					男:<input type="radio" name="gender" checked="checked" value="M" />
					
					女:<input type="radio" name="gender" value="W" />
					
				</div>							
				<div class="modifyDiv">
					<span>出生日期:</span> <input type="date" name="bornDate" value="1997/01/01" />
				</div>
				<div class="modifyDiv" style="text-align: center;">
				<input type="submit" value="添加用户" class="button" />
			</div>
			</form:form>
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
			
			$().ready(
					function() {
						$("#resistForm").validate({
							rules : {
								account : {
									required : true,
									maxlength : 12,
									minlength : 8,
									chrnum : true
									//remote:{
										//type:"post",
										//url://homepage/CheckEuqals
										//data:{account:function(){return $('#account').val();}}
									//}
								},
								password : {
									required : true,
									maxlength : 20,
									minlength : 8,
									chrnum : true
								},
								mail : {
									required : true,
									email : true
								},
								tel: {
									mobile : true
								}
							},
							messages : {
								account : {
									required : "账户不能为空",
									maxlength : "账户至多为12位",
									minlength : "账户至少为8位",
									remote:"用户名已经存在"
								},
								password : {
									required : "密码不能为空",
									maxlength : "密码至多为20位",
									minlength : "密码至少为8位",
								},
								mail : {
									required : "邮箱不能为空",
									email : "邮箱格式有误"
								}
							}
						});
						jQuery.validator.addMethod("chrnum", function(value, element) {
							var chrnum = /^([a-zA-Z0-9]+)$/;
							return this.optional(element) || (chrnum.test(value));
						}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");
						jQuery.validator.addMethod("mobile", function(value, element) {
							var length = value.length;
							var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
							return this.optional(element)
									|| (length == 11 && mobile.test(value));
						}, "手机号码格式错误");
					});
	
	function SelectedPage() {
		var urlStr="<%=request.getContextPath()%>/cms/usermanage";	
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
		var urlStr="<%=request.getContextPath()%>/cms/usermanage";	
		if(num<1)num=1;
		if(num>${userList_sum})num=${userList_sum};
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
		var urlStr="<%=request.getContextPath()%>/cms/usermanage";	
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
		var urlStr="<%=request.getContextPath()%>/cms/usermanage";	
		var num=$("#selectPage").val();
		if(++num<=${userList_sum}){
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
	
	function UserModifyPanel(id){
		var urlStr="<%=request.getContextPath()%>/cms/usermanage/modifypanel";
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
	
</script>	
			
		</aside>
		
		