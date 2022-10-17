<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </head>
    <body>
        <div>
            <header class="bg-secondary pt-3 pl-4" style="height: 100px;">
                <h1><b>stargram</b></h1>
            </header>
            <section class="d-flex justify-content-center">
                <div class="mb-3 col-4">
                    <h1>회원 가입</h1>
                    <div class="border p-3 bg-light" style="box-shadow: 10px 5px 5px rgb(201, 200, 200);">
                        <div class="form-group">
                            <label>ID</label>
                            <div class="d-flex">
                                <input type="text" class="form-control mr-3" placeholder="아이디를 입력해주세요">
                                <button type="button" class="btn btn-primary">중복 확인</button>
                            </div>
                            <div class="text-danger d-none">
                                사용중인 아이디입니다.
                            </div>
                        </div>
                        <div class="form-group">
                            <label>password</label>
                            <input type="password" class="form-control col-7">
                        </div>
                        <div class="form-group">
                            <label>confirm password</label>
                            <input type="password" class="form-control col-7">
                        </div>
                        <div class="form-group">
                            <label>이름</label>
                            <input type="text" class="form-control col-9" placeholder="이름을 입력해주세요">
                        </div>
                        <div class="form-group">
                            <label>이메일</label>
                            <input type="text" class="form-control col-9" placeholder="이메일을 입력해주세요">
                        </div>
                        <div class="d-flex justify-content-center mb-3">
                            <button type="button" class="btn btn-primary">가입하기</button>
                        </div>
                    </div>
                </div>
            </section>
            <footer class="bg-secondary text-center" style="height: 100px">
                <h3 class="pt-4">Copyright forthewy</h3>
            </footer>
        </div>
    </body>
</html>
