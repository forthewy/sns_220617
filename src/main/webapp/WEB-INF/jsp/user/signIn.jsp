<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="signin-box d-flex d-flex align-items-center justify-content-center">
	<form method="post" action="/user/sign_in" id="signInForm">
		<div class="form-group input-group">
		<div class="input-group-prepend">
			<span class="input-group-text">아이디</span>
        </div>
        <input type="text" class="form-control" id="loginId" name="loginId">
    </div>
    <div class="form-group input-group">
       	<div class="input-group-prepend">
            <span class="input-group-text">비밀번호</span>
        </div>
         <input type="password" class="form-control" id="password" name="password">
    </div>
    <diV class="d-flex justify-content-center align-items-center">
		<button type="submit" class="btn btn-primary w-50 mr-3" id="logInBtn">로그인</button>
		<button type="button" class="btn btn-dark w-50" onclick="location.href='/user/sign_up_view'">회원가입</button>
     </diV>
 </form>
</div>


<script>
	$(document).ready(function(e) {
		$('#signInForm').on('submit', function(e) {
			e.preventDefault();
			
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val();
			
			if (loginId == "") {
				alert("아이디를 입력하세요");
				return false;
			} 
			if (password.length < 1) {
				alert("비밀번호를 입력하세요");
				return false;
			}
			
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			$.post(url, params)
			.done(function(data) {
				if (data.code == 300) {
					location.href = "/timeline/timeline_view"
				} else {
					alert(data.errorMessage);
				}
			});
		});
	})
</script>
   