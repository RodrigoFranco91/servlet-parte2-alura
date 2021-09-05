package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

@WebServlet("/empresas" )
public class EmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	List<Empresa> empresas = new Banco().getEmpresas();
	
	//Lendo o cabeçalho da requisição
	// Usariamos esse valor para definir se cliente quer XML ou JSON
	String valor = request.getHeader("accept");
	
	//Agora temos que transformar os dados da empresa em JSON
	Gson gson = new Gson();
	String json = gson.toJson(empresas);
	
	//Definindo o tipo de conteudo a ser devolvido
	response.setContentType("application/json");
	//Retornando o JSON para o cliente
	response.getWriter().print(json);
	}

}
