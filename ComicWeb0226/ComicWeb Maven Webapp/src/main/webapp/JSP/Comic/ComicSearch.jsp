<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOLO漫画网</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/comicList.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.1.4.js"></script>
</head>
<body>
	<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>
	<div style="height:40px;"></div>
	<div class="container">
		<jsp:include page="/JSP/Layout/navgate.jsp"></jsp:include>
		<aside class="left">
			<div class="comicContainer">
				<!--列表头-->
				<div class="comicListTitle">
					<h1>漫画列表</h1>
					<div class="sort">
						排序：<span><a href="">更新时间</a></span> <span>｜</span> <span><a
							href="">总点击</a></span>
					</div>
				</div>
				<!--列表内容-->
				<div id="comicListSub">
					<jsp:include page="ComicSearchSub.jsp"></jsp:include>
				</div>
				
		</aside>
		<aside class="right">
			<!--热门点击-->
			<div class="comicRecommend">
				<!--猜你喜欢-->
				<div class="sameRecommend">
					<div class="recommendTitle">
						<div class="recommendTitleLine"></div>
						<div class="recommendTitleFont">
							猜你<span class="fontRed">喜欢</span>
						</div>
					</div>
					<ul>
						<li><a href=""> <img src="../img/personCenter/tmp_1.jpg" />
								<div>
									<span>武器少女</span> <span>作者:LOLO</span> <span>状态:连载中</span> <span>最新:第08话</span>
								</div>
						</a></li>
						<li><a href=""> <img src="../img/personCenter/tmp_1.jpg" />
								<div>
									<span>武器少女</span> <span>作者:LOLO</span> <span>状态:连载中</span> <span>最新:第08话</span>
								</div>
						</a></li>
						<li><a href=""> <img src="../img/personCenter/tmp_1.jpg" />
								<div>
									<span>武器少女</span> <span>作者:LOLO</span> <span>状态:连载中</span> <span>最新:第08话</span>
								</div>
						</a></li>
						<li><a href=""> <img src="../img/personCenter/tmp_1.jpg" />
								<div>
									<span>武器少女</span> <span>作者:LOLO</span> <span>状态:连载中</span> <span>最新:第08话</span>
								</div>
						</a></li>
					</ul>
				</div>
				<!--同类推荐-->
				<div class="sameRecommend">
				<div class="recommendTitle">
					<div class="recommendTitleLine"></div>
					<div class="recommendTitleFont">
						同类<span class="fontRed">推荐</span>
					</div>
				</div>
				<ul>
					<li><a href=""> <img
							src="<%=request.getContextPath()%>/img/personCenter/tmp_1.jpg" />
							<div>
								<span>武器少女</span> <span>作者:LOLO</span> <span>状态:连载中</span> <span>最新:第08话</span>
							</div>
					</a></li>
					<li><a href=""> <img
							src="<%=request.getContextPath()%>/img/personCenter/tmp_1.jpg" />
							<div>
								<span>武器少女</span> <span>作者:LOLO</span> <span>状态:连载中</span> <span>最新:第08话</span>
							</div>
					</a></li>
					<li><a href=""> <img
							src="<%=request.getContextPath()%>/img/personCenter/tmp_1.jpg" />
							<div>
								<span>武器少女</span> <span>作者:LOLO</span> <span>状态:连载中</span> <span>最新:第08话</span>
							</div>
					</a></li>
					<li><a href=""> <img
							src="<%=request.getContextPath()%>/img/personCenter/tmp_1.jpg" />
							<div>
								<span>武器少女</span> <span>作者:LOLO</span> <span>状态:连载中</span> <span>最新:第08话</span>
							</div>
					</a></li>
				</ul>
			</div>
			</div>
		</aside>
		<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
	</div>
</body>
</html>
