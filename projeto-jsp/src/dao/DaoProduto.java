package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCategoria;
import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void salvarProduto(BeanProduto produto) {

		try {
			String sql = "insert into produto(desc_prod, qtd_prod, preco_prod, categoria_id) values(?, ?, ?, ?)";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, produto.getDesc_prod());
			st.setDouble(2, produto.getQtd_prod());
			st.setDouble(3, produto.getPreco_prod());
			st.setLong(4, produto.getCategoria_id());
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

	public List<BeanProduto> listaProduto() {
		ArrayList<BeanProduto> lista = new ArrayList<BeanProduto>();

		try {

			String sql = "select * from produto";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				BeanProduto produto = new BeanProduto();

				produto.setId_prod(resultSet.getLong("id_prod"));
				produto.setDesc_prod(resultSet.getString("desc_prod"));
				produto.setQtd_prod(resultSet.getFloat("qtd_prod"));
				produto.setPreco_prod(resultSet.getFloat("preco_prod"));
				produto.setCategoria_id(resultSet.getLong("categoria_id"));

				lista.add(produto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void deletaProduto(long id) {

		try {
			String sql = "delete from produto where id_prod = '" + id + "'";
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

	public BeanProduto consultaProd(String id) throws Exception {

		try {
			String sql = "select * from produto where id_prod = '" + id + "'";
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				BeanProduto beanProduto = new BeanProduto();

				beanProduto.setId_prod(rs.getLong("id_prod"));
				beanProduto.setDesc_prod(rs.getString("desc_prod"));
				beanProduto.setQtd_prod(rs.getFloat("qtd_prod"));
				beanProduto.setPreco_prod(rs.getFloat("preco_prod"));
				beanProduto.setCategoria_id(rs.getLong("categoria_id"));

				return beanProduto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void atualizaProduto(BeanProduto produto) {

		try {

			String sql = "update produto set desc_prod=?, qtd_prod=?, preco_prod=?, categoria_id=? where id_prod='"
					+ produto.getId_prod() + "'";
			PreparedStatement st = connection.prepareStatement(sql);

			st.setString(1, produto.getDesc_prod());
			st.setFloat(2, produto.getQtd_prod());
			st.setFloat(3, produto.getPreco_prod());
			st.setLong(4, produto.getCategoria_id());

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

	public int contaProduto() throws Exception {
		int val = 0;

		String sql = "select count(id_prod) as qtd from produto";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			val = rs.getInt("qtd");
		}

		return val;
	}

	public List<BeanCategoria> listaCategorias() {
		List<BeanCategoria> lista = new ArrayList<BeanCategoria>();

		try {
			String sql = "Select * from categoria";

			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				BeanCategoria categoria = new BeanCategoria();
				categoria.setId(rs.getLong("id_cat"));
				categoria.setNome(rs.getString("nome_cat"));

				lista.add(categoria);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

}
