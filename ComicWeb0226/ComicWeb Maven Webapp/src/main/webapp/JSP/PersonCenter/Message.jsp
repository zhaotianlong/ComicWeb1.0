<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>

<aside class="right">
	<div class="messageCenter">
		<h1>评论中心</h1>
		<div class="messageContent">
			<div>
				已发表评论： <span>${comments_total}</span> 条
			</div>
			<div>
				我的回复: <span>${commentfrom_sum}</span> 条
			</div>
			<div>
				收到回复: <span>${commentto_sum}</span> 条
			</div>
			<div>
				新收到回复: <span>${commentsubNew_sum}</span> 条
			</div>
		</div>
		<div class="buttonGroup">
			<a onclick="Comment()" class="button">我的评论</a>
			<a onclick="CommentBackFrom()" class="button">我的回复</a>
			<a onclick="CommentBackTo()" class="button">收到回复</a> 
		</div>
		<!--收件箱/发件箱-->
		<div id="messageSub">

		</div>
	</div>
	<script type="text/javascript">
		function Comment(){
			var urlStr="<%=request.getContextPath()%>/personcenter/message/comment";	
				$.ajax({
					url : urlStr,
					async : false,
					error : function() {
						alert("ajax出错！");
					},
					success : function(data) {
						$("#messageSub").html(data);
					}
				});
		}
		function CommentBackFrom(){
			var urlStr="<%=request.getContextPath()%>/personcenter/message/commentbackfrom";	
				$.ajax({
					url : urlStr,
					async : false,
					error : function() {
						alert("ajax出错！");
					},
					success : function(data) {
						$("#messageSub").html(data);
					}
				});
		}
		function CommentBackTo(){
			var urlStr="<%=request.getContextPath()%>/personcenter/message/commentbackto";	
				$.ajax({
					url : urlStr,
					async : false,
					error : function() {
						alert("ajax出错！");
					},
					success : function(data) {
						$("#messageSub").html(data);
					}
				});
		}
	</script>
</aside>