<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>LOLO漫画网</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/animate.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.1.4.js"></script>
<script type="text/javascript">
	var t = n = 0, count;
	function showAuto() {
		n = n >= (count - 1) ? 0 : ++n;
		$("#ComicTitlePic_small li").eq(n).trigger('click');
	}
	$(document).ready(
			function() {
				count = $("#ComicTitlePic_big a").length;
				$("#ComicTitlePic_big a:not(:first-child)").hide();
				//获取alt值
				$("#ComicTitlePic_small div").html(
						$("#ComicTitlePic_big a:first-child").find("img").attr(
								'alt'));
				$("#ComicTitlePic_small div").click(
						function() {
							window.open($("#ComicTitlePic_big a:first-child")
									.attr('href'), "_blank")
						});

				$("#ComicTitlePic_small li ").click(
						function() {
							var i = $(this).find("img").attr('alt') - 1;//获取Li元素内的值，即1，2，3，4
							//alert(i);
							n = i;
							if (i >= count)
								return;
							$("#ComicTitlePic_small div").html(
									$("#ComicTitlePic_big a").eq(i).find("img")
											.attr('alt'));
							$("#ComicTitlePic_small div").unbind().click(
									function() {
										window.open($("#ComicTitlePic_big a")
												.eq(i).attr('href'), "_blank")
									})
							$("#ComicTitlePic_big a").filter(":visible")
									.fadeOut(500).parent().children().eq(i)
									.fadeIn(1000);

						});
			});
</script>
<script type="text/javascript">
	function action(id) {
		$("#" + id + " > a").each(function() {
			if ($(this).hasClass("flipInX")) {
				$(this).removeClass("flipInX").addClass("flipOutX");
			} else {
				$(this).removeClass("flipOutX").addClass("flipInX");
			}
		});
	}
	function action2(id) {
		$("#" + id + " > a").each(function() {
			if ($(this).hasClass("flipInY")) {
				$(this).removeClass("flipInY").addClass("flipOutY");
			} else {
				$(this).removeClass("flipOutY").addClass("flipInY");
			}
		});
	}
	var left = new Array();
	var right = new Array();
	// left[0]=1;
	// left[1]=1;
	left[1] = left[2] = left[3] = left[4] = 1;
	right[1] = right[2] = right[3] = right[4] = 0;

	function LeftIn(id, id1, id2) {

		if (left[id1] >= 0) {
			$("#" + id + " > ul").eq(0).removeClass("fadeInLeft");
			$("#" + id + " > ul").eq(1).removeClass("fadeOutRight");

			$("#" + id + " > ul").eq(0).addClass("fadeOutLeft");
			$("#" + id + " > ul").eq(0).hide();
			$("#" + id + " > ul").eq(1).show();
			$("#" + id + " > ul").eq(1).addClass("fadeInRight");

			left[id1]--;
			right[id2]++;
		}
	}
	function RightIn(id, id1, id2) {
		if (right[id2] >= 0) {
			$("#" + id + " > ul").eq(0).removeClass("fadeOutLeft");
			$("#" + id + " > ul").eq(1).removeClass("fadeInRight");

			$("#" + id + " > ul").eq(1).addClass("fadeOutRight");
			$("#" + id + " > ul").eq(1).hide();
			$("#" + id + " > ul").eq(0).show();
			$("#" + id + " > ul").eq(0).addClass("fadeInLeft");

			left[id1]++;
			right[id2]--;
		}
	}

