package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.SingleConnection;

public class DaoCalculaDataFinal {
	
	private Connection connection;
	
	public DaoCalculaDataFinal() {
		connection = SingleConnection.getconnection();
		
	}
	
	public void gravaDataFinal(String date) throws Exception{
		String sql = "insert into finalprojetos (datafinal) values (?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, date);
		statement.execute();
	}
	

}
