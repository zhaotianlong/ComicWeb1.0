<%@ page language="java" import="java.util.*" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="top3Div">
	<div class="top3 color7">
		<span>用户</span>
		<div class="topFont">${userSum}人</div>
	</div>
	<div class="top3 color1">
		<span>作者</span>
		<div class="topFont">${authorSum}人</div>
	</div>
	<div class="top3 color2">
		<span>漫画</span>
		<div class="topFont">${comicSum}部</div>
	</div>
	<div class="top3 color3">
		<span>评论</span>
		<div class="topFont">${commentSum}条</div>
	</div>
	<div class="top3 color4">
		<span>订阅记录</span>
		<div class="topFont">${userComicSum}条</div>
	</div>
	<div class="top3 color5">
		<span>关注记录</span>
		<div class="topFont">${authorUserSum}条</div>
	</div>
	<div class="top3 color6">
		<span>付费记录</span>
		<div class="topFont">24条</div>
	</div>
</div>
