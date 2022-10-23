<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<section class="d-flex justify-content-center">
		<div class="mb-3 col-4">
			<h1>회원 가입</h1>
			<form method="post" id="signUpForm" action="/user/sign_up">
				<div class="signUpBox border p-3 bg-light">
					<div class="form-group">
						<label for="loginId">ID</label>
	                    <div class="d-flex">
	                    	<input type="text" class="form-control mr-3" placeholder="아이디를 입력해주세요" id="loginId" name="loginId">
	                    	<button type="button" class="btn btn-primary" id="isDuplicateBtn">중복 확인</button>
	    		      	</div>
	                	<div class="text-danger d-none" id="idCheckDuplicated">
							사용중인 아이디입니다.
	                	</div>
						<div class="text-danger d-none" id="idCheckLength">
							아이디를 4글자 이상 입력해주세요
						</div>
						<div class="text-success d-none" id="idCheckOk">
							사용가능한 아이디입니다.
						</div>
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
					<div class="d-flex justify-content-center mb-3">
						<button type="submit" id="signUpBtn" class="btn btn-primary">가입하기</button>
					</div>
				</div>
            </form>
         </div>
     </section>
</div>