package cursojava.interfaces;

/*Essa � a Interface ser� o contrato de autentica��o*/
public interface PermitirAcesso {
	
	public boolean autenticar(String login, String senha);
	public boolean autenticar();


}


