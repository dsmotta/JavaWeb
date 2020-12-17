package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import connection.SingleConnection;

@WebFilter(urlPatterns = {"/*"})
public class FilterConnection implements Filter {
	
	private static Connection connection;

    public FilterConnection() {
    	
    }

	public void destroy() {
		
		
	}
	//Executado quando se clica em um botão na tela
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
		chain.doFilter(request, response);
		
		connection.commit();
		
		}catch (Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {//sempre e executado
		
		connection = SingleConnection.getconnection();
		
	}

}
