<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>


<head>

<meta charset="ISO-8859-1">

<link rel="stylesheet" href="resources/css/menu.css">


<title>Menu principal</title>

</head>

<body>
<a href="index.jsp" style="text-decoration : inherit" ><img alt="Sair" src="resources/img/Sair.gif" width="40px" height="40px"></a>
	<input type="checkbox" id="bt_menu">
	<label for="bt_menu">&#9776;</label>

	<h1 align="center">Sistema JSP</h1>

	<nav class="menu">
		<ul>
			<li><a href="#"><img alt="Usuários" src="resources/img/usuario.gif" 
			width="30px" height="30px" align="middle">Usuario</a>
				<ul>
					<li><a href="salvarUsuario?acao=listartodos">Cadastrar
							Usuarios</a>
					</li>
				</ul>
			</li>
			<li><a href="#"><img alt="Produtos" src="resources/img/produto.gif"
			width="30px" height="30px" align="middle">Produto</a>
				<ul>
					<li><a href="ProdutoServlet?acao=listaprodutos">Cadastrar Produtos</a></li>
					<li><a href="#">submenu2</a></li>
					<li><a href="#">submenu3</a></li>
				</ul>
			</li>
			<li><a href="#">Menu3</a></li>
			<li><a href="#">Menu4</a></li>
			<li><a href="#">Menu5</a></li>

		</ul>
	</nav>

</body>
</html>