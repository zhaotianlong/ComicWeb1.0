<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
	#arrow2{    
			width:0px;    
			height:0px;    
			border-left: 5px solid transparent;   
			border-right: 5px solid transparent; 
			border-top:5px solid #C9AF2B;
			float: left;
			margin-top: 8px;
			margin-left: 5px;
		}
		
		.arrowHover{    
			-moz-transform:rotateZ(180deg); 
			-webkit-transform:rotateZ(180deg); 
			-o-transform:rotateZ(180deg);  
			transform:rotateZ(180deg);   
			transition:0.5s;   
			-moz-transition:-moz-transform 0.5s; /* Firefox 4 */   
			-webkit-transition:-webkit-transform 0.5s; /* Safari and Chrome */   
			-o-transition:-o-transform 0.5s; /* Opera */
		}
</style>
<script type="text/javascript">
$("html").click(function(e){
	if(e.target.id.indexOf("iconTarget")!=-1||e.target.id.indexOf("topIcon")!=-1||e.target.id.indexOf("arrow2")!=-1){
		$("#topHiddePanle > ul").eq(0).addClass("active");
		$("#arrow2").attr("class","arrowHover");
		}
	else{
		$("#topHiddePanle > ul").eq(0).removeClass("active");
		$("#arrow2").attr("class","");
	}
});
</script>
<header class="top">
	<div class="top_container">
		<div class="top_grid">
			<ul class="pcNavgate textLeft">
				<li><a class="grid_wrap">
						<div class="mobileImg">
							<img
								src="<%=request.getContextPath()%>/img/intdex-SLevel/web16.png">
						</div>
						<div class="webFont">LOLO网</div>
						<div></div>
				</a></li>
				<li><a class="grid_wrap">
						<div class="mobileImg">
							<img
								src="<%=request.getContextPath()%>/img/intdex-SLevel/shopingcart16.png">
						</div>
						<div class="shopingcartFont">周边</div>
						<div class="mobile3"></div>
				</a></li>
				<li><a class="grid_wrap">
						<div class="mobileImg">
							<img
								src="<%=request.getContextPath()%>/img/intdex-SLevel/share16.png">
						</div>
						<div class="shareFont">分享到</div>
						<div class="mobile3"></div>
				</a></li>
			</ul>
		</div>
		<div class="top_grid">
			<%
				if (session.getAttribute("user") != null) {
					
					User user=(User)session.getAttribute("user");
					String userNickName=user.getNickName();
					String userId=user.getAccountId();
					String userIconPath=user.getIconPath();
			%>
			<ul class="pcNavgate textRight">
				<li class="grid_wrap">
					<a>
							<div class="topIconText">
								<span id="iconTarget" class="textLeft"><%=userNickName%></span>
								<span id="arrow2"></span>
								<div  id="topHiddePanle" class="topHiddePanle">
									<ul>
										<li>
											<a href="<%=request.getContextPath()%>/personcenter/home"> <span>个人中心</span></a>
										</li>
										<li>
											<a href="<%=request.getContextPath()%>/personcenter/home"> <span>通知中心</span></a>
										</li>
										<li>
											<a href="<%=request.getContextPath()%>/homepage/logout"> <span>退出</span></a>
										</li>
									</ul>
								</div>
							</div>
							<div class="topIcon">
								<img id="topIcon" src="<%=request.getContextPath()+"/"+userIconPath %>" />
							</div>
					</a>
				</li>
			</ul>
			<%
				} else {
			%>
			<ul class="pcNavgate textRight">
				<li><a href="<%=request.getContextPath()%>/homepage/login"
					class="grid_wrap login">登录</a></li>
				<li><a href="<%=request.getContextPath()%>/homepage/resist"
					class="grid_wrap regist">注册</a></li>
			</ul>
			<%
				}
			%>
		</div>
	</div>
</header>
