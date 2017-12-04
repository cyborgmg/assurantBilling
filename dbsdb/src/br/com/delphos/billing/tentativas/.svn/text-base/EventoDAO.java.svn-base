package br.com.delphos.billing.tentativas;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.enumeracoes.TipoEvento;
import br.com.delphos.billing.persistencia.DAOEntidade;
import br.com.delphos.billing.vendas.Venda;

@Local
public interface EventoDAO extends DAOEntidade<Evento> {
	public void gerarHistoricoEvento(Tentativa tentativa, TipoEvento tipoEvento,
			String complemento);
	public List<Evento> listarEventosPorTentativa(Tentativa tentativa);
	public List<Evento> listarEventosUltimaCobrancaPorVenda(Venda venda);
}
