package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import entidades.Projeto;
import entidades.Series;

public class DaoGanttView {
	
	Connection connection;
	
	public DaoGanttView() {
		connection = SingleConnection.getconnection();
	}
	
	public List<Projeto>getProjetos() throws Exception{
		List<Projeto>listaProjetos = new ArrayList<Projeto>();
		
		
		String sql = "select * from projetos";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			
			Projeto projeto = new Projeto();
			
			projeto.setId(rs.getLong("id"));
			projeto.setNome(rs.getString("nome"));
			
			List<Series>listaSeries = new ArrayList<Series>();
			String sqlSeries = "select * from series where id_projeto = " + rs.getLong("id");
			PreparedStatement stSeries = connection.prepareStatement(sqlSeries);
			ResultSet rsSeries = stSeries.executeQuery();
			
			while(rsSeries.next()) {
				
				Series series = new Series();
				
				series.setId(rsSeries.getLong("id"));
				series.setNome(rsSeries.getString("nome"));
				series.setInicio(rsSeries.getString("inicio"));
				series.setFim(rsSeries.getString("fim"));
				series.setId_projeto(rsSeries.getLong("id_projeto"));
				
				listaSeries.add(series);
				
			}
			
			projeto.setSeries(listaSeries);
			
			listaProjetos.add(projeto);
			
		}
		
		
		return listaProjetos;
		
	}

}
