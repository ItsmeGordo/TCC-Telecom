package br.edu.ifsc.fsbilling.view.login;

import java.io.Serializable;
import java.util.LinkedList;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.edu.ifsc.fsbilling.business.administrador.AdministradorManagerBean;
import br.edu.ifsc.fsbilling.entity.Administrador;
import br.edu.ifsc.fsbilling.utils.Redirect;
import br.edu.ifsc.fsbilling.view.Path;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private AdministradorManagerBean adminManager;

	private Administrador administrador;
	private LinkedList<FacesMessage> messages;
	private String login, senha;

	/**
	 * Executa a autenticacao de usuarios.
	 * 
	 * @return Caso o usuario seja autenticado, direciona para a pagina home,
	 *         caso nao autentique, redireciona para a pagina de login.
	 */
	public void authenticate() {

		// executa a autenticacao dos usuários
		administrador = adminManager.login(login, senha);

		if (this.administrador != null) {
			// caso o usuário exista, redireciona para a página inicial
			Redirect.toUrl(Path.HOME).go();
		} else {
			Redirect.toUrl(Path.LOGIN).go();
			// adiciona mensagem de erro

		}
	}

	/** Exibe as mensagens da fila na tela do usuário. */
	public void showMessages() {
		while (!messages.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					messages.pollFirst());
		}
	}

	/**
	 * Adiciona uma nova mensagem na fila. Esta será posteriormente exibida na
	 * tela do usuário através do método showMessages().
	 * 
	 * @param message
	 */
	public void addMessage(FacesMessage message) {
		messages.add(message);
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
