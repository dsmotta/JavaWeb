package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import beans.BeanTelefones;
import dao.DaoTelefones;
import dao.DaoUsuario;

@WebServlet("/TelefonesServlet")
public class TelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoUsuario daoUsuario = new DaoUsuario();

	DaoTelefones daoTelefones = new DaoTelefones();

	public TelefonesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String user = request.getParameter("user");
			String acao = request.getParameter("acao");
			String tel = request.getParameter("tel");

			if (user != null) {

				BeanCursoJsp usuario = daoUsuario.consultar(user);

				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				if (acao != null && acao.equalsIgnoreCase("delete")) {
					daoTelefones.deletaFone(tel);
					request.setAttribute("msg", "Telefone excluido com sucesso!");

					RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefones.jsp");
					request.setAttribute("telefones", daoTelefones.listaFone(usuario.getId_user()));
					request.setAttribute("contaTelefones", daoTelefones.contaTelefone(usuario.getId_user()));
					dispatcher.forward(request, response);

				} else if (acao != null && acao.equalsIgnoreCase("lista")) {

					RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefones.jsp");
					request.setAttribute("telefones", daoTelefones.listaFone(usuario.getId_user()));
					request.setAttribute("contaTelefones", daoTelefones.contaTelefone(usuario.getId_user()));
					dispatcher.forward(request, response);

				} else if (acao != null && acao.equalsIgnoreCase("editar")) {

					BeanTelefones telefone = daoTelefones.consultaFone(tel);
					request.setAttribute("tel", telefone);

					RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefones.jsp");
					request.setAttribute("telefones", daoTelefones.listaFone(usuario.getId_user()));
					request.setAttribute("contaTelefones", daoTelefones.contaTelefone(usuario.getId_user()));
					dispatcher.forward(request, response);

				}
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				request.setAttribute("cont_usuarios", daoUsuario.contaUsuarios());
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");
			String tel = request.getParameter("tel");

			BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			if (numero == null || (numero != null && numero.isEmpty())) {

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefones.jsp");
				request.setAttribute("telefones", daoTelefones.listaFone(beanCursoJsp.getId_user()));
				request.setAttribute("contaTelefones", daoTelefones.contaTelefone(beanCursoJsp.getId_user()));
				request.setAttribute("msg", "Informe o numero do telefone!");
				dispatcher.forward(request, response);
			} else {

				BeanTelefones telefone = new BeanTelefones();
				telefone.setNumero(numero);
				telefone.setTipo(tipo);
				telefone.setIdusuario(beanCursoJsp.getId_user());

				if (acao.equalsIgnoreCase("editar") && !tel.isEmpty()) {

					telefone.setId(Long.parseLong(tel));
					daoTelefones.atualizaFone(telefone);
					request.setAttribute("msg", "Telefone atualizado com sucesso!");
				} else {

					daoTelefones.salvaFone(telefone);
					request.setAttribute("msg", "Telefone salvo com sucesso!");
				}

				request.getSession().setAttribute("userEscolhido", beanCursoJsp);
				request.setAttribute("userEscolhido", beanCursoJsp);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefones.jsp");
				request.setAttribute("telefones", daoTelefones.listaFone(beanCursoJsp.getId_user()));
				request.setAttribute("contaTelefones", daoTelefones.contaTelefone(beanCursoJsp.getId_user()));
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
