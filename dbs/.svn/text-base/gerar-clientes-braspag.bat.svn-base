@echo off

set CONCILIADOR_PACKAGE_NAME=br.com.delphos.billing.braspag.conciliador.cliente
set CONCILIADOR_CLASSPATH_NAME=META-INF/wsdl/braspag/ReconciliationFilesWebService.wsdl

set CONCILIADOR_MODEL_PACKAGE_NAME=br.com.delphos.billing.braspag.conciliador
set CONCILIADOR_MODEL_CLASSPATH_NAME=META-INF/jaxb/braspag/ConciliationFile.xsd

echo *** Gerando modelo de dados a partir do XSD...
echo.
xjc -p %CONCILIADOR_MODEL_PACKAGE_NAME% ^
	-d src ^
	-b bindings.xjb ^
	-catalog catalog.xml ^
	-no-header -npa ^
	ejbModule/%CONCILIADOR_MODEL_CLASSPATH_NAME%

echo.
echo *** Gerando cliente Conciliador
rem NÃO REFERENCIE O Guid.xsd aqui, o WSDL deste serviço já inclui uma definição de GUID.
wsimport -p %CONCILIADOR_PACKAGE_NAME% ^
	-s src ^
	-b bindings.xjb ^
	-Xnocompile ^
	-B-XautoNameResolution ^
	-target 2.1 ^
	-wsdllocation classpath:%CONCILIADOR_CLASSPATH_NAME% ^
	ejbModule/%CONCILIADOR_CLASSPATH_NAME%
