package br.com.delphos.billing.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	public static TimeZone getTimeZoneGlobal() {
		return TimeZone.getTimeZone("UTC");
	}
	
	public static Date removerHoras(Date data) {
		Calendar cal = getCalendarDataHoraAtual();
		cal.setTime(data);
	    removerHoras(cal);
	    return cal.getTime();
	}

	public static Calendar removerHoras(Calendar cal) {
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal;
	}

	public static Calendar atribuirDataHora(Calendar cal, Date data) {
		cal.setTime(data);
		return cal;
	}
	
	public static Calendar atribuirData(Calendar cal, Date data) {
		cal.setTime(data);
		return removerHoras(cal);
	}
	
	public static Date horaParaMeiaNoite(Date data) {
		Calendar cal = getCalendarDataHoraAtual();
		cal.setTime(data);
		cal = horaParaMeiaNoite(cal);
	    return cal.getTime();
	}
	
	public static Calendar horaParaMeiaNoite(Calendar cal) {
	    cal.set(Calendar.HOUR_OF_DAY, 23);
	    cal.set(Calendar.MINUTE, 59);
	    cal.set(Calendar.SECOND, 59);
	    cal.set(Calendar.MILLISECOND, 999);
	    return cal;
	}
	
	public static Date getDataHoraAtual() {
		return getCalendarDataHoraAtual().getTime();
	}
	
	public static Date getDataAtual() {
		return getCalendarDataAtual().getTime();
	}
	
	public static Calendar getCalendarDataHoraAtual() {
		return Calendar.getInstance();
	}

	public static Calendar getCalendarDataAtual() {
		Calendar cal = getCalendarDataHoraAtual();
		return removerHoras(cal);
	}	
	
	public static String toISOString(Date data) {
	    DateFormat df = getISOFormatter();
	    return df.format(data);
	}
	
	public static DateFormat getISOFormatter() {
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
	    df.setTimeZone(getTimeZoneGlobal());
	    return df;
	}
}
