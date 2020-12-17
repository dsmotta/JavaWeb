package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava userposjava) {
		try {
			String sql = "insert into userposjava(nome_user, email_user) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public void salvaTelefone(Telefone telefone) {

		try {

			String sql = "insert into telefoneuser(numero_tel, tipo_tel, usuariopessoa) values(?,?,?)";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, telefone.getNumero_tel());
			st.setString(2, telefone.getTipo_tel());
			st.setLong(3, telefone.getUsuariopessoa());
			st.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public List<Userposjava> listar() throws Exception {
		List<Userposjava> list = new ArrayList<Userposjava>();

		String sql = "select * from userposjava";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultado.getLong("id_user"));
			userposjava.setNome(resultado.getString("nome_user"));
			userposjava.setEmail(resultado.getString("email_user"));

			list.add(userposjava);
		}
		return list;

	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public List<Telefone> lista_tel() throws Exception {
		List<Telefone> list_tel = new ArrayList<Telefone>();

		String sql = "Select * from telefoneuser";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			Telefone telefone = new Telefone();
			telefone.setId_tel(rs.getLong("id_tel"));
			telefone.setNumero_tel(rs.getString("numero_tel"));
			telefone.setTipo_tel(rs.getString("tipo_tel"));
			telefone.setUsuariopessoa(rs.getLong("usuariopessoa"));

			list_tel.add(telefone);

		}
		return list_tel;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public Userposjava buscar(long id) throws Exception {
		Userposjava retorno = new Userposjava();

		String sql = "select * from userposjava where id_user = " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			retorno.setId(resultado.getLong("id_user"));
			retorno.setNome(resultado.getString("nome_user"));
			retorno.setEmail(resultado.getString("email_user"));

		}
		return retorno;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public Telefone buscaTelefoneId(long id) {
		Telefone telefone = new Telefone();
		try {

			String sql = "select * from telefoneuser where id_tel =" + id;
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				telefone.setId_tel(rs.getLong("id_tel"));
				telefone.setNumero_tel(rs.getString("numero_tel"));
				telefone.setTipo_tel(rs.getString("tipo_tel"));
				telefone.setUsuariopessoa(rs.getLong("usuariopessoa"));

			}

		} catch (Exception e) {
			System.out.println("Usuário não encontrado!");
			e.printStackTrace();
		}

		return telefone;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public List<Userposjava> busca_nome(String nome) throws Exception {
		List<Userposjava> list = new ArrayList<Userposjava>();

		String sql = "select * from userposjava where nome_user = " + nome;
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			Userposjava userposjava = new Userposjava();
			userposjava.setId(rs.getLong("id_user"));
			userposjava.setNome(rs.getString("nome_user"));
			userposjava.setEmail(rs.getString("email_user"));

			list.add(userposjava);
		}

		return list;

	}
	// ---------------------------------------------------------------------------------------------------------------------------

	public List<Telefone> listaTelefoneTipo(String tipo) throws Exception {
		List<Telefone> list = new ArrayList<Telefone>();

		String sql = "select * from telefoneuser where tipo_tel ilike '%" + tipo + "%'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			Telefone telefone = new Telefone();
			telefone.setId_tel(rs.getLong("id_tel"));
			telefone.setNumero_tel(rs.getString("numero_tel"));
			telefone.setTipo_tel(rs.getString("tipo_tel"));
			telefone.setUsuariopessoa(rs.getLong("usuariopessoa"));

			list.add(telefone);
		}

		return list;
	}
	// ---------------------------------------------------------------------------------------------------------------------------

	public void atualizar(Userposjava userposjava) {

		try {
			String sql = "update userposjava set nome_user = ? where id_user = " + userposjava.getId();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, userposjava.getNome());
			st.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	// ---------------------------------------------------------------------------------------------------------------------------

	public void atualizaTelefone(Telefone telefone) {

		try {

			String sql = "update telefoneuser set numero_tel = ?, tipo_tel = ? where id_tel = " + telefone.getId_tel();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, telefone.getNumero_tel());
			st.setString(2, telefone.getTipo_tel());
			st.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public void deletar(Long id) {

		try {
			String sql = "delete from userposjava where id_user = " + id;
			PreparedStatement st = connection.prepareStatement(sql);
			st.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public void deletaTelefone(long id) {
		try {

			String sql = "delete from telefoneuser where id_tel = " + id;
			PreparedStatement st = connection.prepareStatement(sql);
			st.execute();
			connection.commit();

		} catch (Exception e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public List<BeanUserFone> listaUserFone(String nome) {

		List<BeanUserFone> list = new ArrayList<BeanUserFone>();

		try {
			String sql = " select nome_user, numero_tel, tipo_tel, email_user from telefoneuser as fone ";
			sql += " inner join userposjava as userpos ";
			sql += " on fone.id_tel = userpos.id_user ";
			sql += " where nome_user ilike '%" + nome + "%' ";
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				BeanUserFone beanUserFone = new BeanUserFone();
				beanUserFone.setNome(rs.getString("nome_user"));
				beanUserFone.setNumero(rs.getString("numero_tel"));
				beanUserFone.setTipo(rs.getString("tipo_tel"));

				list.add(beanUserFone);

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------

	public void deletaFones_por_User(long iduser) {
		
		String sqlfone = " delete from telefoneuser where usuariopessoa = " + iduser;
		String sqluser = " delete from userposjava where id_user = " + iduser;
		
		try {
			PreparedStatement stfone = connection.prepareStatement(sqlfone);
			stfone.executeUpdate();
			connection.commit();
			
			PreparedStatement stuser = connection.prepareStatement(sqluser);
			stuser.executeUpdate();
			connection.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
