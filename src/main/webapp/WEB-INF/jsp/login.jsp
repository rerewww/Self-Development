<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<title>Login V10</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="/resources/css/login_util.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/login_main.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/font-awesome.min.css">
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-50 p-b-90">
				<form action="/main" class="login100-form validate-form flex-sb flex-w" method="post">
					<span class="login100-form-title p-b-51" style="font-weight: bold;">
						Algorithmus
					</span>

					
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
						<input class="input100" type="text" name="userId" style="font-weight: bold;" placeholder="Username">
						<span class="focus-input100"></span>
					</div>
					
					
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
						<input class="input100" type="password" name="pw" style="font-weight: bold;" placeholder="Password">
						<span class="focus-input100"></span>
					</div>
					
					<div class="flex-sb-m w-full p-t-3 p-b-24">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
							<label class="label-checkbox100" for="ckb1">
								Remember me
							</label>
						</div>
						<div>
							<a href="#" class="txt1">
								Sign Up
							</a>
							<a href="#" class="txt1">
								Forgot?
							</a>
						</div>
					</div>

					<div class="container-login100-form-btn m-t-17">
						<button class="login100-form-btn" style="font-weight: bold; width: 65%;">
							Login
						</button>
						<div style="width: 5%;"></div>
						<button onclick="loginManager.guestLogin();" class="login100-form-btn" style="font-weight: bold; width: 30%; background-color: gray;">
							Guest
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
	<script src="/resources/lib/jquery.min.js"></script>
	<script src="/resources/lib/bootstrap.bundle.min.js"></script>
	<script src="/resources/lib/popper.min.js"></script>
	<script src="/resources/js/login.js"></script>
</body>
</html>