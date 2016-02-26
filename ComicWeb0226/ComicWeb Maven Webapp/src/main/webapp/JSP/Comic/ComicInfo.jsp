<%@page import="model.ComicPart"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>LOLO漫画网</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/readComic.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/star.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.1.4.js"></script>
<script type="text/javascript">
								$("#star").ready(function(){
									var star="rating rating-"+${userstar};
									$("#star").attr("class",star);
								});
</script>

</head>
<body class="readComic_bg">
	<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>
	<div style="height:40px;"></div>
	<div class="container">
		<jsp:include page="/JSP/Layout/navgate.jsp"></jsp:include>
		<div class="breadTab">
			<a href="">首页</a><span>></span> <a href="">少年漫画</a><span>></span> <span
				class="curComic">${comic.comicName}</span>
		</div>
		<div class="comicContent">
			<div class="comicDes">
				<div class="comicDes_left">
					<img src="<%=request.getContextPath()%>/${comic.src}"
						class="comicDesImg">
					<div class="good_bad">
						<span class="fontRed">${comic.good}</span> <a href=""> <img
							src="<%=request.getContextPath()%>/img/readComic/good_bad_03.gif"
							height="24" width="24">
						</a> <span>顶</span>
					</div>
					<div class="good_bad">
						<span class="fontGray">${comic.bad}</span> <a href=""> <img
							src="<%=request.getContextPath()%>/img/readComic/good_bad_06.gif"
							height="24" width="24">
						</a> <span>踩</span>
					</div>
				</div>
				<div class="comicDes_right">
					<h1>妖神记</h1>
					<div>
						<span >请您评分</span> <span>
							
							<c:choose>
								<c:when test="${iscomicscore==0}">
									<div id="star" class="rating rating-1">
								<i class="star-1" onmouseover="ExchangeStar('1');" onclick="SetStar('1')" >★</i>
								<i class="star-2" onmouseover="ExchangeStar('2');" onclick="SetStar('2')">★</i>
								<i class="star-3" onmouseover="ExchangeStar('3');" onclick="SetStar('3')">★</i>
								<i class="star-4" onmouseover="ExchangeStar('4');" onclick="SetStar('4')">★</i>
								<i class="star-5" onmouseover="ExchangeStar('5');" onclick="SetStar('5')">★</i>
								<script type="text/javascript">
								function	ExchangeStar(id){
									var star="rating rating-"+id;
									$("#star").attr("class",star);
								}
								function SetStar(id){
									<%if(session.getAttribute("user")!=null){ %>
									var star="rating rating-"+id;
									$("#star").attr("class",star);
									var urlStr="<%=request.getContextPath() %>/comic/comicscore";
									$.ajax({
										url : urlStr,
										async : false,
										data:{"comicId":"${comic.comicId}","score":id},
										error : function() {
											alert("ajax出错！");
										},
										success : function(data) {
											window.location.href=window.location.href;
										}
									});
									<%}else{%>
										alert("请您先登录再评分");
									<%}%>
								}
								</script>
							</div>
								</c:when>
								<c:otherwise>
								<div id="star" class="rating rating-1">
								<i class="star-1"  >★</i>
								<i class="star-2" >★</i>
								<i class="star-3" >★</i>
								<i class="star-4" >★</i>
								<i class="star-5" >★</i>
							</div>
								</c:otherwise>
							</c:choose>
						</span>
						 <span>${comicscore_avg}分</span>
						 <span>(${comicscorepeople_sum}人评分)</span>
					</div>
					<div>
						作者:<span>${author.realName }</span> 状态:
						<c:choose>
							<c:when test="${comic.comicStatus==0}">
								<span>连载中</span>
							</c:when>
							<c:otherwise>
								<span>已完结</span>
							</c:otherwise>
						</c:choose>
					</div>
					<div>
						类型:<span> ${tabs} </span>
					</div>
					<div>
						订阅:<span>${loveSum}</span>
					</div>
					<div class="comicDes_div">
						<p>${comic.description}</p>
					</div>
					<div class="buttonGroup">
						<a href="" class="button buttonBlue">开始阅读</a> <a href=""
							class="button buttonWhite">订阅收藏</a>
					</div>
				</div>
				<div class="comicerDes">
					<div class="comicerDes_top">
						<img src="<%=request.getContextPath()%>/${author.iconPath}" />
						<div>
							<span>姓名:${author.realName}</span> <span>作品数:${comicSum}</span>
						</div>
					</div>
					<div class="comicerDes_middle">
						<span></span>
						<h3>作者介绍</h3>
						<p>一群意外而死亡的人，忽然死而复生，他们手臂上出现了黑</p>
						<p>色倒计时纹身...以生命作筹码，将命运摆上嗜血的棋盘残</p>
						<p>酷的生存游戏里复活者们堵上了一切，自己也成了这场游戏</p>
						<p>的祭品，相互算计互相残杀，而最终能真正复活的人只有一</p>
						<p>人。 复活者的混战时代已经到来而杀戮的尽头，是结束</p>
						<p>，还是更多的谜题？王座又在何处等待？</p>
					</div>
					<div class="comicerDes_bottom">
						<span></span>
						<h3>代表作品</h3>
						<div>${comicTop3}</div>
					</div>
				</div>
			</div>
		</div>
		<div class="comicRecommend">
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
			<!--猜你喜欢-->
			<div class="sameRecommend">
				<div class="recommendTitle">
					<div class="recommendTitleLine"></div>
					<div class="recommendTitleFont">
						猜你<span class="fontRed">喜欢</span>
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
		<div class="comicCommend">
			<div class="commendContent">
				<div class="commendTitle">
					<div class="commendTitleLine"></div>
					<div class="commendTitleFont">
						章节<span class="fontRed">列表</span>
					</div>
					<div class="commendTitleFontRight">
						<span class="fontRed">${comicPartsSum}</span>话
					</div>
				</div>
				<ul id="cul" class="chapterUl">
					<c:forEach var="i" begin="1" step="1" end="${comicPartsPage}">
						<c:choose>
							<c:when test="${i==1}">
							<li class="active" onclick="DisplayChapterList('${i}');this.className='active'";>第${i}页</li>
						</c:when>
						<c:otherwise>
							<li  onclick="DisplayChapterList('${i}');this.className='active'">第${i}页</li>
						</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
				<div id="cdiv" class="chapterDiv">
					
						<%
						ComicPart[] comicParts=(ComicPart[])request.getAttribute("comicParts");
						Integer maxComic=(Integer)request.getAttribute("maxComic");
						Integer comicPartsSum=(Integer)request.getAttribute("comicPartsSum");
						Integer comicPartsPage=(Integer)request.getAttribute("comicPartsPage");
							for(int i=0;i<comicPartsPage;i++){
						%>
						<%if(i==0){%>
							<ul class="active">
						<%}else{ %>
							<ul>
						<%} %>
						<%
								int end=(i+1)*maxComic;
								int pre=i*maxComic;
								if(i==comicPartsPage-1)
									end=comicPartsSum%maxComic==0?end:comicPartsSum%maxComic;
								for(int j=pre;j<end;j++){
						%>
							<li><a href="<%=request.getContextPath()+"/comic/comiclist/"+comicParts[j].getComic().getComicId()+"/"+comicParts[j].getPartId()%>">第<%=j+1%>话</a></li>
						<%}%>
							</ul>	
						<%}%>
				</div>
			</div>
		</div>
		<div class="comicCommend">
			<div class="commendContent">
				<div class="commendTitle">
					<div class="commendTitleLine"></div>
					<div class="commendTitleFont">
						网友<span class="fontRed">评论</span>
					</div>
					<div class="commendTitleFontRight">
						<span class="fontRed">822</span>条
					</div>
				</div>
				<form action="<%=request.getContextPath()%>/comic/insertcomment"
					method="post" onsubmit="return juge()">
					<textarea id="yourComment" name="comment" cols="30" rows="10"></textarea>
					<div>
						<input type="hidden" name="comicId" id="comicId"
							value="${comic.comicId}" />
						<%
							if (request.getSession().getAttribute("user") == null) {
						%>
						<div class="leftAlt">
							<a href="">登录</a>
							<div class="line"></div>
							<a href="">注册</a>
						</div>
						<%
							}
						%>
						<div class="rightAlt">
							<span>您可以输入<span class="fontRed">180字</span></span> <input
								type="submit" class="button buttonOrange" value="发表评论" />
						</div>
					</div>
				</form>
				<div id="commicComment">
					<jsp:include page="/JSP/Comic/ComicComment.jsp"></jsp:include>
				</div>

			</div>
		</div>
		<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
		function juge() {
	<%if (session.getAttribute("user") == null) {%>
		alert('您尚未登录！请先登录！');
			return false;
	<%}%>
		if ($('#yourComment').val() == "") {
				alert('您没有输入任何文字！');
				return false;
			}

			return true;
		}
		
		function DisplayChapterList(id){
			$(".active").each(function(){
				$(this).removeClass("active");
			});
			id=id-1;
			var str=">ul:eq("+id+")";
			$("#cdiv"+str).addClass("active");
		}
		
	</script>
</body>
</html>
