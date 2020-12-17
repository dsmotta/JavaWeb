package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DaoCalculaDataFinal;
import dao.DaoGanttView;
import entidades.Projeto;

@WebServlet("/pages/ServletBuscarDatasPlanejamento")
public class ServletBuscarDatasPlanejamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoGanttView daoGanttView = new DaoGanttView();

	public ServletBuscarDatasPlanejamento() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Projeto> projetos = daoGanttView.getProjetos();

			if (!projetos.isEmpty()) {
				
				String ganttJson = new Gson().toJson(projetos);
				
				response.setStatus(200);
				response.getWriter().write(ganttJson);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
