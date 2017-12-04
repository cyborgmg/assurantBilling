package br.com.delphos.billing.tentativas;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.enumeracoes.TipoEvento;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface EventoDLO extends DLOEntidade<Evento> {
	public void gerarHistoricoEvento(Tentativa tentativa, TipoEvento tipoEvento,
			String complemento);
	public List<Evento> listarEventosPorTentativa(Long idTentativa);
	public List<Evento> listarEventosUltimaCobrancaPorVenda(Long idVenda);
}