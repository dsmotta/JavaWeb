<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="resources/css/estilo.css">

<title>Login</title>
</head>
<body>

<h1><center>JSP + Servlet + JDBC</center></h1>

	<div class="form-style-6">
	<img alt="Voltar" src="resources/img/login2.gif" width="50px" height="50px">
	
		<h1>Login</h1>

		<form action="LoginServlet" method="post">
			Login/E-mail: <input type="text" id="login" name="login" placeholder="Informe o login"> <br />
			Senha: <input type="password" id="senha" name="senha" placeholder="Informe a senha"> <br />
			<input type="submit" value="Entrar">

		</form>

	</div>



</body>
</html>