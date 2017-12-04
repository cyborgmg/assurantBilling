package br.com.delphos.web.vendas;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.adquirentes.BandeiraDLO;
import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Texto;

public class DetalharVendasAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		VendaDLO dloVenda = (VendaDLO) ServiceLocator.lookup("java:global/dbsdb/VendaDLOBean");

		BandeiraDLO dloBandeira = (BandeiraDLO) ServiceLocator.lookup("java:global/dbsdb/BandeiraDLOBean");

		ItemListaDLO dloItemLista = (ItemListaDLO) ServiceLocator.lookup("java:global/dbsdb/ItemListaDLOBean");

		CobrancaDLO dloCobranca = (CobrancaDLO) ServiceLocator.lookup("java:global/dbsdb/CobrancaDLOBean");

		String idVenda = request.getParameter("idVenda");

		if (!Validador.vazio(idVenda)) {

			String dddTel = "";
			String tel = "";
			String dddCel = "";
			String cel = "";
			String dataCancelamento = "";
			String motCancelamento = "";

			request.getSession().removeAttribute("vaf");

			VendasActionForm vaf = (VendasActionForm) form;

			Venda venda = dloVenda.obterVendaPorId(idVenda);
			Bandeira bandeira = dloBandeira.obterPorCodigo(venda.getCodigoBandeiraCartao());
			ItemLista itemLista = dloItemLista.obterPorCodigo(venda.getTipoCobranca().getValor(),
					TipoListaValor.Cobranca.getValor());
			ItemLista itemListaStatus = dloItemLista.obterPorCodigo(venda.getStatus().getValor(),
					TipoListaValor.Status.getValor());

			if (!Validador.vazio(venda.getCodigoMotivoCancelamento())) {
				ItemLista itemListaCanc = dloItemLista.obterPorCodigo(venda.getCodigoMotivoCancelamento(),
						TipoListaValor.MotivoCancelamento.getValor());
				motCancelamento = itemListaCanc.getDescricao();
			}

			if (!Validador.vazio(String.valueOf(venda.getDataCancelamento()))) {
				dataCancelamento = Data.formatar(venda.getDataCancelamento(), Data.MASCARA_SEM_HORA);
			}

			if (!Validador.vazio(venda.getTelefone())) {
				MaskFormatter phoneFormatter = new MaskFormatter("####-####");
				JFormattedTextField txtPhone = new JFormattedTextField(phoneFormatter);
				txtPhone.setText(venda.getTelefone());
				dddTel = venda.getDddTelefone();
				tel = txtPhone.getText();
			}

			if (!Validador.vazio(venda.getCelular())) {
				MaskFormatter celFormatter = new MaskFormatter("#####-####");
				JFormattedTextField txtCel = new JFormattedTextField(celFormatter);
				txtCel.setText(venda.getCelular());
				dddCel = venda.getDddCelular();
				cel = txtCel.getText();
			}

			String codEmpresa = venda.getEmpresa().getCodigo();
			String descEmpresa = venda.getEmpresa().getDescricao();
			String codProduto = venda.getProduto().getCodigo();
			String descProduto = venda.getProduto().getDescricao();
			String codSistema = venda.getSistema().getCodigo();
			String descSistema = venda.getSistema().getDescricao();
			String certificado = venda.getCodigoVendaOrigem();
			String dataVenda = Data.formatar(venda.getDataVendaOrigem(), Data.MASCARA_SEM_HORA);
			String statusVenda = itemListaStatus.getDescricao();
			String dataFimVig = Data.formatar(venda.getDataFimVigencia(), Data.MASCARA_SEM_HORA);

			String nome = venda.getNome();
			String cpf = Texto.formataCpf(venda.getCpf());
			String email = venda.getEmail();

			String valorCob = Texto.doubleToMoeda(venda.getValorCobranca().doubleValue(), "###,###,###,##0.00");
			String qtdParcelas = String.valueOf(venda.getQuantidadeParcelas());
			String meioCobranca = venda.getContratoCobranca().getMeioPagamento().getDescricao();
			String tipoCobranca = itemLista.getDescricao();
			String bandeiraCartao = bandeira.getNomeBandeira();
			String ultimosDigVenda = venda.getUltimosDigitosCartao();
			String vencCartao = venda.getVencimentoCartao();

			String codTipoCobranca = venda.getTipoCobranca().getValor();

			vaf.setCodEmpresa(codEmpresa);
			vaf.setEmpresa(descEmpresa);
			vaf.setCodProduto(codProduto);
			vaf.setProduto(descProduto);
			vaf.setCodSistema(codSistema);
			vaf.setSistema(descSistema);
			vaf.setCertificado(certificado);
			vaf.setDataVenda(dataVenda);
			vaf.setStatusVenda(statusVenda);
			;
			vaf.setDataFimVig(dataFimVig);
			vaf.setNome(nome);
			vaf.setCpf(cpf);
			vaf.setEmail(email);
			vaf.setValorCob(valorCob);
			vaf.setQtdParcelas(qtdParcelas);
			vaf.setMeioCobranca(meioCobranca);
			vaf.setTipoCobranca(tipoCobranca);
			vaf.setBandeiraCartao(bandeiraCartao);
			vaf.setUltimosDigVenda(ultimosDigVenda);
			vaf.setVencCartao(vencCartao);
			vaf.setCodTipoCobranca(codTipoCobranca);
			vaf.setDddTel(dddTel);
			vaf.setTel(tel);
			vaf.setDddCel(dddCel);
			vaf.setCel(cel);
			vaf.setDataCancelamento(dataCancelamento);
			vaf.setMotCancelamento(motCancelamento);

			List<Cobranca> listCobrancas = new ArrayList<Cobranca>();
			listCobrancas = dloCobranca.listarCobrancasPorVenda(Long.parseLong(idVenda));

			if (listCobrancas != null && listCobrancas.size() > 0) {

				for (Cobranca cobranca : listCobrancas) {
					if (cobranca.getNumeroParcela() == listCobrancas.size()) {

						Bandeira bandeiraCobranca = dloBandeira.obterPorCodigo(cobranca.getCodigoBandeiraCartao());

						vaf.setIdCobranca(String.valueOf(cobranca.getId()));
						vaf.setBandeira(bandeiraCobranca.getNomeBandeira());
						vaf.setUltimosDigCobranca(cobranca.getUltimosDigitosCartao());
						vaf.setCdAutorizacao(cobranca.getCodigoAutorizacao());
						vaf.setDataCobranca(Data.formatar(cobranca.getDataCobranca(), Data.MASCARA_SEM_HORA));
						vaf.setMeioPagamento(cobranca.getContratoCobranca().getMeioPagamento().getDescricao());
						vaf.setNumComprovante(cobranca.getNumeroComprovanteVenda());
						vaf.setValor(
								Texto.doubleToMoeda(cobranca.getValorCobranca().doubleValue(), "###,###,###,##0.00"));
						vaf.setNumParcela(String.valueOf(cobranca.getNumeroParcela()));
					}
				}
			}

			request.getSession().setAttribute("vaf", vaf);

		}

		return mapping.findForward("exibir");
	}
}