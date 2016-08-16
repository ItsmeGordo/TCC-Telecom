package br.edu.ifsc.fsbilling.view;

public interface Path {

	/***************************************************************/
	/** VARIÁVEIS **/
	/***************************************************************/

	String JSF = ".jsf";
	String APP = "/fsbilling/";
	String SEARCH = "search";
	String CREATE = "create";
	String VIEW = "view";
	String CONFIG = "config";

	/***************************************************************/
	/** PÁGINAS DA APLICAÇÃO **/
	// #{linkBean.get('')}
	/***************************************************************/

	String LOGIN = "login/login" + JSF;
	String HOME = "home/home" + JSF;

	//Plano de discagem
	String PLANO_DISCAGEM_CREATE = "planodediscagem/" + CREATE + JSF;
	String PLANO_DISCAGEM_SEARCH = "planodediscagem/" + SEARCH + JSF;

	//Administrador
	String ADMINISTRADOR_CREATE = "administrador/" + CREATE + JSF;
	String ADMINISTRADOR_SEARCH = "administrador/" + SEARCH + JSF;

	//Tarifa Venda
	String TARIFA_VENDA_CREATE = "tarifavenda/" + CREATE + JSF;
	String TARIFA_VENDA_SEARCH = "tarifavenda/" + SEARCH + JSF;
	
	//Tarifa Compra
	String TARIFA_COMPRA_CREATE = "tarifacompra/" + CREATE + JSF;
	String TARIFA_COMPRA_SEARCH = "tarifacompra/" + SEARCH + JSF;

	//Usuariop
	String USUARIO_CREATE = "usuario/" + CREATE + JSF;
	String USUARIO_SEARCH = "usuario/" + SEARCH + JSF;

	// Integração
	String INTEGRACAO_CREATE = "integracao/" + CREATE + JSF;
	String INTEGRACAO_SEARCH = "integracao/" + SEARCH + JSF;

	//Plano
	String PLANO_CREATE = "plano/" + CREATE + JSF;
	String PLANO_SEARCH = "plano/" + SEARCH + JSF;

	//Crédito
	String CREDITO_CREATE = "logcredito/" + CREATE + JSF;
	String CREDITO_SEARCH = "logcredito/" + SEARCH + JSF;

}
