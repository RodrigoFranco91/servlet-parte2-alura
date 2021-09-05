package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//Toda requisiçao com path /entrada vai passar neste filtro.
@WebFilter("/entrada")
public class MonitoramentoFilter implements Filter{

	//DEstoy e Init foram adicionados para fazermos deploy no Jetty sem erro!
	
@Override
public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub
	Filter.super.init(filterConfig);
}

@Override
public void destroy() {
	// TODO Auto-generated method stub
	Filter.super.destroy();
}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//Antes de toda requisição vamos pegar o tempo1
		long antes = System.currentTimeMillis();
		
		String acao = request.getParameter("acao");
		
		//Aqui quero que continue a execução da requisição...
		chain.doFilter(request, response);
		
		//Após a execução da requisicao pego o tempo2
		long depois = System.currentTimeMillis();
		
		System.out.println("Tempo de execução de " + acao + " : " + (depois - antes));	
		
	}

}
