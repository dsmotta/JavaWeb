package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoProduto daoProduto = new DaoProduto();

	public ProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String prod = request.getParameter("prod");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroProduto.jsp");
						
			if (acao != null && acao.equalsIgnoreCase("listaprodutos")) {

				request.setAttribute("produtos", daoProduto.listaProduto());
				request.setAttribute("cont_produto", daoProduto.contaProduto());

			} else if (acao != null && acao.equalsIgnoreCase("delete")) {

				daoProduto.deletaProduto(Long.parseLong(prod));

				request.setAttribute("produtos", daoProduto.listaProduto());
				request.setAttribute("cont_produto", daoProduto.contaProduto());

			} else if (acao != null && acao.equalsIgnoreCase("editar")) {

				BeanProduto produto = new BeanProduto();
				produto = daoProduto.consultaProd(prod);
				
				request.setAttribute("pr", produto);
				request.setAttribute("cont_produto", daoProduto.contaProduto());
			}else {
				request.setAttribute("produtos", daoProduto.listaProduto());
				request.setAttribute("cont_produto", daoProduto.contaProduto());
				
			}
			
			request.setAttribute("categoria", daoProduto.listaCategorias());
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String valorParse = null;
		String qtdParse = null;
		boolean salva = false, atualiza = false;
		try {

			String acao = request.getParameter("acao");

			if (acao != null && acao.equalsIgnoreCase("'reset'")) {

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listaProduto());
				request.setAttribute("cont_produto", daoProduto.contaProduto());
				dispatcher.forward(request, response);

			} else {

				String id = request.getParameter("cod_prod");
				String descr = request.getParameter("desc_prod");
				String qtd = request.getParameter("qtd_prod");
				String prc = request.getParameter("prc_prod");
				String categoria = request.getParameter("categoria_id");

				BeanProduto produto = new BeanProduto();
				
				if(prc != null && !prc.isEmpty()) {
					valorParse = prc.replaceAll("\\.", "");
					valorParse = valorParse.replaceAll("\\,", ".");
				}
				if(qtd != null && !qtd.isEmpty()) {
					qtdParse = qtd.replaceAll("\\.", "");
					qtdParse = qtdParse.replaceAll("\\,", ".");
				}

				produto.setId_prod(!id.isEmpty() ? Long.parseLong(id) : 0);
				produto.setDesc_prod(descr);
				produto.setQtd_prod(Float.parseFloat(qtdParse));
				produto.setPreco_prod(Float.parseFloat(valorParse));
				produto.setCategoria_id(Long.parseLong(categoria));

				if (id == null || id.isEmpty() || Long.parseLong(id) == 0) {

					if (descr == null || descr.isEmpty()) {
						request.setAttribute("msg", "Informe a Descrição do produto!");
					}else if(qtd == null || qtd.isEmpty()) {									
						request.setAttribute("msg", "Informe a quantidade do produto!");
					}else if(prc == null || prc.isEmpty()) {
						request.setAttribute("msg", "Informe o valor do produto!");
					} else {
						daoProduto.salvarProduto(produto);
						request.setAttribute("msg", "Produto incluido!");
						salva = true;
						atualiza = true;
					}

				} else {

					if (descr == null || descr.isEmpty()) {
						request.setAttribute("msg", "Informe a Descrição do produto!");
						salva = false; atualiza = false;
					}else if(qtd == null || qtd.isEmpty()) {									
						request.setAttribute("msg", "Informe a quantidade do produto!");
					}else if(prc == null || prc.isEmpty()) {
						request.setAttribute("msg", "Informe o valor do produto!");
					} else {
						daoProduto.atualizaProduto(produto);
						request.setAttribute("msg", "Produto atualizado!");
						salva = true; atualiza = true;
					}
				}

				if (salva == false && atualiza == false) {
					request.setAttribute("pr", produto);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listaProduto());
			request.setAttribute("cont_produto", daoProduto.contaProduto());
			request.setAttribute("categoria", daoProduto.listaCategorias());
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
