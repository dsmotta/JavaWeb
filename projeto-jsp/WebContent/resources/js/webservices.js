function consultarCep() {

	var cep = $("#cep").val();
	//Consulta o webservice viacep.com.br/
	$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {


		if (!("erro" in dados)) {
			
			$("#rua").val(dados.logradouro);
			$("#bairro").val(dados.bairro);
			$("#cidade").val(dados.localidade);
			$("#estado").val(dados.uf);

		}
		else {

			$("#cep").val('');
			$("#rua").val('');
			$("#bairro").val('');
			$("#cidade").val('');
			$("#estado").val('');

			alert("CEP nao encontrado.");
			
		}
		
	});

}

