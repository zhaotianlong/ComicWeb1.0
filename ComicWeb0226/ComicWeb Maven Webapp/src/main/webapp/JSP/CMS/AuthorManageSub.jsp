<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
		
	function cliclone(li){
		$("#uPanel > li").each(function() {
			$(this).removeClass("active");
		});
		li.className="active";
	}
	function ModifyInfo(){
		var urlStr="<%=request.getContextPath()%>/cms/modifyauthorinfo";
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
	function ModifyIcon(){
		var urlStr="<%=request.getContextPath()%>/cms/modifyauthoricon";
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
	function ModifyComic(){
		var urlStr="<%=request.getContextPath()%>/cms/modifyauthorcomic";
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

</script>
<ul id="uPanel" class="userPanel">
	<li onclick="cliclone(this);ModifyInfo();">个人信息修改</li>
	<li onclick="cliclone(this);ModifyIcon();">头像修改</li>
	<li onclick="cliclone(this);ModifyComic();">他的作品</li>
</ul>
<div id="userSubPanel">

</div>