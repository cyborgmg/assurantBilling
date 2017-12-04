package br.com.delphos.web.vendas;

import java.util.List;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.conciliacoes.ProcConciliacaoEvento;
import br.com.delphos.billing.conciliacoes.ProcConciliacaoEventoDLO;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.tentativas.Evento;
import br.com.delphos.billing.tentativas.EventoDLO;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.tentativas.TentativaDLO;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.Texto;

public class VendaViewHelper {
	
	private VendaDLO vendaDLO = (VendaDLO) ServiceLocator.lookup("java:/global/dbsdb/VendaDLOBean");
	private CobrancaDLO cobrancaDLO = (CobrancaDLO) ServiceLocator.lookup("java:/global/dbsdb/CobrancaDLOBean");
	private TentativaDLO tentativaDLO = (TentativaDLO) ServiceLocator.lookup("java:/global/dbsdb/TentativaDLOBean");
	private EventoDLO eventoDLO = (EventoDLO) ServiceLocator.lookup("java:/global/dbsdb/EventoDLOBean");
	private ProcConciliacaoEventoDLO procConciliacaoEventoDLO = (ProcConciliacaoEventoDLO) ServiceLocator.lookup("java:/global/dbsdb/ProcConciliacaoEventoDLOBean");

	private String codEmpresa;
	private String idProduto;
	private String idSistema;
	private String cpf;
	private String certificado;	
	private String idVenda;
	private String idTentativa;	
	private String idCobranca;	
	
    public List<Venda> listarVendas(){

    	List<Venda> retorno = null;
    	cpf = Texto.tiraMascaraCpf(cpf);
    	
			
			retorno = vendaDLO.listarVendasPorCriterio(codEmpresa, idProduto, idSistema, cpf, certificado);
			
        
        return retorno;
    }
    
    public List<Cobranca> listarCobrancasPorVenda() throws DLOException {
    	return cobrancaDLO.listarCobrancasPorVenda(Long.valueOf(idVenda));
    }
    
    public List<Tentativa> listarTentativasPorCobranca() throws NumberFormatException, DLOException {
    	return tentativaDLO.listarTentativasPorCobranca(Long.valueOf(idCobranca));
    }
    
    public List<Evento> listarEventosPorTentativa() {
    	return eventoDLO.listarEventosUltimaCobrancaPorVenda(Long.valueOf(idVenda));
    }
    
    public List<Evento> listarEventosUltimaCobrancaPorVenda() {
    	return eventoDLO.listarEventosUltimaCobrancaPorVenda(Long.valueOf(idVenda));
    }
    
    public List<ProcConciliacaoEvento> listarConciliacoesPorCobranca() throws NumberFormatException, DLOException {
    	return procConciliacaoEventoDLO.listarConciliacaoPorCobranca(Long.valueOf(idCobranca));
    }

    public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}

	public String getIdTentativa() {
		return idTentativa;
	}

	public void setIdTentativa(String idTentativa) {
		this.idTentativa = idTentativa;
	}

	public String getIdCobranca() {
		return idCobranca;
	}

	public void setIdCobranca(String idCobranca) {
		this.idCobranca = idCobranca;
	}
}