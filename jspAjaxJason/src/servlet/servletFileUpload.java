package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import dao.DaoUsuario;
import user.UserLogado;

@WebServlet("/pages/servletFileUpload")
public class servletFileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public servletFileUpload() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			String acao = request.getParameter("acao");
			
			if(acao.equalsIgnoreCase("carregar")) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Upload.jsp");
				request.setAttribute("listaUserImagem", daoUsuario.listaImagens());
				dispatcher.forward(request, response);
				
			}else if(acao.equalsIgnoreCase("download")) {
				String iduser = request.getParameter("iduser");
				UserLogado imagem = daoUsuario.buscaImagem(iduser);
				
				if(imagem != null) {
					
					
					//pega somente a imagem pura.. depois da virgula
					String imagemPura = imagem.getImagem().split(",")[1];
					//converte base64 em bytes
					byte [] imageBytes = new Base64().decodeBase64(imagemPura);
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo" + imagem.getTipofile());
					//coloca os bytes em um objeto de entrada para processar
					InputStream is = new ByteArrayInputStream(imageBytes);
					
					//INICIO - escrever dados na resposta
					
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
						}
						os.flush();
						os.close();
					
					//FIM - ------------------------------
					
					
				}
				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// usa a variavel fileUpload para salvar no banco de dados
			String fileUpload = request.getParameter("fileUpload");

			// neste momento faz o insert no banco de dados
			daoUsuario.gravaImagem(fileUpload);
			response.getWriter().write("Upload do arquivo realizado com sucesso!");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Upload.jsp");
			request.setAttribute("listaUserImagem", daoUsuario.listaImagens());
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Upload não realizado!");
		}

	}

}
