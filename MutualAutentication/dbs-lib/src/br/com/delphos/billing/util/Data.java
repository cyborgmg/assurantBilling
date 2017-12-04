package br.com.delphos.billing.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Classe para manipulação de tempo.
 */
public final class Data {

    public static final long DIA = 1000 * 60 * 60 * 24;
    public static final String MASCARA_COM_HORA = "dd/MM/yyyy kk:mm:ss";
    public static final String MASCARA_SEM_HORA = "dd/MM/yyyy";

    private Data() {
        //construtor privado para que a classe não seja instanciada
    }

    /**
     * Formata campo do tipo Date para String.
     *
     * @param date Campo date a ser formatado
     * @param mascara Mascara a ser usada para formataÃ§Ã£o do campo.
     * @return String com a data formatada.
     */
    public static String formatar(final Date date, final String mascara) {

        String retorno = null;
        if (date != null && !Validador.vazio(mascara)) {

            SimpleDateFormat sdf = new SimpleDateFormat(mascara);
            retorno = sdf.format(date);
        }
        return retorno;
    }

    /**
     * Formata campo do tipo string para date.
     *
     * @param data
     * @param mascara Mascara a ser usada para formataÃ§Ã£o do campo.
     * @return Date com a data formatada.
     * @throws ParseException
     */
    public static Date obterData(final String data, final String mascara)
            throws ParseException {

        Date retorno = null;
        if (data != null && !Validador.vazio(mascara)) {

            SimpleDateFormat sdf = new SimpleDateFormat(mascara);
            retorno = sdf.parse(data);
        }
        return retorno;
    }

    /**
     * Retorna uma nova DATA a partir de uma data base e a quantidade de dias
     * desejada (MAIS ou MENOS)
     *
     * @param dataInicial
     * @param dias Quantidade de dias para cálculo da nova data (MAIS ou MENOS).
     * @return Date com a nova data calculada.
     */
    public static Date calcularData(final Date dataInicial, final int dias) {
        Date retorno = null;

        if (dataInicial != null && dias != 0) {
            long tempo = dataInicial.getTime();
            tempo += dias * DIA;
            retorno = new Date(tempo);
        }
        return retorno;
    }

    /**
     * Retorna uma nova DATA a partir de uma data base e a quantidade de dias
     * desejada (MAIS ou MENOS)
     *
     * @param dataInicial
     * @param dias Quantidade de dias para cálculo da nova data (MAIS ou MENOS).
     * @return Date com a nova data calculada.
     */
    public static Date calcularData(final Date dataInicial, final int dias, boolean truncaHora) {
        Date retorno = null;

        if (dataInicial != null) {

            if (truncaHora) {

                retorno = calcularData(dataInicial, dias);
                if (retorno != null) {

                    retorno.setHours(0);
                    retorno.setMinutes(0);
                    retorno.setSeconds(0);
                }
            } else {

                retorno = calcularData(dataInicial, dias);
            }
        }
        return retorno;
    }

    /**
     * Retorna o numero de dias entre duas datas
     *
     * @param dataInicial
     * @param dataFinal
     * @return long com o numero de dias entre as duas datas.
     */
    public static long diasEntreDatas(final Date dataInicial, final Date dataFinal) {
        if (dataInicial == null || dataFinal == null) {
            return 0;
        }
        long dias = (long) ((dataFinal.getTime() - dataInicial.getTime()) / (24 * 60 * 60 * 1000));
        return (dias > 0 ? dias : 0);
    }

    /**
     * Retorna o numero de dias úteis (segunda a sexta-feira) entre duas datas
     *
     * @param dataInicial
     * @param dataFinal
     * @param feriados * Lista com as datas feriado. Se informada, diminui dos
     * dias uteis de retorno
     * @return long com o numero de dias uteis entre as duas datas informadas.
     */
    public long diasUteisEntreDatas(final Date dataInicial, final Date dataFinal, List feriados) {

        if (dataInicial == null || dataFinal == null) {
            return 0;
        }
        if (feriados.isEmpty()) {
            feriados.add(null);
        }

        long diasUteis = 0;
        long totalDias = diasEntreDatas(dataInicial, dataFinal);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dataInicial);

        for (int i = 0; i <= totalDias; i++) {
            if (!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                    && !(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                    && !(feriados.contains(calendar.getTime()))) {
                diasUteis++;
            }
            calendar.add(Calendar.DATE, 1);
        }

        return (diasUteis > 0 ? diasUteis : 0);
    }

    public List obterFeriados(final Date dataInicial, final Date dataFinal) {

        if (dataInicial == null || dataFinal == null) {
            return null;
        }


        return null;
    }

    /**
     * Retorna o numero de minutos entre duas datas
     *
     * @param dataInicial
     * @param dataFinal
     * @return int com a quantidade de minutos entre as duas datas informadas.
     */
    public static int minutosEntreDatas(Date dataInicial, Date dataFinal) {

        int retorno = 0;

        try {

            DateFormat fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            String strDataInicio = fm.format(dataInicial);
            Date dataInicio = fm.parse(strDataInicio);

            String strDataFinal = fm.format(dataFinal);
            Date dataFim = fm.parse(strDataFinal);

            long segundos = (dataFim.getTime() - dataInicio.getTime()) / 1000;
            retorno = (int) Math.floor(segundos / 60);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;

    }
}

