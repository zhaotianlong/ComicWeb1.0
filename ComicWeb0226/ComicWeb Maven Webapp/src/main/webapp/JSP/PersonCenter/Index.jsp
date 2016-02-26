<%@page import="model.User"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
User t=(User)session.getAttribute("user");
%>
<aside class="right">
			<!--面板内容-->
			<div class="subLeft_pc">
				<div class="personContent">
					<h1>我的资料</h1>
					<div>
						生日:<%=t.getBornDate()%>
					</div>
					<div>
						手机:<%=t.getTel() %>
					</div>
					<div>
						邮箱:<%=t.getMail() %>
					</div>
					<div>
						<%if(t.getGender()=="M"){%>
							性别:男
							<%}else{ %>
							性别:女
						<%} %>
					</div>
				</div>
				<div class="messageContent">
					<div>
					    新回复:
					 <span>${commentsubNew_sum}</span>
					 条
					</div>
					<div>
					    我的评论:
					 <span>${comment_sum}</span>
					条
					</div>
					<div>
					 我的回复:
					 <span>${commentfrom_sum}</span>
					条
					</div>
					<div>
					收到回复:
						<span>${commentto_sum}</span>
						条
					</div>
					<div>
						关注动态:：
						<span>${comictrend_total}</span>
						条
					</div>
				</div>
				<div id="comicTrend">
					<jsp:include page="AuthorTrend.jsp"></jsp:include>
				</div>
			</div>
			<!--更新-->
			<div class="subRight_pc">
				<div class="myRead">
					<div class="myTitle">
						<h1>我的订阅</h1>
						<div>MORE+</div>
					</div>
					<ul class="myContent_list">
						<li>
							<a href=""><img src="<%=request.getContextPath() %>/img/personCenter/tmp_1.jpg" height="90" width="80" /></a>
							<span>武器少女</span>
						</li>
						<li>
							<a href=""><img src="<%=request.getContextPath() %>/img/personCenter/tmp_2.jpg" height="90" width="80" /></a>
							<span>真红前夜</span>
						</li>
						<li>
							<a href=""><img src="<%=request.getContextPath() %>/img/personCenter/tmp_3.jpg" height="90" width="80" /></a>
							<span>千年山海</span>
						</li>
						<li>
							<a href=""><img src="<%=request.getContextPath() %>/img/personCenter/tmp_4.jpg" height="90" width="80" /></a>
							<span>破晓世纪</span>
						</li>
						<li>
							<a href=""><img src="<%=request.getContextPath() %>/img/personCenter/tmp_5.jpg" height="90" width="80" /></a>
							<span>姐姐妄想日记</span>
						</li>
						<li>
							<a href=""><img src="<%=request.getContextPath() %>/img/personCenter/tmp_6.jpg" height="90" width="80" /></a>
							<span>小春的绷带dasdsasdasdasd</span>
						</li>
					</ul>
				</div>
				<div class="myRead">
					<div class="myTitle">
						<h1>最新更新</h1>
						<div>MORE+</div>
					</div>
					<ul class="update">
						<li>
							<a href="">
								<div>不属于战记</div>
								<div class="textRight">12.14</div>
							</a>
						</li>
						<li>
							<a href="">
								<div>头条都是他</div>
								<div class="textRight">12.14</div>
							</a>
						</li>
						<li>
							<a href="">
								<div>麻辣教师gto</div>
								<div class="textRight">12.14</div>
							</a>
						</li>
						<li>
							<a href="">
								<div>驱魔少年</div>
								<div class="textRight">12.14</div>
							</a>
						</li>
						<li>
							<a href="">
								<div>六天魔王</div>
								<div class="textRight">12.14</div>
							</a>
						</li>
						<li>
							<a href="">
								<div>飞轮少年</div>
								<div class="textRight">12.14</div>
							</a>
						</li>
						<li>
							<a href="">
								<div>圣杯战争</div>
								<div class="textRight">12.14</div>
							</a>
						</li>
						<li>
							<a href="">
								<div>出包女王</div>
								<div class="textRight">12.14</div>
							</a>
						</li>
						<li>
							<a href="">
								<div>心跳回忆</div>
								<div class="textRight">12.14</div>
							</a>
						</li>
						<li>
							<a href="">
								<div>草莓百分百</div>
								<div class="textRight">12.14</div>
							</a>
						</li>
					</ul>
				</div>
				<div class="myRead">
					<div class="myTitle">
						<h1>猜你喜欢</h1>
						<div>换一批</div>
					</div>
					<ul class="youLove">
						<li>
							<a href="">
								<div><img src="<%=request.getContextPath() %>/img/personCenter/tmp_1.jpg" height="60" width="40" /></div>
								<div>
									<span>武器少女</span>
									<span>作者:LOLO</span>
									<span>状态:连载中</span>
									<span>最新:第08话</span>
								</div>
							</a>
						</li>
						<li>
							<a href="">
								<div><img src="<%=request.getContextPath() %>/img/personCenter/tmp_2.jpg" height="60" width="40" /></div>
								<div>
									<span>真红前夜</span>
									<span>作者:LOLO</span>
									<span>状态:连载中</span>
									<span>最新:第08话</span>
								</div>
							</a>
						</li>
						<li>
							<a href="">
								<div><img src="<%=request.getContextPath() %>/img/personCenter/tmp_3.jpg" height="60" width="40" /></div>
								<div>
									<span>千年山海</span>
									<span>作者:LOLO</span>
									<span>状态:连载中</span>
									<span>最新:第08话</span>
								</div>
							</a>
						</li>
						<li>
							<a href="">
								<div><img src="<%=request.getContextPath() %>/img/personCenter/tmp_4.jpg" height="60" width="40" /></div>
								<div>
									<span>武器世纪</span>
									<span>作者:LOLO</span>
									<span>状态:连载中</span>
									<span>最新:第08话</span>
								</div>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<script type="text/javascript">
			function link(id){
				//alert(id);
				var urlStr="<%=path%>/personcenter/"+id;
				$.ajax({
					url:urlStr,
					async:false,
					error:function(){
						alert("ajax出错！");
					},
					success:function(data){
						$("#usrPanelContent").html(data)
					}
				});
			}
			</script>
			
		</aside>