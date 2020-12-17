

function validaCamposFone() {
		
	if (document.getElementById("numero").value == '') {
		alert('Informe o numero de telefone!');
		document.getElementById("numero").focus();
		return false;

	}else {
		
		var tipo = document.getElementById("tipo");
		if(tipo.options[tipo.selectedIndex].value == ""){
			alert("Selecione um tipo para o telefone!")
			return false;
		}
	} 
	return true;

	
}

function validaCamposLogin() {

	if (document.getElementById("login").value == '') {
		alert('Informe o login!');
		document.getElementById("login").focus();
		return false;

	} else if (document.getElementById("senha").value == '') {
		alert('Informe a senha!');
		document.getElementById("senha").focus();
		return false;

	} else if (document.getElementById("nome").value == '') {
		document.getElementById("nome").focus();
		alert('Informe o nome!');
		return false;

	} else if (document.getElementById("cep").value == '') {
		document.getElementById("cep").focus();
		alert('Informe o CEP!');
		return false;

	} else if (document.getElementById("rua").value == '') {
		document.getElementById("rua").focus();
		alert('Informe o endereco!');
		return false;

	} else if (document.getElementById("numero").value == '') {
		document.getElementById("numero").focus();
		alert('Informe o numero!');
		return false;

	} else if (document.getElementById("bairro").value == '') {
		document.getElementById("bairro").focus();
		alert('Informe o bairro!');
		return false;

	} else if (document.getElementById("cidade").value == '') {
		document.getElementById("cidade").focus();
		alert('Informe a Cidade!');
		return false;

	} else if (document.getElementById("estado").value == '') {
		document.getElementById("estado").focus();
		alert('Informe o Estado!');
		return false;
		
	} else {

		return true;

	}
}


function validaCamposProduto() {

	if (document.getElementById("desc_prod").value == '') {
		alert('Informe a descricao do produto!!');
		document.getElementById("desc_prod").focus();
		return false;

	} else if (document.getElementById("qtd_prod").value == '') {
		alert('Informe a quantidade!');
		document.getElementById("qtd_prod").focus();
		return false;

	} else if (document.getElementById("prc_prod").value == '') {
		alert('Informe o  preco!');
		document.getElementById("prc_prod").focus();
		return false;
		
	} else {

		document.getElementById("cadProduto").submit();
		return true;

	}
}

function resetFormProd() {

	limpaForm();

	document.getElementById("cadProduto").action = "ProdutoServlet?acao='reset'";
	document.getElementById("cadProduto").submit();

}


function resetForm() {

	limpaForm();

	document.getElementById("cadUser").action = "salvarUsuario?acao='reset'";
	document.getElementById("cadUser").submit();

}


function limpaForm() {
	var elements = document.getElementsByTagName("input");

	for (var i = 0; i < elements.length; i++) {

		if (elements[i].type == "text") {
			elements[i].value = "";

		} else if (elements[i].type == "radio") {
			elements[i].checked = "false";

		} else if (elements[i].type == "checkbox") {
			elements[i].checked = "false";

		} else if (elements[i].type == "select") {
			elements[i].selectedIndex = 0;

		} else if (elements[i].type == "password") {
			elements[i].value = "";

		} else if (elements[i].type == "number") {
			elements[i].value = "";

		}

	}
}

//validação para buscar CEP

function verificaCEP() {
	if ($('#check_cep').is(':checked')) {

		document.getElementById("cep").hidden = false;
		document.getElementById("cep").focus();
	} else {
		document.getElementById("cep").hidden = true;
		document.getElementById("rua").focus();
	}

}






