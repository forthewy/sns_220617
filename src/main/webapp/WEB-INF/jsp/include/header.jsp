<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header-box d-flex justify-content-between align-items-center">
	<div>
		<a href="/timeline/timeline_view"><h1 class="pl-3"><b>stargram</b></h1></a>
	</div>
	<div>
		<c:if test="${not empty userName}">
			<a href="/user/info_view"><img src="/static/img/cogwheel.png" width="30px" class="mr-2"></a>
			<img src="${profileImg}" alt="프로필 사진" class="profileImg" onerror="this.style.display='none';">
			<a href="/home/home_view/${userLoginId}">${userLoginId}</a>
			<span> 회원님 안녕하세요</span>
			<a href="/user/sign_out" class="ml-3">로그아웃</a>
		</c:if>
	</div>
</div>