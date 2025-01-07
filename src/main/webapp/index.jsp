<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
body {
	font-family: 'Arial', sans-serif;
	background: linear-gradient(to bottom right, #6a11cb, #2575fc);
	color: #ffffff;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

center {
	text-align: center;
	background: rgba(255, 255, 255, 0.1);
	border-radius: 10px;
	padding: 30px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

a {
	display: inline-block;
	text-decoration: none;
	color: #ffffff;
	background: rgba(255, 255, 255, 0.2);
	padding: 10px 20px;
	border-radius: 25px;
	font-size: 18px;
	margin: 10px;
	transition: all 0.3s ease;
	box-shadow: 0 2px 10px rgba(255, 255, 255, 0.2);
}

a:hover {
	background: #ffffff;
	color: #6a11cb;
	box-shadow: 0 4px 15px rgba(255, 255, 255, 0.5);
	transform: scale(1.1);
}
</style>
</head>
<body>
	<center>

		<a href="signup.jsp">Sign Up</a><br> <a href="login.jsp">Login</a>


	</center>
</body>
</html>