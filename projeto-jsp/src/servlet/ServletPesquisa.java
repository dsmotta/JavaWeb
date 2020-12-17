package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/servletPesquisa")
public class ServletPesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daousuario = new DaoUsuario();
	
	public ServletPesquisa() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descricaoPesquisa = request.getParameter("descricaoConsulta");
		String acao = request.getParameter("acao");

		if (descricaoPesquisa != null && !descricaoPesquisa.trim().isEmpty() ) {
			try {
				List<BeanCursoJsp> listaPesquisa = daousuario.listar(descricaoPesquisa);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", listaPesquisa);
				request.setAttribute("cont_usuarios", daousuario.contaUsuariosPesquisados(descricaoPesquisa));
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
			request.setAttribute("usuarios", daousuario.listar());
			request.setAttribute("cont_usuarios", daousuario.contaUsuarios());
			dispatcher.forward(request, response);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
