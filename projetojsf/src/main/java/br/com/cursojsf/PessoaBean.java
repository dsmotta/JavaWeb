package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;


@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {
	
	private String nome;
	private String senha;
	private String texto;
	
	private List<String>listaNomes = new ArrayList<String>();
	
	 private HtmlCommandButton commandButton;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public List<String> getListaNomes() {
		return listaNomes;
	}
	public void setListaNomes(List<String> listaNomes) {
		this.listaNomes = listaNomes;
	}
	public HtmlCommandButton getCommandButton() {
		return commandButton;
	}
	public void setCommandButton(HtmlCommandButton commandButton) {
		this.commandButton = commandButton;
	}
	
	
	
	public String addNome() {
		listaNomes.add(nome);
		if(listaNomes.size() > 2) {
			commandButton.setDisabled(true);
			return "paginanavegada?faces-redirect=true";
		}
		return ""; //NULL ou VAZIO fica na mesma pagina --> chamado de OUTCOME
	}
		
}
