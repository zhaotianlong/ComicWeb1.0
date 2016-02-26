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
		var urlStr="<%=request.getContextPath()%>/cms/modifycomicinfo";
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
	function ModifyChapter(){
		var urlStr="<%=request.getContextPath()%>/cms/modifycomicpart";
		urlStr+="/"+"${comic.comicId}";
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
	<li onclick="cliclone(this);ModifyInfo();">漫画信息修改</li>
	<li onclick="cliclone(this);ModifyChapter();">漫画章节</li>
</ul>
<div id="userSubPanel">

</div>