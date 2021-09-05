package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OiMundo {

	
public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<body>");
	out.println("oi mundo, parabens vc escreveu o primeiro servlets.");
	out.println("</body>");
	out.println("</html>");
	
	System.out.println("o servlet OiMundoServlet foi chamado");
		
	}
}
