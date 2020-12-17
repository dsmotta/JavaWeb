package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefones;
import connection.SingleConnection;

public class DaoTelefones {

	private Connection connection;

	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}

	public void salvaFone(BeanTelefones telefone) {

		try {

			String sql = "insert into telefone(numero, tipo, idusuario) values(?, ?, ?)";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, telefone.getNumero());
			st.setString(2, telefone.getTipo());
			st.setLong(3, telefone.getIdusuario());
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

	public List<BeanTelefones> listaFone(Long user) throws Exception {
		List<BeanTelefones> lista = new ArrayList<BeanTelefones>();

		String sql = "select * from telefone where idusuario =" +user;
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			BeanTelefones telefone = new BeanTelefones();
			telefone.setId(rs.getLong("id_tel"));
			telefone.setNumero(rs.getString("numero"));
			telefone.setTipo(rs.getString("tipo"));
			telefone.setIdusuario(rs.getLong("idusuario"));

			lista.add(telefone);
		}
		return lista;
	}
	
	public BeanTelefones consultaFone(String idtel) throws Exception {
		
		String sql = "select * from telefone where id_tel='"+ idtel +"'";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			
			BeanTelefones telefones = new BeanTelefones();
			
			telefones.setId(rs.getLong("id_tel"));
			telefones.setNumero(rs.getString("numero"));
			telefones.setTipo(rs.getString("tipo"));
			telefones.setIdusuario(rs.getLong("idusuario"));
			
			return telefones;
		}
		return null;
	}
	
	public void deletaFone(String idfone) {

		try {

			String sql = "delete from telefone where id_tel='" + idfone + "'";
			PreparedStatement st = connection.prepareStatement(sql);
			st.execute();
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

	public void atualizaFone(BeanTelefones telefone) {

		try {
			String sql = "UPDATE telefone set numero=?, tipo=? where id_tel = " + telefone.getId();
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, telefone.getNumero());
			st.setString(2, telefone.getTipo());
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
	
	public int contaTelefone(long idusuario) throws Exception {
		
		String sql = "SELECT COUNT(id_tel) as qtd from telefone where idusuario=" + idusuario;
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			
			return rs.getInt("qtd");
		}
		return 0;		
	}
	
	public void deletaFoneId_Usuario(String usuario) {
		String sql = "delete from telefone where idusuario = '"+usuario+"'";
		try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.execute();
		connection.commit();
		
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
