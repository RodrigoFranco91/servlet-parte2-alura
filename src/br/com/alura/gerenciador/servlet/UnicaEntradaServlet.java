package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;
import br.com.alura.gerenciador.acao.AlteraEmpresa;
import br.com.alura.gerenciador.acao.ListaEmpresas;
import br.com.alura.gerenciador.acao.MostraEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresaForm;
import br.com.alura.gerenciador.acao.OiMundo;
import br.com.alura.gerenciador.acao.RemoveEmpresa;

/*
 * Seria uma boa pratica transformar essa Servlet em um Filter, mapeá-la lá no web.xml!
 * Neste caso não usariamos o chain!
 */
@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Pegando o valor de acao, que é valor passado lá na url.
		String paramAcao = request.getParameter("acao");
		
		/*
		 O TRECHO COMENTADO FOI IMPLEMENTADO NO FILTRO: AutorizacaoFilter

		HttpSession sessao = request.getSession();
		boolean usuarioNaoEstaLogado = sessao.getAttribute("usuarioLogado") == null;
		
		//Liberando as páginas de login para usuario não logado se logar!
		boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm"));
		if(ehUmaAcaoProtegida && usuarioNaoEstaLogado) {
			response.sendRedirect("entrada?acao=LoginForm");
			//Força a saída, pois tem mais código a seguir! Se não colcoar teremos erro!
			return;
		}
		
		 */
		
		//A acao mais o pacote vai ser o nome completo da classe de acao a ser chamada
		String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao;
		
		//Variavel que vai ter o retorno da acao (será uma jsp ou outra acao)
		String nome;
		try {
			//Carrega a classe ataves do seu nome
			Class classe = Class.forName(nomeDaClasse);
			
			//Cria uma nova instancia a partir de Class
			Object obj = classe.newInstance();
			
			//Vamos fazer a referencia obj ser do tipo Acao (interface que todas classes de açao implementam)
			Acao acao = (Acao) obj;
			
			//Executando acao da classe correta!
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException
				| IOException e) {
			throw new ServletException(e);
		}
		
		String[] tipoEEndereco = nome.split(":");
		
		if(tipoEEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
			rd.forward(request, response);
		}else {
			response.sendRedirect(tipoEEndereco[1]);
		}
		
		//Vai pegar o nome da jsp que a ação devolveu.
		/*
		String nome = null;
		
		if(paramAcao.equals("AlteraEmpresa")) {
			AlteraEmpresa acao = new AlteraEmpresa();
			nome = acao.executa(request, response);
		}else if(paramAcao.equals("ListaEmpresas")) {
			ListaEmpresas acao = new ListaEmpresas();
			nome = acao.executa(request, response);
		}else if(paramAcao.equals("MostraEmpresa")) {
			MostraEmpresa acao = new MostraEmpresa();
			nome = acao.executa(request, response);
		}else if(paramAcao.equals("NovaEmpresa")) {
			NovaEmpresa acao = new NovaEmpresa();
			nome = acao.executa(request, response);
		}else if(paramAcao.equals("OiMundo")) {
			OiMundo acao = new OiMundo();
			acao.executa(request, response);
		}else if(paramAcao.equals("RemoveEmpresa")){
			RemoveEmpresa acao = new RemoveEmpresa();
			nome = acao.executa(request, response);
		}else if(paramAcao.equals("NovaEmpresaForm")){
			NovaEmpresaForm acao = new NovaEmpresaForm();
			nome = acao.executa(request, response);
		}
		*/
		
	}
	
}
