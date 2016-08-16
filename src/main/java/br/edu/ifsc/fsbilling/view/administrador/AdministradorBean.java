package br.edu.ifsc.fsbilling.view.administrador;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.edu.ifsc.fsbilling.business.administrador.AdministradorManagerBean;
import br.edu.ifsc.fsbilling.entity.Administrador;
import br.edu.ifsc.fsbilling.utils.Redirect;
import br.edu.ifsc.fsbilling.view.AbstractBean;
import br.edu.ifsc.fsbilling.view.Path;

import java.io.Serializable;

@Named(value = "administradorBean")
@ViewScoped
public class AdministradorBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private LazyAdministradorDataModel administradorDataModel;
	private Administrador administrador;

	@EJB
	private AdministradorManagerBean administradorManager;

	@PostConstruct
	public void postConstruct() {
		if (administrador == null) {
			administrador = new Administrador();
		}
	}

	@Override
	public boolean preEdit() {
		if (administrador.getId() == null) {
			return false;
		} else {
			administrador = administradorManager.findById(administrador.getId(), Administrador.class);
			return true;
		}

	}

	public void viewInit() {
		administradorDataModel = new LazyAdministradorDataModel(administradorManager);
	}

	public void delete() {
		administrador = administradorManager.findById(administrador.getId(), Administrador.class);
		administradorManager.delete(administrador);
	}

	public void save() {
		administradorManager.update(administrador);
		Redirect.toUrl(Path.ADMINISTRADOR_SEARCH).go();
	}

	public LazyAdministradorDataModel getAdministradorDataModel() {
		return administradorDataModel;
	}

	public void setAdministradorDataModel(LazyAdministradorDataModel administradorDataModel) {
		this.administradorDataModel = administradorDataModel;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

}
