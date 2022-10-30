<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-100 search-box d-flex justify-content-center">
	<div class="d-flex bg-secondary w-50">
		<form method="get" action="/user/search" id="searchForm">
			<input type="text" class="form-control" id="searchLoginId" placeholder="검색어를 입력하세요">
			<button type="submit" class="btn btn-info" id="searchBtn">검색</button>		
		</form>
		<table class="table">
			<tbody>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.loginId}</td>
						<td>${user.profileImgPath}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#searchForm').on('submit', function(e) {
			e.preventDefault();
			let searchLoginId = $('#searchLoginId').val();
			
			$.ajax({
				data:{"searchLoginId":searchLoginId}
				,url: "/user/search_view"
				,success:function(data) {
					location.reload();
				}
				,error:function(e) {
					alert("조회에 실패했습니다. 관리자에게 문의해주세요");
				}
			});
		}); // 조회할때
	})

</script>