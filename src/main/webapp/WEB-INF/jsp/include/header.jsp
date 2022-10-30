<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header-box d-flex justify-content-between align-items-center">
	<div>
		<%-- 타임라인 화면 --%>
		<a href="/timeline/timeline_view"><h1 class="pl-3"><b>stargram</b></h1></a>
	</div>
	<div>
		<c:if test="${not empty userName}">
			<%-- 홈화면 이동 --%>
			<a href="/home/home_view/${userLoginId}"><img src="/static/img/house.png" width="30px" alt="홈화면 이동"></a>
			<%-- 메세지 리스트화면 이동 --%>
			<a href="/message/messageList_view"><img src="/static/img/letter.webp" width="30px" alt="메세지 리스트 이동"></a>
			<%-- 검색화면 이동 --%>
			<a href="/user/search_view"><img src="/static/img/magnifying-glass.png" width="30px" alt="검색 화면 이동"></a>
			<%-- 회원정보 수정 --%>
			<a href="/user/info_view"><img src="/static/img/cogwheel.png" width="30px" class="mr-2"></a>
			<img src="${profileImg}" alt="프로필 사진" class="profileImg" onerror="this.style.display='none';">
			<%-- 홈화면 이동 --%>
			<a href="/home/home_view/${userLoginId}">${userName}</a>
			<span> 회원님 안녕하세요</span>
			<a href="/user/sign_out" class="ml-3">로그아웃</a>
		</c:if>
	</div>
</div>