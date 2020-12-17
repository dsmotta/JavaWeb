package entidades;

public class Series {

	private Long id;
	private String nome;
	private String inicio;
	private String fim;
	private Long id_projeto;
	
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
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFim() {
		return fim;
	}
	public void setFim(String fim) {
		this.fim = fim;
	}
	public Long getId_projeto() {
		return id_projeto;
	}
	public void setId_projeto(Long id_projeto) {
		this.id_projeto = id_projeto;
	} 
	
	
	
}
