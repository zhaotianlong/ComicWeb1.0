<%@page import="org.springframework.ui.Model"%>
<%@page import="model.Comic"%>
<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<aside id="loveComic" class="right">
	<div class="subscription">
		<h1>我的动态</h1>
		<jsp:include page="AuthorTrend.jsp"></jsp:include>
</aside>
