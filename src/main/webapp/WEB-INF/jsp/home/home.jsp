<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center bg-light">
	<div>
		<%-- 홈유저의 프로필 박스 --%>
		<div class="porfile-box d-flex align-items-center pl-5">
			<img src="${home.user.profileImgPath}" alt="프로필 사진" id="homeProfileImg" onerror="this.src='/static/img/stargram.png'">
			<div>
				<h1 class="ml-5">${home.user.loginId}</h1>
				<div class="ml-3 d-flex">
					<%-- <li class="nav-item"> ${home.followerCount}
							<a href="#none" data-toggle="modal" data-target="#exampleModal" id="modalBtn"><img src="/static/img/person.webp" alt="팔로잉" width="30px">팔로잉 </a>
						</li> --%>
					<a href="#" data-toggle="modal" data-target="#exampleModal">
						팔로워 ${home.followingCount}
					</a>	
				</div>
			</div>
			<div class="pl-5">
				<%-- 팔로우 버튼, 메세지 버튼 --%>
				<c:if test="${home.user.loginId ne userLoginId}">
					<c:choose>
						<c:when test="${home.followOrNot  eq false}">
							<button type="button" class="followBtn btn btn-secondary mr-2" data-home-user-id="${home.user.id}">팔로우</button>
						</c:when>
						<c:when test="${home.followOrNot}">
							<button type="button" class="followBtn btn btn-light border border-secondary mr-2" data-home-user-id="${home.user.id}">팔로잉</button>
						</c:when>
					</c:choose>
					<button class="btn btn-info" onclick="location.href='/message/message_view?receiverId=${home.user.id}'">메세지 보내기</button>
				</c:if>
			</div>
		</div>
		<%-- 홈 유저의 포스트 목록 --%>
		<hr>
		<div id="homePosts">
			<div class="d-flex flex-wrap flew-row">
				<c:forEach items="${home.postList}" var="post">
					<div class="p-2">
						<img src="${post.imgPath}" width="235" height="200px">
					</div>
				</c:forEach>	
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="exampleModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
      	<table class="table">
      		<tbody>
		        <c:forEach items="${home.followeeList}" var="followee">
		        	<tr>
		        		<td><img src="${followee.profileImgPath}" width="30px"></td>
		        		<td>${followee.loginId}</td>
		        	</tr>
		        </c:forEach>
	        </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<script>
	$(document).ready(function() {
		
		$('.followBtn').on('click', function() {
			// 팔로우 취소. 해제를 토글로 변경
			let homeUserId = $(this).data('home-user-id');
			
			//alert(homeUserId);
			 $.ajax({
				url:"/follow/follow"
				,data:{"homeUserId":homeUserId}
				,success:function(data) {
					if (data.code == 300) {
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				,error:function(e) {
					alert(e);
				}
			});// ajax 끝 */
		}); // 팔로우 기능 끝
		
	});
</script>