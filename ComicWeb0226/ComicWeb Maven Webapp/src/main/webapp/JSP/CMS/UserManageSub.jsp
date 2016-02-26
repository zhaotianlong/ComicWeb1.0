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
		var urlStr="<%=request.getContextPath()%>/cms/modifyuserinfo";
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
		var urlStr="<%=request.getContextPath()%>/cms/modifyusericon";
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
	function ModifyLoveComic(){
		var urlStr="<%=request.getContextPath()%>/cms/lovecomic";
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
	function ModifyLoveComicer(){
		var urlStr="<%=request.getContextPath()%>/cms/lovecomicer";
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
	<li onclick="cliclone(this);ModifyInfo();">信息修改</li>
	<li onclick="cliclone(this);ModifyIcon();">头像设置</li>
	<li onclick="cliclone(this);ModifyLoveComic();">他的订阅</li>
	<li onclick="cliclone(this);ModifyLoveComicer();">他的关注</li>
</ul>
<div id="userSubPanel">

</div>