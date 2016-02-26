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
					<jsp:include page="ComicListSub.jsp"></jsp:include>
				</div>
				
		</aside>
		<aside class="right">
			<!--漫画检索-->
			<div class="comicSearch">
				<div class="comicListTitle">
					<h1>漫画检索</h1>
				</div>
				<ul>
					<li>
						<ul class="searchComicType">
							<li><a href="" class="searchActiveItem">全部 ×</a></li>
							<li id="status"></li>
							<li id="charge"></li>
							<li id="type"></li>
							<li id="initial"></li>
						</ul>
					</li>
					<li>
						<div>
							<span class="searchItemIcon searchItemIcon_bg1"></span> <span
								class="searchFontTitle">状态</span>
						</div>
						<div>
							<ul class="searchComicType">
								<li><a onclick="InsertTab('status','','')">全部</a></li>
								<li><a onclick="InsertTab('status','连载中 ×','0')">连载中</a></li>
								<li><a onclick="InsertTab('status','已完结 ×','1')">已完结</a></li>
							</ul>
						</div>
					</li>
					<li>
						<div>
							<span class="searchItemIcon searchItemIcon_bg2"></span> <span
								class="searchFontTitle">收费</span>
						</div>
						<div>
							<ul class="searchComicType">
								<li><a onclick="InsertTab('charge','','')">全部</a></li>
								<li><a onclick="InsertTab('charge','收费 ×','0')">收费</a></li>
								<li><a onclick="InsertTab('charge','免费 ×','1')">免费</a></li>
							</ul>
						</div>
					</li>
					<li>
						<div>
							<span class="searchItemIcon searchItemIcon_bg3"></span> <span
								class="searchFontTitle">题材</span>
						</div>
						<div>
							<ul class="searchComicType">
								<li><a onclick="InsertTab('type','','')">全部</a></li>
								<li><a onclick="InsertTab('type','冒险  ×','1')">冒险</a></li>
								<li><a onclick="InsertTab('type','搞笑 ×','2')">搞笑</a></li>
								<li><a onclick="InsertTab('type','战斗 ×','3')">战斗</a></li>
								<li><a onclick="InsertTab('type','科幻 ×','4')">科幻</a></li>
								<li><a onclick="InsertTab('type','爱情 ×','5')">爱情</a></li>
								<li><a onclick="InsertTab('type','侦探 ×','6')">侦探</a></li>
								<li><a onclick="InsertTab('type','竞技 ×','7')">竞技</a></li>
								<li><a onclick="InsertTab('type','魔法 ×','8')">魔法</a></li>
								<li><a onclick="InsertTab('type','历史 ×','9')">历史</a></li>
								<li><a onclick="InsertTab('type','战争 ×','10')">战争</a></li>
								<li><a onclick="InsertTab('type','仙侠 ×','11')">仙侠</a></li>

							</ul>
						</div>
					</li>
					<li>
						<div>
							<span class="searchItemIcon searchItemIcon_bg4"></span> <span
								class="searchFontTitle">首字母</span>
						</div>
						<div>
							<ul class="searchComicType">
								<li><a onclick="InsertTab('initial','','')">全部</a></li>
								<li><a onclick="InsertTab('initial','首字母A ×','A')">A</a></li>
								<li><a onclick="InsertTab('initial','首字母B ×','B')">B</a></li>
								<li><a onclick="InsertTab('initial','首字母C ×','C')">C</a></li>
								<li><a onclick="InsertTab('initial','首字母D ×','D')">D</a></li>
								<li><a onclick="InsertTab('initial','首字母E ×','E')">E</a></li>
								<li><a onclick="InsertTab('initial','首字母F ×','F')">F</a></li>
								<li><a onclick="InsertTab('initial','首字母H ×','H')">H</a></li>
								<li><a onclick="InsertTab('initial','首字母I ×','I')">I</a></li>
								<li><a onclick="InsertTab('initial','首字母J ×','J')">J</a></li>
								<li><a onclick="InsertTab('initial','首字母K ×','K')">K</a></li>
								<li><a onclick="InsertTab('initial','首字母L ×','L')">L</a></li>
								<li><a onclick="InsertTab('initial','首字母M ×','M')">M</a></li>
								<li><a onclick="InsertTab('initial','首字母N ×','N')">N</a></li>
								<li><a onclick="InsertTab('initial','首字母O ×','O')">O</a></li>
								<li><a onclick="InsertTab('initial','首字母P ×','P')">P</a></li>
								<li><a onclick="InsertTab('initial','首字母Q ×','Q')">Q</a></li>
								<li><a onclick="InsertTab('initial','首字母R ×','R')">R</a></li>
								<li><a onclick="InsertTab('initial','首字母S ×','S')">S</a></li>
								<li><a onclick="InsertTab('initial','首字母T ×','T')">T</a></li>
								<li><a onclick="InsertTab('initial','首字母U ×','U')">U</a></li>
								<li><a onclick="InsertTab('initial','首字母V ×','V')">V</a></li>
								<li><a onclick="InsertTab('initial','首字母W ×','W')">W</a></li>
								<li><a onclick="InsertTab('initial','首字母X ×','X')">X</a></li>
								<li><a onclick="InsertTab('initial','首字母Y ×','Y')">Y</a></li>
								<li><a onclick="InsertTab('initial','首字母Z ×','Z')">Z</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
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
			</div>
		</aside>
		<jsp:include page="/JSP/Layout/footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
	
	var arr=new Array();
	arr['status']="";
	arr['charge']="";
	arr['type']="";
	arr['initial']="";
	arr['order']="newupdate";
	
	function InsertTab(itemId,itemText,value,Id){
		if(value!=-1)
		var str="<a class='searchActiveItem' onclick='DeleteTab(\""+itemId+"\")' >"+itemText+"</a>";
		$("#"+itemId).html(str);
		arr[itemId]=value;
		Requst();
	}
	function DeleteTab(itemId){
		$("#"+itemId).html("");
		arr[itemId]="";
		Requst();
	}
	function Requst(){
		var urlStr="<%=request.getContextPath()%>/comic/comiclistparam";
		$.ajax({
			url : urlStr,
			async : false,
			data:{"status":arr['status'],"charge":arr['charge'],"type":arr['type'],"initial":arr['initial'],"order":arr['order']},
			error : function() {
				alert("ajax出错！");
			},
			success : function(data) {
				$("#comicListSub").html(data);
			}
		});	
	}
	
	</script>
</body>
</html>
