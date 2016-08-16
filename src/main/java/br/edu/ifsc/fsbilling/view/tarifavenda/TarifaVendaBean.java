package br.edu.ifsc.fsbilling.view.tarifavenda;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.edu.ifsc.fsbilling.business.tarifacompra.TarifaCompraManagerBean;
import br.edu.ifsc.fsbilling.business.tarifavenda.TarifaVendaManagerBean;
import br.edu.ifsc.fsbilling.entity.TarifaCompra;
import br.edu.ifsc.fsbilling.entity.TarifaVenda;
import br.edu.ifsc.fsbilling.utils.Redirect;
import br.edu.ifsc.fsbilling.view.AbstractBean;
import br.edu.ifsc.fsbilling.view.Path;

import java.io.Serializable;
import java.util.List;

@Named(value = "tarifaVendaBean")
@ViewScoped
public class TarifaVendaBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private LazyTarifaVendaDataModel tarifaDataModel;
	private TarifaVenda tarifa;
	private List<TarifaCompra> tarifasCompra;
	
	@EJB
	private TarifaVendaManagerBean tarifaManager;

	@EJB
	private TarifaCompraManagerBean tarifaCompraManager;
	
	@PostConstruct
	public void postConstruct() {
		if (tarifa == null) {
			tarifa = new TarifaVenda();
		}
		
		setTarifasCompra(tarifaCompraManager.listAll());
	}

	@Override
	public boolean preEdit() {
		if (tarifa.getId() == null) {
			return false;
		} else {
			tarifa = tarifaManager.findById(tarifa.getId(), TarifaVenda.class);
			return true;
		}

	}

	public void viewInit() {
		tarifaDataModel = new LazyTarifaVendaDataModel(tarifaManager);
	}

	public void delete() {
		tarifa = tarifaManager.findById(tarifa.getId(), TarifaVenda.class);
		tarifaManager.delete(tarifa);
	}

	public void save() {
		tarifaManager.update(tarifa);
		Redirect.toUrl(Path.TARIFA_VENDA_SEARCH).go();
	}

	public LazyTarifaVendaDataModel getTarifaDataModel() {
		return tarifaDataModel;
	}

	public void setTarifaDataModel(LazyTarifaVendaDataModel tarifaDataModel) {
		this.tarifaDataModel = tarifaDataModel;
	}

	public TarifaVenda getTarifa() {
		return tarifa;
	}

	public void setTarifa(TarifaVenda tarifa) {
		this.tarifa = tarifa;
	}
	
	public List<TarifaCompra> getTarifasCompra() {
		return tarifasCompra;
	}

	public void setTarifasCompra(List<TarifaCompra> tarifasCompra) {
		this.tarifasCompra = tarifasCompra;
	}


}
