package br.com.delphos.billing.teste;

import br.com.delphos.billing.client.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        ServicoServerImplService service1 = new ServicoServerImplService();
	        System.out.println("Create Web Service...");
	        ServicoServer port1 = service1.getServicoServerImplPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.iniciarVendaCartaoCredito(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.cancelarVenda(null,null,null,null,null,null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Create Web Service...");
	        ServicoServer port2 = service1.getServicoServerImplPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.iniciarVendaCartaoCredito(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.cancelarVenda(null,null,null,null,null,null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
