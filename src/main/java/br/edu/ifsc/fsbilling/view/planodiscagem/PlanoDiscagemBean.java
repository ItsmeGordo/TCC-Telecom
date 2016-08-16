package br.edu.ifsc.fsbilling.view.planodiscagem;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.edu.ifsc.fsbilling.business.plano.PlanoManagerBean;
import br.edu.ifsc.fsbilling.business.planodiscagem.PlanoDiscagemManagerBean;
import br.edu.ifsc.fsbilling.business.tarifavenda.TarifaVendaManagerBean;
import br.edu.ifsc.fsbilling.entity.Plano;
import br.edu.ifsc.fsbilling.entity.PlanoDiscagem;
import br.edu.ifsc.fsbilling.entity.TarifaVenda;
import br.edu.ifsc.fsbilling.utils.Redirect;
import br.edu.ifsc.fsbilling.view.AbstractBean;
import br.edu.ifsc.fsbilling.view.Path;

import java.io.Serializable;
import java.util.List;

@Named(value = "planoDiscagemBean")
@ViewScoped
public class PlanoDiscagemBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private PlanoDiscagemManagerBean planoDiscagemManager;

	@EJB
	private PlanoManagerBean planoManager;
	
	@EJB
	private TarifaVendaManagerBean tarifaVendaManager;
	
	private LazyPlanoDiscagemDataModel planosDiscagemDataModel;
	private PlanoDiscagem planoDiscagem;
	private List<Plano> planos;
	private List<TarifaVenda> tarifas;
	
	@PostConstruct
	public void postConstruct() {
		if (planoDiscagem == null) {
			planoDiscagem = new PlanoDiscagem();
		}
		
		setPlanos(planoManager.listAll());
		setTarifas(tarifaVendaManager.listAll());
	}

	@Override
	public boolean preEdit() {
		if (planoDiscagem.getId() == null) {
			return false;
		} else {
			planoDiscagem = planoDiscagemManager.findById(planoDiscagem.getId(), PlanoDiscagem.class);
			return true;
		}

	}

	public void viewInit() {
		planosDiscagemDataModel = new LazyPlanoDiscagemDataModel(planoDiscagemManager);
	}

	public void delete() {
		planoDiscagem = planoDiscagemManager.findById(planoDiscagem.getId(), PlanoDiscagem.class);
		planoDiscagemManager.delete(planoDiscagem);
	}

	public void save() {
		planoDiscagemManager.update(planoDiscagem);
		Redirect.toUrl(Path.PLANO_DISCAGEM_SEARCH).go();
	}

	public LazyPlanoDiscagemDataModel getPlanosDiscagemDataModel() {
		return planosDiscagemDataModel;
	}

	public void setPlanosDiscagemDataModel(LazyPlanoDiscagemDataModel planosDiscagemDataModel) {
		this.planosDiscagemDataModel = planosDiscagemDataModel;
	}

	public PlanoDiscagem getPlanoDiscagem() {
		return planoDiscagem;
	}

	public void setPlanoDiscagem(PlanoDiscagem planoDiscagem) {
		this.planoDiscagem = planoDiscagem;
	}

	public List<Plano> getPlanos() {
		return planos;
	}

	public void setPlanos(List<Plano> planos) {
		this.planos = planos;
	}

	public List<TarifaVenda> getTarifas() {
		return tarifas;
	}

	public void setTarifas(List<TarifaVenda> tarifas) {
		this.tarifas = tarifas;
	}

}
