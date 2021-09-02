<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/signin.css" rel="stylesheet" type="text/css" />

	 	<script src="js/login.js"></script>
	<title>Document</title>
</head>
 <body>
    <div class="container">
        <div class="top"></div>
        <div class="bottom"></div>
        <div class="center">
    <form name="frmLogin" action="main.jsp" method="post" encType="UTF-8">
            <h2>Please Sign In</h2>
            <input type="text" placeholder="id" name="user_id"/>
            <input type="password" placeholder="password" name="user_pwd" />
                 
            <input type="submit" value="로그인" class="myButton">
            <h2>&nbsp;</h2>
      </form> 

        </div>
    </div>
</body>


</html>
	