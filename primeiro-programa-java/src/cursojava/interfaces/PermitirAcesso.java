package cursojava.interfaces;

/*Essa é a Interface será o contrato de autenticação*/
public interface PermitirAcesso {
	
	public boolean autenticar(String login, String senha);
	public boolean autenticar();


}


