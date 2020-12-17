package enviando.email;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class AppTest {
	
	@Test
	public void testeEmail() throws Exception {

		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
			stringBuilderTextoEmail.append("Olá, </br></br>");
			stringBuilderTextoEmail.append("Acesso ao curso de Java</br></br>");
			stringBuilderTextoEmail.append("Clique no botão para ter acesso</br></br>");
			
			
			stringBuilderTextoEmail.append("<b>Login:</br>douglassmotta@bol.com</br>");
			stringBuilderTextoEmail.append("<b>Senha:</br>5790631</br>");
			
			
			stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"http://projetojavaweb.com/certificado-aluno/login\" style=\"color:#2525a7; padding: 14px 25px; text-align: center; text-decoration: nome; display: inline-block; border-radius: 30px; font-size: 20px border: 3px solid green; background-color: #99DA39; \" >Clique aqui</a></br></br>");
			
			stringBuilderTextoEmail.append("<span style=\"font-size:8px;\">Ass.: Douglas</span>");
			
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("douglassmotta@hotmail.com", 
															"Douglas Motta", 
															"Email Java Teste", 
															stringBuilderTextoEmail.toString());
		
		//enviaEmail.enviarEmail(true);
		
		enviaEmail.enviarEmailAnexo(true);
			
			/*Caso o email não esteja sendo enviado então
			 * coloque um tempo de espera mas isso só pode 
			 * ser usado para teste*/
				
			Thread.sleep(5000);
		
	}
	

}
