package br.com.delphos.billing.tentativas;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.persistencia.DAOEntidade;
import br.com.delphos.billing.vendas.Venda;

@Local
public interface TentativaDAO extends DAOEntidade<Tentativa> {
	
	public List<Tentativa> listarTentativasCobrancaPorVenda(Venda venda);
	
	public List<Tentativa> listarTentativasPorCobranca(Cobranca cobranca);
	
}
