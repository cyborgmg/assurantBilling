package br.com.delphos.sca.wsclient.clientsample;

import br.com.delphos.sca.servicos.ControleAcessoService;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.wsclient.ControleAcessoServiceClient;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        ControleAcessoServiceClient service1 = new ControleAcessoServiceClient();
	        System.out.println("Create Web Service...");
	        ControleAcessoService port1 = service1.getControleAcessoPort();
	        System.out.println("Call Web Service Operation...");
	        Usuario usuario1 = port1.autenticar("DBS.CONSULTA.01","iXlLYhoxO7We7Q2fD06CBQ==");
	        System.out.println("Server said: " + usuario1);
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.obterEmpresasUsuario(usuario1.getId().toString(),usuario1.getToken()));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.obterUsuarioPorId(usuario1.getId().toString()));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.listarUsuarios());
	        System.out.println("Server said: " + port1.obterNomeUsuario(usuario1.getId().toString()));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said (should be true): " + port1.alterarSenha(usuario1.getId().toString(),"iXlLYhoxO7We7Q2fD06CBQ==","+l7DHl99cZSWT2+fADGgtg=="));
	        System.out.println("Server said (should be false): " + port1.alterarSenha(usuario1.getId().toString(),"+l7DHl99cZSWT2+fADGgtg==","iXlLYhoxO7We7Q2fD06CBQ=="));
	        System.out.println("Server said (should be true): " + port1.alterarSenha(usuario1.getId().toString(),"+l7DHl99cZSWT2+fADGgtg==","4mVqsyIgsz4IuVor/YSjLA=="));
	        System.out.println("Server said (should be true): " + port1.alterarSenha(usuario1.getId().toString(),"4mVqsyIgsz4IuVor/YSjLA==","iXlLYhoxO7We7Q2fD06CBQ=="));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.obterPorNomeLogin(usuario1.getCodigo()));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.listarUsuariosPorParteDescricao("CONSULTA"));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said (should be true): " + port1.autorizar(usuario1.getId().toString(),usuario1.getToken(),"/DBS/PRINCIPAL/INDEX.JSP"));
	        System.out.println("Server said (should be false): " + port1.autorizar(usuario1.getId().toString(),usuario1.getToken(),"/DBS/PRINCIPAL/INDEX.JSPMUDADO"));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.sincronizar(usuario1.getId().toString(), "") + " (token: " + usuario1.getToken() + " )");
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Create Web Service...");
	        ControleAcessoService port2 = service1.getControleAcessoPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.autenticar(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.obterEmpresasUsuario(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.obterUsuarioPorId(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.listarUsuarios());
	        System.out.println("Server said: " + port2.obterNomeUsuario(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.alterarSenha(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.obterPorNomeLogin(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.listarUsuariosPorParteDescricao(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.autorizar(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.sincronizar(null, null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
