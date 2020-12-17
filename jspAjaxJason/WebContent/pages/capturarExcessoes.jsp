<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Capturando Exceções com JQuery</h3>
	<input type="text" value="valor informado" id="txtvalor">
	<input type="button" onclick="testarExcecao();" value="Testar Exceção">
</body>

<script type="text/javascript">
	function testarExcecao(){
		var valorInformado = $('#txtvalor').val();
		
		$.ajax({
			method: "POST",
			url: "ServletCapturarExcecao", //para qual servlet
			data: { valorParam: valorInformado}
		})
			.done(function(response){//resposta ok - nenhum erro
				alert("Sucesso: " + response);
			
				///fazer algo
			})
		
			.fail(function(xhr, status, errorThrow){//resposta erro - algum problema
				alert("Error: " + xhr.responseText);//mostra resposta do servidor
		
				//fazer algo se der errado
		});
		
	}


</script>


</html>