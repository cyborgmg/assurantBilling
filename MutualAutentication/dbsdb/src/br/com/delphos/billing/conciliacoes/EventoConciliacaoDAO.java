package br.com.delphos.billing.conciliacoes;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface EventoConciliacaoDAO extends DAOEntidade<EventoConciliacao> {

}
