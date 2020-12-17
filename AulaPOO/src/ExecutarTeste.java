import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;





public class ExecutarTeste {
	
	public static void main(String[] args) {
		
		List<Venda> vendas = new ArrayList<Venda>();
					
		int controle = 0;
		long id = 0;
		
		
		do {
			int controleProd = 0;
			long idprod = 0;
						
			int usu = JOptionPane.showConfirmDialog(null, "Iniciar Venda?");
			
						
			if (usu == 0) {
				
				String descricaoVenda = JOptionPane.showInputDialog("Descricao Venda: ");
				String nomeCliente = JOptionPane.showInputDialog("Cliente: ");
				String enderecoEntrega = JOptionPane.showInputDialog("Endereço Entrega:");
				
				Venda venda = new Venda();
				
				id = id + 1L;
				venda.setId(id);
				venda.setDescricaoVenda(descricaoVenda);
				venda.setEnderecoEntrega(enderecoEntrega);
				venda.setNomeCliente(nomeCliente);
				
				while (controleProd == 0) {
					
					int prod = JOptionPane.showConfirmDialog(null, "Lançar Produto?");
					
					if (prod == 0) {
						String nomeProduto = JOptionPane.showInputDialog("Nome Produto: ");
						String valorProduto = JOptionPane.showInputDialog("Valor: ");
						
						Produto produto = new Produto();
						
						idprod = idprod + 1L;
						produto.setId(idprod);
						produto.setNome(nomeProduto);
						BigDecimal val = new BigDecimal(valorProduto);
						produto.setValor(val);
						
						venda.getListaProdutos().add(produto);
						//venda.addProduto(produto); /*Chamada do metodo para adicionar os produtos*/
						
											
					}else {
						controleProd = 2;
						
					}
					
				}
							
				vendas.add(venda);
				
				int fim = JOptionPane.showConfirmDialog(null, "Finalizar Vendas??");
				if (fim == 0) {
				
					controle = 1;
					for (Venda venda2 : vendas) {
					
						venda2.getValorTotal();
						System.out.println(venda2);
						System.out.println("--------------------------------------------------");
									
					}
					
				}
				
			}
						
					
		}while (controle == 0);
		
		System.exit(0);
			
			
		
		
	}

}
