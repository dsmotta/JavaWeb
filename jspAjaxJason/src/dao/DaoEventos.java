package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import entidades.Eventos;

public class DaoEventos {
	
	Connection connection;
	
	public DaoEventos() {
		connection = SingleConnection.getconnection();
	}
	
	public List<Eventos>listaEventos() throws Exception{
		
		List<Eventos>listaEvento = new ArrayList<Eventos>();
		
		String sql = "select * from eventos";
		
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			
			Eventos eventos = new Eventos();
			eventos.setId(rs.getString("id"));
			eventos.setDescricao(rs.getString("descricao"));
			eventos.setDataevento(rs.getString("dataevento"));
			
			listaEvento.add(eventos);
		}
		
		return listaEvento;
		
	}

}
