package br.com.delphos.web.consultas;

import java.util.ArrayList;
import java.util.List;

import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.util.ServiceLocator;

public class ConsultasViewHelper {
	
	private VendaDLO vendaDLO = (VendaDLO) ServiceLocator.lookup("java:/global/dbsdb/VendaDLOBean");
	
    public List<Venda> listarVendas(){
        
        List<Venda> retorno = vendaDLO.listar();  
        
        return retorno;
    }

	public List<Files> resultado() {

		List<Files> retorno = new ArrayList<Files>();

		Files linha01 = new Files();

		linha01.setEmpresa("AIZ");
		linha01.setSistema("PYB");
		linha01.setProduto("BMS");
		linha01.setCpf("342.298.574-38");
		linha01.setNome("AFONSO SILVERINO NOGUEIRA");
		linha01.setCertificado("00102030405060708090");
		linha01.setValor("160,00");
		linha01.setDataVenda("20/03/2015");

		retorno.add(linha01);

		Files linha02 = new Files();

		linha02.setEmpresa("XXX");
		linha02.setSistema("YYYYYYYYYY");
		linha02.setProduto("ZZZZZZZZZZ");
		linha02.setCpf("342.298.574-38");
		linha02.setNome("AFONSO SILVERINO NOGUEIRA");
		linha02.setCertificado("00102030405060708080");
		linha02.setValor("160,00");
		linha02.setDataVenda("21/03/2015");

		retorno.add(linha02);

		Files linha03 = new Files();

		linha03.setEmpresa("XXX");
		linha03.setSistema("YYYYYYYYYY");
		linha03.setProduto("ZZZZZZZZZZ");
		linha03.setCpf("342.298.574-38");
		linha03.setNome("AFONSO SILVERINO NOGUEIRA");
		linha03.setCertificado("00102030405060708060");
		linha03.setValor("160,00");
		linha03.setDataVenda("22/03/2015");

		retorno.add(linha03);

		return retorno;
	}

	public List<Files> tentativas() {

		List<Files> retorno = new ArrayList<Files>();

		Files linha01 = new Files();
		Files linha02 = new Files();
		Files linha03 = new Files();

		linha01.setNumeroTentativa("001");
		linha01.setDataTentativa("20/03/2015 13:10");
		linha01.setCodRetorno("12345677");
		linha01.setMensagem("Mensagem teste 1.");

		linha02.setNumeroTentativa("002");
		linha02.setDataTentativa("23/03/2015 13:15");
		linha02.setCodRetorno("12345688");
		linha02.setMensagem("Mensagem teste 2.");

		linha03.setNumeroTentativa("003");
		linha03.setDataTentativa("26/03/2015 13:20");
		linha03.setCodRetorno("12345699");
		linha03.setMensagem("Mensagem teste 3.");

		retorno.add(linha03);
		retorno.add(linha02);
		retorno.add(linha01);

		return retorno;
	}

	public List<Files> eventos() {

		List<Files> retorno = new ArrayList<Files>();

		Files linha01 = new Files();
		Files linha02 = new Files();
		Files linha03 = new Files();

		linha01.setDataEvento("26/03/2015 15:02:20");
		linha01.setTipo("Gerada cobrança por recorrência");
		linha01.setComplemento("");
		linha02.setDataEvento("26/03/2015 15:02:30");
		linha02.setTipo("Chamado método AuthorizeTransaction do Gateway");
		linha02.setComplemento("");
		linha03.setDataEvento("26/03/2015 15:02:35");
		linha03.setTipo("Retorno método AuthorizeTransaction do Gateway");
		linha03.setComplemento("");

		retorno.add(linha03);
		retorno.add(linha02);
		retorno.add(linha01);

		return retorno;
	}

	public List<Files> vendasPaginacao() {

		List<Files> retorno = new ArrayList<Files>();

		Files linha01 = new Files();
		Files linha02 = new Files();
		Files linha03 = new Files();
		Files linha04 = new Files();
		Files linha05 = new Files();
		Files linha06 = new Files();
		Files linha07 = new Files();
		Files linha08 = new Files();

		linha01.setNumeroParcela("001");
		linha01.setDataCobranca("30/08/2014 12:00:00");
		linha01.setValor("20,00");
		linha01.setStatus("CAPTURADO");

		linha02.setNumeroParcela("002");
		linha02.setDataCobranca("30/09/2014 12:10:00");
		linha02.setValor("20,00");
		linha02.setStatus("CAPTURADO");

		linha03.setNumeroParcela("003");
		linha03.setDataCobranca("30/10/2014 12:20:00");
		linha03.setValor("20,00");
		linha03.setStatus("CAPTURADO");

		linha04.setNumeroParcela("004");
		linha04.setDataCobranca("30/11/2014 12:30:00");
		linha04.setValor("20,00");
		linha04.setStatus("CAPTURADO");

		linha05.setNumeroParcela("005");
		linha05.setDataCobranca("30/12/2014 12:40:00");
		linha05.setValor("20,00");
		linha05.setStatus("CAPTURADO");

		linha06.setNumeroParcela("006");
		linha06.setDataCobranca("30/01/2015 12:50:00");
		linha06.setValor("20,00");
		linha06.setStatus("CAPTURADO");

		linha07.setNumeroParcela("007");
		linha07.setDataCobranca("28/02/2015 13:00:00");
		linha07.setValor("20,00");
		linha07.setStatus("CAPTURADO");

		linha08.setNumeroParcela("008");
		linha08.setDataCobranca("");
		linha08.setValor("20,00");
		linha08.setStatus("PENDENTE");

		retorno.add(linha08);
		retorno.add(linha07);
		retorno.add(linha06);
		retorno.add(linha05);
		retorno.add(linha04);
		retorno.add(linha03);
		retorno.add(linha02);
		retorno.add(linha01);

		return retorno;
	}
}