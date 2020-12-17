<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>Upload Files</title>
</head>
<body>
	<h1>Upload</h1>
	
	<form action="servletFileUpload" method="post" id=form_upload enctype="multipart/form-data">
		<table>
			<tr>
				<input type="file" id="file" name="file" onchange="uploadFile()" />
				<img alt="Imagem" src="" id="target" width="100" height="100">
			</tr>
		</table>
	</form>
	</br>
	</br>
	</br>
	<a href="servletFileUpload?acao=carregar">Carregar Imagens</a>
	</br>
	</br>
	<table>
		<c:forEach items="${listaUserImagem}" var="user">
			<tr>
				<td>${user.iduser}</td>
				<td>${user.login}</td>
				<td>${user.senha}</td>
				<td><a href="servletFileUpload?acao=download&iduser=${user.iduser}">Download Arquivo</a></td>
								
			</tr>
		</c:forEach>
	</table>
	
</body>

<script type="text/javascript">
	function uploadFile() {
		var target = document.querySelector("img");
		var file = document.querySelector("input[type=file]").files[0];

		var reader = new FileReader();

		reader.onloadend = function() {
			target.src = reader.result;
			
			//------------upload ajax-------------//
			$.ajax({
				method: "POST",
				url: "servletFileUpload",
				data: {fileUpload : reader.result}
			})
			.done(function(response){
				alert("sucesso: " + response);
				
			})
			.fail(function(xhr, status, errorThrown){
				alert("Error: " + xhr.responseText);
			});	
				
			//--------upload ajax-------------//
		};

		if (file) {
			reader.readAsDataURL(file);
			
		} else {
			target.src = "";
		}
	}
	
</script>
</html>