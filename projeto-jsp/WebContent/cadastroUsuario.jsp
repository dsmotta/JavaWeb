<%@page import="beans.BeanCursoJsp"%>
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
<script type="text/javascript" src="resources/js/webservices.js"></script>
<title>Cadastro de Usuários</title>
</head>
<body>
	<a href="acessoliberado.jsp" style="text-decoration: inherit"><img
		alt="Home" src="resources/img/home.png" width="40px" height="40px"></a>
	<a href="index.jsp" style="text-decoration: inherit"><img
		alt="Sair" src="resources/img/Sair.gif" width="40px" height="40px"></a>

	<div class="form-style-6">

		<h1>Cadastro Usuários</h1>

		<form action="salvarUsuario" method="post" id="cadUser"
			onsubmit="return validaCamposLogin() ? true : false"
			enctype="multipart/form-data">

			<table>

				<tr>
					<td>Código:</td>
					<td><input type="text" readonly="readonly" id="id" name="id"
						value="${user.id_user}"></td>
				</tr>
				<tr>
					<td>Login:</td>
					<td><input type="text" id="login" name="login"
						value="${user.login}" placeholder="Login"></td>

					<td>Senha:</td>
					<td><input type="password" id="senha" name="senha"
						value="${user.senha}" placeholder="Senha" maxlength="15"></td>
				</tr>
				<tr>
					<td>Nome:</td>
					<td><input type="text" id="nome" name="nome"
						value="${user.nome}" placeholder="Nome do usuário"></td>

					<td>Cep:</td>
					<td><input type="text" id="cep" name="cep"
						onblur="consultarCep()" value="${user.cep}"
						placeholder="Informe o CEP" maxlength="10"></td>

				</tr>
				<tr>
					<td>End.:</td>
					<td><input type="text" id="rua" name="rua" value="${user.rua}"
						placeholder="Infome a Rua"></td>

					<td>N.o:</td>
					<td><input type="text" id="numero" name="numero"
						value="${user.numero}" placeholder="Informe o Numero"></td>
				</tr>
				<tr>
					<td>Bairro:</td>
					<td><input type="text" id="bairro" name="bairro"
						value="${user.bairro}" placeholder="Informe o Bairro"></td>

					<td>Cidade:</td>
					<td><input type="text" id="cidade" name="cidade"
						value="${user.cidade}" placeholder="Informe a Cidade"></td>

				</tr>
				<tr>
					<td>Estado:</td>
					<td><input type="text" id="estado" name="estado"
						value="${user.estado}" placeholder="Informe o Estado"></td>
					<td>Sexo:</td>
					<td><input type="radio" name="sexo"
						<%if (request.getAttribute("user") != null) {
	BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
	if (user.getSexo().equalsIgnoreCase("masculino")) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");
	}
}%>
						value="masculino">Masc.</input> <input type="radio" name="sexo"
						<%if (request.getAttribute("user") != null) {
	BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
	if (user.getSexo().equalsIgnoreCase("feminino")) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");
	}
}%>
						value="feminino">Fem.</input></td>

				</tr>

				<tr>

					<td>Perfil:</td>
					<td><select id="perfil" name="perfil">
							<option value="nao_informado">--SELECIONE--</option>
							<option value="administrador"
								<%if (request.getAttribute("user") != null) {
	BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
	if (user.getPerfil().equalsIgnoreCase("administrador")) {
		out.print(" ");
		out.print("selected=\"selected\"");
		out.print(" ");
	}
}%>>Administrador</option>

							<option value="secretario"
								<%if (request.getAttribute("user") != null) {
	BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
	if (user.getPerfil().equalsIgnoreCase("secretario")) {
		out.print(" ");
		out.print("selected=\"selected\"");
		out.print(" ");
	}
}%>>Secretario(a)</option>

							<option value="gerente"
								<%if (request.getAttribute("user") != null) {
	BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
	if (user.getPerfil().equalsIgnoreCase("gerente")) {
		out.print(" ");
		out.print("selected=\"selected\"");
		out.print(" ");
	}
}%>>Gerente</option>

							<option value="funcionario"
								<%if (request.getAttribute("user") != null) {
	BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
	if (user.getPerfil().equalsIgnoreCase("funcionario")) {
		out.print(" ");
		out.print("selected=\"selected\"");
		out.print(" ");
	}
}%>>Funcionario</option>
					</select></td>

					<td>Ativo:</td>
					<td><input type="checkbox" id="ativo" name="ativo"
						<%if (request.getAttribute("user") != null) {

	BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");

	if (user.isAtivo()) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");
	}
}%>>
					</td>
				</tr>
				<tr>
					<td>Foto:</td>
					<td colspan="4"><input type="file" name="foto"></td>

				</tr>
				<tr>
					<td>Curriculo:</td>
					<td colspan="4"><input type="file" name="curriculo"
						value="Curriculo"></td>
				</tr>
			</table>

			<table>
				<tbody>
					<tr>

						<td><input id="salva" type="submit"
							style="width: 70px; height: 30px" value="Salvar"></td>
						<td><input id="cancela" type="button"
							style="width: 70px; height: 30px" value="Cancelar"
							onclick="resetForm()"></td>
					</tr>
				</tbody>

			</table>
			<p align="center"
				style="color: red; font-size: 15px; font-weight: bold">${msg}</p>


		</form>
	</div>

	<div class="form-style-6">
		<form action="servletPesquisa" method="post" id="cadPesquisa">
			<center>
				<table>
					<tr>
						<td>Descrição</td>
						<td><input type="text" id="descricaoConsulta"
							name="descricaoConsulta" style="height: 30px"></td>
						<td><input type="submit" value="Pesquisar"
							style="width: 70px; height: 30px"></td>
						<td><input type="button" value="Limpar"
							onclick="limpaPesquisa()" style="width: 70px; height: 30px"></td>
					</tr>
				</table>
		</form>
	</div>

	<table class="blueTable">
		<thead>
			<tr>
				<th>Cod.</th>
				<th>Login/E-mail</th>
				<th>Foto</th>
				<th>Curriculo</th>
				<th>Nome</th>
				<th>Cep</th>
				<th>Endereço</th>
				<th>N.o</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>Excluir</th>
				<th>Editar</th>
				<th>Telefone</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id_user}"></c:out></td>
					<td><c:out value="${user.login }"></c:out></td>

					<c:if test="${user.fotoBase64Mini.isEmpty() == false}">
						<td align="center"><a
							href="salvarUsuario?acao=download&tipo=imagem&user=${user.id_user}">
								<img title="Usuario" alt="Usuario"
								src="<c:out value='${user.fotoBase64Mini}'></c:out>"
								width="35px" height="35px">
						</a></td>
					</c:if>
					<c:if test="${user.fotoBase64Mini == null}">
						<td align="center"><img title="Usuario" alt="Usuario"
							src="resources/img/nofoto2.png" width="35px" height="35px"
							onclick="alert('Usuário não possui imagem!')">
					</c:if>
					<c:if test="${user.curriculoBase64.isEmpty() == false }">
						<td align="center"><a
							href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id_user}">
								<img title="Curriculo" alt="Curriculo"
								src="resources/img/curriculo1.png" width="32px" height="32px">
						</a></td>
					</c:if>
					<c:if test="${user.curriculoBase64 == null}">
						<td align="center"><img title="Curriculo" alt="Curriculo"
							src="resources/img/nocurriculo1.png" width="32px" height="32px"
							onclick="alert('Usuário não possui curriculo!')"></td>
					</c:if>

					<td><c:out value="${user.nome }"></c:out></td>
					<td><c:out value="${user.cep }"></c:out></td>
					<td><c:out value="${user.rua }"></c:out></td>
					<td><c:out value="${user.numero }"></c:out></td>
					<td><c:out value="${user.bairro }"></c:out></td>
					<td><c:out value="${user.cidade }"></c:out></td>
					<td><c:out value="${user.estado }"></c:out></td>

					<td align="center"><a
						href="salvarUsuario?acao=delete&user=${user.id_user}"
						onclick="return confirm('Confirma e exclusão?');"><img
							title="Excluir" alt="Excluir" src="resources/img/excluir.png"
							width="25px" height="25px"></a></td>

					<td align="center"><a
						href="salvarUsuario?acao=editar&user=${user.id_user}"><img
							title="Editar" alt="Editar" src="resources/img/editar.gif"
							width="25px" height="25px"></a></td>

					<td align="center"><a
						href="TelefonesServlet?acao=lista&user=${user.id_user}"><img
							title="Telefone" alt="Telefone" src="resources/img/telefone2.gif"
							width="25px" height="25px"></a></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<i>Total de Usuários: ${cont_usuarios }</i>

</body>

<script type="text/javascript">
	function limpaPesquisa() {
		document.getElementById("descricaoConsulta").value = " ";
		document.getElementById("cadPesquisa").action = "servletPesquisa?acao=reset";
		document.getElementById("cadPesquisa").submit();
	}
</script>

</html>