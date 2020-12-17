<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="resources/css/cadastro.css">
<script type="text/javascript" src="resources/js/validacoes.js"></script>
<script type="text/javascript" src="resources/js/mask.js"></script>
<script src="resources/js/jquery.min.js" type="text/javascript"></script>
<script src="resources/js/jquery.maskMoney.min.js" type="text/javascript"></script>

<title>Cadastro de Produtos</title>
</head>


<body>
<a href="acessoliberado.jsp" style="text-decoration : inherit" ><img alt="Home" src="resources/img/home.png" 
width="40px" height="40px"></a>
<a href="index.jsp" style="text-decoration : inherit" ><img alt="Sair" src="resources/img/Sair.gif" 
width="40px" height="40px"></a>

	<div class="form-style-6">
	
		<h1>Cadastro Produtos</h1>
		
		<form action="ProdutoServlet" method="post" id="cadProduto">

			<table>
				<tr>
					<td>Cod:</td>
					<td><input type="text" id="cod_prod" name="cod_prod"
						readonly="readonly" value="${pr.id_prod}"></td>
				</tr>
				<tr>
					<td>Descrição:</td>
					<td><input type="text" id="desc_prod" name="desc_prod" value="${pr.desc_prod}" maxlength="100"></td>
				</tr>
				<tr>
					<td>Qtde:</td>
					<td><input onfocus="maskQtd()" type="text" id="qtd_prod" name="qtd_prod" value="${pr.qtdTexto}" maxlength="10"></td>
				</tr>
				<tr>
					<td>Valor R$:</td>
					<td><input onfocus="maskMoeda()" type="text" id="prc_prod" name="prc_prod" value="${pr.valorTexto}" maxlength="20"></td>
				</tr>
				<tr>
					<td>Categoria:</td>
					<td>
						<select id="categoria" name="categoria_id">
							<c:forEach items="${categoria}" var="cat">
								<option value="${cat.id }" id="${cat.id }"
									<c:if test="${cat.id == pr.categoria_id }">
										<c:out value = "selected=selected"/>
									</c:if>
								>
									${cat.nome}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>

			</table>
			<table>
				<tbody>
					<tr>

						<td><input type="button" onclick="validaCamposProduto()" style="width: 70px; height: 30px"
							value="Salvar"></td>
						<td><input type="button" style="width: 70px; height: 30px"
							value="Cancelar"
							onclick="resetFormProd()"></td>

					</tr>

				</tbody>

			</table>
							
				<p align="center" style="color: red; font-size: 15px; font-weight: bold ">${msg}</p>
		</form>
		
	</div>
	
	<table class="blueTable">
		<thead>
			<tr>
				<th>Cod.</th>
				<th>Descrição</th>
				<th>Qtde.</th>
				<th>Valor R$</th>
				<th>Excluir</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${produtos}" var="prod" >
				<tr>
					<td><c:out value="${prod.id_prod}"></c:out></td>
					<td><c:out value="${prod.desc_prod}"></c:out></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${prod.qtd_prod}"/>
					<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${prod.preco_prod}"  />
					<td align="center"><a href="ProdutoServlet?acao=delete&prod=${prod.id_prod}" onclick="return confirm('Confirma e exclusão?');"><img alt="Excluir" 
					src="resources/img/excluir.png" width="20px" height="20px"></a></td>
					<td align="center"><a href="ProdutoServlet?acao=editar&prod=${prod.id_prod}"><img alt="Editar" 
					src="resources/img/editar.gif" width="20px" height="20px"></a></td>
					
				</tr>
			</c:forEach>
		
		</tbody>
		
	</table>
	<i>Total de Produtos: ${cont_produto}</i>
</body>

</html>