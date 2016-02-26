<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOLO漫画网</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/rankList.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.1.4.js"></script>
</script>
</head>
<body>
	<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>
	<div style="height:40px;"></div>
	<div class="container">
		<jsp:include page="/JSP/Layout/navgate.jsp"></jsp:include>
		<div class="navBarAnnote">
			<span class="navBarAnnoteFirst">热门点击:</span> <a href="#">野良神</a> <a
				href="#">一人之下</a> <a href="#">凌舞云姬</a> <a href="#">血魔人</a> <a
				href="#">最终幻想</a> <a href="#">风色幻想</a>
		</div>

		<aside class="left rankLeft">
			<div class="comicListTitle">
				<h1>排行榜</h1>
			</div>
			<ul id="ranklistPanel">
				<li class="active"><a href="<%=request.getContextPath()%>/comic/ranklist" >人气排行</a></li>
				<li onclick="RankByLove();this.className='active'">
					<a>订阅排行</a>
				</li>
				<li onclick="RankByTab('4');this.className='active'"><a>科幻排行</a></li>
				<li onclick="RankByTab('8');this.className='active'"><a>魔法排行</a></li>
				<li onclick="RankByTab('3');this.className='active'"><a>战斗排行</a></li>
				<li onclick="RankByTab('1');this.className='active'"><a>冒险排行</a></li>
				<li onclick="RankByTab('6');this.className='active'"><a>悬疑排行</a></li>
				<li onclick="RankByTab('2');this.className='active'"><a>搞笑排行</a></li>
				<li onclick="RankByTab('5');this.className='active'"><a>爱情排行</a></li>
				<li onclick="RankByTab('7');this.className='active'"><a>竞技排行</a></li>
				<li onclick="RankByTab('9');this.className='active'"><a>历史排行</a></li>
				<li onclick="RankByTab('10');this.className='active'"><a>战争排行</a></li>
				<li onclick="RankByTab('11');this.className='active'"><a>仙侠排行</a></li>
			</ul>
		</aside>
		<aside class="right rankRight">
			<div class="comicListTitle">
				<h1>人气排行榜</h1>
			</div>
			<ul id="comicRankSub">
				<jsp:include page="ComicRankSub.jsp"></jsp:include>	
			</ul>
		</aside>
		<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
		function RankByTab(id){
			$("#ranklistPanel > li").each(function() {
				$(this).removeClass("active");	
			});
			var urlStr="<%=request.getContextPath()%>/comic/ranklist/tab/"+id;
			$.ajax({
				url : urlStr,
				async : false,
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#comicRankSub").html(data);
				}
			});
		}
		function RankByLove(){
			$("#ranklistPanel > li").each(function() {
				$(this).removeClass("active");	
			});
			var urlStr="<%=request.getContextPath()%>/comic/ranklist/love";
			$.ajax({
				url : urlStr,
				async : false,
				error : function() {
					alert("ajax出错！");
				},
				success : function(data) {
					$("#comicRankSub").html(data);
				}
			});
		}
		
	</script>

</body>
</html>