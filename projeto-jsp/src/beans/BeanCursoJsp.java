package beans;

public class BeanCursoJsp {

	private long id_user;
	private String login;
	private String senha;
	private String nome;
	private String telefone;
	private String cep;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String fotoBase64;
	private String fotoBase64Mini;
	private String contentType;
	private String curriculoBase64;
	private String contentTypeCurriculo;
	private String tempFotouser;
	private boolean ativo;
	private String sexo;
	private String perfil;
	
	private boolean atualizarImagem = true;
	private boolean atualizarPDF = true;
	
	
	
	public boolean isAtualizarImagem() {
		return atualizarImagem;
	}

	public void setAtualizarImagem(boolean atualizarImagem) {
		this.atualizarImagem = atualizarImagem;
	}

	public boolean isAtualizarPDF() {
		return atualizarPDF;
	}

	public void setAtualizarPDF(boolean atualizarPDF) {
		this.atualizarPDF = atualizarPDF;
	}

	public String getTempFotouser() {
		
		tempFotouser = "data:"+contentType+";base64,"+fotoBase64;
		
		return tempFotouser;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public long getId_user() {
		return id_user;
	}
	public void setId_user(long id_user) {
		this.id_user = id_user;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getFotoBase64() {
		return fotoBase64;
	}
	
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getCurriculoBase64() {
		return curriculoBase64;
	}
	
	public void setCurriculoBase64(String curriculoBase64) {
		this.curriculoBase64 = curriculoBase64;
	}
	
	public String getContentTypeCurriculo() {
		return contentTypeCurriculo;
	}
	
	public void setContentTypeCurriculo(String contentTypeCurriculo) {
		this.contentTypeCurriculo = contentTypeCurriculo;
	}
	
	public String getFotoBase64Mini() {
		return fotoBase64Mini;
	}
	
	public void setFotoBase64Mini(String fotoBase64Mini) {
		this.fotoBase64Mini = fotoBase64Mini;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	

}

	