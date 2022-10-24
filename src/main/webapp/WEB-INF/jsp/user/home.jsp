<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center bg-light">
	<div>
		<div class="porfile-box d-flex align-items-center pl-5">
			<img src="${homeUser.profileImgPath}" alt="프로필 사진" id="homeProfileImg" onerror="this.src='/static/img/stargram.png'">
			<div>
				<h1 class="ml-5">${homeUser.loginId}</h1>
				<ul class="nav ml-3">
					<li class="nav-item">
						<a class="nav-link" href="#"><img src="/static/img/person.webp" alt="팔로워" width="30px">팔로워</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#"><img src="/static/img/person.webp" alt="팔로잉" width="30px">팔로잉</a>
					</li>
				</ul>
			</div>
			<div class="pl-5">
				<c:if test="${homeUser.loginId ne userLoginId}">
					<button type="button" class="btn btn-secondary mr-2" id="followBtn">팔로우</button>
					<button type="button" class="btn btn-light border border-secondary mr-2" id="followedBtn">팔로잉</button>
					<button class="btn btn-info">메세지 보내기</button>
				</c:if>
			</div>
		</div>
		<div id="homePosts" class="bg-secondary">
		</div>
	</div>
	
</div>
<script>
	$(document).ready(function() {
		
		
		$('#followBtn').on('click', function() {
			
			$.ajax({
				url:"/follow/create"
				,data:{"followedUserId":"${homeUser.id}"}
				,success:function(data) {
					if (data.code == 300) {
						alert(data.result);
					} else {
						alert(data.errorMessage);
					}
				}
				,error:function(e) {
					alert(e);
				}
			});
		});
		
		$('#followedBtn').on('click', function() {
			
			$.ajax({
				url:"/follow/delete"
				,data:{"followedUserId":"${homeUser.id}"}
				,success:function(data) {
					if (data.code == 300) {
						alert(data.result);
					} else {
						alert(data.errorMessage);
					}
				}
				,error:function(e) {
					alert(e);
				}
			});
		});
	});
</script>