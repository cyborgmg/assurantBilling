package br.com.delphos.billing.conciliacoes;

import javax.ejb.Remote;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.enumeracoes.TipoEvento;
import br.com.delphos.billing.excecoes.DLOException;

@Remote
public interface EventoConciliacaoDLO {

	void gerarHistoricoEvento(Cobranca cobranca, TipoEvento tipoEvento,
			String complemento, Integer numeroParcelaLoja) throws DLOException;
}
