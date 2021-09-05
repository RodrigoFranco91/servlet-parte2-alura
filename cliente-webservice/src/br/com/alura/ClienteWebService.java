package br.com.alura;

import java.io.IOException;

import javax.sound.midi.Soundbank;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class ClienteWebService {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		String resposta = Request.Post("http://localhost:8080/gerenciador/empresas")
		.addHeader("accept", "application/json")
		.execute()
		.returnContent()
		.asString();
		
	System.out.println(resposta);
	}

}
