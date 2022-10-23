<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="post-box d-flex justify-content-center pt-5 pb-5">
	<div class="w-50">
		<div class="border bg-white">
			<textarea id="postContent"></textarea>
			<div class="bg-white d-flex justify-content-between"> <%-- 파일첨부 및 게시 --%>
				<img src="/static/img/camera.webp" width="35px">
				<button type="button" class="btn btn-info">게시</button>
			</div>
		</div>
		<div class="post-card mt-3 border">
			<div class="post-header bg-secondary d-flex justify-content-between">
				<h4 class="mt-3 ml-3">작성자 닉네임</h4>
				<img src="/static/img/more-icon.png" alt="modal">
			</div>
			<img src="/static/img/whale.jpg" alt="포스트사진" class="w-100" height="500px">
			<div class="like-bar ml-3">
				<img src="/static/img/heart-icon-fill.png" width="20px">
				좋아요
			</div>
			<div class="pt-3 pl-3">
				<b>작성자아이디</b>
				<span class="pl-3">포스트 영역 컨텐트입니다 작성한 글이 나옵니다</span>
			</div>
			<div>
				<div class="m-3">
					<b>댓글</b>
				</div>
				<hr>
				<div class="m-3">
					<b>id1</b>
					<span>댓글 1입니다</span>
					<img src="/static/img/x-icon.png" alt="삭제버튼" width="10px">
				</div>
				<div class="m-3">
					<b>id2</b>
					<span>댓글 2입니다</span>
					<img src="/static/img/x-icon.png" alt="삭제버튼" width="10px">
				</div>
			</div>
			<div class="comment-bar d-flex justify-content-between input-group">
				<input type="text" class="comment-input form-control" placeholder="댓글 달기">
				<div class="input-group-append">
					<button type="button" class="btn btn-lighty">게시</button>
				</div>
			</div>
		</div>
	</div>
</div>