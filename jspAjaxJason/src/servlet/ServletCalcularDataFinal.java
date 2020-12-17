package servlet;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoCalculaDataFinal;

@WebServlet("/pages/ServletCalcularDataFinal")
public class ServletCalcularDataFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DaoCalculaDataFinal daoCalculaDataFinal = new DaoCalculaDataFinal();
	
	public ServletCalcularDataFinal() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//08:00 - 12:00 e 13:30 - 17:30
		//1 dia é igual a 8 horas
		
		try {
			int horaDia = 8;
			Date dataCalculada = null;
			Double totalDias = 0.0;
			
			String data = request.getParameter("data");
			int tempo = Integer.parseInt(request.getParameter("tempo"));
			
			if(tempo <= horaDia) {
				
				Date dataInformada =  new SimpleDateFormat("dd/MM/yyyy").parse(data);
				Calendar calendar = Calendar.getInstance();
				
				calendar.setTime(dataInformada);
				calendar.add(Calendar.DATE, 1);
				
				dataCalculada = (Date) calendar.getTime();
				totalDias = 1.0;
				
				
			}else {
				
				totalDias = (double) (tempo / horaDia);
				
				if(totalDias <= 1) {
					dataCalculada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				}else {
					Date dataInformada =  new SimpleDateFormat("dd/MM/yyyy").parse(data);
					Calendar calendar = Calendar.getInstance();
					
					calendar.setTime(dataInformada);
					calendar.add(Calendar.DATE, totalDias.intValue());
					
					dataCalculada = (Date) calendar.getTime();
				}
			}
			
			daoCalculaDataFinal.gravaDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/datas.jsp");
			request.setAttribute("datafinal", new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
			request.setAttribute("dias", totalDias);
			dispatcher.forward(request, response);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
