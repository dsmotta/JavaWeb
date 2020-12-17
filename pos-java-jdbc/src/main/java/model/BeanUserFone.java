package model;

public class BeanUserFone {

	private String Nome;
	private String numero;
	private String tipo;
	private String email;
	
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "BeanUserFone [Nome=" + Nome + ", numero=" + numero + ", tipo=" + tipo + ", email=" + email + "]";
	}
	
	
	
	
	
	
	
}
