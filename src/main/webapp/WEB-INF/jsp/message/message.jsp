<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="w-100 d-flex justify-content-center">
	<div class="message-box bg-secondary w-50">
		<%-- 메세지 주고 받은 내역 --%>
		<div class="message-history d-flex align-items-end mb-4">
			<div class="message-history">
				<c:forEach items="${messageList}" var="message">
					<c:choose>
						<c:when test="${message.userIdSender eq userId}">
							<div class="d-flex justify-content-end align-items-center">
								<span class="text-white mr-3"><fmt:formatDate value="${message.createdAt}" pattern="HH:mm"/></span>
								<div class="messageCard card bg-warning mb-3 mr-3">
									<div class="card-body">
										<h5>${message.content}</h5>
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="d-flex justify-content-start align-items-center">
								<div class="messageCard card bg-info mb-3 ml-3">
									<div class="card-body">
										<h5>${message.content}</h5>
									</div>
								</div>
								<span class="text-white ml-3"><fmt:formatDate value="${message.createdAt}" pattern="HH:mm"/></span>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<%-- 메세지 보내기 --%>
		<div class="d-flex justify-content-between input-group">
			<input type="text" class="form-control" id="messageContent" placeholder="메세지를 입력하세요">
			<button type="button" id="sendBtn" class="btn btn-lighty" data-receiver-id="${receiverId}">보내기</button>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		// 가장 최근 메세지를 보도록 스크롤 이동
		window.scrollTo({ left: 0, top: 400, behavior: "smooth" });
		
		// 메세지 보내기
		$('#sendBtn').on('click', function() {
			let messageContent = $('#messageContent').val();
			let receiverId = $(this).data('receiver-id');
			
			if (messageContent == "") {
				alert("메세지가 입력되지 않았습니다");
				return;
			}
			
			$.ajax({
				type:"POST"
				, data:{"content":messageContent, "receiverId":receiverId}
				, url: "/message/create"
				,success:function(data) {
					if (data.code == 300) {
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				,error:function(e) {
					alert("메세지 전송에 실패했습니다. 관리자에게 문의해주세요");
				}
			}); // ajax 끝
		});
	})

</script>