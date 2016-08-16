package br.edu.ifsc.fsbilling.view.tarifacompra;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.edu.ifsc.fsbilling.business.tarifacompra.TarifaCompraManagerBean;
import br.edu.ifsc.fsbilling.entity.TarifaCompra;
import br.edu.ifsc.fsbilling.utils.Redirect;
import br.edu.ifsc.fsbilling.view.AbstractBean;
import br.edu.ifsc.fsbilling.view.Path;

import java.io.Serializable;

@Named(value = "tarifaCompraBean")
@ViewScoped
public class TarifaCompraBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private LazyTarifaCompraDataModel tarifaDataModel;
	private TarifaCompra tarifa;

	@EJB
	private TarifaCompraManagerBean tarifaManager;

	@PostConstruct
	public void postConstruct() {
		if (tarifa == null) {
			tarifa = new TarifaCompra();
		}
	}

	@Override
	public boolean preEdit() {
		if (tarifa.getId() == null) {
			return false;
		} else {
			tarifa = tarifaManager.findById(tarifa.getId(), TarifaCompra.class);
			return true;
		}

	}

	public void viewInit() {
		tarifaDataModel = new LazyTarifaCompraDataModel(tarifaManager);
	}

	public void delete() {
		tarifa = tarifaManager.findById(tarifa.getId(), TarifaCompra.class);
		tarifaManager.delete(tarifa);
	}

	public void save() {
		tarifaManager.update(tarifa);
		Redirect.toUrl(Path.TARIFA_COMPRA_SEARCH).go();
	}

	public LazyTarifaCompraDataModel getTarifaDataModel() {
		return tarifaDataModel;
	}

	public void setTarifaDataModel(LazyTarifaCompraDataModel tarifaDataModel) {
		this.tarifaDataModel = tarifaDataModel;
	}

	public TarifaCompra getTarifa() {
		return tarifa;
	}

	public void setTarifa(TarifaCompra tarifa) {
		this.tarifa = tarifa;
	}

}
