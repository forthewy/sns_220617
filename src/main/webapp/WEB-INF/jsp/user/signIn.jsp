<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="signin-box d-flex d-flex align-items-center justify-content-center">
	<div>
		<div class="form-group input-group">
		<div class="input-group-prepend">
			<span class="input-group-text">아이디</span>
        </div>
        <input type="text" class="form-control" id="logInId">
    </div>
    <div class="form-group input-group">
       	<div class="input-group-prepend">
            <span class="input-group-text">비밀번호</span>
        </div>
         <input type="password" class="form-control" id="password">
    </div>
    <diV class="d-flex justify-content-center align-items-center">
		<button type="button" class="btn btn-primary w-50 mr-3" id="logInBtn">로그인</button>
		<button type="button" class="btn btn-dark w-50" onclick="location.href='/user/sign_up_view'">회원가입</button>
     </diV>
 </div>
</div>


<script>
	
</script>
   