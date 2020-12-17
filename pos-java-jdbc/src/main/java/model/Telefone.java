package model;

public class Telefone {
	
	private long id_tel;
	private String numero_tel;
	private String tipo_tel;
	private long usuariopessoa;
	
	
	public long getId_tel() {
		return id_tel;
	}
	public void setId_tel(long id_tel) {
		this.id_tel = id_tel;
	}
	public String getNumero_tel() {
		return numero_tel;
	}
	public void setNumero_tel(String numero_tel) {
		this.numero_tel = numero_tel;
	}
	public String getTipo_tel() {
		return tipo_tel;
	}
	public void setTipo_tel(String tipo_tel) {
		this.tipo_tel = tipo_tel;
	}
	public long getUsuariopessoa() {
		return usuariopessoa;
	}
	public void setUsuariopessoa(long usuariopessoa) {
		this.usuariopessoa = usuariopessoa;
	}
	
	
	@Override
	public String toString() {
		return "Telefone [id_tel=" + id_tel + ", numero_tel=" + numero_tel + ", tipo_tel=" + tipo_tel
				+ ", usuariopessoa=" + usuariopessoa + "]";
	}
	
	
	
	

}
