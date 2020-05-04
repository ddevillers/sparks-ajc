<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
 
  
<!DOCTYPE html>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ma 1ère page</title>
</head>
<body>



<form method="POST" action="connect">
	<input type="text" name="login" value="${sessionScope.login}" placeholder="login">
	<input type="password" name="password" placeholder="password">
	
	 <c:if test = "${sessionScope.isConnect=='N'}">
		<div id="error">Login/Password invalides</div>
	</c:if>
	
	<br>
	<input type="submit" value="Valider">
</form>
	
</body>
</html>