package br.edu.ifsc.fsbilling.view.plano;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.edu.ifsc.fsbilling.business.plano.PlanoManagerBean;
import br.edu.ifsc.fsbilling.entity.Plano;
import br.edu.ifsc.fsbilling.utils.Redirect;
import br.edu.ifsc.fsbilling.view.AbstractBean;
import br.edu.ifsc.fsbilling.view.Path;

import java.io.Serializable;

@Named(value = "planoBean")
@ViewScoped
public class PlanoBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private LazyPlanoDataModel planoDataModel;
	private Plano plano;
	
	@EJB
	private PlanoManagerBean planoManager;

	@PostConstruct
	public void postConstruct() {
		if (plano == null) {
			plano = new Plano();
		}
	}

	@Override
	public boolean preEdit() {
		if (plano.getId() == null) {
			return false;
		} else {
			plano = planoManager.findById(plano.getId(), Plano.class);
			return true;
		}

	}

	public void viewInit() {
		planoDataModel = new LazyPlanoDataModel(planoManager);
	}

	public void delete() {
		plano = planoManager.findById(plano.getId(), Plano.class);
		planoManager.delete(plano);
	}

	public void save() {
		planoManager.update(plano);
		Redirect.toUrl(Path.PLANO_SEARCH).go();
	}

	public LazyPlanoDataModel getPlanoDataModel() {
		return planoDataModel;
	}

	public void setPlanoDataModel(LazyPlanoDataModel planoDataModel) {
		this.planoDataModel = planoDataModel;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

}
