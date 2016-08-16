package br.edu.ifsc.fsbilling.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.inject.Inject;

import br.edu.ifsc.fsbilling.utils.MessageUtils;
import br.edu.ifsc.fsbilling.view.login.LoginBean;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean editing;
	
	@Inject
	private LoginBean loginBean;
	
	/***************************************************************/
	/** VIEW LOAD **/
	/***************************************************************/

	/** Acionado pela maioria das páginas, antes da renderização das mesmas,
	 * através do evento preRenderView. */
	public void init() {
		if (!isEditing()) {
			editing = preEdit();
		}
	}

	/** Carrega os dados da(s) entidade(s) para edição.
	 * @return */
	protected boolean preEdit() {
		// sobreescrever caso necessario
		return false;
	}

	/***************************************************************/
	/** BUSINESS **/
	/***************************************************************/

	/** Retorna a mensagem do sistema referente a chave passada por parâmetro. As
	 * mensagens encontram-se em Messages_pt_BR.properties.
	 * @param key chave da mensagem
	 * @return */
	protected String label(String key) {
		return MessageUtils.getMessage(key);
	}

	/** Retorna a mensagem do sistema referente a chave passada por parâmetro e a
	 * formata com os valores passados no parâmetro params. As mensagens
	 * encontram-se em Messages_pt_BR.properties.
	 * @param key chave da mensagem
	 * @param params valores para inclusão na mensagem
	 * @return */
	protected String label(String key, Object... params) {
		return MessageUtils.getMessage(key, params);
	}

	/** Exibe uma mensagem informativa na tela. Caso o parâmetro informado seja a
	 * chave de alguma mensagem do sistema, a mesma será exibida.
	 * @param message = mensagem para exibição */
	protected void infoMessage(String message, Object... params) {
		addMessage(FacesMessage.SEVERITY_INFO, message, "", params);
	}

	/** Exibe uma mensagem informativa, com detalhes, na tela. Caso o parâmetro
	 * informado seja a chave de alguma mensagem do sistema, a mesma será
	 * exibida.
	 * @param message */
	protected void infoDetailMessage(String message, String detail, Object... params) {
		addMessage(FacesMessage.SEVERITY_INFO, message, detail, params);
	}

	/** Exibe uma mensagem de alerta na tela. Caso o parâmetro informado seja a
	 * chave de alguma mensagem do sistema, a mesma será exibida.
	 * @param message = mensagem para exibição */
	protected void warnMessage(String message) {
		addMessage(FacesMessage.SEVERITY_WARN, message, "");
	}

	/** Exibe uma mensagem de alerta, com detalhes, na tela. Caso o parâmetro
	 * informado seja a chave de alguma mensagem do sistema, a mesma será
	 * exibida.
	 * @param message = mensagem para exibição */
	protected void warnMessage(String message, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, message, detail);
	}

	/** Exibe uma mensagem de erro na tela. Caso o parâmetro informado seja a
	 * chave de alguma mensagem do sistema, a mesma será exibida.
	 * @param message = mensagem para exibição */
	protected void errorMessage(String message, Object... params) {
		addMessage(FacesMessage.SEVERITY_ERROR, message, "", params);
	}

	/** Exibe uma mensagem de erro, com detalhes, na tela. Caso o parâmetro
	 * informado seja a chave de alguma mensagem do sistema, a mesma será
	 * exibida.
	 * @param message = mensagem para exibição */
	protected void errorDetailMessage(String message, String detail, Object... params) {
		addMessage(FacesMessage.SEVERITY_ERROR, message, detail, params);
	}

	/** Exibe uma mensagem na tela. Caso o parâmetro informado seja a chave de
	 * alguma mensagem do sistema, a mesma será exibida.
	 * @param severity = severidade da mensagem
	 * @param message = mensagem para exibição
	 * @param detail = mensagem adicional em forma de detalhe */
	protected void addMessage(Severity severity, String message, String detail, Object... params) {
		loginBean.addMessage(new FacesMessage(severity, label(message, params), label(detail)));
	}

	/***************************************************************/
	/** ACCESSORS **/
	/***************************************************************/

	/** Retorna true caso o estado atual do bean seja a edição de uma entidade.
	 * (Utilize o método preEdit() para carregar a entidade da edição e retorne
	 * true, simbolizando que uma edição estará em andamento)
	 * @return */
	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

}
