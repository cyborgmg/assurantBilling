package br.com.delphos.web.vendas;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.Empresa;
import br.com.delphos.util.StrutsAction;

public class VendasAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("resultado");
		VendasActionForm vaf = (VendasActionForm) form;

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
String descUser = (String) session.getAttribute("corporativo_usuario");

		String codEmpresa = vaf.getEmpresa() == null ? vaf.getCodEmpresa() : vaf.getEmpresa();
		String idProduto = vaf.getProduto() == null ? vaf.getCodProduto() : vaf.getProduto();
		String idSistema = vaf.getSistema() == null ? vaf.getCodSistema() : vaf.getSistema();
		String cpf = vaf.getCpf();
		String certificado = vaf.getCertificado();

		List<Empresa> empresas = (List<Empresa>) session.getAttribute("empresas_usuario_logado");

		if (codEmpresa == null || Validador.vazio(codEmpresa)) {
			for (Iterator iterator = empresas.iterator(); iterator.hasNext();) {
				Empresa empresa = (Empresa) iterator.next();
				codEmpresa += empresa.getCodigo() + ",";
			}
			// codEmpresa = codEmpresa.substring(0, codEmpresa.length() - 1);
		}

		session.setAttribute("codEmpresa", codEmpresa);
		session.setAttribute("idProduto", idProduto);
		session.setAttribute("idSistema", idSistema);
		session.setAttribute("cpf", cpf);
		session.setAttribute("certificado", certificado);

		return retorno;
	}
}