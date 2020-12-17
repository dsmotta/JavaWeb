package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import user.UserLogado;

public class DaoUsuario {

	public static Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getconnection();
	}

	public void gravaImagem(String imagem) {

		try {

			String tipodados = imagem.split(",")[0].split(";")[0].split("/")[1];
			
			String sql = "insert into usuario(imagem, tipofile) values(?, ?);";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, imagem);
			statement.setString(2,tipodados);
			statement.execute();
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

	public List<UserLogado> listaImagens() throws SQLException {
		List<UserLogado> lista = new ArrayList<UserLogado>();

		String sql = "select * from usuario ";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			UserLogado user = new UserLogado();
			user.setIduser(resultSet.getLong("iduser"));
			user.setLogin(resultSet.getString("login"));
			user.setSenha(resultSet.getString("senha"));
			user.setImagem(resultSet.getString("imagem"));

			lista.add(user);
		}

		return lista;
	}

	public UserLogado buscaImagem(String usuario) {

		try {
			String sql = "select imagem from usuario where iduser = " + usuario;

			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				UserLogado user = new UserLogado();
				user.setIduser(rs.getLong("iduser"));
				user.setLogin(rs.getString("login"));
				user.setSenha(rs.getString("senha"));
				user.setImagem(rs.getString("imagem"));
				user.setTipofile(rs.getString("tipofile"));
							
				return user;
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<UserLogado>listaTodos() throws Exception{
		List<UserLogado>lista = new ArrayList<UserLogado>();
		
		String sql = "select * from usuario";
		
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			UserLogado usuario = new UserLogado();
			usuario.setIduser(rs.getLong("iduser"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setImagem(rs.getString("imagem"));
			usuario.setTipofile(rs.getString("tipofile"));
			
			lista.add(usuario);
		}
		
		return lista;
	}

}
