package br.com.delphos.billing.tentativas;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.TipoTentativa;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.excecoes.ValidacaoDLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDLO;

/**
 * Session Bean implementation class TentativaDLOBean
 */
@Stateless
public class TentativaDLOBean extends AbstractDLO<Tentativa> implements TentativaDLO {
	public static Logger LOGGER = LoggerFactory.getLogger(TentativaDLOBean.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);
	
	@EJB
	private TentativaDAO dao;
	
	@EJB(mappedName = "java:global/dbsdb/VendaDLOBean")
	private VendaDLO vendaDLO;
	
	@EJB(mappedName = "java:global/dbsdb/CobrancaDLOBean")
	private CobrancaDLO cobrancaDLO;
	
	@Override
	protected TentativaDAO getDAOEntidade() {
		return dao;
	}
	
	public List<Tentativa> listarTentativasCobrancaPorVenda(Long vendaId) throws DLOException {
		
		if (vendaId == null) {
			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.idVenda")));
		}

		Venda venda = vendaDLO.obterVendaPorId(String.valueOf(vendaId));
		
		if (venda == null) {
			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.venda")));
		}
		
		List<Tentativa> listaTentativas = dao.listarTentativasCobrancaPorVenda(venda);
		
		return listaTentativas;
	}
	
	public List<Tentativa> listarTentativasPorCobranca(Long idcobranca) throws DLOException {
		
		if (idcobranca == null) {
			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.idCobranca")));
		}
		
		Cobranca cobranca = cobrancaDLO.obter(idcobranca);
		
		if (cobranca == null) {
			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.cobranca")));
		}
		
		List<Tentativa> listaTentativas = dao.listarTentativasPorCobranca(cobranca);
		
		return listaTentativas;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Tentativa gerarTentativa(Cobranca cobranca, TipoTentativa tipoTentativa) throws DLOException {
		cobranca = cobrancaDLO.obterEntidadeAtualizada(cobranca);
		
		if (cobranca == null) {
			throw new ValidacaoDLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.cobranca")));
		}
		
		if (tipoTentativa == null) {
			throw new ValidacaoDLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.tipoTentativa")));
		}
		
		cobranca.setNumeroUltimaTentativa(cobranca.getNumeroUltimaTentativa() + 1);
		
		Tentativa tentativa = new Tentativa();
		tentativa.setCobranca(cobranca);
		tentativa.setNumeroTentativa(cobranca.getNumeroUltimaTentativa());
		tentativa.setDataTentativa(DateUtils.getDataHoraAtual());
		tentativa.setTipoTentativa(tipoTentativa);
		
		tentativa.setId((Long) dao.incluir(tentativa));
		
		if (tentativa.getId() == null || cobrancaDLO.manter(cobranca) == null) {
			throw new DLOException(CodigoMensagem.Falha);
		}
		
		return obterEntidadeAtualizada(tentativa);
	}

	@Override
	public boolean isEditavel(Tentativa entidade) throws DLOException {
		// TODO Auto-generated method stub
		return false;
	}
}