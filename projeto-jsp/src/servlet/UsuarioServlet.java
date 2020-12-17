package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.DaoTelefones;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
@MultipartConfig
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoUsuario daousuario = new DaoUsuario();
	DaoTelefones daotelefones = new DaoTelefones();

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao != null && acao.equalsIgnoreCase("delete")) {

				daousuario.delete(user);
				daotelefones.deletaFoneId_Usuario(user);
				

				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daousuario.listar());
				request.setAttribute("cont_usuarios", daousuario.contaUsuarios());
				view.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("editar")) {

				BeanCursoJsp beanCursoJsp = daousuario.consultar(user);

				RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				request.setAttribute("cont_usuarios", daousuario.contaUsuarios());
				dispatcher.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daousuario.listar());
				request.setAttribute("cont_usuarios", daousuario.contaUsuarios());
				dispatcher.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("download")) {

				BeanCursoJsp usuario = daousuario.consultar(user);
				if (usuario != null) {
					String contentType = "";
					byte[] fileBytes = null;
					
					String tipo = request.getParameter("tipo");
					
					if(tipo.equalsIgnoreCase("imagem")) {
						contentType = usuario.getContentType();
						fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());

					}else if(tipo.equalsIgnoreCase("curriculo")) {
						contentType = usuario.getContentTypeCurriculo();
						fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());
					}
					
					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);

					/* Converte os bytes em um objeto de entrada para processar */
					InputStream is = new ByteArrayInputStream(fileBytes);

					/* Inicio da resposta para o navegador */
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();

				}
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daousuario.listar());
				request.setAttribute("cont_usuarios", daousuario.contaUsuarios());
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean salva = false, atualiza = false;

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("'reset'")) {
			try {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daousuario.listar());
				request.setAttribute("cont_usuarios", daousuario.contaUsuarios());
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			try {
				String id = request.getParameter("id");
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				String nome = request.getParameter("nome");
				String telefone = request.getParameter("telefone");
				String cep = request.getParameter("cep");
				String rua = request.getParameter("rua");
				String numero = request.getParameter("numero");
				String bairro = request.getParameter("bairro");
				String cidade = request.getParameter("cidade");
				String estado = request.getParameter("estado");
				String sexo = request.getParameter("sexo");
				String perfil = request.getParameter("perfil");
				
				
				BeanCursoJsp usuario = new BeanCursoJsp();
				usuario.setId_user(!id.isEmpty() ? Long.parseLong(id) : 0);
				usuario.setLogin(login);
				usuario.setSenha(senha);
				usuario.setNome(nome);
				usuario.setTelefone(telefone);
				usuario.setCep(cep);
				usuario.setRua(rua);
				usuario.setNumero(numero);
				usuario.setBairro(bairro);
				usuario.setCidade(cidade);
				usuario.setEstado(estado);
				usuario.setSexo(sexo);
				usuario.setPerfil(perfil);
				
				if(request.getParameter("ativo") != null && request.getParameter("ativo").equalsIgnoreCase("on")) {
					usuario.setAtivo(true);
				}else {
					usuario.setAtivo(false);
				}

				/*----------------- INICIO upload foto e pdf ------------------- */

				if (ServletFileUpload.isMultipartContent(request)) {

					Part foto = request.getPart("foto");

					if (foto != null && foto.getInputStream().available() > 0) {

						byte[] bytesFoto = daousuario.convertStreamToByte(foto.getInputStream());
						String fotoBase64 = new Base64().encodeBase64String(bytesFoto);
						String contentType = foto.getContentType();
						
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(contentType);
						
						//-----------Inicio miniatura imagem--------------------
						
						/*Transforma em um bufferedImage*/
						
						byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						/*Pega tipo da imagem*/
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB: bufferedImage.getType();
						/*Cria a imagem em miniatura*/
						BufferedImage resizedImage = new BufferedImage(100, 100, type);
						Graphics2D g = resizedImage.createGraphics();
						g.drawImage(bufferedImage, 0, 0, 100, 100, null);
						/*Escrever a imagem novamente*/
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizedImage, "png", baos);
						g.dispose();
						
						String miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
						usuario.setFotoBase64Mini(miniaturaBase64);
						
						//-----------Fim miniatura imagem--------------------
						
						
					}else {
						usuario.setAtualizarImagem(false);
					}
					
					Part pdf = request.getPart("curriculo");
					
					if(pdf != null && pdf.getInputStream().available() > 0) {
						
						byte[] bytesPdf = daousuario.convertStreamToByte(pdf.getInputStream());
						String curriculoBase64 = new Base64().encodeBase64String(bytesPdf);
						String contentTypeCurriculo = pdf.getContentType();
						
						usuario.setCurriculoBase64(curriculoBase64);
						usuario.setContentTypeCurriculo(contentTypeCurriculo);
						
					}else {
						usuario.setAtualizarPDF(false);
					}
				}

				/*-------------------- FIM upload foto e pdf ----------------------------*/

				if (id == null || id.isEmpty() || Long.parseLong(id) == 0) {

					if (login == null || login.isEmpty()) {
						request.setAttribute("msg", "Digite o login!");
					} else if (senha == null || senha.isEmpty()) {
						request.setAttribute("msg", "Digite a senha!");
					} else if (nome == null || nome.isEmpty()) {
						request.setAttribute("msg", "Digite o nome!");
					} else if (cep == null || cep.isEmpty()) {
						request.setAttribute("msg", "Digite o CEP!");
					} else if (rua == null || rua.isEmpty()) {
						request.setAttribute("msg", "Digite a rua!");
					} else if (numero == null || numero.isEmpty()) {
						request.setAttribute("msg", "Digite o numero!");
					} else if (cidade == null || cidade.isEmpty()) {
						request.setAttribute("msg", "Digite a Cidade!");
					} else if (estado == null || estado.isEmpty()) {
						request.setAttribute("msg", "Digite o Estado!");
					} else if (!daousuario.validaLogin(login)) {
						request.setAttribute("msg", "Login já cadastrado!");
					} else if (daousuario.validaSenha(senha)) {
						request.setAttribute("msg", "Senha já cadastrada!");

					} else {
						daousuario.salvar(usuario);
						request.setAttribute("msg", "Usuário incluido com sucesso!");
						salva = true;
						atualiza = true;
					}

				} else {

					if (login == null || login.isEmpty()) {
						request.setAttribute("msg", "Login inválido!");
					} else if (senha == null || senha.isEmpty()) {
						request.setAttribute("msg", "Senha inválida!");
					} else if (nome == null || nome.isEmpty()) {
						request.setAttribute("msg", "Nome inválidos!");
					} else if (cep == null || cep.isEmpty()) {
						request.setAttribute("msg", "Digite o CEP!");
					} else if (rua == null || rua.isEmpty()) {
						request.setAttribute("msg", "Digite a rua!");
					} else if (numero == null || numero.isEmpty()) {
						request.setAttribute("msg", "Digite o numero!");
					} else if (cidade == null || cidade.isEmpty()) {
						request.setAttribute("msg", "Digite a Cidade!");
					} else if (estado == null || estado.isEmpty()) {
						request.setAttribute("msg", "Digite o Estado!");
					} else if (!daousuario.validaLoginUpdate(login, id)) {
						request.setAttribute("msg", "Login já cadastrado!");
					} else if (daousuario.validaSenhaUpdate(senha, id)) {
						request.setAttribute("msg", "Senha já cadastrada!");

					} else {
						daousuario.atualizar(usuario);
						request.setAttribute("msg", "Usuárrio atualizado com sucesso!");
						salva = true;
						atualiza = true;
					}

				}

				if (salva == false && atualiza == false) {
					request.setAttribute("user", usuario);
				}

				// Redireciona a lista dos dados
				try {
					RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
					request.setAttribute("usuarios", daousuario.listar());
					request.setAttribute("cont_usuarios", daousuario.contaUsuarios());
					view.forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
