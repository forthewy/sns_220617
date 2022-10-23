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
        <script>
        	$(document).ready(function(e) {
        		$('#loginId').keyup(function() {
        			$('#idCheckDuplicated').addClass('d-none');
        			$('#idCheckOk').addClass('d-none');
        			$('#idCheckLength').addClass('d-none');
        		}); // ID를 이후에 변경하면 중복체크를 다시해야한다.
        		
        		
        		$('#isDuplicateBtn').on('click', function(e) {
        			e.preventDefault();
        			let loginId = $('#loginId').val().trim();
        			
        			if (loginId.length < 4) {
        				$('#idCheckDuplicated').addClass('d-none');
        				$('#idCheckOk').addClass('d-none');
        				$('#idCheckLength').removeClass('d-none');
        				return;
        			} 
        			
        			
        	
        			$.ajax({
        				url:"/user/is_duplicated_id"
        				,data:{"loginId":loginId}
        				,success:function(data) {
        					if (data.duplicate == false) {
        						$('#idCheckLength').addClass('d-none');
        						$('#idCheckDuplicated').addClass('d-none');
        						$('#idCheckOk').removeClass('d-none');
        					} else {
        						$('#idCheckLength').addClass('d-none');
        						$('#idCheckDuplicated').removeClass('d-none');
        						$('#idCheckOk').addClass('d-none');
        					}
        				}
        				,error:function(e) {
        					alert("중복 조회에 실패하였습니다. 관리자에게 문의하여주세요");
        				}
        			});
        		}); 
        		
        		$('#signUpForm').on('submit', function(e) {
        			e.preventDefault();
        			
        			let loginId = $('#loginId').val().trim();
        			let password = $('#password').val();
        			let passwordConfirm = $('#passwordConfirm').val();
        			let name = $('#name').val().trim();
        			let email = $('#email').val().trim();
        			
        			if (loginId.length < 1) {
        				alert ("아이디를 입력해주세요");
        				return false;
        			}
        			if (password.length < 1) {
        				alert ("비밀번호를 입력해주세요");
        				return false;
        			}
        			if (password != passwordConfirm) {
        				alert("비밀번호가 일치하지 않습니다");
        				return false;
        			}
        			if (name.length < 1) {
        				alert("이름을 입력해주세요");
        				return false;
        			}
        			if (email.length < 1) {
        				alert("이메일을 입력해주세요");
        				return false;
        			}
        			
        			if ($('#idCheckOk').hasClass('d-none')) {
        				alert("id 중복 확인을 다시해주세요");
        				return false;
        			}
        			
        			let url = $(this).attr('action');
        			let params = $(this).serialize(); // name 속성에 있는 값들을 파라미터로 구성
        			
        			//console.log(params);
        			
        			$.post(url, params)
        			.done(function(data) {
        				if (data.code == 200) {
        					alert("가입완료");
        					location.href = "/user/sign_in_view";
        				} else {
        					alert("가입에 실패했습니다");
        				} 
        			});
        		});
        	});
        </script>
    </body>
</html>
