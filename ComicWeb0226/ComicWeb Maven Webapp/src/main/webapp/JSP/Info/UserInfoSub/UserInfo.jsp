<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>

<article class='uno'>
<h2>
	<img src='<%=request.getContextPath()%>/${tagUser.iconPath}' />
	${tagUser.nickName}
	<span>加为好友</span>
</h2>
	<footer>
		<ul>
			<li class='fontawesome-link'></li>
			<li class='fontawesome-paper-clip'></li>
			<li class='fontawesome-sitemap'></li>
			<li class='fontawesome-wrench'></li>
			<li class='fontawesome-magic'></li>
		</ul>
	</footer>
</article>