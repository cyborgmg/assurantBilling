package br.com.delphos.billing.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import br.com.delphos.billing.configuracoes.Constantes;

public class CurrencyUtils {
	
	public static BigDecimal fromString(String valor) {
		BigDecimal decimal = new BigDecimal(tratarValor(valor), getMathContext());
		return decimal;
	}
	
	public static BigDecimal fromLong(long valor) {
		BigDecimal decimal = new BigDecimal(valor, getMathContext()).setScale(Constantes.EscalaValorMonetario);
		return decimal;
	}
	
	public static BigDecimal fromUnscaledLong(long valor) {
		BigDecimal decimal = new BigDecimal(valor, getMathContext()).setScale(Constantes.EscalaValorMonetario).divide(new BigDecimal(100));
		return decimal;
	}
	
	public static long fromBigDecimal(BigDecimal valor) {
		long resultado = valor.scaleByPowerOfTen(Constantes.EscalaValorMonetario).longValue();
		return resultado;
	}
	
	public static MathContext getMathContext() {
		return new MathContext(Constantes.PrecisaoValorMonetario, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal fromTaxString(String valor) {
		BigDecimal decimal = new BigDecimal(valor, getTaxMathContext());
		return decimal;
	}
	
	public static BigDecimal fromTaxLong(long valor) {
		BigDecimal decimal = new BigDecimal(valor, getTaxMathContext()).setScale(Constantes.EscalaValorTaxa);
		return decimal;
	}
	
	public static BigDecimal fromUnscaledTaxLong(long valor) {
		BigDecimal decimal = new BigDecimal(valor, getTaxMathContext()).setScale(Constantes.EscalaValorTaxa).divide(new BigDecimal(100));
		return decimal;
	}
	
	public static long fromTaxBigDecimal(BigDecimal valor) {
		long resultado = valor.scaleByPowerOfTen(Constantes.EscalaValorTaxa).longValue();
		return resultado;
	}
	
	public static MathContext getTaxMathContext() {
		return new MathContext(Constantes.PrecisaoValorTaxa, RoundingMode.HALF_EVEN);
	}
	
	public static String tratarValor(String valor) {
		return valor.replace(".", "").replace(",", ".");
	}
}
