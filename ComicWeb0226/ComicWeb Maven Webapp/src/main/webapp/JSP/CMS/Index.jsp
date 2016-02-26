<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台模板管理系统</title>
<link type="text/css" rel="stylesheet" href="<%=path %>/css/personCenter.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/style.css" />
<script type="text/javascript" src="<%=path %>/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=path %>/js/menu.js"></script>
<script type="text/javascript">
	function tab(id){
		//alert(id);
		var urlStr="<%=path%>/cms/"+id;
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
</head>

<body>
<div class="top"></div>
<div id="header">
	<div class="logo">***后台管理系统</div>
	<div class="navigation">
		<ul>
		 	<li>欢迎您！</li>
			<li><a href="">张山</a></li>
			<li><a href="">修改密码</a></li>
			<li><a href="">设置</a></li>
			<li><a href="">退出</a></li>
		</ul>
	</div>
</div>
<div id="content">
	<div class="left_menu">
				<ul id="nav_dot">
      <li>
          <h4 class="M1"><span></span>系统公告</h4>
      </li>
      <li>
          <h4   class="M10"><span></span>个人信息</h4>
          <div  class="list-item none">
            <a  onclick="tab('modifyinfo')">修改个人信息</a>
            <a	onclick="tab('modifypassword')">修改密码</a>
            <a 	onclick="tab('modifyicon')">修改头像</a>
          </div>
        </li>
       <li>
          <h4 class="M2"><span></span>用户管理</h4>
          <div class="list-item none">
            <a 	onclick="tab('usermanage')">读者管理</a>
            <a 	onclick="tab('authormanage')">作者管理</a>         
           </div>
        </li>
        <li>
          <a onclick="tab('comicmanage')"><h4 class="M3"><span></span>漫画管理</h4></a>
        </li>
		<li>
          <h4 class="M4"><span></span>发言管理</h4>
       	  <div class="list-item none">
            <a onclick="tab('commentmanage')">评论管理</a>
            <a onclick="tab('spitslotmanage')">吐槽管理</a>         
          </div>
        </li>
		<li>
            <a onclick="tab('usercomicmanage')"><h4 class="M5"><span></span>订阅管理</h4></a>
        </li>
        <li>
            <a onclick="tab('userauthormanage')"><h4 class="M5"><span></span>关注管理</h4></a>
        </li>
		 <li>
          <a onclick="tab('comictrendmanage')"><h4  class="M6"><span></span>动态管理</h4></a>
        </li>
		<li>
          <h4  class="M6"><span></span>充值 </h4>
        </li>
  </ul>
		</div>
		<div class="m-right">
			<div class="right-nav">
					<ul>
							<li><img src="<%=path%>/img/cms/home.png"></li>
								<li style="margin-left:25px;">您当前的位置：</li>
								<li><a href="#">系统公告</a></li>
								<li>></li>
								<li><a href="#">最新公告</a></li>
						</ul>
			</div>
			<div id="usrPanelContent" class="main">
				
				<jsp:include page="Home.jsp"></jsp:include>
			</div>
		</div>
</div>
<!-- 
<div class="bottom"></div>
<div id="footer"><p>Copyright©  2015 版权所有 京ICP备05019125号-10  来源:<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p></div>
 -->
<script>navList(12);</script>
</body>
</html>

