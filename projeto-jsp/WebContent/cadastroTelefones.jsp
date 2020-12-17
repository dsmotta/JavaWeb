<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/cadastro.css">
<script type="text/javascript" src="resources/js/mask.js"></script>
<script type="text/javascript" src="resources/js/validacoes.js"></script>
<script type="text/javascript" src="resources/js/jquery-3.5.1.min.js"></script>
<title>Cadastro de Telefones</title>
</head>
<body>
	<a href="acessoliberado.jsp" style="text-decoration: inherit"><img
		alt="Home" src="resources/img/home.png" width="40px"
		height="40px"></a>
	<a href="salvarUsuario?acao=listartodos" style="text-decoration: inherit"><img
		alt="Voltar" src="resources/img/voltar2.gif" width="40px"
		height="40px"></a>
	<a href="index.jsp" style="text-decoration : inherit" ><img alt="Sair" src="resources/img/Sair.gif" 
		width="40px" height="40px"></a>

	<div class="form-style-6">

		<h1>Cadastro Telefones</h1>

		<form action="TelefonesServlet?acao=editar&user=${userEscolhido.id_user}&tel=${tel.id}"
			onsubmit="return validaCamposFone()? true : false" method="post" id="cadTel">

			<table>

				<tr>
					<td>Usuário:</td>
					<td><input type="text" readonly="readonly" id="id" name="id" value="${userEscolhido.id_user}"></td>
					<td><input type="text" readonly="readonly" id="nome"  name="nome" value="${userEscolhido.nome}"></td>
				</tr>

				<tr>
					<td>Número:</td>
					<td><input oninput="mascara(this,'tel')" type="text"
						id="numero" name="numero" class="form-control"
						autocomplete="off" value="${tel.numero}" placeholder="Infome o telefone"></td>
						
					<td><select id="tipo" name="tipo">
					
						<option value="">Selecione</option>
						<option value="Celular">Celular</option>
						<option value="Casa">Casa</option>
						<option value="Recado">Recado</option>
						<option value="Comercial">Comercial</option>
						
					</select></td>
				</tr>
			</table>
			<table>
				<tbody>
					<tr>
						<td />
						<td><input id="salva" type="submit" style="width: 70px; height: 30px"
							value="Salvar"></td>
						
					
					</tr>

				</tbody>

			</table>
			<p align="center"
				style="color: red; font-size: 15px; font-weight: bold">${msg}</p>


		</form>
	</div>


	<table class="blueTable">
		<thead>
			<tr>
				<th>Cod.</th>
				<th>Numero</th>
				<th>Tipo</th>
				<th>Excluir</th>
				<th>Editar</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td><c:out value="${fone.id}"></c:out></td>
					<td><c:out value="${fone.numero}"></c:out></td>
					<td><c:out value="${fone.tipo}"></c:out></td>
					
					<td align="center"><a
						href="TelefonesServlet?acao=delete&tel=${fone.id}&user=${userEscolhido.id_user}" onclick="return confirm('Confirma e exclusão?');"><img
							title="Excluir" alt="Excluir" src="resources/img/excluir.png"
							width="20px" height="20px"></a></td>
							
					<td align="center"><a
						href="TelefonesServlet?acao=editar&tel=${fone.id}&user=${userEscolhido.id_user}"><img
							title="Editar" alt="Editar" src="resources/img/editar.gif"
							width="20px" height="20px"></a></td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<i>Total de Telefones: ${contaTelefones} </i>

</body>
</html>