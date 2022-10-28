<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="post-box d-flex justify-content-center pt-5 pb-5">
	<div class="w-50">
		<%-- 파일첨부 및 게시 --%>
		<div class="border bg-white">
			<textarea id="postContent"></textarea>
			<div class="bg-white d-flex justify-content-between"> 
				<div class="d-flex justify-content-start">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
					<input type="file" id="file" class="d-none" accept=".gif,.jpg,.jpeg,.png">
					<%-- 이미지에 마우스 올리면 마우스 커서가 링크 커서로 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img src="/static/img/camera.webp" width="35px"></a>
					
					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2">
					</div>
				</div>
				<button type="button" class="btn btn-info" id="createPostBtn">게시</button>
			</div>
		</div>
		<div>
		<%-- 여기서부터 포스트 카드들(타임라인 영역) --%>
		<c:forEach items="${cardList}" var="card">
			<div class="post-card mt-3 border">
				<div class="post-header bg-secondary d-flex justify-content-between">
					<%-- <img src="${card.user.profileImgPath}" width="20px"> --%>
					<h4 class="mt-3 ml-3">${card.user.loginId}</h4>
					<img src="/static/img/more-icon.png" alt="modal">
				</div>
				<img src="${card.post.imgPath}" alt="포스트사진" class="w-100" height="500px">
				
				<div class="like-bar ml-3">
					<c:choose>
						<c:when test="${card.filledLike}">
							<a href="#" class="likeBtn" data-post-id="${card.post.id}"><img src="/static/img/heart-icon-fill.png"  width="20px"></a>
						</c:when>
						<c:otherwise>
							<a href="#" class="likeBtn" data-post-id="${card.post.id}"><img src="/static/img/heart-icon-empty.png"  width="20px"></a>
						</c:otherwise>
					</c:choose>
					좋아요 ${card.likeCount}개
				</div>
				<div class="pt-3 pl-3">
					<b>${card.user.loginId}</b>
					<span class="pl-3">${card.post.content}</span>
				</div>
				<div>
					<div class="m-3">
						<b>댓글</b>
					</div>
					<hr>
					<%-- 댓글 목록 --%>
					<c:forEach items="${card.commentList}" var="comment">
							<div class="m-3">
								<b>${comment.user.loginId}</b>
								<span>${comment.comment.content}</span>
								<img src="/static/img/x-icon.png" alt="삭제버튼" width="10px">
							</div>
					</c:forEach>
				</div>
				<div class="comment-bar d-flex justify-content-between input-group">
					<input type="text" class="comment-input form-control" placeholder="댓글 달기">
					<button type="button" class="commentBtn btn btn-lighty" data-post-id="${card.post.id}">게시</button>
				</div>
			</div>
			<c:remove var="post"/>
		</c:forEach>
		</div>
	</div>
</div>


<script>
$(document).ready(function() {
	// 파일 업로드 이미지 (a) 클릭 => 파일 선택 창이 떠야함
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); // a태그의 기본동작 멈춤 (화면이 위로 올라가는 것 방지)
		$('#file').click(); // input file 을 클릭하는 것과 같은 효과
	});
	
	// 사용자가 파일 업로드를 했을때, 유효성 확인 및 업로드 된 파일 이름 노출
	$('#file').on('change', function(e) {
		/* alert("체인지"); */
		let fileName = e.target.files[0].name; // ex) more-icon.png
		//alert(fileName);
		let ext = fileName.split('.').pop().toLowerCase();
		
		// 유효성 확인
		if (fileName.split('.').length < 2 || 
				(ext != 'gif'
						&& ext != 'jpg'
							&&	ext != 'jpeg'
								&&	ext != 'png'))  {
			alert("이미지 파일만 업로드 할 수 있습니다");
			$(this).val(''); // 파일 태그에 실제 파일 제거
			$('#fileName').text(''); // 파일 이름 비우기
			return;
		}
		
		// 상자에 업로드 된 이름 노출
		$('#fileName').text(fileName);
	});
	
	$('#createPostBtn').on('click', function() {
		//alert('포스트 게시 버튼 클릭');
		let file = $('#file').val();
		let postContent = $('#postContent').val();
		
		if (file == "") {
			alert("사진을 첨부해주세요");
			return;
		}
		if (postContent == "") {
			alert("내용을 기재해주세요");
			return;
		}
		
		let formData = new FormData();
		formData.append('file', $('#file')[0].files[0]);
		formData.append('content', postContent);
		
		$.ajax({
			type:"POST"
			, url:"/post/create"
			, data:formData
			, enctype: "multipart/form-data"
			, processData: false
			, contentType: false
			, success:function(data) {
				if (data.code == 300) {
					location.reload();
				} else if (data.code == 550) {
					alert(data.errorMessage);
					location.href = "/user/sign_in_view";
				} else {
					alert(data.errorMessage);
				}
			}
			, error:function(e) {
				alert("포스트 등록에 실패했습니다. 관리자에게 문의해주세요");
			}
		}); // -- ajax 끝
		
	}); // 글쓰기 버튼 끝
	
	// 댓글 게시 버튼 클릭
	$('.commentBtn').on('click', function() {
		// 코멘트를 쓸 포스트 아이디
		let postId = $(this).data('post-id'); // data-post-id
		//alert(postId);
		// 지금 클릭된 게시버튼의 형제인 input 태그를 가져온다 (siblings)
		let comment = $(this).siblings('input').val().trim();
		
		 $.ajax({
			type:"POST"
			, url:"/comment/create"
			, data:{"comment":comment,"postId":postId}
			, success:function(data) {
				if (data.code == 300) {
					location.reload();
				} else if (data.code == 550) {
					location.href = "/user/sign_in_view";					
				} else {
					alert(data.errorMessage);
				}
			}
			, error:function(e) {
				alert(e);
			}
		});  // ajax 끝
	}); // 댓글 게시 버튼 클릭 끝
	
	// 좋아요 버튼
	 $('.likeBtn').on('click', function(e) {
		 e.preventDefault();
		 let postId = $(this).data('post-id');
		//alert(postId);
		
		$.ajax({
			url:"/like/" + postId
			, success:function(data) {
				if (data.code == 300) {
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			, error:function(e) {
				alert("좋아요/좋아요 취소에 실패했습니다. 관리자에게 문의해주세요");
			}
		});
	}); 
	
});

</script>