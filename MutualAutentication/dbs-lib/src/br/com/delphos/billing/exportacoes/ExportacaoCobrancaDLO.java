package br.com.delphos.billing.exportacoes;

import javax.ejb.Remote;

import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ExportacaoCobrancaDLO extends DLOEntidade<ExportacaoCobranca> {

}