</script>
</head>
<body>
	<jsp:include page="/JSP/Layout/head.jsp"></jsp:include>
	<div style="height:40px;"></div>
	<div class="container">
		<jsp:include page="/JSP/Layout/navgate.jsp"></jsp:include>

		<aside  class="left">
			<div class="subLeft">
				<div class="subLeftOne" width="600" height="400">
					<div id="ComicTitlePic_big" class="ComicTitlePic_big">
						<a href=""><img class="cardImageWrap" src="<%=request.getContextPath()%>/img/bleach.jpg"  alt="我是一号选手"></a>
						<a href=""><img class="cardImageWrap" src="<%=request.getContextPath()%>/img/error404.jpg"  alt="我是二号选手" ></a>
						<a href=""><img class="cardImageWrap" src="<%=request.getContextPath()%>/img/bleach.jpg"  alt="我是三号选手"></a>
						<a href=""><img class="cardImageWrap" src="<%=request.getContextPath()%>/img/error404.jpg" alt="我是四号选手"></a>
						<a href=""><img class="cardImageWrap" src="<%=request.getContextPath()%>/img/bleach.jpg" alt="我是五号选手" ></a>
					</div>
					<div id="ComicTitlePic_small" class="turnTitle">
						<div>标题！！！！</div>
						<ul>
							<li><img class="cardImageWrapIcon" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg"  alt="1" ></li>
							<li><img class="cardImageWrapIcon" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg"  alt="2" ></li>
							<li><img class="cardImageWrapIcon" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg"  alt="3"></li>
							<li><img class="cardImageWrapIcon" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg"  alt="4" ></li>
							<li><img class="cardImageWrapIcon" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg"  alt="5" ></li>
						</ul>
					</div>
					<div class="subleftFont"></div>
				</div>
				<div class="subLeftOne">
					<div class="imgContainer">
						<div id="test3" class="buttonImgTurn1" onclick="action2('test3');">
							<a  class="animated flipInY"><img class="cardImageWrap"  src="<%=request.getContextPath()%>/img/advance_1.jpg" ></a>
							<a  class="animated flipOutY"><img   class="cardImageWrap" src="<%=request.getContextPath()%>/img/advance_2.jpg" ></a>
						</div>
					</div>
					<div  class="imgContainer">
						<div id="test4" class="buttonImgTurn1" onclick="action2('test4');">
							<a  class="animated flipInY"><img   class="cardImageWrap" src="<%=request.getContextPath()%>/img/advance_1.jpg" ></a>
							<a  class="animated flipOutY"><img  class="cardImageWrap" src="<%=request.getContextPath()%>/img/advance_2.jpg" ></a>
						</div>
					</div>
					<div class="imgContainer">
						<div id="test" class="buttonImgTurn" onclick="action('test');">
							<a class="animated flipInX" ><img  class="cardImageWrap" src="<%=request.getContextPath()%>/img/advance_1.jpg" ></a>
							<a class="animated flipOutX" ><img  class="cardImageWrap" src="<%=request.getContextPath()%>/img/advance_2.jpg" ></a>
						</div>
						<div id="test2" class="buttonImgTurn" onclick="action('test2');">
							<a class="animated flipInX" ><img  class="cardImageWrap" src="<%=request.getContextPath()%>/img/advance_1.jpg" ></a>
							<a class="animated flipOutX" ><img  class="cardImageWrap" src="<%=request.getContextPath()%>/img/advance_2.jpg" ></a>
						</div>
					</div>
				</div>
			</div>
			<div class="subLeftBottom">
				<div class="subRowTitle bg1">
					<span class="leftSpan">[一周热门点击]</span>
					<span class="rightSpan"><a href="">{更多]</a></span>
				</div>
				<div id="fantasy" class="subRowContent">
					<span class="leftSpan" onclick="LeftIn('fantasy',1,1)"></span>
					<ul class="animated">
						<li>
							<a href="">
								<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
							</a>
							<a href="" class="link">妖神记</a>
							<span>妖灵世界中的生存</span>
						</li>
						<li>
							<a href="">
								<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
							</a>
							<a href="" class="link">三千虚</a>
							<span>落难皇子与奇葩千金</span>
						</li>
						<li>
							<a href="">
								<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
							</a>
							<a href="" class="link">猫之名</a>
							<span>从天上掉下来个妹</span>
						</li>
						<li>
							<a href="">
								<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
							</a>
							<a href="" class="link">最暗恋</a>
							<span>开启不一样的暗恋吧</span>
						</li>
						<li>
							<a href="">
								<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
							</a>
							<a href="" class="link">丑男变美女</a>
							<span>屌丝性转火辣美女</span></li>
							<li>
								<a href="">
									<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/6.jpg" height="137" width="104">
								</a>
								<a href="" class="link">记忆分裂</a>
								<span>你的记忆值多少钱！</span>
							</li>
							<li>
								<a href="">
									<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
								</a>
								<a href="" class="link">端脑</a>
								<span>极致大脑游戏解谜</span>
							</li>
						</ul>
						<ul class="animated" style="display:none;">
							<li>
								<a href="">
									<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
								</a>
								<a href="" class="link">妖神记</a>
								<span>妖灵世界中的生存</span>
							</li>
							<li>
								<a href="">
									<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
								</a>
								<a href="" class="link">三千虚</a>
								<span>落难皇子与奇葩千金</span>
							</li>
							<li>
								<a href="">
									<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
								</a>
								<a href="" class="link">猫之名</a>
								<span>从天上掉下来个妹</span>
							</li>
							<li>
								<a href="">
									<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
								</a>
								<a href="" class="link">最暗恋</a>
								<span>开启不一样的暗恋吧</span>
							</li>
							<li>
								<a href="">
									<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
								</a>
								<a href="" class="link">丑男变美女</a>
								<span>屌丝性转火辣美女</span></li>
								<li>
									<a href="">
										<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
									</a>
									<a href="" class="link">记忆分裂</a>
									<span>你的记忆值多少钱！</span>
								</li>
								<li>
									<a href="">
										<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
									</a>
									<a href="" class="link">端脑</a>
									<span>极致大脑游戏解谜</span>
								</li>
							</ul>
							<span class="rightSpan" onclick="RightIn('fantasy','1','1')"></span>
						</div>
					</div>
					<div class="subLeftBottom">
						<div class="subRowTitle bg2">
							<span class="leftSpan">[一周热门点击]</span>
							<span class="rightSpan"><a href="">{更多]</a></span>
						</div>
						<div id="fantasy1" class="subRowContent">
							<span class="leftSpan" onclick="LeftIn('fantasy1','2','2')"></span>
							<ul class="animated">
								<li>
									<a href="">
										<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
									</a>
									<a href="" class="link">妖神记</a>
									<span>妖灵世界中的生存</span>
								</li>
								<li>
									<a href="">
										<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
									</a>
									<a href="" class="link">三千虚</a>
									<span>落难皇子与奇葩千金</span>
								</li>
								<li>
									<a href="">
										<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
									</a>
									<a href="" class="link">猫之名</a>
									<span>从天上掉下来个妹</span>
								</li>
								<li>
									<a href="">
										<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
									</a>
									<a href="" class="link">最暗恋</a>
									<span>开启不一样的暗恋吧</span>
								</li>
								<li>
									<a href="">
										<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
									</a>
									<a href="" class="link">丑男变美女</a>
									<span>屌丝性转火辣美女</span></li>
									<li>
										<a href="">
											<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/6.jpg" height="137" width="104">
										</a>
										<a href="" class="link">记忆分裂</a>
										<span>你的记忆值多少钱！</span>
									</li>
									<li>
										<a href="">
											<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
										</a>
										<a href="" class="link">端脑</a>
										<span>极致大脑游戏解谜</span>
									</li>
								</ul>
								<ul class="animated" style="display:none;">
									<li>
										<a href="">
											<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
										</a>
										<a href="" class="link">妖神记</a>
										<span>妖灵世界中的生存</span>
									</li>
									<li>
										<a href="">
											<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
										</a>
										<a href="" class="link">三千虚</a>
										<span>落难皇子与奇葩千金</span>
									</li>
									<li>
										<a href="">
											<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
										</a>
										<a href="" class="link">猫之名</a>
										<span>从天上掉下来个妹</span>
									</li>
									<li>
										<a href="">
											<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
										</a>
										<a href="" class="link">最暗恋</a>
										<span>开启不一样的暗恋吧</span>
									</li>
									<li>
										<a href="">
											<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
										</a>
										<a href="" class="link">丑男变美女</a>
										<span>屌丝性转火辣美女</span></li>
										<li>
											<a href="">
												<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
											</a>
											<a href="" class="link">记忆分裂</a>
											<span>你的记忆值多少钱！</span>
										</li>
										<li>
											<a href="">
												<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
											</a>
											<a href="" class="link">端脑</a>
											<span>极致大脑游戏解谜</span>
										</li>
									</ul>
									<span class="rightSpan" onclick="RightIn('fantasy1','2','2')"></span>
								</div>
							</div>
							<div class="subLeftBottom">
								<div class="subRowTitle bg3">
									<span class="leftSpan">[一周热门点击]</span>
									<span class="rightSpan2"><a href="">{更多]</a></span>
								</div>
								<div id="fantasy2" class="subRowContent">
									<span class="leftSpan" onclick="LeftIn('fantasy2','3','3')"></span>
									<ul class="animated">
										<li>
											<a href="">
												<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
											</a>
											<a href="" class="link">妖神记</a>
											<span>妖灵世界中的生存</span>
										</li>
										<li>
											<a href="">
												<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
											</a>
											<a href="" class="link">三千虚</a>
											<span>落难皇子与奇葩千金</span>
										</li>
										<li>
											<a href="">
												<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
											</a>
											<a href="" class="link">猫之名</a>
											<span>从天上掉下来个妹</span>
										</li>
										<li>
											<a href="">
												<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
											</a>
											<a href="" class="link">最暗恋</a>
											<span>开启不一样的暗恋吧</span>
										</li>
										<li>
											<a href="">
												<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
											</a>
											<a href="" class="link">丑男变美女</a>
											<span>屌丝性转火辣美女</span></li>
											<li>
												<a href="">
													<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/6.jpg" height="137" width="104">
												</a>
												<a href="" class="link">记忆分裂</a>
												<span>你的记忆值多少钱！</span>
											</li>
											<li>
												<a href="">
													<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
												</a>
												<a href="" class="link">端脑</a>
												<span>极致大脑游戏解谜</span>
											</li>
										</ul>
										<ul class="animated" style="display:none;">
											<li>
												<a href="">
													<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
												</a>
												<a href="" class="link">妖神记</a>
												<span>妖灵世界中的生存</span>
											</li>
											<li>
												<a href="">
													<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
												</a>
												<a href="" class="link">三千虚</a>
												<span>落难皇子与奇葩千金</span>
											</li>
											<li>
												<a href="">
													<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
												</a>
												<a href="" class="link">猫之名</a>
												<span>从天上掉下来个妹</span>
											</li>
											<li>
												<a href="">
													<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
												</a>
												<a href="" class="link">最暗恋</a>
												<span>开启不一样的暗恋吧</span>
											</li>
											<li>
												<a href="">
													<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
												</a>
												<a href="" class="link">丑男变美女</a>
												<span>屌丝性转火辣美女</span></li>
												<li>
													<a href="">
														<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
													</a>
													<a href="" class="link">记忆分裂</a>
													<span>你的记忆值多少钱！</span>
												</li>
												<li>
													<a href="">
														<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
													</a>
													<a href="" class="link">端脑</a>
													<span>极致大脑游戏解谜</span>
												</li>
											</ul>
											<span class="rightSpan" onclick="RightIn('fantasy2','3','3')"></span>
										</div>
									</div>
									<div class="subLeftBottom">
										<div class="subRowTitle bg4">
											<span class="leftSpan">[一周热门点击]</span>
											<span class="rightSpan"><a href="">{更多]</a></span>
										</div>
										<div id="fantasy3" class="subRowContent">
											<span class="leftSpan" onclick="LeftIn('fantasy3','4','4')"></span>
											<ul class="animated">
												<li>
													<a href="">
														<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
													</a>
													<a href="" class="link">妖神记</a>
													<span>妖灵世界中的生存</span>
												</li>
												<li>
													<a href="">
														<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
													</a>
													<a href="" class="link">三千虚</a>
													<span>落难皇子与奇葩千金</span>
												</li>
												<li>
													<a href="">
														<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
													</a>
													<a href="" class="link">猫之名</a>
													<span>从天上掉下来个妹</span>
												</li>
												<li>
													<a href="">
														<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
													</a>
													<a href="" class="link">最暗恋</a>
													<span>开启不一样的暗恋吧</span>
												</li>
												<li>
													<a href="">
														<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
													</a>
													<a href="" class="link">丑男变美女</a>
													<span>屌丝性转火辣美女</span></li>
													<li>
														<a href="">
															<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/6.jpg" height="137" width="104">
														</a>
														<a href="" class="link">记忆分裂</a>
														<span>你的记忆值多少钱！</span>
													</li>
													<li>
														<a href="">
															<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/1.jpg" height="137" width="104">
														</a>
														<a href="" class="link">端脑</a>
														<span>极致大脑游戏解谜</span>
													</li>
												</ul>
												<ul class="animated" style="display:none;">
													<li>
														<a href="">
															<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
														</a>
														<a href="" class="link">妖神记</a>
														<span>妖灵世界中的生存</span>
													</li>
													<li>
														<a href="">
															<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
														</a>
														<a href="" class="link">三千虚</a>
														<span>落难皇子与奇葩千金</span>
													</li>
													<li>
														<a href="">
															<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
														</a>
														<a href="" class="link">猫之名</a>
														<span>从天上掉下来个妹</span>
													</li>
													<li>
														<a href="">
															<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
														</a>
														<a href="" class="link">最暗恋</a>
														<span>开启不一样的暗恋吧</span>
													</li>
													<li>
														<a href="">
															<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
														</a>
														<a href="" class="link">丑男变美女</a>
														<span>屌丝性转火辣美女</span></li>
														<li>
															<a href="">
																<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
															</a>
															<a href="" class="link">记忆分裂</a>
															<span>你的记忆值多少钱！</span>
														</li>
														<li>
															<a href="">
																<img class="cardImageWrap" src="<%=request.getContextPath()%>/img/intdex-SLevel/2.jpg" height="137" width="104">
															</a>
															<a href="" class="link">端脑</a>
															<span>极致大脑游戏解谜</span>
														</li>
													</ul>
													<span class="rightSpan" onclick="RightIn('fantasy3','4','4')"></span>
												</div>

											</div>
										</aside>
		<aside class="right">
			<!--最新更新-->
			<div class="subRight">
				<div class="subRightTitle1">最新更新</div>
				<div class="subRightContent">
					<ul>
						<li>
							<div class="seq_1" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_2" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_3" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_4" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_5" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_6" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_7" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_8" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_9" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_10" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>

					</ul>
				</div>
			</div>
			<!--推荐榜-->
			<div class="subRight">
				<div class="subRightTitle2">推荐榜</div>
				<div class="subRightContent">
					<ul>
						<li>
							<div class="seq_1" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_2" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_3" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_4" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_5" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_6" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_7" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_8" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_9" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_10" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>

					</ul>
				</div>
			</div>
			<!--动漫新报-->
			<div class="subRight">
				<div class="subRightTitle3">动漫快报</div>
				<div class="subRightContent">
					<ul>
						<li>
							<div class="seq_1" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_2" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_3" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_4" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_5" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_6" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_7" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>
						<li>
							<div class="seq_8" style="height=14px;width=14px"></div> <a
							href="">我和失恋宝宝</a> <span>0</span>
						</li>

					</ul>
				</div>
			</div>
			<!--广告区-->
			<div class="subRight">
				<a href=""> <img
					src="<%=request.getContextPath()%>/img/advance_1.jpg" height="70"
					width="220">
				</a> <a href=""> <img
					src="<%=request.getContextPath()%>/img/advance_2.jpg" height="140"
					width="210">
				</a>
			</div>
		</aside>
		<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
	</div>

</body>
</html>