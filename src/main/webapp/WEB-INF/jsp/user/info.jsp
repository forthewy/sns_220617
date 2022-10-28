<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<section class="d-flex justify-content-center">
		<div class="mb-3 col-4">
			<h1>회원 정보 수정</h1>
				<div class="infoBox border p-3 bg-light">
					<div>
						<label for="profileImg">프로필 이미지</label><br>
						<input type="file" name="file" id="file" accept=".jpg,.jpeg,.webp,.gif,.png">
					</div>
					<div>
						<label for="loginId">ID</label>
						<input type="text" class="form-control col-5 text-dark mb-3" name="userLoginId" disabled placeholder="${userLoginId}"/>
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
						<input type="text" class="form-control col-9" id="name" name="name" placeholder="${userName}">
					</div>
					<div class="form-group">
						<label>이메일</label>
						<input type="text" class="form-control col-9" id="email" name="email" placeholder="${userEmail}">
					</div>
					<div class="d-flex justify-content-between mb-3">
						<button type="button" id="userInfoUpdateBtn" class="btn btn-info mr-3">수정 하기</button>
						<button type="button" id="resignBtn" class="btn btn-primary">탈퇴 하기</button>
					</div>
				</div>
         </div>
     </section>
</div>

<script>
	$(document).ready(function() {
		//alert("회원 정보 화면");
			$('#file').on('change', function(e) {
				let fileName = e.target.files[0].name;
				//alert(fileName);
				let ext = fileName.split('.').pop().toLowerCase();
				let arr = ["jpg", "jpeg", "webp", "gif", "png"];
			
				if (fileName.split('.').length < 2 || !arr.includes(ext)) {
					alert("이미지 파일만 업로드 할수 있습니다");
					$(this).val("");
					fileName.val("");
					return; 
				} 
			}); // 프로필 사진 첨부
		
		
		$('#userInfoUpdateBtn').on('click', function(e) {
			e.preventDefault();
			
			let file = $('#file').val();
			let password = $('#password').val();
			let passwordConfirm = $('#passwordConfirm').val();
			
			if (password != passwordConfirm) {
				alert("비밀번호가 비밀번호 확인과 일치하지 않습니다");
				return;
			}
			
			let formData = new FormData();
			formData.append(data["name"], data["value"]);
			formData.append('file', file[0].files[0]);
			
			
			$.ajax({
				type: "POST"
				, data:formData
				, url:"/user/update"
				, enctype: "multipart/form-data"
				, processData: false
				, contentType: false
				, success:function(data) {
					if (data.code == 300) {
						alert(data.result);
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert("회원정보 수정에 실패했습니다. 관리자에게 문의해주세요");
				}
			});
		});
		
	});

</script>