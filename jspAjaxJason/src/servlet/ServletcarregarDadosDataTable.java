package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import user.UserLogado;

@WebServlet("/pages/ServletcarregarDadosDataTable")
public class ServletcarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DaoUsuario daousuario = new DaoUsuario();
	
    public ServletcarregarDadosDataTable() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			List<UserLogado>usuarios = daousuario.listaTodos();
		
		if(!usuarios.isEmpty()) {	
			
			String data = "";
			
			int totalUsuarios = usuarios.size();
			int index = 1;
					
			for (UserLogado usuario : usuarios) {
				
				data +=  " ["+
					      "\""+usuario.getIduser()+"\","+
					      "\""+usuario.getLogin()+"\","+
					      "\""+usuario.getSenha()+"\""+
					    "]";
				if(index < totalUsuarios) {
					data += ",";
				}
				index++;
			} 
			
			String json = "{"+
			  "\"draw\": 1,"+
			  "\"recordsTotal\": "+usuarios.size()+","+
			  "\"recordsFiltered\": "+usuarios.size()+","+
			  "\"data\": ["+data+"]"+//fechamento do data
			"}";//fechamento json
		
			response.setStatus(200);//resposta completa OK
			response.getWriter().write(json);//json de resposta
		}
		
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
