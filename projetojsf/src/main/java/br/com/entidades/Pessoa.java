package br.com.entidades;

import java.io.Serializable;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.type.LocalDateType;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.constraints.br.TituloEleitoral;

import net.bytebuddy.asm.Advice.Local;

import java.util.Calendar;

@Entity
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = -7032452935271345677L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@Size(min = 10, max = 50, message = "Nome deve ter entre 10 e 50 letras!")
	private String nome;
	@NotEmpty(message = "Sobrenome deve ser informado!")
	@NotNull(message = "Sobrenome deve ser informado!")
	private String sobrenome;
	@DecimalMax(value = "50", message = "Idade deve ser menor que 50")
	@DecimalMin(value = "10", message = "Idade deve ser maior que 10!")
	private Integer idade;
	@Temporal(TemporalType.DATE)
	private java.util.Date dataNascimento = new java.util.Date();
	private String sexo;
	private String [] frameworks;
	private Boolean ativo;
	private String login;
	private String senha;
	private String perfilUser;
	private String nivelProgramador;
	private Integer[] linguagens;
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	@CPF(message = "Cpf Inválido!")
	private String cpf;
	@TituloEleitoral(message = "Título eleitoral inválido!")
	private String tituloEleitoral;
	
	@Transient /*não fica persistente ou não grava no banco*/
	private Estados estados;
	
	@ManyToOne
	private Cidades cidades;
	
	@Column(columnDefinition = "text") /*TIPO TEXT GRAVA ARQUIVOS EM BASE 64*/
	private String fotoIconBase64;
	
	private String extensao; /* EXTENSÃO JFG, PNG, JPEG, PNH */
	
	@Lob /*GRAVAR ARQUIVOS NO BANCO DE DADOS*/
	@Basic(fetch = FetchType.LAZY) /* FAZ O CARREGAMENTO SOMENTE QUANDO É CHAMADO*/
	private byte[] fotoIconBase64Original;
	
	public Pessoa() {
	
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public java.util.Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(java.util.Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String[] getFrameworks() {
		return frameworks;
	}
	public void setFrameworks(String[] frameworks) {
		this.frameworks = frameworks;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
	public String getPerfilUser() {
		return perfilUser;
	}
	public void setPerfilUser(String perfilUser) {
		this.perfilUser = perfilUser;
	}
	
	public String getNivelProgramador() {
		return nivelProgramador;
	}
	public void setNivelProgramador(String nivelProgramador) {
		this.nivelProgramador = nivelProgramador;
	}
	public Integer[] getLinguagens() {
		return linguagens;
	}
	public void setLinguagens(Integer[] linguagens) {
		this.linguagens = linguagens;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	public Estados getEstados() {
		return estados;
	}
	public void setEstados(Estados estados) {
		this.estados = estados;
	}
	public Cidades getCidades() {
		return cidades;
	}
	public void setCidades(Cidades cidades) {
		this.cidades = cidades;
	}
	
	public String getFotoIconBase64() {
		return fotoIconBase64;
	}

	public void setFotoIconBase64(String fotoIconBase64) {
		this.fotoIconBase64 = fotoIconBase64;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getFotoIconBase64Original() {
		return fotoIconBase64Original;
	}

	public void setFotoIconBase64Original(byte[] fotoIconBase64Original) {
		this.fotoIconBase64Original = fotoIconBase64Original;
	}
	
	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTituloEleitoral() {
		return tituloEleitoral;
	}

	public void setTituloEleitoral(String tituloEleitoral) {
		this.tituloEleitoral = tituloEleitoral;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
