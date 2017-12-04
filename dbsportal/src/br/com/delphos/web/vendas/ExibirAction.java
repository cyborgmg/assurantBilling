package br.com.delphos.web.vendas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.adquirentes.BandeiraDLO;
import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Texto;

public class ExibirAction extends StrutsAction {

    @Override
    public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	CobrancaDLO dloCobranca = (CobrancaDLO) ServiceLocator
            			.lookup("java:global/dbsdb/CobrancaDLOBean");
    	
    	BandeiraDLO dloBandeira = (BandeiraDLO) ServiceLocator
    			.lookup("java:global/dbsdb/BandeiraDLOBean");
    	    	
    	VendasActionForm vaf = (VendasActionForm) form;
    	VendasActionForm vafsession = (VendasActionForm) request.getSession().getAttribute("vaf");
        
        Cobranca cobranca = dloCobranca.obter(Long.parseLong(vaf.getIdCobranca()));
        Bandeira bandeira = dloBandeira.obterPorCodigo(cobranca.getCodigoBandeiraCartao());
        
        vaf.setBandeira(bandeira.getNomeBandeira());
        vaf.setUltimosDigCobranca(cobranca.getUltimosDigitosCartao());
        vaf.setCdAutorizacao(cobranca.getCodigoAutorizacao());
        vaf.setDataCobranca(Data.formatar(cobranca.getDataCobranca(), Data.MASCARA_SEM_HORA));
        vaf.setMeioPagamento(cobranca.getContratoCobranca().getMeioPagamento().getDescricao());
        vaf.setNumComprovante(cobranca.getNumeroComprovanteVenda());
        vaf.setValor(Texto.doubleToMoeda(cobranca.getValorCobranca().doubleValue(), "###,###,###,##0.00"));
        vaf.setNumParcela(String.valueOf(cobranca.getNumeroParcela()));
        //vaf.setStatusCobrancaDescricao(cobranca.getStatusCobranca().getDescricao());
        
        vafsession.setIdCobranca(vaf.getIdCobranca());
        vafsession.setBandeira(vaf.getBandeira());
        vafsession.setUltimosDigCobranca(cobranca.getUltimosDigitosCartao());
        vafsession.setCdAutorizacao(vaf.getCdAutorizacao());
        vafsession.setDataCobranca(vaf.getDataCobranca());
        vafsession.setMeioPagamento(vaf.getMeioPagamento());
        vafsession.setNumComprovante(vaf.getNumComprovante());
        vafsession.setValor(vaf.getValor());
        vafsession.setNumParcela(vaf.getNumParcela());
        //eaf.setNumMaxTentativas(cobranca.get);
        
        
        request.getSession().setAttribute("vaf", vafsession);
        
        return mapping.getInputForward();
    }
}