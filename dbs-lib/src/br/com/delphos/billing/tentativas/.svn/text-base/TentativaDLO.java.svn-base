package br.com.delphos.billing.tentativas;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.enumeracoes.TipoTentativa;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface TentativaDLO extends DLOEntidade<Tentativa> {
	
	public Tentativa gerarTentativa(Cobranca cobranca, TipoTentativa tipoTentativa) throws DLOException;
	
	public List<Tentativa> listarTentativasCobrancaPorVenda(Long vendaId) throws DLOException;

	public List<Tentativa> listarTentativasPorCobranca(Long idcobranca) throws DLOException;

}
