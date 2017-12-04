package br.com.delphos.web.vendas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.Texto;

public class VendasActionForm extends ActionForm {
	
	//Flag para aferir se é uma nova busca
	private String novaBusca;

	//PAGAMENTO
    private String empresa;
    private String produto;
    private String sistema;
    private String cpf;
    private String certificado;
    private String codEmpresa;
    private String codProduto;
    private String codSistema;
    private String dataVenda;
    private String dataFimVig;
    private String nome;
    private String email;
    private String valorCob;
    private String qtdParcelas;
    private String meioCobranca;
    private String tipoCobranca;
    private String bandeiraCartao;
    private String vencCartao;
    private String codTipoCobranca;
    private String dddTel;
    private String tel;
    private String dddCel;
    private String cel;
    private String dataCancelamento;
    private String motCancelamento;
    private String statusVenda;
    private String ultimosDigVenda;
    
    //COBRANCA
    private String idCobranca;
    private String dataCobranca;
    private String valor;
    private String meioPagamento;
    private String bandeira;
    private String cdAutorizacao;
    private String numComprovante;
    private String numMaxTentativas;
    private String numParcela;
    private String ultimosDigCobranca;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public String getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(String codSistema) {
		this.codSistema = codSistema;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getDataFimVig() {
		return dataFimVig;
	}

	public void setDataFimVig(String dataFimVig) {
		this.dataFimVig = dataFimVig;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getValorCob() {
		return valorCob;
	}

	public void setValorCob(String valorCob) {
		this.valorCob = valorCob;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public String getMeioCobranca() {
		return meioCobranca;
	}

	public void setMeioCobranca(String meioCobranca) {
		this.meioCobranca = meioCobranca;
	}

	public String getTipoCobranca() {
		return tipoCobranca;
	}

	public void setTipoCobranca(String tipoCobranca) {
		this.tipoCobranca = tipoCobranca;
	}

	public String getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setBandeiraCartao(String bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}

	public String getVencCartao() {
		return vencCartao;
	}

	public void setVencCartao(String vencCartao) {
		this.vencCartao = vencCartao;
	}

	public String getCodTipoCobranca() {
		return codTipoCobranca;
	}

	public void setCodTipoCobranca(String codTipoCobranca) {
		this.codTipoCobranca = codTipoCobranca;
	}

	public String getDddTel() {
		return dddTel;
	}

	public void setDddTel(String dddTel) {
		this.dddTel = dddTel;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDddCel() {
		return dddCel;
	}

	public void setDddCel(String dddCel) {
		this.dddCel = dddCel;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public String getIdCobranca() {
		return idCobranca;
	}

	public void setIdCobranca(String idCobranca) {
		this.idCobranca = idCobranca;
	}

	public String getDataCobranca() {
		return dataCobranca;
	}

	public void setDataCobranca(String dataCobranca) {
		this.dataCobranca = dataCobranca;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getMeioPagamento() {
		return meioPagamento;
	}

	public void setMeioPagamento(String meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getCdAutorizacao() {
		return cdAutorizacao;
	}

	public void setCdAutorizacao(String cdAutorizacao) {
		this.cdAutorizacao = cdAutorizacao;
	}

	public String getNumComprovante() {
		return numComprovante;
	}

	public void setNumComprovante(String numComprovante) {
		this.numComprovante = numComprovante;
	}

	public String getNumMaxTentativas() {
		return numMaxTentativas;
	}

	public void setNumMaxTentativas(String numMaxTentativas) {
		this.numMaxTentativas = numMaxTentativas;
	}

	public String getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(String numParcela) {
		this.numParcela = numParcela;
	}

	public String getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(String dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String getMotCancelamento() {
		return motCancelamento;
	}

	public void setMotCancelamento(String motCancelamento) {
		this.motCancelamento = motCancelamento;
	}


	public String getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(String statusVenda) {
		this.statusVenda = statusVenda;
	}

	public String getUltimosDigVenda() {
		return ultimosDigVenda;
	}

	public void setUltimosDigVenda(String ultimosDigVenda) {
		this.ultimosDigVenda = ultimosDigVenda;
	}

	public String getUltimosDigCobranca() {
		return ultimosDigCobranca;
	}

	public void setUltimosDigCobranca(String ultimosDigCobranca) {
		this.ultimosDigCobranca = ultimosDigCobranca;
	}

	public String getNovaBusca() {
		return novaBusca;
	}

	public void setNovaBusca(String novaBusca) {
		this.novaBusca = novaBusca;
	}
	@Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
        HttpSession session = request.getSession();
        
        ActionErrors errors = new ActionErrors();
        boolean campoVazio = false;
        
        // Se a consulta é feita a partir da opção voltar do Detalhar venda....
        if (!Boolean.valueOf(getNovaBusca()).booleanValue()) {
	        if (session.getAttribute("codEmpresa") != null && !Validador.vazio((String)session.getAttribute("codEmpresa"))) {
	        	this.codEmpresa = (String) session.getAttribute("codEmpresa");
	        }
	        if (session.getAttribute("idProduto") != null && !Validador.vazio((String)session.getAttribute("idProduto"))) {
	        	this.codProduto = (String) session.getAttribute("idProduto");
	        }
	        if (session.getAttribute("idSistema") != null && !Validador.vazio((String)session.getAttribute("idSistema"))) {
	        	this.codSistema = (String) session.getAttribute("idSistema");
	        }
	        if (session.getAttribute("cpf") != null && !Validador.vazio((String)session.getAttribute("cpf"))) {
	        	this.cpf = (String) session.getAttribute("cpf");
	        }
	        if (session.getAttribute("certificado") != null && !Validador.vazio((String)session.getAttribute("certificado"))) {
	        	this.certificado = (String) session.getAttribute("certificado");
	        }
        }

        // - CPF e CERTIFICADO -------------------------------------------------------------------------------------  
        if (Validador.vazio(this.cpf)) {
        	if (Validador.vazio(this.certificado)) {
	            errors.add("cpf", new ActionMessage("", "cpf"));
	            errors.add("certificado", new ActionMessage("", "certificado"));
	            errors.add("", new ActionMessage("erro.cpf.certificado.vazio", ""));
        	}
        } else {
        	if (!Validador.cpf(Texto.tiraMascaraCpf(this.cpf))) {
            	campoVazio = true;
        		errors.add("cpf", new ActionMessage("", "cpf"));
        	}
        }

        if (campoVazio) {
            errors.add("", new ActionMessage("erro.campoVermelho", ""));
            session.removeAttribute("certificado");
        }
        
        if (Boolean.valueOf(getNovaBusca()).booleanValue()) {
        	if (errors.size() > 0) {
        		session.removeAttribute("codEmpresa");
		        if (errors.get("produto").hasNext() == true) {
		        	session.removeAttribute("idProduto");
		        }
		        if (errors.get("sistema").hasNext() == true) {
		        	session.removeAttribute("idSistema");
		        }
		        if (errors.get("cpf").hasNext() == true) {
		        	session.removeAttribute("cpf");
		        }
		        if (errors.get("certificado").hasNext() == true) {
		        		session.removeAttribute("certificado");
		        }
        	} else {
    	        session.removeAttribute("codEmpresa");
    	        session.removeAttribute("idProduto");
    	        session.removeAttribute("idSistema");
    	        session.removeAttribute("cpf");  
    	        session.removeAttribute("certificado");
        	}
        }

        return errors;
    }
}