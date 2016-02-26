<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<style type="text/css">
		/* 三角形 */
		#Arrow{    
			width:0px;    
			height:0px;    
			border-left: 5px solid transparent;   
			border-right: 5px solid transparent; 
			border-top:5px solid #efefef;
			float: right;
			margin-top: 5px;
			margin-left: 5px;
		}

		#hidePanel{
			position: relative;
			z-index: 1;
			width: 1200px;
		}
		#hidePanel ul{
			padding:0px;
			margin-top: 0px;
			margin-left: 150px;
			list-style-type: none;
			position: absolute;
			padding: 10px;
			background-color:#F1FBEC; 
			border-left: 5px solid #3a3a3a;
			border-right: 5px solid #3a3a3a;
			border-bottom: 5px solid #3a3a3a;

		}
		#hidePanel ul li{
			float:left;
			margin:5px;
		}
		#hidePanel ul li div{
			text-align: center;
			color: #3a3a3a;
		}
		.trans-fadeout{
			-webkit-transition:all 0.5s linear;
			-moz-transition:all 0.5s linear;
			-ms-transition:all 0.5s linear;
			-o-transition:all 0.5s linear;
			transition:all 0.5s linear;
		}
		.hideFadeout{
			visibility:hidden; 
			opacity:0;
		}
		.showFadeout{ 
			visibility:visible; 
			opacity:1;
		}
	</style>
<div class="navgate">
	<div class="navgateBGImage"></div>
	<div class="navBar">
		<a
			href="<%=request.getContextPath()%>/comic/comiclist"
			class="navGrid navFirstGrid">全部分类<span class="fontLine"></span></a>
		 <a
			class="navGrid fontStyle">魔幻<span class="fontLine"></span></a> <a
			class="navGrid fontStyle">玄幻<span class="fontLine"></span></a> <a
			class="navGrid fontStyle">机战<span class="fontLine"></span></a> <a
			class="navGrid fontStyle">魔幻<span class="fontLine"></span></a> <a
			class="navGrid fontStyle">动作<span class="fontLine"></span></a> <a
			class="navGrid fontStyle">爱情<span class="fontLine"></span></a> <a
			class="navGrid fontStyle">耽美<span class="fontLine"></span></a> 
			 <div  id="panelList" class="navGrid" onMouseOver="ShowHidePanel()" onMouseOut="HidePanel()">
					<div>LO事迹<span id="Arrow"></span></div>
			</div>
			<div class="navGrid">
				<form action="<%= request.getContextPath()%>/comic/search" method="get">
					<input type="search" name="keyword" />
					<input type="submit" class="button" />
				</form>
			</div>
	</div>
</div>
<div id="hidePanel" class="navgate" onMouseOver="ShowHidePanel()" onMouseOut="HidePanel()">
			<ul class="trans-fadeout hideFadeout">
				<li>
					<a href=""><img class="cardImageWrap" src="<%=request.getContextPath() %>/img/advance_1.jpg" height="70" width="220" /></a>
					<div>我的小苹果</div>
				</li>
				<li>
					<a href=""><img class="cardImageWrap" src="<%=request.getContextPath() %>/img/advance_1.jpg" height="70" width="220" /></a>
					<div>我的小苹果</div>
				</li>
				<li>
					<a href=""><img class="cardImageWrap" src="<%=request.getContextPath() %>/img/advance_1.jpg" height="70" width="220" /></a>
					<div>我的小苹果</div>
				</li>
				<li>
					<a href=""><img class="cardImageWrap" src="<%=request.getContextPath() %>/img/advance_1.jpg" height="70" width="220" /></a>
					<div>我的小苹果</div>
				</li>
				
			</ul>
</div>
<script type="text/javascript">
function ShowHidePanel(){
	$("#hidePanel > ul").eq(0).attr("class","trans-fadeout showFadeout");
	$("#Arrow").attr("class","arrowHover");
}
function HidePanel(){
	$("#hidePanel > ul").eq(0).attr("class","trans-fadeout hideFadeout");
	$("#Arrow").attr("class","");
}
</script>