package br.edu.ifsc.fsbilling.view.logcredito;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.edu.ifsc.fsbilling.business.logcredito.LogCreditoManagerBean;
import br.edu.ifsc.fsbilling.business.usuario.UsuarioManagerBean;
import br.edu.ifsc.fsbilling.entity.LogCredito;
import br.edu.ifsc.fsbilling.entity.Usuario;
import br.edu.ifsc.fsbilling.utils.Redirect;
import br.edu.ifsc.fsbilling.view.AbstractBean;
import br.edu.ifsc.fsbilling.view.Path;
import br.edu.ifsc.fsbilling.view.login.LoginBean;

import java.io.Serializable;
import java.util.List;

@Named(value = "logCreditoBean")
@ViewScoped
public class LogCreditoBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private LazyLogCreditoDataModel logCreditoDataModel;
	private LogCredito logCredito;
	
	@EJB
	private LogCreditoManagerBean logCreditoManager;

	@EJB
	private UsuarioManagerBean usuarioManager;
	
	@Inject
	private LoginBean loginBean;
	
	private List<Usuario> usuarios;
	
	@PostConstruct
	public void postConstruct() {
		if (logCredito == null) {
			logCredito = new LogCredito();
		}
		
		setUsuarios(usuarioManager.listAll());
	}

	@Override
	public boolean preEdit() {
		if (logCredito.getId() == null) {
			return false;
		} else {
			logCredito = logCreditoManager.findById(logCredito.getId(), LogCredito.class);
			return true;
		}

	}

	public List<Usuario> completeUsuario(final String filter) {
		return usuarioManager.retrieveUsers(filter, 6);
	}
	
	public void viewInit() {
		logCreditoDataModel = new LazyLogCreditoDataModel(logCreditoManager);
	}

	public void delete() {
		logCredito = logCreditoManager.findById(logCredito.getId(), LogCredito.class);
		logCreditoManager.delete(logCredito);
	}

	public void save() {
		logCredito.setAdmin(loginBean.getAdministrador());
		logCreditoManager.update(logCredito);
		Redirect.toUrl(Path.CREDITO_SEARCH).go();
	}

	public LazyLogCreditoDataModel getLogCreditoDataModel() {
		return logCreditoDataModel;
	}

	public void setLogCreditoDataModel(LazyLogCreditoDataModel logCreditoDataModel) {
		this.logCreditoDataModel = logCreditoDataModel;
	}

	public LogCredito getLogCredito() {
		return logCredito;
	}

	public void setLogCredito(LogCredito logCredito) {
		this.logCredito = logCredito;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
