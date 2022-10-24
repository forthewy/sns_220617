<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<section class="d-flex justify-content-center">
		<div class="mb-3 col-4">
			<h1>회원 가입</h1>
			<form method="post" id="signUpForm" action="/user/sign_up">
				<div class="signUpBox border p-3 bg-light">
					<div>
						<label for="loginId">ID</label>
	                    <div class="bg-light col-5">${userLoginId}</div>
	            	</div>
					<div class="form-group">
						<label>password</label>
						<input type="password" id="password" name="password" class="form-control col-7">
					</div>
					<div class="form-group">
						<label>confirm password</label>
						<input type="password" id="passwordConfirm"  class="form-control col-7">
					</div>
	 				<div class="form-group">
						<label>이름</label>
						<input type="text" class="form-control col-9" id="name" name="name" placeholder="이름을 입력해주세요">
					</div>
					<div class="form-group">
						<label>이메일</label>
						<input type="text" class="form-control col-9" id="email" name="email" placeholder="이메일을 입력해주세요">
					</div>
					<div class="d-flex justify-content-between mb-3">
						<button type="submit" id="userInfoUpdateBtn" class="btn btn-info mr-3">수정 하기</button>
						<button type="submit" id="signUpBtn" class="btn btn-primary">탈퇴 하기</button>
					</div>
				</div>
            </form>
         </div>
     </section>
</div>