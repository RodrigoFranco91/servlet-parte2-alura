package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;


@WebServlet("/empresas2" )
public class EmpresasService2 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	List<Empresa> empresas = new Banco().getEmpresas();
	
	//Agora temos que transformar os dados da empresa em XML
	XStream xstream = new XStream();
	//Definindo que quando encontrar um objeto do tipo empresa chamá-lo de empresa 
	//Nos atributos ele mantem o nome de atributo, neste caso como temos uma lista de empresa ele não saberia nomear
	xstream.alias("empresa", Empresa.class);
 	String xml = xstream.toXML(empresas);
	
	//Definindo o tipo de conteudo a ser devolvido
	response.setContentType("application/xml");
	//Retornando o JSON para o cliente
	response.getWriter().print(xml);
	}

}
