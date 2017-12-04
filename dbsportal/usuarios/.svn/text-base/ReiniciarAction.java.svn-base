package br.com.delphos.web.usuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.sca.wsclient.OperacaoUsuario;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.sca.wsclient.Usuario;

public class ReiniciarAction extends Action {

   @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //--- inicializações gerais
   		SCAClient scaClient = new SCAClient();
        ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("reiniciarSenha");

        String token = request.getParameter(OperacaoUsuario.NOME_PROPRIEDADE_TOKEN);
        String origem = request.getParameter(OperacaoUsuario.NOME_PROPRIEDADE_OPERACAO);
        OperacaoUsuario operacao = OperacaoUsuario.buscarPorValor(origem);
        
        //Testa se esta sendo passado algum token pela URL
        if (token != null) {            
            Usuario usuario = scaClient.obterUsuarioPorToken(token);

            //Testa se existe algum usuário 
            if (usuario != null) {
                ReiniciarSenhaActionForm reiniciarSenhaActionForm = new ReiniciarSenhaActionForm();

                if (operacao == OperacaoUsuario.CONFIRMAR_EMAIL) {
                	reiniciarSenhaActionForm.setChaveTitulo("label.ativar.senha");
                	
                } else if (operacao == OperacaoUsuario.ESQUECI_SENHA) {
                	reiniciarSenhaActionForm.setChaveTitulo("label.ativar.nova.senha");
                }
                
                reiniciarSenhaActionForm.setOrigem(origem);
                reiniciarSenhaActionForm.setId(usuario.getId().toString());
                reiniciarSenhaActionForm.setUsuario(usuario.getCodigo());
                reiniciarSenhaActionForm.setEmail(usuario.getEmail());
                reiniciarSenhaActionForm.setToken(token);
                request.setAttribute("ReiniciarSenhaActionForm", reiniciarSenhaActionForm);

            } else {
                ActionMessage msg = new ActionMessage(Mensagens.get(CodigoMensagem.Falha), false);
                messages.add("dialogoErro", msg);
                this.addErrors(request, messages);
                retorno = mapping.findForward("login");
            }

        } else {
            ActionMessage msg = new ActionMessage("erro.token.invalido");
            messages.add("dialogoErro", msg);
            this.addErrors(request, messages);
            retorno = mapping.findForward("login");
        }

        return retorno;
    }
}
