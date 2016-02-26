<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<aside id="modifyPassword" class="right">
			<div class="password">
				<h1>修改密码</h1>

				<form:form  modelAttribute="UserPasswordModel" action="modifypassword/do" cssClass="passwordContent">
					<h5><form:errors path="*"></form:errors></h5>
					<div>
						<span>当前密码：</span><span>${UserPasswordModel.password}</span>
					</div>
					<div>
						<span>新 密 码：</span>
						<input type="text" name="password" value="" class="enableInput" />
					</div>
					<div>
						<span>确认密码：</span>
						<input type="text" name="rePassword" value="" class="enableInput" />
					</div>
					<div class="buttonGroup">
						<input type="submit" class="button buttonYellowGreen" value="提交">
						<button onclick="Reset()" class="button buttonOrange">重置</button>
					</div>
				</form:form>
			</div>
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/additional-methods.js"></script>
			<script type="text/javascript">
			function Reset(){
				var urlStr="<%=request.getContextPath()%>/personcenter/modifypassword";
				$.ajax({
					url : urlStr,
					async : false,
					error : function() {
						alert("ajax出错！");
					},
					success : function(data) {
						$("#modifyPassword").html(data);
					}
				});	
			}
			$().ready(
					function() {
						$("#resistForm").validate({
							rules : {
								password : {
									required : true,
									maxlength : 20,
									minlength : 8,
									chrnum : true
								},
								rePassword : {
									required : true,
									chrnum : true,
									equalTo : "#password"
								}
							},
							messages : {
								password : {
									required : "密码不能为空",
									maxlength : "密码至多为20位",
									minlength : "密码至少为8位",
								},
								rePassword : {
									required : "确认密码不能为空",
									equalTo : "两次输入密码不相同"
								}
							}
						});
					});
			
			</script>
			
		</aside>
