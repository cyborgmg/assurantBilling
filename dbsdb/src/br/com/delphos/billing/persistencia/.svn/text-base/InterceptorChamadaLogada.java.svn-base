package br.com.delphos.billing.persistencia;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChamadaLogada
@Interceptor
public class InterceptorChamadaLogada {
	public static Logger LOGGER = LoggerFactory.getLogger(InterceptorChamadaLogada.class);

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		Object retorno = ctx.proceed();
		LOGGER.trace("Retorno do método '{}.{}': {}",
				new Object[] {ctx.getMethod().getDeclaringClass().getCanonicalName(),
				ctx.getMethod().getName(), retorno});
		return retorno;
	}
}
