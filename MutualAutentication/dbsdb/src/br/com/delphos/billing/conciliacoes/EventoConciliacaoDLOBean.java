package br.com.delphos.billing.conciliacoes;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.enumeracoes.TipoEvento;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.excecoes.ValidacaoDLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.util.DateUtils;

@Stateless
public class EventoConciliacaoDLOBean extends AbstractDLO<EventoConciliacao> implements EventoConciliacaoDLO {
	
	@EJB
	private EventoConciliacaoDAO dao;

	@Override
	public boolean isEditavel(EventoConciliacao entidade) throws DLOException {
		// Eventos de conciliação não são editáveis
		return false;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void gerarHistoricoEvento(Cobranca cobranca, TipoEvento tipoEvento, String complemento,
			Integer numeroParcelaLoja) throws DLOException {
		if (cobranca == null 
				|| cobranca.getId() == null
				|| tipoEvento == null
				|| (complemento != null && complemento.isEmpty())) {
			throw new ValidacaoDLOException();
		}
		EventoConciliacao evento = new EventoConciliacao();
		evento.setCobranca(cobranca);
		evento.setTipo(tipoEvento);
		evento.setDataEvento(DateUtils.getDataHoraAtual());
		evento.setNumeroParcelaLoja(numeroParcelaLoja);
		evento.setComplemento(complemento);
		salvar(evento);
	}

	@Override
	protected EventoConciliacaoDAO getDAOEntidade() {
		return dao;
	}

}
