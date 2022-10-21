<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header-box d-flex justify-content-between align-items-center">
	<div>
		<h1><b>stargram</b></h1>
	</div>
	<div>
		<c:if test="${not empty userName}">
			<span>${userName}회원님 안녕하세요</span>
			<a href="/user/sign_out" class="ml-3">로그아웃</a>
		</c:if>
	</div>
</div>