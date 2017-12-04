package br.com.delphos.billing.tentativas;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.enumeracoes.TipoEvento;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDAO;
import br.com.delphos.billing.vendas.VendaDAOBean;

/**
 * Session Bean implementation class EventoDLOBean
 */
@Stateless
public class EventoDLOBean extends AbstractDLO<Evento> implements EventoDLO {

	@EJB
	private EventoDAO dao;
	
	@EJB
	private TentativaDAO tentativaDAO;
	
	@EJB
	private VendaDAO vendaDAO;

	@Override
	protected EventoDAO getDAOEntidade() {
		return dao;
	}

	/**
	 * @see VendaDAOBean#obterPorIdentidade(Venda)
	 */
	@Override
	protected boolean isEntidadePersistidaPorIdentidade(Evento entidade) {
		return false;
	};

	@Override
	public void gerarHistoricoEvento(Tentativa tentativa, TipoEvento tipoEvento,
			String complemento) {
		if (tentativa != null) { // Obrigatório
			dao.gerarHistoricoEvento(tentativa, tipoEvento, complemento);
		}
	}
	
	public List<Evento> listarEventosPorTentativa(Long idTentativa) {
		
		Tentativa tentativa = tentativaDAO.obter(idTentativa);
		
		return dao.listarEventosPorTentativa(tentativa);
	}
	
	public List<Evento> listarEventosUltimaCobrancaPorVenda(Long idVenda) {
		Venda venda = vendaDAO.obterVendaPorId(String.valueOf(idVenda));
		return dao.listarEventosUltimaCobrancaPorVenda(venda);
	}

	@Override
	public boolean isEditavel(Evento entidade) throws DLOException {
		// TODO Auto-generated method stub
		return false;
	}
}
