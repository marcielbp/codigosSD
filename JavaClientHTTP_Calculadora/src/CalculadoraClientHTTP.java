import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

	public static String operate(double oper1, double oper2, char operacao) {
		// este metodo manipula dois operandos e realiza operacao especificada pelo terceiro operando (char)
		String params = "oper1="+Double.toString(oper1)+"&oper2="+Double.toString(oper2)+"&operacao=";
		//1-somar 2-subtrair 3-dividir 4-multiplicar
		switch(operacao) {
		case '+':
			params = params + "1";
			break;
		case '-':
			params = params + "2";
			break;
		case '*':
			params = params + "3";
			break;
		case '/':
			params = params + "4";
			break;
		default:
			// nao implementei tratamento de excecao: a rotina ira retornar alguma operacao
			params = params + "1";
		}
		return params;
	}
	
	
	public static void processRequisition(double oper1, double oper2, char operacao) {
		String result="";
	    try {
	    	// endereco remoto da aplicacao PHP
	        URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
	        
	        // cria o objeto conexao
	        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	        
	        // parametros a priori da conexao
	        conn.setReadTimeout(10000);
	        conn.setConnectTimeout(15000);
	        conn.setRequestMethod("POST");
	        conn.setDoInput(true);
	        conn.setDoOutput(true) ;

	        //ENVIO DOS PARAMETROS
	        OutputStream os = conn.getOutputStream();
	        BufferedWriter writer = new BufferedWriter(
	                new OutputStreamWriter(os, "UTF-8"));
	        String message = CalculadoraClientHTTP.operate(oper1,oper2,operacao);
	        writer.write(message); 
	        writer.flush();
	        writer.close();
	        os.close();
	        // status da resposta de conexao. esperamos status 200 (HTTP_OK)
	        int responseCode=conn.getResponseCode();
	        if (responseCode == HttpsURLConnection.HTTP_OK) {

	            //RECBIMENTO DOS PARAMETROS
	        	// leitura do resultado da requisicao PHP, armazenado no objeto response
	            BufferedReader br = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream(), "utf-8"));
	            StringBuilder response = new StringBuilder();
	            String responseLine = null;
	            while ((responseLine = br.readLine()) != null) {
	                response.append(responseLine.trim());
	            }
	            result = response.toString();
	            System.out.println(""+Double.toString(oper1)+operacao+Double.toString(oper2)+"="+result);
	            System.out.println("Resultado da operacao servidor PHP="+result);
	            //System.out.println("Resposta do Servidor PHP="+result);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public static void main(String[] args) {
		System.out.println("Teste operacao de soma");
		CalculadoraClientHTTP.processRequisition(15,15,'+');
		System.out.println();
		
		System.out.println("Teste operacao de subtracao");
		CalculadoraClientHTTP.processRequisition(15.8,37.6,'-');
		System.out.println();
		
		System.out.println("Teste operacao de multiplicacao");
		CalculadoraClientHTTP.processRequisition(15.6,15.1,'*');
		System.out.println();
		
		System.out.println("Teste operacao de divisao");
		CalculadoraClientHTTP.processRequisition(15,15.5,'/');
		System.out.println();

	}
}
