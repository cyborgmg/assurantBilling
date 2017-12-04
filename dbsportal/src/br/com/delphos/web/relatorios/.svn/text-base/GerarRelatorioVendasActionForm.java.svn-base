package br.com.delphos.web.relatorios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.util.Validador;

public class GerarRelatorioVendasActionForm extends org.apache.struts.action.ActionForm {

	private String relatorio;
	private String empresa;
	private String produto;
	private String sistema;
	private String status;
	private String periodoDe;
	private String periodoAte;
	private String op;
	private String statusSel;
	private String diasAtraso;

	public GerarRelatorioVendasActionForm() {
		super();
	}

	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPeriodoDe() {
		return periodoDe;
	}

	public void setPeriodoDe(String periodoDe) {
		this.periodoDe = periodoDe;
	}

	public String getPeriodoAte() {
		return periodoAte;
	}

	public void setPeriodoAte(String periodoAte) {
		this.periodoAte = periodoAte;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		// TODO:IMPLEMENTAR A VALIDAÇÃO DE PESQUISA VAZIA(NENHUM RESULTADO
		// ENCONTRADO) AQUI
		HttpSession session = request.getSession();

		ActionErrors errors = new ActionErrors();

		request.removeAttribute("erro_validacao");

		int qtdErrosCampos = 0;

		if (relatorio.equals("0")) {
			errors.add("relatorio", new ActionMessage("msg.relatorio.selecione", "relatorio"));
			qtdErrosCampos++;
			
		} else {
			
			if (op.equals("10")) {
				if (Validador.vazio(diasAtraso)) {
					errors.add("diasAtraso", new ActionMessage("erro.dias.vazio", "diasAtraso"));
					qtdErrosCampos++;
				}
				
			} else {
				boolean periodoDeVazio = Validador.vazio(periodoDe);
				boolean periodoAteVazio = Validador.vazio(periodoAte);
				
				if (periodoDeVazio) {
					errors.add("periodoDe", new ActionMessage("erro.periodoDe.log.vazio", "periodoDe"));
					qtdErrosCampos++;
				}
				
				if (periodoAteVazio) {
					errors.add("periodoAte", new ActionMessage("erro.periodoAte.log.vazio", "periodoAte"));
					qtdErrosCampos++;
				}

				if (!periodoDeVazio && !periodoAteVazio) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					sdf.setLenient(false);

					Date dataInicial = null;
					Date dataFinal = null;

					if (!"".equals(periodoDe) && !"".equals(periodoAte)) {
						try {
							dataInicial = sdf.parse(periodoDe);
							dataFinal = sdf.parse(periodoAte);

							if (dataInicial.after(dataFinal)) {
								errors.add("", new ActionMessage("erro.periodoDe.log.depois.periodoAte", ""));
								qtdErrosCampos++;
								
							} else if (dataFinal.before(dataInicial)) {
								errors.add("", new ActionMessage("erro.periodoAte.log.depois.periodoDe", ""));
								qtdErrosCampos++;
							}

						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}

			}

		}

		if (errors.size() > 0) {
			request.getSession().setAttribute("erro_validacao", "erro_validacao");

			if (qtdErrosCampos > 1) {
				errors.add("", new ActionMessage("erro.campoVermelho", ""));
			}

		}

		request.setAttribute("verificaForm", "S");
		request.setAttribute("relatorio_selecionado", relatorio);
		session.setAttribute("status_selecionado", this.getStatusSel());

		return errors;
	}

	public String getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(String diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public String getStatusSel() {
		return statusSel;
	}

	public void setStatusSel(String statusSel) {
		this.statusSel = statusSel;
	}

}