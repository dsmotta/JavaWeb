package dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp usuario) {

		try {
			String sql = "insert into usuario(login, senha, nome, cep, rua, numero, bairro, "
					+ "cidade, estado, fotobase64, contenttype, curriculobase64, contenttypecurriculo, fotoBase64Mini,"
					+ " ativo, sexo, perfil) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, usuario.getLogin());
			st.setString(2, usuario.getSenha());
			st.setString(3, usuario.getNome());
			st.setString(4, usuario.getCep());
			st.setString(5, usuario.getRua());
			st.setString(6, usuario.getNumero());
			st.setString(7, usuario.getBairro());
			st.setString(8, usuario.getCidade());
			st.setString(9, usuario.getEstado());
			st.setString(10, usuario.getFotoBase64());
			st.setString(11, usuario.getContentType());
			st.setString(12, usuario.getCurriculoBase64());
			st.setString(13, usuario.getContentTypeCurriculo());
			st.setString(14, usuario.getFotoBase64Mini());
			st.setBoolean(15, usuario.isAtivo());
			st.setString(16, usuario.getSexo());
			st.setString(17, usuario.getPerfil());
			st.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	
	public List<BeanCursoJsp> listar(String descricaoConsulta) throws Exception{
		String sql = "select * from usuario where login <> 'admin' and nome like '%"+descricaoConsulta+"%'";
		return consultarUsuarios(sql);
	}
	
	public List<BeanCursoJsp> listar() throws Exception {

		String sql = "(select * from usuario where login <> 'admin')";
		return consultarUsuarios(sql);
	}

	private List<BeanCursoJsp> consultarUsuarios(String sql) throws SQLException {
		ArrayList<BeanCursoJsp> lista = new ArrayList<BeanCursoJsp>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();

			beanCursoJsp.setId_user(resultSet.getLong("id_user"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setNumero(resultSet.getString("numero"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setEstado(resultSet.getString("estado"));
			// beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setFotoBase64Mini(resultSet.getString("fotoBase64Mini"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));

			lista.add(beanCursoJsp);

		}
		return lista;
	}

	public void delete(String id) {

		try {

			String sql = "delete from usuario where id_user = '" + id + "' and login <> 'admin'";
			PreparedStatement st = connection.prepareStatement(sql);
			st.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}

	public BeanCursoJsp consultar(String id) throws Exception {

		String sql = "select * from usuario where id_user = '" + id + "' and login <> 'admin'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		if (rs.next()) {

			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId_user(rs.getLong("id_user"));
			beanCursoJsp.setLogin(rs.getString("login"));
			beanCursoJsp.setSenha(rs.getString("senha"));
			beanCursoJsp.setNome(rs.getString("nome"));
			beanCursoJsp.setCep(rs.getString("cep"));
			beanCursoJsp.setRua(rs.getString("rua"));
			beanCursoJsp.setNumero(rs.getString("numero"));
			beanCursoJsp.setBairro(rs.getString("bairro"));
			beanCursoJsp.setCidade(rs.getString("cidade"));
			beanCursoJsp.setEstado(rs.getString("estado"));
			beanCursoJsp.setFotoBase64(rs.getString("fotobase64"));
			beanCursoJsp.setContentType(rs.getString("contenttype"));
			beanCursoJsp.setCurriculoBase64(rs.getString("curriculobase64"));
			beanCursoJsp.setContentTypeCurriculo(rs.getString("contenttypecurriculo"));
			beanCursoJsp.setFotoBase64Mini(rs.getString("fotoBase64Mini"));
			beanCursoJsp.setAtivo(rs.getBoolean("ativo"));
			beanCursoJsp.setSexo(rs.getString("sexo"));
			beanCursoJsp.setPerfil(rs.getString("perfil"));

			return beanCursoJsp;

		}

		return null;
	}

	public void atualizar(BeanCursoJsp usuario) {

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("update usuario set login = ?, senha = ?, nome = ?, cep = ?, ");
			sql.append("rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, ativo = ?, sexo = ?, perfil = ? ");
			if (usuario.isAtualizarImagem()) {
				sql.append(", fotobase64 = ?, contenttype = ? ");
			}
			if (usuario.isAtualizarPDF()) {
				sql.append(", curriculobase64 = ?, contenttypecurriculo = ? ");
			}

			if (usuario.isAtualizarImagem()) {
				sql.append(", fotoBase64Mini = ? ");
			}
			sql.append(" where id_user = " + usuario.getId_user());

			PreparedStatement st = connection.prepareStatement(sql.toString());
			st.setString(1, usuario.getLogin());
			st.setString(2, usuario.getSenha());
			st.setString(3, usuario.getNome());
			st.setString(4, usuario.getCep());
			st.setString(5, usuario.getRua());
			st.setString(6, usuario.getNumero());
			st.setString(7, usuario.getBairro());
			st.setString(8, usuario.getCidade());
			st.setString(9, usuario.getEstado());
			st.setBoolean(10, usuario.isAtivo());
			st.setString(11, usuario.getSexo());
			st.setString(12, usuario.getPerfil());
			if (usuario.isAtualizarImagem()) {
				
				st.setString(13, usuario.getFotoBase64());
				st.setString(14, usuario.getContentType());
			}
			if (usuario.isAtualizarPDF()) {
				if(usuario.isAtualizarPDF() && !usuario.isAtualizarImagem()) {
					st.setString(13, usuario.getCurriculoBase64());
					st.setString(14, usuario.getContentTypeCurriculo());
				}else {
					st.setString(15, usuario.getCurriculoBase64());
					st.setString(16, usuario.getContentTypeCurriculo());
				}
				
			}else {
				if(usuario.isAtualizarImagem()) {
					st.setString(15, usuario.getFotoBase64Mini());
				}
			}
			if (usuario.isAtualizarImagem() && usuario.isAtualizarPDF()) {
				st.setString(17, usuario.getFotoBase64Mini());
			}

			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public boolean validaLogin(String login) {
		try {
			String sql = "select count(1) as qtd from usuario where login='" + login + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				return resultSet.getInt("qtd") <= 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean validaLoginUpdate(String login, String id) {
		try {
			String sql = "select count(1) as qtd from usuario where login='" + login + "' and id_user <> " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				return resultSet.getInt("qtd") <= 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean validaSenha(String senha) {
		boolean val = false;
		try {

			String sql = "select (senha) from usuario where senha ='" + senha + "'";
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				if (rs.getString("senha").equals(senha)) {
					val = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	public boolean validaSenhaUpdate(String senha, String id) {
		boolean val = false;
		try {
			String sql = "select (senha) from usuario where senha = '" + senha + "' and id_user <>" + id;
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				if (rs.getString("senha").equals(senha)) {
					val = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	public int contaUsuariosPesquisados(String descricao) throws SQLException {
		String sql = "select count(nome) as qtd from usuario where login <> 'admin' and nome like '%"+descricao+"%'";
		return conta(sql);
	}
	
	public int contaUsuarios() throws SQLException {
	
			String sql = "select count(id_user) as qtd from usuario where login <> 'admin'" ;
			return conta(sql);	
	}

	private int conta(String sql) throws SQLException {
		int val = 0;
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			val = rs.getInt("qtd");
		}
		return val;
	}

	/* Converte a entrada de fluxo de dados da imagem para byte */

	public byte[] convertStreamToByte(InputStream foto) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int leitor = foto.read();
		while (leitor != -1) {
			baos.write(leitor);
			leitor = foto.read();
		}
		return baos.toByteArray();
	}

}
